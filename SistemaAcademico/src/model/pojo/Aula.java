package model.pojo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Filipe
 */
@Entity
public class Aula implements Serializable, Comparable<Aula> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String id1;
    private String diaDaSemana;
    private String local;
    private String Hora;
    
    public Aula(){}
    
    public Aula (String id1, String diaDaSemana, String hora, String local) {
        this.id1 = id1;
        this.diaDaSemana = diaDaSemana;
        this.Hora = hora;
        this.local = local;
    }
    
    public String getId1() {
        return id1;
    }


    public String getDiaDaSemana() {
        return diaDaSemana;
    }

    public String getLocal() {
        return local;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String Hora) {
        this.Hora = Hora;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

       
    @Override
    public int compareTo (Aula aula) {
        return this.id1.compareTo(aula.id1);
    }
    
    @Override
    public String toString () {
        return ("ID: " + this.id + "\nDia da semana: " + this.diaDaSemana +
                "\nHora: " + this.Hora + "\nLocal: " + this.local + "\n");
    }
    
}
