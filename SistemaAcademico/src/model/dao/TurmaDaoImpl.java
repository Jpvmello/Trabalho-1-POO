package model.dao;

import java.util.Collections;
import java.util.List;
import model.pojo.Turma;
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

public class TurmaDaoImpl implements Dao<Turma> {
    private static List<Turma> listaTurma = new ArrayList<Turma>();
    private static TurmaDaoImpl instancia = null;
    
    public static TurmaDaoImpl getInstancia(){
        if(TurmaDaoImpl.instancia == null)
            instancia = new TurmaDaoImpl();
        return instancia;
    }
    
    @Override
    public Boolean inserir (Turma turma) {
        if (this.indice(turma.getId()) <= -1) {
            listaTurma.add(turma);
            Collections.sort(listaTurma);
            return true;
        }
        return false;
    }
    
    @Override
    public int indice (String id) {
        return Collections.binarySearch(listaTurma, new Turma (id, null, 
                null, null, null, null, null, null));
    }
    
    @Override
    public Turma obter (String id) {
        if (this.indice(id) >= 0)
            return listaTurma.get(this.indice(id));
        return null;
    }
    
    @Override
    public List<Turma> obterTodos () {
        return listaTurma;
    }
    
   
    
    /*@Override
    public void salvar () throws IOException{
        File file = new File("Turma.txt");
        if(!file.exists())
            file.createNewFile();
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        for(Turma turma: this.listaTurma){
            bw.write(turma.getId());
            bw.newLine();
            bw.write(turma.getAno().toString());
            bw.newLine();
            bw.write(turma.getPeriodo().toString());
            bw.newLine();
            bw.write(turma.getNumeroDeVagas().toString());
            bw.newLine();
            bw.write(turma.getDisciplina().getNome());
            bw.newLine();
            bw.write(turma.getProfessor().getCpf());
            bw.newLine();
            for(int i = 0; i< turma.getAula().size(); i++){
                bw.write(turma.getAula().get(i).getId());
                bw.write(",");
            }
            bw.newLine();
        }
        bw.close();
        fw.close();
    }
    
    @Override
    public void carregar () throws IOException{
        File file = new File ("Turma.txt");
        FileReader fr = new FileReader (file);
        BufferedReader br = new BufferedReader (fr);
        while (br.ready()){
            String id = br.readLine();
            Integer ano = Integer.parseInt(br.readLine());
            Integer periodo = Integer.parseInt(br.readLine());
            Integer numeroDeVagas = Integer.parseInt(br.readLine());
            String disciplina = br.readLine();
            Turma turma = new Turma (id, ano, periodo, numeroDeVagas, null, null, null, null);
            if (DisciplinaDaoImpl.getInstancia().obter(disciplina) != null){
                turma.setDisciplina(DisciplinaDaoImpl.getInstancia().obter(disciplina));
                turma.getDisciplina().adicionarTurma(turma);
            }
            String professor = br.readLine();
            if (ProfessorDaoImpl.getInstancia().obter(professor) != null)
                turma.setProfessor(ProfessorDaoImpl.getInstancia().obter(professor));
            String[] aula = br.readLine().split(",");
            for (String a: aula)
                if (AulaDaoImpl.getInstancia().obter(a) != null)
                    turma.adicionarAula(AulaDaoImpl.getInstancia().obter(a));
            this.inserir(turma);
        }
        br.close();
        fr.close();
    }*/
}