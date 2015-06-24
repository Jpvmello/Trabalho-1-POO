package model.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import model.pojo.Disciplina;
import model.pojo.Professor;

public class ProfessorDaoImpl implements Dao<Professor> {
    private static List<Professor> listaProfessor = new ArrayList<>();
    private static ProfessorDaoImpl instancia = null;
    
    public static ProfessorDaoImpl getInstancia(){
        if(ProfessorDaoImpl.instancia == null)
            instancia = new ProfessorDaoImpl();
        return instancia;
    }
    
    @Override
    public Boolean salvar (EntityManager em, Professor professor) throws Exception {
        if (professor.getDisciplina() == null) {
            professor.setDisciplina(new ArrayList<Disciplina>());
        }
        try {
            em.getTransaction().begin();
            List<Disciplina> attachedDisciplina = new ArrayList<Disciplina>();
            for (Disciplina disciplinaDisciplinaToAttach : professor.getDisciplina()) {
                disciplinaDisciplinaToAttach = em.getReference(disciplinaDisciplinaToAttach.getClass(), disciplinaDisciplinaToAttach.getNome());
                attachedDisciplina.add(disciplinaDisciplinaToAttach);
            }
            professor.setDisciplina(attachedDisciplina);
            em.persist(professor);
            for (Disciplina disciplinaDisciplina : professor.getDisciplina()) {
                disciplinaDisciplina.getProfessor().add(professor);
                disciplinaDisciplina = em.merge(disciplinaDisciplina);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (obter(em, professor.getCpf()) != null) {
                return false;
            }
            throw ex;
        }
        return true;
    }
    
    @Override
    public Professor obter(EntityManager em, String cpf) {
        return em.find(Professor.class, cpf);
    }

    @Override
    public List<Professor> obterTodos (EntityManager em) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Professor.class));
        Query q = em.createQuery(cq);
        return q.getResultList();
    }
    
    @Override
    public Boolean atualizar (EntityManager em, Professor professor) throws Exception {
        try {
            em.getTransaction().begin();
            Professor persistentProfessor = em.find(Professor.class, professor.getCpf());
            List<Disciplina> disciplinaOld = persistentProfessor.getDisciplina();
            List<Disciplina> disciplinaNew = professor.getDisciplina();
            List<Disciplina> attachedDisciplinaNew = new ArrayList<Disciplina>();
            for (Disciplina disciplinaNewDisciplinaToAttach : disciplinaNew) {
                disciplinaNewDisciplinaToAttach = em.getReference(disciplinaNewDisciplinaToAttach.getClass(), disciplinaNewDisciplinaToAttach.getNome());
                attachedDisciplinaNew.add(disciplinaNewDisciplinaToAttach);
            }
            disciplinaNew = attachedDisciplinaNew;
            professor.setDisciplina(disciplinaNew);
            professor = em.merge(professor);
            for (Disciplina disciplinaOldDisciplina : disciplinaOld) {
                if (!disciplinaNew.contains(disciplinaOldDisciplina)) {
                    disciplinaOldDisciplina.getProfessor().remove(professor);
                    disciplinaOldDisciplina = em.merge(disciplinaOldDisciplina);
                }
            }
            for (Disciplina disciplinaNewDisciplina : disciplinaNew) {
                if (!disciplinaOld.contains(disciplinaNewDisciplina)) {
                    disciplinaNewDisciplina.getProfessor().add(professor);
                    disciplinaNewDisciplina = em.merge(disciplinaNewDisciplina);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String cpf = professor.getCpf();
                if (obter(em, cpf) == null) {
                    return false;
                }
            }
            throw ex;
        }
        return true;
    }
}