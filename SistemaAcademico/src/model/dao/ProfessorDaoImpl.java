package model.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.pojo.Atividade;
import model.pojo.Professor;
/*import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;*/

public class ProfessorDaoImpl implements Dao<Professor> {
    private static List<Professor> listaProfessor = new ArrayList<>();
    private static ProfessorDaoImpl instancia = null;
    
    public static ProfessorDaoImpl getInstancia(){
        if(ProfessorDaoImpl.instancia == null)
            instancia = new ProfessorDaoImpl();
        return instancia;
    }
    
    @Override
    public Boolean inserir (Professor professor) {
        if (this.indice(professor.getCpf()) <= -1) {
            listaProfessor.add(professor);
            Collections.sort(listaProfessor);
            return true;
        }
        return false;
    }
    
    @Override
    public int indice (String cpf) {
        return Collections.binarySearch(listaProfessor, new Professor (null, cpf, null));
    }
    
    @Override
    public Professor obter (String cpf) {
        if (this.indice(cpf) >= 0)
            return listaProfessor.get(this.indice(cpf));
        return null;
    }
    
    @Override
    public List<Professor> obterTodos () {
        return listaProfessor;
    }
    
    @Override
    public void persist(Professor object) {
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
        File file = new File("Professor.txt");
        if(!file.exists())
            file.createNewFile();
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        for(Professor professor: this.listaProfessor){
            bw.write(professor.getNome());
            bw.newLine();
            bw.write(professor.getCpf());
            bw.newLine();
            bw.write(professor.getDepartamento());
            bw.newLine();
            if (professor.getDisciplina().isEmpty())
                bw.write("NULL");
            else
                for(int i = 0; i< professor.getDisciplina().size(); i++){
                    bw.write(professor.getDisciplina().get(i).getNome());
                    bw.write(",");
                }
            bw.newLine();
        }
        bw.close();
        fw.close();
    }
    
    @Override
    public void carregar () throws IOException{
        File file = new File ("Professor.txt");
        FileReader fr = new FileReader (file);
        BufferedReader br = new BufferedReader (fr);
        while (br.ready()){
            String nome = br.readLine();
            String cpf = br.readLine();
            String departamento = br.readLine();
            Professor professor = new Professor(nome,cpf,departamento);
            this.inserir(professor);
            String[] disciplina = br.readLine().split(",");
            for(String d: disciplina)
                if((!(d.equals("NULL"))) && (DisciplinaDaoImpl.getInstancia().obter(d) != null))
                    professor.adicionarDisciplina(DisciplinaDaoImpl.getInstancia().obter(d));
        }
        br.close();
        fr.close();
    }*/
}