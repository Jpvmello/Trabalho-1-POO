package model.dao;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.pojo.Disciplina;

/**
 *
 * @author Lucas
 */
public class DisciplinaDaoImpl implements Dao {
    private static List<Disciplina> listaDisciplina;
    private static DisciplinaDaoImpl instancia = null;
    
    public static DisciplinaDaoImpl getInstancia(){//TALVEZ ISSO DÊ ERRO
        if(DisciplinaDaoImpl.instancia == null)
            instancia = new DisciplinaDaoImpl();
        return instancia;
    }
    
    @Override
    public Boolean inserir (Object objeto) {
        Disciplina disciplina = (Disciplina) objeto;
        if (this.indice(disciplina.getNome()) == -1) {
            listaDisciplina.add(disciplina);
            Collections.sort(listaDisciplina);
            return true;
        }
        return false;
    }
    
    @Override
    public Boolean remover (Object objeto) {
        Disciplina disciplina = (Disciplina) objeto;
        return listaDisciplina.remove(disciplina);h
    }
    
    @Override
    public int indice (String nome) {
        return Collections.binarySearch(listaDisciplina, new Disciplina (nome, null, null));
    }
    
    @Override
    public Object obter (String nome) {
        if (this.indice(nome) != -1)
            return listaDisciplina.get(this.indice(nome));
        return null;
    }
    
    @Override
    public List<Object> obterTodos () {
        return (List<Object>) (Object) listaDisciplina;
    }
    
    @Override
    public void salvar () throws IOException{
        File file = new File ("Disciplina.txt");
        if (!(file.exists()))
            file.createNewFile();
        FileWriter fw = new FileWriter (file);
        BufferedWriter bw = new BufferedWriter (fw);
        for (Disciplina disciplina: this.listaDisciplina){
            bw.write (disciplina.getNome());
            bw.newLine ();
            bw.write (disciplina.getEmenta());
            bw.newLine ();
            bw.write (disciplina.getCargaHoraria().toString());
            bw.newLine ();
        }
        bw.close();
        fw.close();
    }
    
    @Override
    public void carregar () throws IOException{
        File file = new File ("Disciplina.txt");
        FileReader fr = new FileReader (file);
        BufferedReader br = new BufferedReader (fr);
        while (br.ready()){
            String nome = br.readLine();
            String ementa = br.readLine();
            Integer cargaHoraria = Integer.parseInt(br.readLine());
            this.inserir(new Disciplina(nome, ementa, cargaHoraria));
        }
    }
}
