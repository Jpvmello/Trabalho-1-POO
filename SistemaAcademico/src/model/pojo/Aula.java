package model.pojo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Aula implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    private String diaDaSemana;
    private String local;
    private String hora;
    
    public Aula(){}
    
    public Aula (String id, String diaDaSemana, String hora, String local) {
        this.id = id;
        this.diaDaSemana = diaDaSemana;
        this.hora = hora;
        this.local = local;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDiaDaSemana() {
        return diaDaSemana;
    }

    public void setDiaDaSemana(String diaDaSemana) {
        this.diaDaSemana = diaDaSemana;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
        
    @Override
    public String toString () {
        return ("ID: " + this.id + "\nDia da semana: " + this.diaDaSemana +
                "\nHora: " + this.hora + "\nLocal: " + this.local + "\n");
    }
    
}
