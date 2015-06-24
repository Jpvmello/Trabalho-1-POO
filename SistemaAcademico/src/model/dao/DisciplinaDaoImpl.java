package model.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import model.pojo.Disciplina;
import model.pojo.Professor;
import model.pojo.Turma;

public class DisciplinaDaoImpl implements Dao<Disciplina> {
    private static List<Disciplina> listaDisciplina = new ArrayList<>();
    private static DisciplinaDaoImpl instancia = null;
    
    public static DisciplinaDaoImpl getInstancia(){
        if(DisciplinaDaoImpl.instancia == null)
            instancia = new DisciplinaDaoImpl();
        return instancia;
    }
    
    @Override
    public Boolean salvar (EntityManager em, Disciplina disciplina) throws Exception {
        if (disciplina.getProfessor() == null) {
            disciplina.setProfessor(new ArrayList<Professor>());
        }
        if (disciplina.getTurma() == null) {
            disciplina.setTurma(new ArrayList<Turma>());
        }
        try {
            em.getTransaction().begin();
            List<Professor> attachedProfessor = new ArrayList<Professor>();
            for (Professor professorProfessorToAttach : disciplina.getProfessor()) {
                professorProfessorToAttach = em.getReference(professorProfessorToAttach.getClass(), professorProfessorToAttach.getCpf());
                attachedProfessor.add(professorProfessorToAttach);
            }
            disciplina.setProfessor(attachedProfessor);
            List<Turma> attachedTurma = new ArrayList<Turma>();
            for (Turma turmaTurmaToAttach : disciplina.getTurma()) {
                turmaTurmaToAttach = em.getReference(turmaTurmaToAttach.getClass(), turmaTurmaToAttach.getId());
                attachedTurma.add(turmaTurmaToAttach);
            }
            disciplina.setTurma(attachedTurma);
            em.persist(disciplina);
            for (Professor professorProfessor : disciplina.getProfessor()) {
                professorProfessor.getDisciplina().add(disciplina);
                professorProfessor = em.merge(professorProfessor);
            }
            for (Turma turmaTurma : disciplina.getTurma()) {
                Disciplina oldDisciplinaOfTurmaTurma = turmaTurma.getDisciplina();
                turmaTurma.setDisciplina(disciplina);
                turmaTurma = em.merge(turmaTurma);
                if (oldDisciplinaOfTurmaTurma != null) {
                    oldDisciplinaOfTurmaTurma.getTurma().remove(turmaTurma);
                    oldDisciplinaOfTurmaTurma = em.merge(oldDisciplinaOfTurmaTurma);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (obter(em, disciplina.getNome()) != null) {
                return false;
            }
            throw ex;
        }
        return true;
    }
    
    @Override
    public Disciplina obter(EntityManager em, String nome) {
        return em.find(Disciplina.class, nome);
    }

    @Override
    public List<Disciplina> obterTodos (EntityManager em) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Disciplina.class));
        Query q = em.createQuery(cq);
        return q.getResultList();
    }
    
   @Override
   public Boolean atualizar (EntityManager em, Disciplina disciplina) throws Exception {
        try {
            em.getTransaction().begin();
            Disciplina persistentDisciplina = em.find(Disciplina.class, disciplina.getNome());
            List<Professor> professorOld = persistentDisciplina.getProfessor();
            List<Professor> professorNew = disciplina.getProfessor();
            List<Turma> turmaOld = persistentDisciplina.getTurma();
            List<Turma> turmaNew = disciplina.getTurma();
            List<Professor> attachedProfessorNew = new ArrayList<Professor>();
            for (Professor professorNewProfessorToAttach : professorNew) {
                professorNewProfessorToAttach = em.getReference(professorNewProfessorToAttach.getClass(), professorNewProfessorToAttach.getCpf());
                attachedProfessorNew.add(professorNewProfessorToAttach);
            }
            professorNew = attachedProfessorNew;
            disciplina.setProfessor(professorNew);
            List<Turma> attachedTurmaNew = new ArrayList<Turma>();
            for (Turma turmaNewTurmaToAttach : turmaNew) {
                turmaNewTurmaToAttach = em.getReference(turmaNewTurmaToAttach.getClass(), turmaNewTurmaToAttach.getId());
                attachedTurmaNew.add(turmaNewTurmaToAttach);
            }
            turmaNew = attachedTurmaNew;
            disciplina.setTurma(turmaNew);
            disciplina = em.merge(disciplina);
            for (Professor professorOldProfessor : professorOld) {
                if (!professorNew.contains(professorOldProfessor)) {
                    professorOldProfessor.getDisciplina().remove(disciplina);
                    professorOldProfessor = em.merge(professorOldProfessor);
                }
            }
            for (Professor professorNewProfessor : professorNew) {
                if (!professorOld.contains(professorNewProfessor)) {
                    professorNewProfessor.getDisciplina().add(disciplina);
                    professorNewProfessor = em.merge(professorNewProfessor);
                }
            }
            for (Turma turmaOldTurma : turmaOld) {
                if (!turmaNew.contains(turmaOldTurma)) {
                    turmaOldTurma.setDisciplina(null);
                    turmaOldTurma = em.merge(turmaOldTurma);
                }
            }
            for (Turma turmaNewTurma : turmaNew) {
                if (!turmaOld.contains(turmaNewTurma)) {
                    Disciplina oldDisciplinaOfTurmaNewTurma = turmaNewTurma.getDisciplina();
                    turmaNewTurma.setDisciplina(disciplina);
                    turmaNewTurma = em.merge(turmaNewTurma);
                    if (oldDisciplinaOfTurmaNewTurma != null && !oldDisciplinaOfTurmaNewTurma.equals(disciplina)) {
                        oldDisciplinaOfTurmaNewTurma.getTurma().remove(turmaNewTurma);
                        oldDisciplinaOfTurmaNewTurma = em.merge(oldDisciplinaOfTurmaNewTurma);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String nome = disciplina.getNome();
                if (obter(em, nome) == null) {
                    return false;
                }
            }
            throw ex;
        }
        return true;
    }
}