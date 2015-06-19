package model.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Professor implements Comparable<Professor>, Serializable{
    private String nome;
    @Id
    private String cpf;
    private String departamento;
    private List<Disciplina> disciplina = new ArrayList<>();

    public Professor() {
    }
    
    public Professor (String nome, String cpf, String departamento){
        this.nome = nome;
        this.cpf = cpf;
        this.departamento = departamento;
    }
    
    public String getNome (){
        return nome;
    }
    
    public String getCpf (){
        return cpf;
    }
    
    public String getDepartamento (){
        return departamento;
    }
    
    public List<Disciplina> getDisciplina (){
        return disciplina;
    }
    
    public Boolean adicionarDisciplina (Disciplina disciplina){
        if (!(this.getDisciplina().contains(disciplina))) {
            disciplina.getProfessor().add(this);
            return this.getDisciplina().add(disciplina);
        }
        return false;
    }
    
    @Override
    public int compareTo (Professor professor){
        return this.cpf.compareTo(professor.cpf);
    }
    
    @Override
    public String toString () {
        return ("Nome: " + this.nome + "\nCPF: " + this.cpf + "\nDepartamento: " + this.departamento + "\n");
    }
}
