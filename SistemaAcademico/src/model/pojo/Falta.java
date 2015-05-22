package model.pojo;

import java.util.Comparator;

/**
 *
 * @author Filipe
 */
public class Falta implements Comparable<Falta>, Comparator<Turma> {
    private String id;
    private Integer falta;
    private Turma turma;

    public Falta (String id, Integer falta, Turma turma){
        this.id = id;
        this.falta = falta;
        this.turma = turma;
    }
    
    public Falta () {
    }
    
    public String getId(){
        return id;
    }
    
    public Integer getFalta(){
        return falta;
    }
    
    public void setFalta(Integer falta){
        this.falta = falta;
    }
    
    public Turma getTurma(){
        return turma;
    }
    
    public void setTurma(Object objeto){
        Turma turma = (Turma) objeto;
        this.turma = turma;
    }
    
    @Override
    public int compareTo (Falta falta) {
        return this.id.compareTo(falta.id);
    }
    
    @Override
    public int compare (Turma turma1, Turma turma2) {
        return turma1.compareTo(turma2);
    }
    
    @Override
    public String toString () {
        return ("ID: " + this.id + "\nFaltas: " + this.falta + "\nDisciplina: " 
                + this.turma.getDisciplina().getNome() + "\n");
    }    
}