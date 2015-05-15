package model.dao;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import model.pojo.Atividade;

/**
 *
 * @author Mônicka
 */
public class AtividadeDaoImpl implements Dao {
    
    private static List<Atividade> listaAtividade;
    
    @Override
    public Boolean inserir (Object objeto) {
        Atividade atividade = (Atividade) objeto;
        if (this.indice(atividade.getId()) == -1) {
            listaAtividade.add(atividade);
            Collections.sort(listaAtividade);
            return true;
        }
        return false;
    }
    
    @Override
    public Boolean remover (Object objeto) {
        Atividade atividade = (Atividade) objeto;
        return listaAtividade.remove(atividade);
    }
    
    @Override
    public int indice (String id) {
        return Collections.binarySearch(listaAtividade, new Atividade (id, null, 
                null, null, null, null));
    }
    
    @Override
    public Object obter (String id) {
        if (this.indice(id) != -1)
            return listaAtividade.get(this.indice(id));
        return null;
    }
    
    @Override
    public List<Object> obterTodos () {
        return (List<Object>) (Object) listaAtividade;
    }
    
    @Override
    public void salvar () throws IOException{
        File file = new File ("Atividade.txt");
        if (!(file.exists()))
            file.createNewFile();
        FileWriter fw = new FileWriter (file);
        BufferedWriter bw = new BufferedWriter (fw);
        for (Atividade atividade: this.listaAtividade){
            bw.write (atividade.getId());
            bw.newLine ();
            bw.write (atividade.getNome());
            bw.newLine ();
            bw.write (atividade.getTipo());
            bw.newLine ();
            bw.write (atividade.getData());
            bw.newLine ();
            bw.write (atividade.getValor().toString());
            bw.newLine ();
            bw.write (atividade.notasLancadas().toString());
            bw.newLine ();
            bw.write (atividade.getTurma().getId());
            bw.newLine ();
            for (int i = 0; i < atividade.getNota().size(); i++){
                bw.write (atividade.getNota().get(i).getId());
                bw.write (",");
            }
            bw.newLine();
        }
        bw.close();
        fw.close();
    }
    
    @Override
    public void carregar (){
    //*Implementar*//
    }
}