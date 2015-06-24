package model.dao;

import java.util.List;
import model.pojo.Falta;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public class FaltaDaoImpl implements Dao<Falta> {    
    private static List<Falta> listaFalta = new ArrayList<>();
    private static FaltaDaoImpl instancia = null;
    
    public static FaltaDaoImpl getInstancia(){
        if(FaltaDaoImpl.instancia == null)
            instancia = new FaltaDaoImpl();
        return instancia;
    }
    
    @Override
    public Boolean salvar (EntityManager em, Falta falta) {
        em.getTransaction().begin();
        em.persist(falta);
        em.getTransaction().commit();
        return true;
    }
    
    @Override
    public Falta obter (EntityManager em, String id) {
        return em.find(Falta.class, id);
    }

    @Override
    public List<Falta> obterTodos (EntityManager em) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Falta.class));
        Query q = em.createQuery(cq);
        return q.getResultList();
    }
    
    @Override
    public Boolean atualizar (EntityManager em, Falta falta) throws Exception {
        try {
            em.getTransaction().begin();
            falta = em.merge(falta);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = falta.getId();
                if (obter(em, id) == null) {
                    return false;
                }
            }
            throw ex;
        }
        return true;
    }
}