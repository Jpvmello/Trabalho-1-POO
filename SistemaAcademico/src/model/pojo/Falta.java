package model.pojo;

import java.io.Serializable;
import java.util.Comparator;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Falta implements Serializable, Comparable<Falta>, Comparator<Falta> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String id1;
    private Integer falta;
    @OneToOne
    @JoinColumn(name="idTurma")
    private Turma turma;

    public Falta (String id1, Integer falta, Turma turma){
        this.id1 = id1;
        this.falta = falta;
        this.turma = turma;
    }
    
    public Falta () {
    }
    
    public String getId(){
        return id1;
    }
    
    public Integer getFalta(){
        return falta;
    }
    
    public Turma getTurma(){
        return turma;
    }
    
    public void setTurma(Turma turma){
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