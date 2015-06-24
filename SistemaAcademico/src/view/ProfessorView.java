package view;

import java.util.Scanner;
import javax.persistence.EntityManager;
import model.dao.Dao;
import model.dao.ProfessorDaoImpl;
import model.pojo.Professor;

public class ProfessorView {

    private static Scanner scanner = new Scanner (System.in);
    private static Dao professorDao = ProfessorDaoImpl.getInstancia();
    
    public Boolean cadastrar (EntityManager em) throws Exception {
        System.out.println("CADASTRO DE PROFESSORES\nCadastre um novo professor:\n");
        System.out.println("Nome: ");
        String nome = scanner.nextLine();
        String cpf = this.validarId(em);
        if (cpf == null)
            return false;
        System.out.println("Departamento: ");
        String departamento = scanner.nextLine();
        Professor professor = new Professor (nome, cpf, departamento);
        return professorDao.salvar(em, professor);
    }
    
    public String validarId (EntityManager em) {
        while (true) {
            System.out.println("CPF (\"cancelar\" para cancelar): ");
            String id = scanner.nextLine();
            if (id.equals("cancelar"))
                break;
            if (professorDao.obter(em, id) == null)
                return id;
            else
                System.out.println("\nUM(A) PROFESSOR(A) COM ESTE CPF JÁ ESTÁ CADASTRADO(A)!"
                        + " TENTE NOVAMENTE!\n");
        }
        return null;
    }
    
    public void editar (EntityManager em) throws Exception {
        System.out.println("Informe o CPF do professor:");
        Professor professor = (Professor) professorDao.obter(em, scanner.nextLine());
        if (professor != null) {
            while (true) {
                System.out.println("\n" + professor.toString());
                System.out.println("1 - EDITAR NOME");
                System.out.println("2 - EDITAR CPF");
                System.out.println("3 - EDITAR DEPARTAMENTO");
                System.out.println("OUTRO - VOLTAR");
                System.out.println("\nOpção:");
                Integer opcao = scanner.nextInt();
                scanner.nextLine();
                System.out.println("");
                switch(opcao) {
                    case 1:{
                        System.out.println("Nome:");
                        professor.setNome(scanner.nextLine());
                        break;
                    }
                    case 2:{
                        System.out.println("CPF:");
                        professor.setCpf(scanner.nextLine());
                        break;
                    }
                    case 3:{
                        System.out.println("Departamento:");
                        professor.setDepartamento(scanner.nextLine());
                        break;
                    }
                    default:{}
                }
                if (opcao < 1 || opcao > 3)
                    break;
                System.out.println("\nEDIÇÃO EFETUADA!\n");
            }
            professorDao.atualizar(em, professor);
        }
        else
            System.out.println("\nPROFESSOR NÃO ENCONTRADO!\n");
    }

    public Boolean quantidadeDisciplina(EntityManager em){
        System.out.println("Informe o CPF do professor: ");
        Professor professor = (Professor) professorDao.obter(em, scanner.nextLine());
        if(professor != null){
            System.out.println("A quantidade de disciplinas já lecionadas pelo(a) professsor(a) " + professor.getNome()
                       + " é " + professor.getDisciplina().size() + ".");
            return true;
        }
        System.out.println("\nNÃO EXISTE PROFESSOR(A) CADASTRADO(A) COM ESTE CPF!\n");
        return false;
    }    
}
