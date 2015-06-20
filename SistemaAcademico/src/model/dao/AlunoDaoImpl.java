package model.dao;

import java.util.Collections;
import java.util.List;
import model.pojo.Aluno;
/*import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import model.pojo.Turma;*/
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.pojo.Aula;

public class AlunoDaoImpl implements Dao<Aluno> {
    
    private static List<Aluno> listaAluno = new ArrayList<>();
    private static AlunoDaoImpl instancia = null;
    
    public static AlunoDaoImpl getInstancia(){
        if(AlunoDaoImpl.instancia == null)
            instancia = new AlunoDaoImpl();
        return instancia;
    }
    
    @Override
    public Boolean inserir (Aluno aluno) {
        if (this.indice(aluno.getCpf()) <= -1) {
            listaAluno.add(aluno);
            Collections.sort(listaAluno);
            return true;
        }
        return false;
    }
    
    @Override
    public int indice (String cpf) {
        return Collections.binarySearch(listaAluno, new Aluno (null, cpf));
    }
    
    @Override
    public Aluno obter (String cpf) {
        if (this.indice(cpf) >= 0)
            return listaAluno.get(this.indice(cpf));
        return null;
    }
    
    @Override
    public List<Aluno> obterTodos () {
        return listaAluno;
    }
    
   
    
    
    /*@Override
    public void salvar () throws IOException{
        File file = new File("Aluno.txt");
        if(!file.exists())
            file.createNewFile();
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        for(Aluno aluno: this.listaAluno){
            bw.write(aluno.getNome());
            bw.newLine();
            bw.write(aluno.getCpf());
            bw.newLine();
            if (aluno.getTurma().isEmpty())
                bw.write("NULL");
            else
                for(int i = 0; i< aluno.getTurma().size(); i++){
                    bw.write(aluno.getTurma().get(i).getId());
                    bw.write(",");
                }
            bw.newLine();
            if (aluno.getFalta().isEmpty())
                bw.write("NULL");
            else
                for(int i = 0; i< aluno.getFalta().size(); i++){
                    bw.write(aluno.getFalta().get(i).getId());
                    bw.write(",");
                }
            bw.newLine();
        }
        bw.close();
        fw.close();
    }
    
    @Override
    public void carregar () throws IOException{
        File file = new File ("Aluno.txt");
        FileReader fr = new FileReader (file);
        BufferedReader br = new BufferedReader (fr);
        while (br.ready()){
            String nome = br.readLine();
            String cpf = br.readLine();
            Aluno aluno = new Aluno(nome, cpf);
            this.inserir(aluno);
            String[] turma = br.readLine().split(",");
            for(String t: turma) {
                Turma turmaConsultada = (Turma) TurmaDaoImpl.getInstancia().obter(t);
                if((!(t.equals("NULL"))) && (turmaConsultada != null))
                    turmaConsultada.adicionarAluno(aluno);
            }
            String[] falta = br.readLine().split(",");
            for(String f: falta)
                if((!(f.equals("NULL"))) && (FaltaDaoImpl.getInstancia().obter(f) != null))
                    aluno.adicionarFalta(FaltaDaoImpl.getInstancia().obter(f));
        }
        br.close();
        fr.close();
    }*/
}