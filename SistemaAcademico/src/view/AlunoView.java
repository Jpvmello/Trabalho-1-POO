package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import model.dao.AlunoDaoImpl;
import model.dao.Dao;
import model.pojo.Aluno;

public class AlunoView {
    
    private static Scanner scanner = new Scanner (System.in);
    private static final Dao alunoDao = AlunoDaoImpl.getInstancia();
        
  
    public String validarId (EntityManager em, String id) {
            if (alunoDao.obter(em, id) == null)
                return id;
            else
               return null;
    }
    
    public void listar(EntityManager em){
        
        System.out.println("-------Lista de Alunos Cadastrados -------");
        List<Aluno> attachedAluno = alunoDao.obterTodos(em);
        for(Aluno aluno : attachedAluno){
        aluno.toString();
        }
    }
}