package model.pojo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Aula implements Comparable<Aula>, Serializable {
    @Id
    private String id;
    private String diaDaSemana;
    private String hora;
    private String local;

    public Aula() {
    }

    public Aula (String id, String diaDaSemana, String hora, String local) {
        this.id = id;
        this.diaDaSemana = diaDaSemana;
        this.hora = hora;
        this.local = local;
    }
    
    public String getId() {
        return id;
    }
    
    public String getDiaDaSemana() {
        return diaDaSemana;
    }
    
    public String getHora() {
        return hora;
    }
    
    public String getLocal() {
        return local;
    }
    
    @Override
    public int compareTo (Aula aula) {
        return this.id.compareTo(aula.id);
    }
    
    @Override
    public String toString () {
        return ("ID: " + this.id + "\nDia da semana: " + this.diaDaSemana +
                "\nHora: " + this.hora + "\nLocal: " + this.local + "\n");
    }
}
