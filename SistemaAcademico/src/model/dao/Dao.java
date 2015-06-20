package model.dao;

import java.util.List;
import javax.persistence.EntityManager;

public interface Dao<T> {
    Boolean inserir (T objeto);
    int indice (String id);
    T obter (String id);
    List<T> obterTodos ();
    void persist(EntityManager em, T object);
    /*void salvar () throws IOException;
    void carregar () throws IOException;*/
}