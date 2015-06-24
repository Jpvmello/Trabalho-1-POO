package model.pojo;

import java.io.Serializable;
import java.util.Comparator;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Falta implements Serializable, Comparable<Falta>, Comparator<Falta> {
    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    private Integer falta;
    @OneToOne
    //@JoinColumn(name="idTurma")
    private Turma turma;

    public Falta (String id, Integer falta, Turma turma){
        this.id = id;
        this.falta = falta;
        this.turma = turma;
    }
    
    public Falta () {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getFalta() {
        return falta;
    }

    public void setFalta(Integer falta) {
        this.falta = falta;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }    
    
    @Override
    public int compareTo (Falta falta) {
        return this.id.compareTo(falta.id);
    }
    
    @Override
    public int compare (Falta falta1, Falta falta2) {
        return falta1.turma.compareTo(falta2.turma);
    }
    
    @Override
    public String toString () {
        return ("ID: " + this.id + "\nFaltas: " + this.falta + "\nDisciplina: " 
                + this.turma.getDisciplina().getNome() + "\n");
    }    
}