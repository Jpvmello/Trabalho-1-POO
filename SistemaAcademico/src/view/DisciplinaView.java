package view;

import java.util.Scanner;
import javax.persistence.EntityManager;
import model.dao.Dao;
import model.dao.DisciplinaDaoImpl;
import model.dao.ProfessorDaoImpl;
import model.pojo.Disciplina;
import model.pojo.Professor;

public class DisciplinaView {
    
    private static Scanner scanner = new Scanner (System.in);
    private static Dao disciplinaDao = DisciplinaDaoImpl.getInstancia();
    private static Dao professorDao = ProfessorDaoImpl.getInstancia();
    
    public Boolean cadastrar(EntityManager em) throws Exception{
        System.out.println("CADASTRO DE DISCIPLINAS\nCadastre uma nova disciplina:\n");
        String nome = this.validarId(em);
        if (nome == null)
            return false;
        System.out.println("Ementa: ");
        String ementa = scanner.nextLine();
        System.out.println("Carga Horária: ");
        Integer cargaHoraria = scanner.nextInt();
        scanner.nextLine();
        Disciplina disciplina = new Disciplina(nome, ementa, cargaHoraria);
        return disciplinaDao.salvar(em, disciplina);
    }
    
    public String validarId (EntityManager em) {
        while (true) {
            System.out.println("Nome (\"cancelar\" para cancelar): ");
            String id = scanner.nextLine();
            if (id.equals("cancelar"))
                break;
            if (disciplinaDao.obter(em, id) == null)
                return id;
            else
                System.out.println("\nUMA DISCIPLINA COM ESTE NOME JÁ ESTÁ CADASTRADA!"
                        + " TENTE NOVAMENTE!\n");
        }
        return null;
    }
    
    public Boolean quantidadeTurmas(EntityManager em){
        System.out.println("Informe o nome da disciplina: ");
        Disciplina disciplina=(Disciplina)disciplinaDao.obter(em, scanner.nextLine());
        if(disciplina != null){
            System.out.println("A quantidade de turmas da disciplina " + disciplina.getNome() +
                    " já oferecidas é " + disciplina.getTurma().size() + ".");
            return true;
        }
        return false;
    }
    
    public Boolean atribuirProfessor (EntityManager em) {
        System.out.println("Informe o nome da disciplina: ");
        Disciplina disciplina = (Disciplina) disciplinaDao.obter(em, scanner.nextLine());
        if(disciplina != null) {
            System.out.println("Informe o CPF do(a) professor(a) a ser atribuído à disciplina: ");
            Professor professor = (Professor) professorDao.obter(em, scanner.nextLine());
            if (professor != null)
                if (professor.adicionarDisciplina(disciplina))
                    return true;
                else
                    System.out.println("\nESTE PROFESSOR JÁ FOI ATRIBUÍDO A ESTA DISCIPLINA"
                            + " ANTERIORMENTE!");
            else
                System.out.println("\nPROFESSOR NÃO ENCONTRADO!");
        }
        else
            System.out.println("\nDISCIPLINA NÃO ENCONTRADA!");
        return false;
    }
}
