package view;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import javax.persistence.EntityManager;
import model.dao.AtividadeDaoImpl;
import model.dao.Dao;
import model.dao.TurmaDaoImpl;
import model.pojo.Atividade;
import model.pojo.Turma;

public class AtividadeView {
    
    private static Scanner scanner = new Scanner (System.in);
    private static Dao atividadeDao = AtividadeDaoImpl.getInstancia();
    private static Dao turmaDao = TurmaDaoImpl.getInstancia();

    public Boolean cadastrar (EntityManager em) throws Exception {
        scanner.useLocale(Locale.US);
        System.out.println("CADASTRO DE ATIVIDADES");
        System.out.println("Turma:");
        Turma turma = (Turma) this.obterCadastrado(em, turmaDao);
        if(turma == null)
            return false;
        System.out.println("\nCadastre uma nova atividade:\n");
        String id = this.validarId(em);
        if (id == null)
            return false;
        System.out.println("Nome: ");
        String nome = scanner.nextLine();
        System.out.println("Tipo: ");
        String tipo = scanner.nextLine();
        System.out.println("Data (DD/MM/AAAA): ");
        String data = scanner.nextLine();
        System.out.println("Valor (XX.XX): ");
        Double valor = scanner.nextDouble();
        scanner.nextLine();
        Atividade atividade = new Atividade (id, nome, tipo, data, valor, turma);
        return atividadeDao.salvar(em, atividade);
    }
    
    public String validarId (EntityManager em) {
        while (true) {
            System.out.println("ID (\"cancelar\" para cancelar): ");
            String id = scanner.nextLine();
            if (id.equals("cancelar"))
                break;
            if (atividadeDao.obter(em, id) == null)
                return id;
            else
                System.out.println("\nUMA ATIVIDADE COM ESTE ID JÁ ESTÁ CADASTRADA! TENTE NOVAMENTE!\n");
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
    public void listar(EntityManager em){
        
        System.out.println("-------Lista de Atividades Cadastradas-------");
        List<Atividade> attachedAtividade = atividadeDao.obterTodos(em);
        for(Atividade atividade : attachedAtividade){
        atividade.toString();
        }
    }
}
