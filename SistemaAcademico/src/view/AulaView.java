package view;

import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import model.dao.AulaDaoImpl;
import model.dao.Dao;
import model.pojo.Aula;

public class AulaView {

    private static Scanner scanner = new Scanner (System.in);
    private static Dao aulaDao = AulaDaoImpl.getInstancia();


    public String validarId (EntityManager em, String id) {
            if (aulaDao.obter(em, id) == null)
                return id;
            else
                return null;
    }
    
    public void listar(EntityManager em){
        
        System.out.println("-------Lista de Aulas Cadastradas-------");
        List<Aula> attachedAula = aulaDao.obterTodos(em);
        for(Aula aula : attachedAula){
        aula.toString();
        }
    }
}
