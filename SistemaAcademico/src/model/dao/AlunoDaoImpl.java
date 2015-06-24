package model.dao;

import java.util.List;
import model.pojo.Aluno;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import model.pojo.Nota;
import model.pojo.Turma;

public class AlunoDaoImpl implements Dao<Aluno> {
    
    private static List<Aluno> listaAluno = new ArrayList<>();
    private static AlunoDaoImpl instancia = null;
    
    public static AlunoDaoImpl getInstancia(){
        if(AlunoDaoImpl.instancia == null)
            instancia = new AlunoDaoImpl();
        return instancia;
    }
    
    @Override
    public Boolean salvar (EntityManager em, Aluno aluno) throws Exception {
        if (aluno.getTurma() == null) {
            aluno.setTurma(new ArrayList<Turma>());
        }
        if (aluno.getNota() == null) {
            aluno.setNota(new ArrayList<Nota>());
        }
        try {
            em.getTransaction().begin();
            List<Turma> attachedTurma = new ArrayList<Turma>();
            for (Turma turmaTurmaToAttach : aluno.getTurma()) {
                turmaTurmaToAttach = em.getReference(turmaTurmaToAttach.getClass(), turmaTurmaToAttach.getId());
                attachedTurma.add(turmaTurmaToAttach);
            }
            aluno.setTurma(attachedTurma);
            List<Nota> attachedNota = new ArrayList<Nota>();
            for (Nota notaNotaToAttach : aluno.getNota()) {
                notaNotaToAttach = em.getReference(notaNotaToAttach.getClass(), notaNotaToAttach.getId());
                attachedNota.add(notaNotaToAttach);
            }
            aluno.setNota(attachedNota);
            em.persist(aluno);
            for (Turma turmaTurma : aluno.getTurma()) {
                turmaTurma.getAluno().add(aluno);
                turmaTurma = em.merge(turmaTurma);
            }
            for (Nota notaNota : aluno.getNota()) {
                Aluno oldAlunoOfNotaNota = notaNota.getAluno();
                notaNota.setAluno(aluno);
                notaNota = em.merge(notaNota);
                if (oldAlunoOfNotaNota != null) {
                    oldAlunoOfNotaNota.getNota().remove(notaNota);
                    oldAlunoOfNotaNota = em.merge(oldAlunoOfNotaNota);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (obter(em, aluno.getCpf()) != null) {
                return false;
            }
            throw ex;
        }
        return true;
    }
    
    @Override
    public Aluno obter(EntityManager em, String cpf) {
        return em.find(Aluno.class, cpf);
    }

    @Override
    public List<Aluno> obterTodos (EntityManager em) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Aluno.class));
        Query q = em.createQuery(cq);
        return q.getResultList();
    }
    
    @Override
    public Boolean atualizar (EntityManager em, Aluno aluno) throws Exception {
        try {
            em.getTransaction().begin();
            Aluno persistentAluno = em.find(Aluno.class, aluno.getCpf());
            List<Turma> turmaOld = persistentAluno.getTurma();
            List<Turma> turmaNew = aluno.getTurma();
            List<Nota> notaOld = persistentAluno.getNota();
            List<Nota> notaNew = aluno.getNota();
            List<Turma> attachedTurmaNew = new ArrayList<Turma>();
            for (Turma turmaNewTurmaToAttach : turmaNew) {
                turmaNewTurmaToAttach = em.getReference(turmaNewTurmaToAttach.getClass(), turmaNewTurmaToAttach.getId());
                attachedTurmaNew.add(turmaNewTurmaToAttach);
            }
            turmaNew = attachedTurmaNew;
            aluno.setTurma(turmaNew);
            List<Nota> attachedNotaNew = new ArrayList<Nota>();
            for (Nota notaNewNotaToAttach : notaNew) {
                notaNewNotaToAttach = em.getReference(notaNewNotaToAttach.getClass(), notaNewNotaToAttach.getId());
                attachedNotaNew.add(notaNewNotaToAttach);
            }
            notaNew = attachedNotaNew;
            aluno.setNota(notaNew);
            aluno = em.merge(aluno);
            for (Turma turmaOldTurma : turmaOld) {
                if (!turmaNew.contains(turmaOldTurma)) {
                    turmaOldTurma.getAluno().remove(aluno);
                    turmaOldTurma = em.merge(turmaOldTurma);
                }
            }
            for (Turma turmaNewTurma : turmaNew) {
                if (!turmaOld.contains(turmaNewTurma)) {
                    turmaNewTurma.getAluno().add(aluno);
                    turmaNewTurma = em.merge(turmaNewTurma);
                }
            }
            for (Nota notaOldNota : notaOld) {
                if (!notaNew.contains(notaOldNota)) {
                    notaOldNota.setAluno(null);
                    notaOldNota = em.merge(notaOldNota);
                }
            }
            for (Nota notaNewNota : notaNew) {
                if (!notaOld.contains(notaNewNota)) {
                    Aluno oldAlunoOfNotaNewNota = notaNewNota.getAluno();
                    notaNewNota.setAluno(aluno);
                    notaNewNota = em.merge(notaNewNota);
                    if (oldAlunoOfNotaNewNota != null && !oldAlunoOfNotaNewNota.equals(aluno)) {
                        oldAlunoOfNotaNewNota.getNota().remove(notaNewNota);
                        oldAlunoOfNotaNewNota = em.merge(oldAlunoOfNotaNewNota);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String cpf = aluno.getCpf();
                if (obter(em, cpf) == null) {
                    return false;
                }
            }
            throw ex;
        }
        return true;
    }
}