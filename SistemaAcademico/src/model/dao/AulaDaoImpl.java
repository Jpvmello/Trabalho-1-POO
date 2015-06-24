package model.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import model.pojo.Aula;

public class AulaDaoImpl implements Dao<Aula> {    
    private static List<Aula> listaAula = new ArrayList<Aula>();
    private static AulaDaoImpl instancia = null;
    
    public static AulaDaoImpl getInstancia(){
        if(AulaDaoImpl.instancia == null)
            instancia = new AulaDaoImpl();
        return instancia;
    }
    
    @Override
    public Boolean salvar (EntityManager em, Aula aula) {
        em.getTransaction().begin();
        em.persist(aula);
        em.getTransaction().commit();
        return true;
    }

    @Override
    public Aula obter(EntityManager em, String id) {
        return em.find(Aula.class, id);
    }

    @Override
    public List<Aula> obterTodos (EntityManager em) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Aula.class));
        Query q = em.createQuery(cq);
        return q.getResultList();
    }
    
    @Override
    public Boolean atualizar (EntityManager em, Aula aula) throws Exception {
        try {
            em.getTransaction().begin();
            aula = em.merge(aula);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = aula.getId();
                if (obter(em, id) == null) {
                    return false;
                }
            }
            throw ex;
        }
        return true;
    }
}
