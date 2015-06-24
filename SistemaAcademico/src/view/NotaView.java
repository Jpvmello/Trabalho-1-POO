package view;

import java.util.Collections;
import java.util.Locale;
import java.util.Scanner;
import javax.persistence.EntityManager;
import model.dao.AlunoDaoImpl;
import model.dao.AtividadeDaoImpl;
import model.dao.Dao;
import model.dao.NotaDaoImpl;
import model.dao.TurmaDaoImpl;
import model.pojo.Aluno;
import model.pojo.Atividade;
import model.pojo.Nota;
import model.pojo.Turma;

public class NotaView {
    
    private static Scanner scanner = new Scanner (System.in);
    private static Dao alunoDao = AlunoDaoImpl.getInstancia();
    private static Dao atividadeDao = AtividadeDaoImpl.getInstancia();
    private static Dao notaDao = NotaDaoImpl.getInstancia();
    private static Dao turmaDao = TurmaDaoImpl.getInstancia();
            
    public Boolean cadastrar (EntityManager em) throws Exception {
        scanner.useLocale(Locale.US);
        System.out.println("CADASTRO DE NOTAS");
        System.out.println("Atividade:");
        Atividade atividade = (Atividade) this.obterCadastrado(em, atividadeDao);
        if (atividade == null)
            return false;
        if (!atividade.notasLancadas()) {
            for (Aluno aluno: atividade.getTurma().getAluno()) {
                Collections.sort(aluno.getNota(), new Atividade());
                if (Collections.binarySearch(aluno.getNota(), new Nota (null, null, null, atividade),
                        new Atividade()) <= -1) {
                    System.out.println("\nAtualize a nota do aluno abaixo nessa atividade:\n");
                    System.out.println(aluno.toString());
                    String id = this.validarId(em);
                    if (id == null) {
                        System.out.println("\nO registro de notas da atividade ainda não foi concluído"
                                + " para todos os alunos. Você pode retomar a operação a qualquer momento.");
                        return true;
                    }
                    System.out.println("Nota (XX.XX):");
                    Double valorDaNota = scanner.nextDouble();
                    scanner.nextLine();
                    Nota nota = new Nota (id, valorDaNota, aluno, atividade);
                    notaDao.salvar(em, nota);
                }
            }
            atividade.setNotasLancadas(true);
            return true;
        }
        else {
            System.out.println("\nAS NOTAS CORRESPONDENTES À ESTA ATIVIDADE JÁ FORAM LANÇADAS!");
            System.out.println("Você pode alterar as notas lançadas a qualquer momento através do"
                    + " menu \"GERENCIAR TURMAS E DISCIPLINAS\".");
            return false;
        }
    }
    
    public String validarId (EntityManager em) {
        while (true) {
            System.out.println("ID (\"cancelar\" para cancelar): ");
            String id = scanner.nextLine();
            if (id.equals("cancelar"))
                break;
            if (notaDao.obter(em, id) == null)
                return id;
            else
                System.out.println("\nUMA NOTA COM ESTE ID JÁ ESTÁ CADASTRADA! TENTE NOVAMENTE!\n");
        }
        return null;
    }
    
    public void editar (EntityManager em) throws Exception {
        scanner.useLocale(Locale.US);
        System.out.println("Informe o ID do registro de nota:");
        Nota nota = (Nota) notaDao.obter(em, scanner.nextLine());
        if (nota != null) {
            System.out.println("\n" + nota.toString());
            System.out.println("Nota:");
            nota.setNota(scanner.nextDouble());
            System.out.println("\nEDIÇÃO EFETUADA!\n");
            notaDao.atualizar(em, nota);
        }
        else
            System.out.println("\nREGISTRO DE NOTA NÃO ENCONTRADO!\n");
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
    
    public Boolean alterarNotasLancadas (EntityManager em) {
        scanner.useLocale(Locale.US);
        System.out.println("Informe o CPF do aluno:");
        Aluno aluno = (Aluno) alunoDao.obter(em, scanner.nextLine());
        if (aluno != null) {
            System.out.println("Informe o ID da turma:");
            Turma turma = (Turma) turmaDao.obter(em, scanner.nextLine());
            if (turma != null) {
                if (turma.getAluno().contains(aluno)) {
                    System.out.println("Informe o ID da atividade:");
                    Atividade atividade = (Atividade) atividadeDao.obter(em, scanner.nextLine());
                    if (atividade != null) {
                        if (atividade.getTurma().equals(turma)) {
                            if (atividade.notasLancadas()) {
                                Collections.sort(atividade.getNota(), new Nota());
                                Nota nota = atividade.getNota().get(Collections.binarySearch(atividade.getNota(),
                                        new Nota (null, null, aluno, null), new Nota()));
                                System.out.println("\nNota (XX.XX):");
                                Double novaNota = scanner.nextDouble();
                                scanner.nextLine();
                                nota.setNota(novaNota);
                                return true;
                            }
                            else
                                System.out.println("\nAS NOTAS PARA ESTA ATIVIDADE AINDA NÃO FORAM LANÇADAS");
                        }
                        else
                            System.out.println("\nATIVIDADE NÃO APLICADA A ESTA TURMA!");
                    }
                    else
                        System.out.println("\nATIVIDADE NÃO ENCONTRADA!");
                }
                else
                    System.out.println("\nO(A) ALUNO(A) NÃO ESTÁ MATRICULADO(A) NESTA TURMA!");
            }
            else
                System.out.println("\nTURMA NÃO ENCONTRADA!");
        }
        else
            System.out.println("\nALUNO NÃO ENCONTRADO!");
        return false;
    }
}
