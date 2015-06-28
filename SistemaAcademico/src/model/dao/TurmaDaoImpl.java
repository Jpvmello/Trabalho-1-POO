package model.dao;

import java.util.List;
import model.pojo.Turma;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import model.pojo.Aluno;
import model.pojo.Atividade;
import model.pojo.Aula;
import model.pojo.Disciplina;

public class TurmaDaoImpl implements Dao<Turma> {
    private static List<Turma> listaTurma = new ArrayList<Turma>();
    private static TurmaDaoImpl instancia = null;
    
    public static TurmaDaoImpl getInstancia(){
        if(TurmaDaoImpl.instancia == null)
            instancia = new TurmaDaoImpl();
        return instancia;
    }
    
    @Override
    public Boolean salvar (EntityManager em, Turma turma) throws Exception {
        if (turma.getAluno() == null) {
            turma.setAluno(new ArrayList<Aluno>());
        }
        if (turma.getAtividade() == null) {
            turma.setAtividade(new ArrayList<Atividade>());
        }
//        if (turma.getAula() == null) {
//            turma.setAula(new ArrayList<Aula>());
//        }
        try {
            em.getTransaction().begin();
            Disciplina disciplina = turma.getDisciplina();
            if (disciplina != null) {
                disciplina = em.getReference(disciplina.getClass(), disciplina.getNome());
                turma.setDisciplina(disciplina);
            }
            List<Aluno> attachedAluno = new ArrayList<Aluno>();
            for (Aluno alunoAlunoToAttach : turma.getAluno()) {
                alunoAlunoToAttach = em.getReference(alunoAlunoToAttach.getClass(), alunoAlunoToAttach.getCpf());
                attachedAluno.add(alunoAlunoToAttach);
            }
            turma.setAluno(attachedAluno);
            List<Atividade> attachedAtividade = new ArrayList<Atividade>();
            for (Atividade atividadeAtividadeToAttach : turma.getAtividade()) {
                atividadeAtividadeToAttach = em.getReference(atividadeAtividadeToAttach.getClass(), atividadeAtividadeToAttach.getId());
                attachedAtividade.add(atividadeAtividadeToAttach);
            }
            turma.setAtividade(attachedAtividade);
//            List<Aula> attachedAula = new ArrayList<Aula>();
//            for (Aula aulaAulaToAttach : turma.getAula()) {
//                aulaAulaToAttach = em.getReference(aulaAulaToAttach.getClass(), aulaAulaToAttach.getId());
//                attachedAula.add(aulaAulaToAttach);
//            }
//            turma.setAula(attachedAula);
            em.persist(turma);
            if (disciplina != null) {
                disciplina.getTurma().add(turma);
                disciplina = em.merge(disciplina);
            }
            for (Aluno alunoAluno : turma.getAluno()) {
                alunoAluno.getTurma().add(turma);
                alunoAluno = em.merge(alunoAluno);
            }
            for (Atividade atividadeAtividade : turma.getAtividade()) {

        Turma oldTurmaOfAtividadeAtividade = atividadeAtividade.getTurma();
                atividadeAtividade.setTurma(turma);
                atividadeAtividade = em.merge(atividadeAtividade);
                if (oldTurmaOfAtividadeAtividade != null) {
                    oldTurmaOfAtividadeAtividade.getAtividade().remove(atividadeAtividade);
                    oldTurmaOfAtividadeAtividade = em.merge(oldTurmaOfAtividadeAtividade);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (obter(em, turma.getId()) != null) {
                return false;
            }
            throw ex;
        }
        return true;
    }
    
    @Override
    public Turma obter(EntityManager em, String id) {
        return em.find(Turma.class, id);
    }

    @Override
    public List<Turma> obterTodos (EntityManager em) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Turma.class));
        Query q = em.createQuery(cq);
        return q.getResultList();
    }
    
    @Override
    public Boolean atualizar (EntityManager em, Turma turma) throws Exception {
        try {
            em.getTransaction().begin();
            Turma persistentTurma = em.find(Turma.class, turma.getId());
            Disciplina disciplinaOld = persistentTurma.getDisciplina();
            Disciplina disciplinaNew = turma.getDisciplina();
            List<Aluno> alunoOld = persistentTurma.getAluno();
            List<Aluno> alunoNew = turma.getAluno();
            List<Atividade> atividadeOld = persistentTurma.getAtividade();
            List<Atividade> atividadeNew = turma.getAtividade();
            if (disciplinaNew != null) {
                disciplinaNew = em.getReference(disciplinaNew.getClass(), disciplinaNew.getNome());
                turma.setDisciplina(disciplinaNew);
            }
            List<Aluno> attachedAlunoNew = new ArrayList<Aluno>();
            for (Aluno alunoNewAlunoToAttach : alunoNew) {
                alunoNewAlunoToAttach = em.getReference(alunoNewAlunoToAttach.getClass(), alunoNewAlunoToAttach.getCpf());
                attachedAlunoNew.add(alunoNewAlunoToAttach);
            }
            alunoNew = attachedAlunoNew;
            turma.setAluno(alunoNew);
            List<Atividade> attachedAtividadeNew = new ArrayList<Atividade>();
            for (Atividade atividadeNewAtividadeToAttach : atividadeNew) {
                atividadeNewAtividadeToAttach = em.getReference(atividadeNewAtividadeToAttach.getClass(), atividadeNewAtividadeToAttach.getId());
                attachedAtividadeNew.add(atividadeNewAtividadeToAttach);
            }
            atividadeNew = attachedAtividadeNew;
            turma.setAtividade(atividadeNew);
            turma = em.merge(turma);
            if (disciplinaOld != null && !disciplinaOld.equals(disciplinaNew)) {
                disciplinaOld.getTurma().remove(turma);
                disciplinaOld = em.merge(disciplinaOld);
            }
            if (disciplinaNew != null && !disciplinaNew.equals(disciplinaOld)) {
                disciplinaNew.getTurma().add(turma);
                disciplinaNew = em.merge(disciplinaNew);
            }
            for (Aluno alunoOldAluno : alunoOld) {
                if (!alunoNew.contains(alunoOldAluno)) {
                    alunoOldAluno.getTurma().remove(turma);
                    alunoOldAluno = em.merge(alunoOldAluno);
                }
            }
            for (Aluno alunoNewAluno : alunoNew) {
                if (!alunoOld.contains(alunoNewAluno)) {
                    alunoNewAluno.getTurma().add(turma);
                    alunoNewAluno = em.merge(alunoNewAluno);
                }
            }
            for (Atividade atividadeOldAtividade : atividadeOld) {
                if (!atividadeNew.contains(atividadeOldAtividade)) {
                    atividadeOldAtividade.setTurma(null);
                    atividadeOldAtividade = em.merge(atividadeOldAtividade);
                }
            }
            for (Atividade atividadeNewAtividade : atividadeNew) {
                if (!atividadeOld.contains(atividadeNewAtividade)) {
                    Turma oldTurmaOfAtividadeNewAtividade = atividadeNewAtividade.getTurma();
                    atividadeNewAtividade.setTurma(turma);
                    atividadeNewAtividade = em.merge(atividadeNewAtividade);
                    if (oldTurmaOfAtividadeNewAtividade != null && !oldTurmaOfAtividadeNewAtividade.equals(turma)) {
                        oldTurmaOfAtividadeNewAtividade.getAtividade().remove(atividadeNewAtividade);
                        oldTurmaOfAtividadeNewAtividade = em.merge(oldTurmaOfAtividadeNewAtividade);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = turma.getId();
                if (obter(em, id) == null) {
                    return false;
                }
            }
            throw ex;
        }
        return true;
    }
}