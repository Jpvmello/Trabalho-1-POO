package view;

import view.cadastros.CadastrarProfessorGUI;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import view.cadastros.CadastrarAlunoGUI;
import view.cadastros.CadastrarAtividadeGUI;
import view.cadastros.CadastrarAulaGUI;
import view.cadastros.CadastrarDisciplinaGUI;
import view.consultas.ConsultarAlunoDisciplina;
import view.consultas.ConsultarAlunosTurma;
import view.consultas.ConsultarDisciplinaslecionadas;
import view.consultas.ConsultarQuantTurma;
import view.listagens.ListarGUI;

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
                    CadastrarAlunoGUI cadastrarAluno = new CadastrarAlunoGUI(em);
//                    cadastrarAluno.setTitle("CADASTRAR ALUNO");
//                    cadastrarAluno.setVisible(true);
                    //cadastroEfetuado = this.alunoView.cadastrar(em);
                    break;
                }
                case 2:{
                    cadastroEfetuado = this.turmaView.cadastrar(em);
                    break;
                }
                case 3:{
                    CadastrarProfessorGUI cadastrarProfessor = new CadastrarProfessorGUI(em);
//                    cadastrarProfessor.setTitle("CADASTRAR PROFESSOR");
//                    cadastrarProfessor.setVisible(true);
                    //cadastroEfetuado = this.professorView.cadastrar(em);
                    break;
                }
                case 4:{
                    CadastrarAtividadeGUI cadastrarAtividade = new CadastrarAtividadeGUI(em);
//                    cadastrarAtividade.setTitle("CADASTRAR ATIVIDADE");
//                    cadastrarAtividade.setVisible(true);
                    //1cadastroEfetuado = this.atividadeView.cadastrar(em);
                    break;
                }
                case 5:{
                    CadastrarDisciplinaGUI cadastrarDisciplina = new CadastrarDisciplinaGUI(em);
//                    cadastrarDisciplina.setTitle("CADASTRAR DISCIPLINA");
//                    cadastrarDisciplina.setVisible(true);
                    //cadastroEfetuado = this.disciplinaView.cadastrar(em);
                    break;
                }
                case 6:{
                    CadastrarAulaGUI cadastrarAula = new CadastrarAulaGUI(em);
//                    cadastrarAula.setTitle("CADASTRAR ATIVIDADE");
//                    cadastrarAula.setVisible(true);
                    //cadastroEfetuado = this.aulaView.cadastrar(em);
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
    
    private void imprimirMenuEdicao(EntityManager em) throws Exception{
        while (true) {
            System.out.println("1 - EDITAR ALUNO");
            System.out.println("2 - EDITAR TURMA");
            System.out.println("3 - EDITAR PROFESSOR");
            System.out.println("4 - EDITAR ATIVIDADE");
            System.out.println("5 - EDITAR DISCIPLINA");
            System.out.println("6 - EDITAR AULA");
            System.out.println("7 - EDITAR NOTAS");
            System.out.println("8 - EDITAR FALTAS");
            System.out.println("OUTRO - VOLTAR");

            System.out.println("\nOpção: ");
            Scanner entrada = new Scanner(System.in);
            Integer opcao = entrada.nextInt();
            entrada.nextLine();
            System.out.println("");
            switch(opcao) {
                case 1:{
                    //this.alunoView.editar(em);
                    break;
                }
                case 2:{
                    this.turmaView.editar(em);
                    break;
                }
                case 3:{
                    //this.professorView.editar(em);
                    break;
                }
                case 4:{
                    //this.atividadeView.editar(em);
                    break;
                }
                case 5:{
                    //this.disciplinaView.editar(em);
                    break;
                }
                case 6:{
                    //this.aulaView.editar(em);
                    break;
                }
                case 7:{
                    //this.notaView.editar(em);
                    break;
                }
                case 8:{
                    //this.faltaView.editar(em);
                    break;
                }
                default:{}
            }
            if (opcao < 1 || opcao > 8)
                break;
        }
    }
    
    private void imprimirMenuGerenciamento(EntityManager em){
        while(true){
            System.out.println("1 - MATRICULAR ALUNO");
            System.out.println("2 - ATRIBUIR PROFESSOR A DISCIPLINA");
            System.out.println("3 - ATRIBUIR AULA A TURMA");
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
                default:{}
            }
            if (opcao < 1 || opcao > 3)
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
                    ConsultarAlunosTurma consultarAlunosTurma = new ConsultarAlunosTurma(em);
                    //consultaEfetuada = this.turmaView.listarAlunos(em);4
                    break;
                }
                case 2:{
                    ConsultarAlunoDisciplina consultarAlunoDisciplina = new ConsultarAlunoDisciplina(em);
                    //consultaEfetuada = this.turmaView.consultarSituacaoAluno(em);
                    break;
                }
                case 3:{
                    ConsultarQuantTurma consultarQuantTurma = new ConsultarQuantTurma(em);
                    //consultaEfetuada = this.disciplinaView.quantidadeTurmas(em);
                    break;
                }
                case 4:{
                    ConsultarDisciplinaslecionadas consultarDisciplinaslecionadas = new ConsultarDisciplinaslecionadas(em);
                    // consultaEfetuada = this.professorView.quantidadeDisciplina(em);
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
            System.out.println("2 - LISTAR");
            System.out.println("3 - EDITAR");
            System.out.println("4 - CONSULTAR");
            System.out.println("5 - GERENCIAR TURMAS E DISCIPLINAS");
            System.out.println("6 - SAIR");
            
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
                        ListarGUI listarAluno = new ListarGUI(em);
                        listarAluno.setTitle("LISTAR ALUNOS!2");
                        listarAluno.setVisible(true);
                        break;
                    }
                    case 3: {
                        main.imprimirMenuEdicao(em);
                        break;
                    }
                    case 4: {
                        main.imprimirMenuConsultar(em);
                        break;
                    }
                    case 5:{
                        main.imprimirMenuGerenciamento(em);
                        break;
                    }
                    case 6:{
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
