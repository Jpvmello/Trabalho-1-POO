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
import model.pojo.Atividade;
import model.pojo.Nota;

public class NotaDaoImpl implements Dao<Nota> {
    private static List<Nota> listaNota = new ArrayList<>();
    private static NotaDaoImpl instancia = null;
    
    public static NotaDaoImpl getInstancia(){
        if(NotaDaoImpl.instancia == null)
            instancia = new NotaDaoImpl();
        return instancia;
    }
    
    @Override
    public Boolean inserir (Nota nota) {
        if (this.indice(nota.getId()) <= -1) {
            listaNota.add(nota);
            Collections.sort(listaNota);
            return true;
        }
        return false;
    }
    
    @Override
    public int indice (String id) {
        return Collections.binarySearch(listaNota, new Nota (id, null, null, null));
    }
    
    @Override
    public Nota obter (String id) {
        if (this.indice(id) >= 0)
            return listaNota.get(this.indice(id));
        return null;
    }
    
    @Override
    public List<Nota> obterTodos () {
        return listaNota;
    }
    
    
    
    /*@Override
    public void salvar () throws IOException{
        File file = new File ("Nota.txt");
        if (!(file.exists()))
            file.createNewFile();
        FileWriter fw = new FileWriter (file);
        BufferedWriter bw = new BufferedWriter (fw);
        for (Nota nota: this.listaNota){
            bw.write (nota.getId());
            bw.newLine ();
            bw.write (nota.getNota().toString());
            bw.newLine ();
            bw.write (nota.getAluno().getCpf());
            bw.newLine ();
            if (nota.getAtividade() != null){
                bw.write (nota.getAtividade().getId());
                bw.newLine ();
            }
        }
        bw.close();
        fw.close();
    }
    
  @Override
    public void carregar () throws IOException{
        File file = new File ("Nota.txt");
        FileReader fr = new FileReader (file);
        BufferedReader br = new BufferedReader (fr);
        while (br.ready()){
            String id = br.readLine();
            Double valor = Double.parseDouble (br.readLine());
            String aluno = br.readLine();
            String atividade = br.readLine();
            Nota nota = new Nota(id, valor, null, null);
            this.inserir(nota);
            if (AlunoDaoImpl.getInstancia().obter(aluno) != null){
                nota.setAluno(AlunoDaoImpl.getInstancia().obter(aluno));
                nota.getAluno().adicionarNota(nota);
            }
            if (AtividadeDaoImpl.getInstancia().obter(atividade) != null){
                nota.setAtividade(AtividadeDaoImpl.getInstancia().obter(atividade));
                nota.getAtividade().adicionarNotaArquivo(nota);
            }
        }
        br.close();
        fr.close();
    }*/
}