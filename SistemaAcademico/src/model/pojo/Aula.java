package model.pojo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
//@Table(name = "Aula")
public class Aula implements Comparable<Aula>, Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private String id;
    @Column (name = "dia da semana")
    private String diaDaSemana;
    @Column
    private String hora;
    @Column
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
