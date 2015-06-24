package model.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import model.pojo.Atividade;
import model.pojo.Nota;
import model.pojo.Turma;

public class AtividadeDaoImpl implements Dao<Atividade> {
    
    private static List<Atividade> listaAtividade = new ArrayList<>();
    private static AtividadeDaoImpl instancia = null;
    
    public static AtividadeDaoImpl getInstancia(){
        if(AtividadeDaoImpl.instancia == null)
            instancia = new AtividadeDaoImpl();
        return instancia;
    }
    
    @Override
    public Boolean salvar (EntityManager em, Atividade atividade) throws Exception {
        if (atividade.getNota() == null) {
            atividade.setNota(new ArrayList<Nota>());
        }
        try {
            em.getTransaction().begin();
            Turma turma = atividade.getTurma();
            if (turma != null) {
                turma = em.getReference(turma.getClass(), turma.getId());
                atividade.setTurma(turma);
            }
            List<Nota> attachedNota = new ArrayList<Nota>();
            for (Nota notaNotaToAttach : atividade.getNota()) {
                notaNotaToAttach = em.getReference(notaNotaToAttach.getClass(), notaNotaToAttach.getId());
                attachedNota.add(notaNotaToAttach);
            }
            atividade.setNota(attachedNota);
            em.persist(atividade);
            if (turma != null) {
                turma.getAtividade().add(atividade);
                turma = em.merge(turma);
            }
            for (Nota notaNota : atividade.getNota()) {
                Atividade oldAtividadeOfNotaNota = notaNota.getAtividade();
                notaNota.setAtividade(atividade);
                notaNota = em.merge(notaNota);
                if (oldAtividadeOfNotaNota != null) {
                    oldAtividadeOfNotaNota.getNota().remove(notaNota);
                    oldAtividadeOfNotaNota = em.merge(oldAtividadeOfNotaNota);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (obter(em, atividade.getId()) != null) {
                return false;
            }
            throw ex;
        }
        return true;
    }
    
    @Override
    public Atividade obter(EntityManager em, String id) {
        return em.find(Atividade.class, id);
    }

    @Override
    public List<Atividade> obterTodos (EntityManager em) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Atividade.class));
        Query q = em.createQuery(cq);
        return q.getResultList();
    }
    
    @Override  
    public Boolean atualizar (EntityManager em, Atividade atividade) throws Exception {
        try {
            em.getTransaction().begin();
            Atividade persistentAtividade = em.find(Atividade.class, atividade.getId());
            Turma turmaOld = persistentAtividade.getTurma();
            Turma turmaNew = atividade.getTurma();
            List<Nota> notaOld = persistentAtividade.getNota();
            List<Nota> notaNew = atividade.getNota();
            if (turmaNew != null) {
                turmaNew = em.getReference(turmaNew.getClass(), turmaNew.getId());
                atividade.setTurma(turmaNew);
            }
            List<Nota> attachedNotaNew = new ArrayList<Nota>();
            for (Nota notaNewNotaToAttach : notaNew) {
                notaNewNotaToAttach = em.getReference(notaNewNotaToAttach.getClass(), notaNewNotaToAttach.getId());
                attachedNotaNew.add(notaNewNotaToAttach);
            }
            notaNew = attachedNotaNew;
            atividade.setNota(notaNew);
            atividade = em.merge(atividade);
            if (turmaOld != null && !turmaOld.equals(turmaNew)) {
                turmaOld.getAtividade().remove(atividade);
                turmaOld = em.merge(turmaOld);
            }
            if (turmaNew != null && !turmaNew.equals(turmaOld)) {
                turmaNew.getAtividade().add(atividade);
                turmaNew = em.merge(turmaNew);
            }
            for (Nota notaOldNota : notaOld) {
                if (!notaNew.contains(notaOldNota)) {
                    notaOldNota.setAtividade(null);
                    notaOldNota = em.merge(notaOldNota);
                }
            }
            for (Nota notaNewNota : notaNew) {
                if (!notaOld.contains(notaNewNota)) {
                    Atividade oldAtividadeOfNotaNewNota = notaNewNota.getAtividade();
                    notaNewNota.setAtividade(atividade);
                    notaNewNota = em.merge(notaNewNota);
                    if (oldAtividadeOfNotaNewNota != null && !oldAtividadeOfNotaNewNota.equals(atividade)) {
                        oldAtividadeOfNotaNewNota.getNota().remove(notaNewNota);
                        oldAtividadeOfNotaNewNota = em.merge(oldAtividadeOfNotaNewNota);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = atividade.getId();
                if (obter(em, id) == null) {
                    return false;
                }
            }
            throw ex;
        }
        return true;
    }
}