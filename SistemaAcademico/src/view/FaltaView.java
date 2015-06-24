package view;

import java.util.Collections;
import java.util.Scanner;
import javax.persistence.EntityManager;
import model.dao.Dao;
import model.dao.FaltaDaoImpl;
import model.dao.TurmaDaoImpl;
import model.pojo.Aluno;
import model.pojo.Falta;
import model.pojo.Turma;

public class FaltaView {

    private static Scanner scanner = new Scanner (System.in);
    private static Dao faltaDao = FaltaDaoImpl.getInstancia();
    private static Dao turmaDao = TurmaDaoImpl.getInstancia();
    
    public Boolean cadastrar (EntityManager em) throws Exception {
        System.out.println("CADASTRO DE FALTAS");
        System.out.println("Turma:");
        Turma turma = (Turma) this.obterCadastrado(em, turmaDao);
        if (turma == null)
            return false;
        if (!turma.faltasLancadas()) {
            for (Aluno aluno: turma.getAluno()) {
                Collections.sort(aluno.getFalta(), new Falta());
                if (Collections.binarySearch(aluno.getFalta(), new Falta (null, null, turma),
                        new Falta()) <= -1) {
                    System.out.println("\nAtualize o registro de faltas do aluno abaixo:\n");
                    System.out.println(aluno.toString() + "\n");
                    String id = this.validarId(em);
                    if (id == null) {
                        System.out.println("\nO registro de faltas ainda não foi concluído para todos os"
                                + " alunos da turma. Você pode retomar a operação a qualquer momento.");
                        return true;
                    }
                    System.out.println("Número de faltas: ");
                    Integer numeroDeFalta = scanner.nextInt();
                    scanner.nextLine();
                    Falta falta = new Falta (id, numeroDeFalta, turma);
                    aluno.getFalta().add(falta);
                    faltaDao.salvar(em, falta);
                }
            }
            return true;
        }
        else {
            System.out.println("\nAS FALTAS CORRESPONDENTES AOS ALUNOS DESTA TURMA JÁ FORAM LANÇADAS!");
            return false;
        }
    }
    
    public String validarId (EntityManager em) {
        while (true) {
            System.out.println("ID (\"cancelar\" para cancelar): ");
            String id = scanner.nextLine();
            if (id.equals("cancelar"))
                break;
            if (faltaDao.obter(em, id) == null)
                return id;
            else
                System.out.println("\nUM REGISTRO DE FALTAS COM ESTE ID JÁ ESTÁ CADASTRADO!"
                        + " TENTE NOVAMENTE!\n");
        }
        return null;
    }
    
    public Object obterCadastrado (EntityManager em, Dao dao) {    
        while (true) {
            System.out.println("ID (\"cancelar\" para cancelar): ");
            String entrada = scanner.nextLine();
            if (entrada.equals("cancelar"))
                break;
            Object objeto = dao.obter(em, entrada);
            if (objeto != null)
                return objeto;
            else
                System.out.println("\nITEM NÃO CADASTRADO! TENTE NOVAMENTE.\n");
        }
        return null;
    }
}
