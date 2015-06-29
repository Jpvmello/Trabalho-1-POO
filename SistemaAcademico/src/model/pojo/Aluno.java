package model.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;     
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Aluno implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String cpf;
    private String nome;
    @ManyToMany
    private List<Turma> turma = new ArrayList<>();
    @OneToMany
    private List<Falta> falta = new ArrayList<>();
    @OneToMany
    private List<Nota> nota = new ArrayList<>();

    public Aluno() {}
           
    public Aluno (String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }    

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Turma> getTurma() {
        return turma;
    }

    public void setTurma(List<Turma> turma) {
        this.turma = turma;
    }

    public List<Falta> getFalta() {
        return falta;
    }

    public void setFalta(List<Falta> falta) {
        this.falta = falta;
    }

    public List<Nota> getNota() {
        return nota;
    }

    public void setNota(List<Nota> nota) {
        this.nota = nota;
    }
    
    public Boolean adicionarFalta(Falta falta){
        if(!this.getFalta().contains(falta))
            return this.getFalta().add(falta);
        return false;
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
    public String toString () {
        return ("Nome: " + this.nome + "\nCPF: " + this.cpf + "\n");
    }
}
