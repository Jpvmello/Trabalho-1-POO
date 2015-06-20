package model.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;


@Entity
public class Professor implements Serializable, Comparable<Professor> {
    private static final long serialVersionUID = 1L;
    @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cpf;
    private String nome;
    private String departamento;
    @ManyToMany
//    @JoinTable(name="DisciplinaProfessor", inverseJoinColumns={@JoinColumn(name="nomeDisciplina")},
//            joinColumns={@JoinColumn(name="cpfProfessor")})
    private List<Disciplina> disciplina = new ArrayList();
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
