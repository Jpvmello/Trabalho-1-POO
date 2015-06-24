package model.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import model.pojo.Aluno;
import model.pojo.Atividade;
import model.pojo.Nota;

public class NotaDaoImpl implements Dao<Nota> {
    private static List<Nota> listaNota = new ArrayList<>();
    private static NotaDaoImpl instancia = null;
    
    public static NotaDaoImpl getInstancia(){
        if(NotaDaoImpl.instancia == null)
            instancia = new NotaDaoImpl();
        return instancia;
    }
    
    @Override
    public Boolean salvar (EntityManager em, Nota nota) {
        em.getTransaction().begin();
        Aluno aluno = nota.getAluno();
        if (aluno != null) {
            aluno = em.getReference(aluno.getClass(), aluno.getCpf());
            nota.setAluno(aluno);
        }
        Atividade atividade = nota.getAtividade();
        if (atividade != null) {
            atividade = em.getReference(atividade.getClass(), atividade.getId());
            nota.setAtividade(atividade);
        }
        em.persist(nota);
        if (aluno != null) {
            aluno.getNota().add(nota);
            aluno = em.merge(aluno);
        }
        if (atividade != null) {
            atividade.getNota().add(nota);
            atividade = em.merge(atividade);
        }
        em.getTransaction().commit();
        return true;
    }
    
    @Override
    public Nota obter(EntityManager em, String id) {
        return em.find(Nota.class, id);
    }

    @Override
    public List<Nota> obterTodos (EntityManager em) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Nota.class));
        Query q = em.createQuery(cq);
        return q.getResultList();
    }
    
    @Override
    public Boolean atualizar (EntityManager em, Nota nota) throws Exception {
        try {
            em.getTransaction().begin();
            Nota persistentNota = em.find(Nota.class, nota.getId());
            Aluno alunoOld = persistentNota.getAluno();
            Aluno alunoNew = nota.getAluno();
            Atividade atividadeOld = persistentNota.getAtividade();
            Atividade atividadeNew = nota.getAtividade();
            if (alunoNew != null) {
                alunoNew = em.getReference(alunoNew.getClass(), alunoNew.getCpf());
                nota.setAluno(alunoNew);
            }
            if (atividadeNew != null) {
                atividadeNew = em.getReference(atividadeNew.getClass(), atividadeNew.getId());
                nota.setAtividade(atividadeNew);
            }
            nota = em.merge(nota);
            if (alunoOld != null && !alunoOld.equals(alunoNew)) {
                alunoOld.getNota().remove(nota);
                alunoOld = em.merge(alunoOld);
            }
            if (alunoNew != null && !alunoNew.equals(alunoOld)) {
                alunoNew.getNota().add(nota);
                alunoNew = em.merge(alunoNew);
            }
            if (atividadeOld != null && !atividadeOld.equals(atividadeNew)) {
                atividadeOld.getNota().remove(nota);
                atividadeOld = em.merge(atividadeOld);
            }
            if (atividadeNew != null && !atividadeNew.equals(atividadeOld)) {
                atividadeNew.getNota().add(nota);
                atividadeNew = em.merge(atividadeNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = nota.getId();
                if (obter(em, id) == null) {
                    return false;
                }
            }
            throw ex;
        }
        return true;
    }
}