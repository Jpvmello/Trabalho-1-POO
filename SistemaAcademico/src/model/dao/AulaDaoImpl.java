package model.dao;

/*import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;*/
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.pojo.Aula;

public class AulaDaoImpl implements Dao<Aula> {    
    private static List<Aula> listaAula = new ArrayList<Aula>();
    private static AulaDaoImpl instancia = null;
    
    public static AulaDaoImpl getInstancia(){
        if(AulaDaoImpl.instancia == null)
            instancia = new AulaDaoImpl();
        return instancia;
    }
    
    @Override
    public Boolean inserir (Aula aula) {
        if (this.indice(aula.getId()) <= -1) {
            listaAula.add(aula);
            Collections.sort(listaAula);
            return true;
        }
        return false;
    }

    @Override
    public int indice(String id) {
        return Collections.binarySearch(listaAula, new Aula (id, null, 
                null, null));
    }

    @Override
    public Aula obter(String id) {
        if (this.indice(id) >= 0)
            return listaAula.get(this.indice(id));
        return null;
    }

    @Override
    public List<Aula> obterTodos() {
        return listaAula;
    }
    
    public void persist(Aula object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SistemaAcademicoPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
    
    /*@Override
    public void salvar () throws IOException{
        File file = new File ("Aula.txt");
        if (!(file.exists()))
            file.createNewFile();
        FileWriter fw = new FileWriter (file);
        BufferedWriter bw = new BufferedWriter (fw);
        for (Aula aula: this.listaAula){
            bw.write (aula.getId());
            bw.newLine ();
            bw.write (aula.getDiaDaSemana());
            bw.newLine ();
            bw.write (aula.getHora());
            bw.newLine ();
            bw.write (aula.getLocal());
            bw.newLine ();
        }
        bw.close();
        fw.close();
    }
    
    @Override
    public void carregar () throws IOException{
        File file = new File ("Aula.txt");
        FileReader fr = new FileReader (file);
        BufferedReader br = new BufferedReader (fr);
        while (br.ready()){
            String id = br.readLine();
            String diaDaSemana = br.readLine();
            String hora = br.readLine();
            String local = br.readLine();
            this.inserir(new Aula(id, diaDaSemana, hora, local));
        }
        br.close();
        fr.close();
    }*/
}
