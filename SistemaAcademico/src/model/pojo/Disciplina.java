package model.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Disciplina implements Serializable,  Comparable<Disciplina> {
    private static final long serialVersionUID = 1L;
    @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String nome;
    private String ementa;
    private Integer cargaHoraria;
    @ManyToMany
//    @JoinTable(name="DisciplinaProfessor", inverseJoinColumns={@JoinColumn(name="cpfProfessor")},
//            joinColumns={@JoinColumn(name="nomeDisciplina")})
    private List<Professor> professor = new ArrayList<>();
    @OneToMany(mappedBy="disciplina")
    private List<Turma> turma = new ArrayList<>();
    public Disciplina() {
    }
    
    public Disciplina(String nome, String ementa, Integer cargaHoraria){
        this.nome = nome;
        this.ementa = ementa;
        this.cargaHoraria = cargaHoraria;
    }
    
    public String getNome(){
        return nome;
    }
    
    public String getEmenta(){
        return ementa;
    }
    
    public Integer getCargaHoraria(){
        return cargaHoraria;
    }
    
    public List<Professor> getProfessor(){
        return professor;
    }
    
    public List<Turma> getTurma(){
        return turma;
    }

    public Turma turmaQueContem (Aluno aluno) {
        for (Turma turma: this.getTurma()) {
            Collections.sort(turma.getAluno());
            if (Collections.binarySearch(turma.getAluno(), aluno) >= 0)
                return turma;
        }
        return null;
    }
    
    public Boolean adicionarTurma (Turma turma) {
        if (!this.getTurma().contains(turma))
            return this.getTurma().add(turma);
        return false;
    }
    
    @Override
    public int compareTo(Disciplina disciplina) {
        return this.nome.compareTo(disciplina.getNome());
    }
    
    @Override
    public String toString () {
        return ("Nome: " + this.nome + "\nEmenta: " + this.ementa + 
                "\nCarga Horária: " + this.cargaHoraria + "\n");
    }
}
