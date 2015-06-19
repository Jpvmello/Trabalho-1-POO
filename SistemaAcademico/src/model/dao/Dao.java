package model.dao;

import java.util.List;

public interface Dao<T> {
    Boolean inserir (T objeto);
    int indice (String id);
    T obter (String id);
    List<T> obterTodos ();
    void persist (T objeto); 
    /*void salvar () throws IOException;
    void carregar () throws IOException;*/
}