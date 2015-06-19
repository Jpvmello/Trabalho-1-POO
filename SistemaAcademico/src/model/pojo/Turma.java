package model.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Turma implements Comparable<Turma>, Serializable {
    @Id
    private String id;
    private Integer ano;
    private Integer periodo;
    private Integer numeroDeVagas;
    private Disciplina disciplina;
    private Professor professor;
    private List<Aula> aula = new ArrayList<>();
    private List<Aluno> aluno = new ArrayList<>();
    private List<Atividade> atividade = new ArrayList<>();

    public Turma() {
    }
    
    public Turma (String id, Integer ano, Integer periodo, Integer numeroDeVagas,
            Disciplina disciplina, Professor professor, List<Aula> aula, List<Aluno> aluno) {
        this.id = id;
        this.ano = ano;
        this.periodo = periodo;
        this.numeroDeVagas = numeroDeVagas;
        this.disciplina = disciplina;
        this.professor = professor;
        if(aula != null)
            this.aula = aula;
        if(aluno !=null)
            this.aluno = aluno;
    }
    
    public String getId () {
        return id;
    }
    
    public Integer getAno () {
        return ano;
    }
    
    public Integer getPeriodo () {
        return periodo;
    }
    
    public Integer getNumeroDeVagas () {
        return numeroDeVagas;
    }
    
    public Disciplina getDisciplina () {
        return disciplina;
    }
    
    public void setDisciplina (Disciplina disciplina) {
        this.disciplina = disciplina;
    }
    
    public Professor getProfessor () {
        return professor;
    }
    
    public void setProfessor (Professor professor) {
        this.professor = professor;
    }
    
    public List<Aula> getAula () {
        return aula;
    }
    
    public List<Aluno> getAluno () {
        return aluno;
    }
    
    public List<Atividade> getAtividade () {
        return atividade;
    }
    
    public Boolean adicionarAula (Aula aula) {
        if (!this.getAula().contains(aula))
            return this.getAula().add(aula);
        return false;
    }
    
    public Boolean faltasLancadas () {
        if (this.aluno.size() > 0) {
            Aluno ultimoMatriculado = this.aluno.get(this.aluno.size() - 1);
            Collections.sort(ultimoMatriculado.getFalta(), new Falta());
            return (Collections.binarySearch(ultimoMatriculado.getFalta(), new Falta(null, null, this), 
                    new Falta()) >= 0);
        }
        return false;
    }
    
    public Boolean todasAsNotasLancadas () {
        for (Atividade atividade: this.getAtividade()) {
            if (!atividade.notasLancadas())
                return false;
        }
        return true;
    }
    
    public Boolean adicionarAluno (Aluno aluno) {
        if (this.getAluno().size() < this.getNumeroDeVagas() ||
                this.getDisciplina().turmaQueContem(aluno) == null) {
            aluno.getTurma().add(this);
            return this.getAluno().add(aluno);
        }
        return false;
    }
    
    public Boolean adicionarAtividade (Atividade atividade) {
        if (!this.getAtividade().contains(atividade))
            return this.getAtividade().add(atividade);
        return false;
    }
    
    @Override
    public int compareTo (Turma turma) {
        return this.id.compareTo(turma.id);
    }
    
    @Override
    public String toString () {
        return ("ID: " + this.id + "\nAno: " + this.ano +
                "\nPeríodo: " + this.periodo + "\nNúmero de vagas: " + this.numeroDeVagas +
                "\nDisciplina: " + this.disciplina.getNome() +
                "\nProfessor: " + this.professor.getNome() + "\n");
    }
}
