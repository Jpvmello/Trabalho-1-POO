package model.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;     
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
public class Aluno implements Serializable,Comparable<Aluno> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cpf;
    private String nome;
    @ManyToMany
//    @JoinTable(name="AlunoTurma", inverseJoinColumns={@JoinColumn(name="idTurma")},
//            joinColumns={@JoinColumn(name="cpfAluno")})
    private List<Turma> turma = new ArrayList<>();
    @OneToMany
    @JoinColumn(name = "cpfAluno")
    private List<Falta> falta = new ArrayList<>();
    @OneToMany (mappedBy="aluno")
    private List<Nota> nota = new ArrayList<>();

    public Aluno() {
     
    }
    
           
    public Aluno (String nome, String cpf) {
        this();
        this.nome = nome;
        this.cpf = cpf;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
