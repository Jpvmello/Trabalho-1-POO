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
        
    public Boolean cadastrar (EntityManager em) throws Exception {
        System.out.println("CADASTRO DE ALUNOS\nCadastre um novo aluno:\n");
        System.out.println("Nome: ");
        String nome = scanner.nextLine();
        String cpf = this.validarId(em);
        if (cpf == null)
            return false;
        Aluno aluno = new Aluno (nome, cpf);
        return alunoDao.salvar(em, aluno);
    }
    
    public String validarId (EntityManager em) {
        while (true) {
            System.out.println("CPF (\"cancelar\" para cancelar): ");
            String id = scanner.nextLine();
            if (id.equals("cancelar"))
                break;
            if (alunoDao.obter(em, id) == null)
                return id;
            else
                System.out.println("\nUM(A) ALUNO(A) COM ESTE CPF JÁ ESTÁ CADASTRADO(A)!"
                        + " TENTE NOVAMENTE!\n");
        }
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