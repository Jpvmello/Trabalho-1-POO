package model.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Disciplina implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    private String nome;
    private String ementa;
    private Integer cargaHoraria;
    @ManyToMany
    private List<Professor> professor = new ArrayList<>();
    @OneToMany
    private List<Turma> turma = new ArrayList<>();
    
    public Disciplina() {}
    
    public Disciplina(String nome, String ementa, Integer cargaHoraria){
        this.nome = nome;
        this.ementa = ementa;
        this.cargaHoraria = cargaHoraria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmenta() {
        return ementa;
    }

    public void setEmenta(String ementa) {
        this.ementa = ementa;
    }

    public Integer getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Integer cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public List<Professor> getProfessor() {
        return professor;
    }

    public void setProfessor(List<Professor> professor) {
        this.professor = professor;
    }

    public List<Turma> getTurma() {
        return turma;
    }

    public void setTurma(List<Turma> turma) {
        this.turma = turma;
    }

    public Turma turmaQueContem (Aluno aluno) {
        for (Turma turma: this.getTurma()) {
            for(Aluno alunoConsultado : turma.getAluno()){
                if (aluno.equals(alunoConsultado))
                    return turma;
            }
        }
        return null;
    }
    
    public Boolean adicionarTurma (Turma turma) {
        if (!this.getTurma().contains(turma))
            return this.getTurma().add(turma);
        return false;
    }
    
    @Override
    public String toString () {
        return ("Nome: " + this.nome + "\nEmenta: " + this.ementa + 
                "\nCarga Horária: " + this.cargaHoraria + "\n");
    }
}
