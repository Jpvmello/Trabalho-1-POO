package model.pojo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Disciplina implements Comparable<Disciplina>{
    
    private String nome;
    private String ementa;
    private Integer cargaHoraria;
    private List<Professor> professor = new ArrayList<>();
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
