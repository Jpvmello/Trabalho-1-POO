package view;

import java.util.Scanner;
import model.dao.Dao;
import model.dao.ProfessorDaoImpl;
import model.pojo.Professor;

public class ProfessorView {

    private static Scanner scanner = new Scanner (System.in);
    private static Dao professorDao = ProfessorDaoImpl.getInstancia();
    
    public Boolean cadastrar () {
        System.out.println("CADASTRO DE PROFESSORES\nCadastre um novo professor:\n");
        System.out.println("Nome: ");
        String nome = scanner.nextLine();
        String cpf = this.validarId();
        if (cpf == null)
            return false;
        System.out.println("Departamento: ");
        String departamento = scanner.nextLine();
        Professor professor = new Professor (nome, cpf, departamento);
        return professorDao.inserir(professor);
    }
    
    public String validarId () {
        while (true) {
            System.out.println("CPF (\"cancelar\" para cancelar): ");
            String id = scanner.nextLine();
            if (id.equals("cancelar"))
                break;
            if (professorDao.indice(id) <= -1)
                return id;
            else
                System.out.println("\nUM(A) PROFESSOR(A) COM ESTE CPF JÁ ESTÁ CADASTRADO(A)!"
                        + " TENTE NOVAMENTE!\n");
        }
        return null;
    }

    public Boolean quantidadeDisciplina(){
        System.out.println("Informe o CPF do professor: ");
        Professor professor = (Professor) professorDao.obter(scanner.nextLine());
        if(professor != null){
            System.out.println("A quantidade de disciplinas já lecionadas pelo(a) professsor(a) " + professor.getNome()
                       + " é " + professor.getDisciplina().size() + ".");
            return true;
        }
        System.out.println("\nNÃO EXISTE PROFESSOR(A) CADASTRADO(A) COM ESTE CPF!\n");
        return false;
    }    
}
