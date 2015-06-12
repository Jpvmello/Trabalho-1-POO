package model.pojo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Atividade implements Comparable<Atividade>, Comparator<Nota> {
    private String id;
    private String nome;
    private String tipo;
    private String data;
    private Double valor;
    private Boolean notasLancadas;
    private Turma turma;
    private List<Nota> nota = new ArrayList<>();
  
    public Atividade(String id, String nome, String tipo, String data, Double valor, Turma turma){
        this.id = id;
        this.data = data;
        this.nome = nome;
        this.tipo = tipo;
        this.valor = valor;
        this.notasLancadas = false;
        this.turma = turma;
    }
    
    public Atividade () {
    }
    
    public String getId () {
        return id;
    }
    
    public void setNotasLancadas(Boolean notasLancadas){
        this.notasLancadas = notasLancadas;
    }
    
    public void setTurma(Turma turma){
        this.turma = turma;
    }
        
    public String getNome(){
        return nome;
    }
    
    public Double getValor(){
        return valor;
    }
    
    public String getData(){
        return data;
    }
    
    public String getTipo(){
        return tipo;
    }
    
    public Turma getTurma(){
        return turma;
    }
    
    public List<Nota> getNota(){
        return nota;
    }
    
    public Boolean notasLancadas(){
        return notasLancadas;
    }   
       
    public Boolean adicionarNota(Nota nota){
        if (this.notasLancadas())
            return false;
        for (Nota notaConsultada: this.getNota()) {
            if (notaConsultada.getAluno().equals(nota.getAluno()))
                return false;
        }
        return this.getNota().add(nota);
    }
    
    /*public Boolean adicionarNotaArquivo(Nota nota){
        for (Nota notaConsultada: this.getNota()) {
            if (notaConsultada.getAluno().equals(nota.getAluno()))
                return false;
        }
        return this.getNota().add(nota);
    }*/
    
    public Nota retornaNota (Aluno aluno) {
        return this.getNota().get(this.getNota().indexOf(nota));
    }
    
    @Override
    public int compareTo (Atividade atividade) {
        return this.id.compareTo(atividade.id);
    }
    
    @Override
    public int compare (Nota nota1, Nota nota2) {
        return nota1.getAtividade().compareTo(nota2.getAtividade());
    }
    
    @Override
    public String toString () {
        return ("ID: " + this.id + "\nNome: " + this.nome +
                "\nTipo: " + this.tipo + "\nValor: " + this.valor +
                "\nDisciplina: " + this.turma.getDisciplina().getNome() + "\n");
    }
}