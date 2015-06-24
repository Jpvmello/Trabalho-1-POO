package model.dao;

import java.util.List;
import javax.persistence.EntityManager;

public interface Dao<T> {
    Boolean salvar (EntityManager em, T objeto) throws Exception;
    T obter (EntityManager em, String id);
    List<T> obterTodos (EntityManager em);
    Boolean atualizar (EntityManager em, T objeto) throws Exception;
}