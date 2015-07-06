package view;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
            
    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SistemaAcademicoPU");
        EntityManager em = emf.createEntityManager();
        new MenuPrincipalGUI(em);
    }
}
