package model.dao;

import java.util.Collections;
import java.util.List;
import model.pojo.Falta;
/*import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;*/
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.pojo.Atividade;

public class FaltaDaoImpl implements Dao<Falta> {    
    private static List<Falta> listaFalta = new ArrayList<>();
    private static FaltaDaoImpl instancia = null;
    
    public static FaltaDaoImpl getInstancia(){
        if(FaltaDaoImpl.instancia == null)
            instancia = new FaltaDaoImpl();
        return instancia;
    }
    
    @Override
    public Boolean inserir (Falta falta) {
        if (this.indice(falta.getId()) <= -1) {
            listaFalta.add(falta);
            Collections.sort(listaFalta);
            return true;
        }
        return false;
    }
    
    @Override
    public int indice (String id) {
        return Collections.binarySearch(listaFalta, new Falta (id, null, null));
    }
    
    @Override
    public Falta obter (String id) {
        if (this.indice(id) >= 0)
            return listaFalta.get(this.indice(id));
        return null;
    }
    
    @Override
    public List<Falta> obterTodos () {
        return listaFalta;
    }
    @Override
      public void persist(EntityManager em, Falta object) {
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
 
    
    /*@Override
    public void salvar () throws IOException{
        File file = new File("Falta.txt");
        if(!file.exists())
            file.createNewFile();
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        for(Falta falta: this.listaFalta){
            bw.write(falta.getId());
            bw.newLine();
            bw.write(falta.getFalta().toString());
            bw.newLine();
            bw.write(falta.getTurma().getId());
            bw.newLine();
        }
        bw.close();
        fw.close();
    }
    
    @Override
    public void carregar () throws IOException{
        File file = new File ("Falta.txt");
        FileReader fr = new FileReader (file);
        BufferedReader br = new BufferedReader (fr);
        while (br.ready()){
            String id = br.readLine();
            Integer numFalta = Integer.parseInt(br.readLine());
            String turma = br.readLine();
            Falta falta = new Falta(id, numFalta, null);
            this.inserir(falta);
            if (TurmaDaoImpl.getInstancia().obter(turma) != null)
                falta.setTurma(TurmaDaoImpl.getInstancia().obter(turma));
            }
        br.close();
        fr.close();
    }*/
}