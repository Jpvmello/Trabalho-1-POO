package model.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Turma implements Serializable, Comparable<Turma> {
    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    private Integer ano;
    private Integer periodo;
    private Integer numeroDeVagas;
    @ManyToOne
    @JoinColumn(name="nomeDisciplina")
    private Disciplina disciplina;
    @OneToOne
    @JoinColumn(name="cpfProfessor")
    private Professor professor;
    @ManyToMany
    @JoinTable(name="AulaTurma", inverseJoinColumns={@JoinColumn(name="IdAula")},
            joinColumns={@JoinColumn(name="idTurma")})   
//(mappedBy="turma",targetEntity = Aula.class, fetch = FetchType.LAZY)
    private List<Aula> aula = new ArrayList<>();
    @ManyToMany
    @JoinTable(name="AlunoTurma", inverseJoinColumns={@JoinColumn(name="cpfAluno")},
            joinColumns={@JoinColumn(name="idTurma")})
    private List<Aluno> aluno = new ArrayList<>();
    @OneToMany(mappedBy="turma")
    private List<Atividade> atividade = new ArrayList<>();

    public Turma() {}
    
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
        if(aluno != null)
            this.aluno = aluno;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Integer getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Integer periodo) {
        this.periodo = periodo;
    }

    public Integer getNumeroDeVagas() {
        return numeroDeVagas;
    }

    public void setNumeroDeVagas(Integer numeroDeVagas) {
        this.numeroDeVagas = numeroDeVagas;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Aula> getAula() {
        return aula;
    }

    public void setAula(List<Aula> aula) {
        this.aula = aula;
    }

    public List<Aluno> getAluno() {
        return aluno;
    }

    public void setAluno(List<Aluno> aluno) {
        this.aluno = aluno;
    }

    public List<Atividade> getAtividade() {
        return atividade;
    }

    public void setAtividade(List<Atividade> atividade) {
        this.atividade = atividade;
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
