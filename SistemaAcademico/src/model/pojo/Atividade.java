package model.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Atividade implements Serializable, Comparable<Atividade>, Comparator<Nota> {
    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    private String nome;
    private String tipo;
    private String data;
    private Double valor;
    private Boolean notasLancadas;
    @ManyToOne
    //@JoinColumn(name="idTurma")
    private Turma turma;
    @OneToMany//(mappedBy="atividade")
    private List<Nota> nota = new ArrayList<>();
    
    public Atividade () {}
    
    public Atividade(String id, String nome, String tipo, String data, Double valor, Turma turma){
        this.id = id;
        this.data = data;
        this.nome = nome;
        this.tipo = tipo;
        this.valor = valor;
        this.notasLancadas = false;
        this.turma = turma;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Boolean notasLancadas() {
        return notasLancadas;
    }

    public void setNotasLancadas(Boolean notasLancadas) {
        this.notasLancadas = notasLancadas;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public List<Nota> getNota() {
        return nota;
    }

    public void setNota(List<Nota> nota) {
        this.nota = nota;
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