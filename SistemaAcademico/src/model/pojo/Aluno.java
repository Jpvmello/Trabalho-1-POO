package model.pojo;

import java.util.ArrayList;
import java.util.List;     


public class Aluno implements Comparable<Aluno>{
    private String nome;
    private String cpf;
    private List<Turma> turma = new ArrayList<>();  
    private List<Falta> falta = new ArrayList<>();
    private List<Nota> nota = new ArrayList<>();

    public Aluno() {
    }

    public Aluno (String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }
    
    public String getNome() {
        return nome;
    }
    
    public String getCpf() {
        return cpf;
    }

    public List<Turma> getTurma() {
        return turma;
    }
    
    public Boolean adicionarFalta(Falta falta){
        if(!this.getFalta().contains(falta))
            return this.getFalta().add(falta);
        return false;
    }

    public List<Falta> getFalta() {
        return falta;
    }
    
    public List<Nota> getNota(){
        return nota;
    }
    
    public Boolean adicionarNota(Nota nota){
        if(!this.getNota().contains(nota))
            return this.getNota().add(nota);
        return false;
    }

    public Double notaFinal(Turma turma){
        Double somaNotas = -1.0;
        if (turma.todasAsNotasLancadas()) {
            somaNotas = 0.0;
            for(Nota notaConsultada: this.getNota())
                if(notaConsultada.getAtividade().getTurma().equals(turma))
                    somaNotas += notaConsultada.getNota();
        }
        return somaNotas;
    }
    
    @Override
    public int compareTo (Aluno aluno) {
        return this.cpf.compareTo(aluno.cpf);
    }
    
    @Override
    public String toString () {
        return ("Nome: " + this.nome + "\nCPF: " + this.cpf + "\n");
    }
}
