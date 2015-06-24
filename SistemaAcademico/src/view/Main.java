package view;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    
    private TurmaView turmaView = new TurmaView();
    private AlunoView alunoView = new AlunoView();
    private ProfessorView professorView = new ProfessorView();
    private AtividadeView atividadeView = new AtividadeView();
    private FaltaView faltaView = new FaltaView();
    private NotaView notaView = new NotaView();
    private DisciplinaView disciplinaView = new DisciplinaView();
    private AulaView aulaView = new AulaView();
        
    private void imprimirMenuCadastro(EntityManager em) throws Exception{
        while (true) {
            System.out.println("1 - CADASTRAR ALUNO");
            System.out.println("2 - CADASTRAR TURMA");
            System.out.println("3 - CADASTRAR PROFESSOR");
            System.out.println("4 - CADASTRAR ATIVIDADE");
            System.out.println("5 - CADASTRAR DISCIPLINA");
            System.out.println("6 - CADASTRAR AULA");
            System.out.println("7 - LANÇAR NOTAS");
            System.out.println("8 - LANÇAR FALTAS");
            System.out.println("OUTRO - VOLTAR");

            System.out.println("\nOpção: ");
            Scanner entrada = new Scanner(System.in);
            Integer opcao = entrada.nextInt();
            entrada.nextLine();
            System.out.println("");
            Boolean cadastroEfetuado = false;
            switch(opcao) {
                case 1:{
                    cadastroEfetuado = this.alunoView.cadastrar(em);
                    break;
                }
                case 2:{
                    cadastroEfetuado = this.turmaView.cadastrar(em);
                    break;
                }
                case 3:{
                    cadastroEfetuado = this.professorView.cadastrar(em);
                    break;
                }
                case 4:{
                    cadastroEfetuado = this.atividadeView.cadastrar(em);
                    break;
                }
                case 5:{
                    cadastroEfetuado = this.disciplinaView.cadastrar(em);
                    break;
                }
                case 6:{
                    cadastroEfetuado = this.aulaView.cadastrar(em);
                    break;
                }
                case 7:{
                    cadastroEfetuado = this.notaView.cadastrar(em);
                    break;
                }
                case 8:{
                    cadastroEfetuado = this.faltaView.cadastrar(em);
                    break;
                }
                default:{}
            }
            if (opcao < 1 || opcao > 8)
                break;
            if (cadastroEfetuado)
                System.out.println("\nCADASTRO/LANÇAMENTO EFETUADO COM SUCESSO!\n");
            else
                System.out.println("\nCADASTRO/LANÇAMENTO NÃO EFETUADO!\n");
        }
    }
    
    private void imprimirMenuGerenciamento(EntityManager em){
        while(true){
            System.out.println("1 - MATRICULAR ALUNO");
            System.out.println("2 - ATRIBUIR PROFESSOR A DISCIPLINA");
            System.out.println("3 - ATRIBUIR AULA A TURMA");
            System.out.println("4 - ALTERAR NOTAS LANÇADAS");
            System.out.println("OUTRO - VOLTAR");

            System.out.println("\nOpção: ");
            Scanner entrada = new Scanner(System.in);
            Integer opcao = entrada.nextInt();
            entrada.nextLine();
            System.out.println("");
            Boolean operacaoEfetuada = false;
            switch(opcao){
                case 1:{
                    operacaoEfetuada = this.turmaView.matricularAluno(em);
                    break;
                }
                case 2:{
                    operacaoEfetuada = this.disciplinaView.atribuirProfessor(em);
                    break;
                }
                case 3:{
                    operacaoEfetuada = this.turmaView.atribuirAula(em);
                    break;
                }
                case 4:{
                    operacaoEfetuada = this.notaView.alterarNotasLancadas(em);
                    break;
                }
                default:{}
            }
            if (opcao < 1 || opcao > 4)
                break;
            if (operacaoEfetuada)
                System.out.println("\nCONCLUÍDO COM ÊXITO!\n");
            else
                System.out.println("\nNÃO CONCLUÍDO!\n");
            
        }
    }
    
    private void imprimirMenuConsultar(EntityManager em){
        while(true){
            System.out.println("1 - CONSULTAR OS ALUNOS DE UMA TURMA COM SUAS RESPECTIVAS NOTAS E FALTAS");
            System.out.println("2 - CONSULTAR A SITUAÇÃO DO ALUNO EM DETERMINADA DISCIPLINA");
            System.out.println("3 - CONSULTAR A QUANTIDADE DE TURMAS OFERECIDAS DE UMA DISCIPLINA");
            System.out.println("4 - CONSULTAR O NÚMERO DE DISCIPLINAS JÁ LECIONADAS POR UM PROFESSOR");
            System.out.println("OUTRO - VOLTAR");

            System.out.println("\nOpção: ");
            Scanner entrada = new Scanner(System.in);
            Integer opcao = entrada.nextInt();
            entrada.nextLine();
            System.out.println("");
            Boolean consultaEfetuada = false;
            switch(opcao){
                case 1:{
                    consultaEfetuada = this.turmaView.listarAlunos(em);
                    break;
                }
                case 2:{
                    consultaEfetuada = this.turmaView.consultarSituacaoAluno(em);
                    break;
                }
                case 3:{
                    consultaEfetuada = this.disciplinaView.quantidadeTurmas(em);
                    break;
                }
                case 4:{
                    consultaEfetuada = this.professorView.quantidadeDisciplina(em);
                    break;
                }
                default:{}
            }
            if (opcao < 1 || opcao > 4)
                break;
            if (!consultaEfetuada)
                System.out.println("\nNÃO FORAM ENCONTRADOS RESULTADOS PARA ESTA CONSULTA!\n");    
        }
    }
        
    public static void main(String[] args) throws IOException, Exception{
        Integer opcao;
        Main main = new Main();
        Scanner entrada = new Scanner(System.in);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SistemaAcademicoPU");
        EntityManager em = emf.createEntityManager();
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);

        while(true){
            System.out.println("1 - CADASTRAR/LANÇAR");
            System.out.println("2 - CONSULTAR");
            System.out.println("3 - GERENCIAR TURMAS E DISCIPLINAS");
            System.out.println("4 - SAIR");
            
            System.out.println("\nOpção:");
            opcao = entrada.nextInt();
            entrada.nextLine();
            System.out.println("");
            switch(opcao){
                    case 1: {
                        main.imprimirMenuCadastro(em);
                        break;
                    }
                    case 2: {
                        main.imprimirMenuConsultar(em);
                        break;
                    }
                    case 3:{
                        main.imprimirMenuGerenciamento(em);
                        break;
                    }
                    case 4:{
                        em.close();
                        emf.close();
                        System.exit(0);
                    }
                    default:{
                        System.out.println("\nDIGITE UMA OPÇÃO VÁLIDA!\n");
                    }
            }
        }
    }
}
