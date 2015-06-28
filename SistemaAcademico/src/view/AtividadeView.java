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

    
    public String validarId (EntityManager em, String id) {
        if (atividadeDao.obter(em, id) == null)
            return id;
        else
            return null;
    }
    
    public Object obterCadastrado (EntityManager em, Dao dao) {
            System.out.println("ID (\"cancelar\" para cancelar): ");
            String entrada = scanner.nextLine();
            Object objeto = dao.obter(em, entrada);
            if (objeto != null)
                return objeto;
            else
                System.out.println("\nITEM NÃO CADASTRADO! TENTE NOVAMENTE.\n");
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
