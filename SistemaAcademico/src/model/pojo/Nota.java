package model.pojo;

import java.io.Serializable;
import java.util.Comparator;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Nota implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    private Double nota;
    @ManyToOne
    //@JoinColumn(name="cpfAluno")
    private Aluno aluno;
    @ManyToOne
    //@JoinColumn(name="idAtividade")
    private Atividade atividade;
 
    public Nota() {}
    
    public Nota (String id, Double nota, Aluno aluno, Atividade atividade){
        this.id = id;
        this.nota = nota;
        this.aluno = aluno;
        this.atividade = atividade;
    }

    public String getId() {
        return id;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Atividade getAtividade() {
        return atividade;
    }

    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
    }
    
    @Override
    public String toString () {
        return ("ID: " + this.id + "\nNotas: " + this.nota + "\nAluno: " 
                + this.aluno.getNome() + "\nAtividade: " + atividade.getNome() 
                + "("+atividade.getTipo()+")" + "\n");
    }    
}
