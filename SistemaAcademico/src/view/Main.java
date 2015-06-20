package view;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.dao.AlunoDaoImpl;

import model.pojo.Aula;
import model.dao.AulaDaoImpl;
import model.dao.Dao;
import model.pojo.Aluno;
/*
import model.dao.AtividadeDaoImpl;
import model.dao.AulaDaoImpl;
import model.dao.DisciplinaDaoImpl;
import model.dao.FaltaDaoImpl;
import model.dao.NotaDaoImpl;
import model.dao.ProfessorDaoImpl;
import model.dao.TurmaDaoImpl;*/

public class Main {
    
//    private TurmaView turmaView = new TurmaView();
//    private AlunoView alunoView = new AlunoView();
//    private ProfessorView professorView = new ProfessorView();
//    private AtividadeView atividadeView = new AtividadeView();
//    private FaltaView faltaView = new FaltaView();
//    private NotaView notaView = new NotaView();
//    private DisciplinaView disciplinaView = new DisciplinaView();
//    private AulaView aulaView = new AulaView();
//    
//    /*private void carregarArquivo() throws IOException{
//        DisciplinaDaoImpl.getInstancia().carregar();
//        ProfessorDaoImpl.getInstancia().carregar();
//        AulaDaoImpl.getInstancia().carregar();
//        TurmaDaoImpl.getInstancia().carregar();
//        FaltaDaoImpl.getInstancia().carregar();
//        AlunoDaoImpl.getInstancia().carregar();
//        AtividadeDaoImpl.getInstancia().carregar();
//        NotaDaoImpl.getInstancia().carregar();
//    }
//    
//    private void salvarArquivo() throws IOException{
//        DisciplinaDaoImpl.getInstancia().salvar();
//        ProfessorDaoImpl.getInstancia().salvar();
//        AulaDaoImpl.getInstancia().salvar();
//        TurmaDaoImpl.getInstancia().salvar();
//        FaltaDaoImpl.getInstancia().salvar();
//        AlunoDaoImpl.getInstancia().salvar();
//        AtividadeDaoImpl.getInstancia().salvar();
//        NotaDaoImpl.getInstancia().salvar();
//    }*/
//    
//    private void imprimirMenuCadastro(){
//        while (true) {
//            System.out.println("1 - CADASTRAR ALUNO");
//            System.out.println("2 - CADASTRAR TURMA");
//            System.out.println("3 - CADASTRAR PROFESSOR");
//            System.out.println("4 - CADASTRAR ATIVIDADE");
//            System.out.println("5 - CADASTRAR DISCIPLINA");
//            System.out.println("6 - CADASTRAR AULA");
//            System.out.println("7 - LANÇAR NOTAS");
//            System.out.println("8 - LANÇAR FALTAS");
//            System.out.println("OUTRO - VOLTAR");
//
//            System.out.println("\nOpção: ");
//            Scanner entrada = new Scanner(System.in);
//            Integer opcao = entrada.nextInt();
//            entrada.nextLine();
//            System.out.println("");
//            Boolean cadastroEfetuado = false;
//            switch(opcao) {
//                case 1:{
//                    cadastroEfetuado = this.alunoView.cadastrar();
//                    break;
//                }
//                case 2:{
//                    cadastroEfetuado = this.turmaView.cadastrar();
//                    break;
//                }
//                case 3:{
//                    cadastroEfetuado = this.professorView.cadastrar();
//                    break;
//                }
//                case 4:{
//                    cadastroEfetuado = this.atividadeView.cadastrar();
//                    break;
//                }
//                case 5:{
//                    cadastroEfetuado = this.disciplinaView.cadastrar();
//                    break;
//                }
//                case 6:{
//                    cadastroEfetuado = this.aulaView.cadastrar();
//                    break;
//                }
//                case 7:{
//                    cadastroEfetuado = this.notaView.cadastrar();
//                    break;
//                }
//                case 8:{
//                    cadastroEfetuado = this.faltaView.cadastrar();
//                    break;
//                }
//                default:{}
//            }
//            if (opcao < 1 || opcao > 8)
//                break;
//            if (cadastroEfetuado)
//                System.out.println("\nCADASTRO/LANÇAMENTO EFETUADO COM SUCESSO!\n");
//            else
//                System.out.println("\nCADASTRO/LANÇAMENTO NÃO EFETUADO!\n");
//        }
//    }
//    
//    private void imprimirMenuGerenciamento(){
//        while(true){
//            System.out.println("1 - MATRICULAR ALUNO");
//            System.out.println("2 - ATRIBUIR PROFESSOR A DISCIPLINA");
//            System.out.println("3 - ATRIBUIR AULA A TURMA");
//            System.out.println("4 - ALTERAR NOTAS LANÇADAS");
//            System.out.println("OUTRO - VOLTAR");
//
//            System.out.println("\nOpção: ");
//            Scanner entrada = new Scanner(System.in);
//            Integer opcao = entrada.nextInt();
//            entrada.nextLine();
//            System.out.println("");
//            Boolean operacaoEfetuada = false;
//            switch(opcao){
//                case 1:{
//                    operacaoEfetuada = this.turmaView.matricularAluno();
//                    break;
//                }
//                case 2:{
//                    operacaoEfetuada = this.disciplinaView.atribuirProfessor();
//                    break;
//                }
//                case 3:{
//                    operacaoEfetuada = this.turmaView.atribuirAula();
//                    break;
//                }
//                case 4:{
//                    operacaoEfetuada = this.notaView.alterarNotasLancadas();
//                    break;
//                }
//                default:{}
//            }
//            if (opcao < 1 || opcao > 4)
//                break;
//            if (operacaoEfetuada)
//                System.out.println("\nCONCLUÍDO COM ÊXITO!\n");
//            else
//                System.out.println("\nNÃO CONCLUÍDO!\n");
//            
//        }
//    }
//    
//    private void imprimirMenuConsultar(){
//        while(true){
//            System.out.println("1 - CONSULTAR OS ALUNOS DE UMA TURMA COM SUAS RESPECTIVAS NOTAS E FALTAS");
//            System.out.println("2 - CONSULTAR A SITUAÇÃO DO ALUNO EM DETERMINADA DISCIPLINA");
//            System.out.println("3 - CONSULTAR A QUANTIDADE DE TURMAS OFERECIDAS DE UMA DISCIPLINA");
//            System.out.println("4 - CONSULTAR O NÚMERO DE DISCIPLINAS JÁ LECIONADAS POR UM PROFESSOR");
//            System.out.println("OUTRO - VOLTAR");
//
//            System.out.println("\nOpção: ");
//            Scanner entrada = new Scanner(System.in);
//            Integer opcao = entrada.nextInt();
//            entrada.nextLine();
//            System.out.println("");
//            Boolean consultaEfetuada = false;
//            switch(opcao){
//                case 1:{
//                    consultaEfetuada = this.turmaView.listarAlunos();
//                    break;
//                }
//                case 2:{
//                    consultaEfetuada = this.turmaView.consultarSituacaoAluno();
//                    break;
//                }
//                case 3:{
//                    consultaEfetuada = this.disciplinaView.quantidadeTurmas();
//                    break;
//                }
//                case 4:{
//                    consultaEfetuada = this.professorView.quantidadeDisciplina();
//                    break;
//                }
//                default:{}
//            }
//            if (opcao < 1 || opcao > 4)
//                break;
//            if (!consultaEfetuada)
//                System.out.println("\nNÃO FORAM ENCONTRADOS RESULTADOS PARA ESTA CONSULTA!\n");    
//        }
//    }
        
    public static void main(String[] args) throws IOException{
        Integer opcao;
        Main main = new Main();
        Scanner entrada = new Scanner(System.in);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SistemaAcademicoPU");
        EntityManager em = emf.createEntityManager();
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
       // Professor professor = new Professor("123","Helcio","Computacao");
       // Disciplina disciplina = new Disciplina("Arquitetura","Coisas",12);
        Aluno aluno = new Aluno("22","Calouro");
        AlunoDaoImpl.getInstancia().persist(em,aluno);
        
        
        //em.close();
        emf.close();
        //main.persist(aluno1);
        //main.carregarArquivo();
//        while(true){
//            System.out.println("1 - CADASTRAR/LANÇAR");
//            System.out.println("2 - CONSULTAR");
//            System.out.println("3 - GERENCIAR TURMAS E DISCIPLINAS");
//            System.out.println("4 - SALVAR");
//            System.out.println("5 - SALVAR E SAIR");
//            
//            System.out.println("\nOpção:");
//            opcao = entrada.nextInt();
//            entrada.nextLine();
//            System.out.println("");
//            switch(opcao){
//                    case 1: {
//                        main.imprimirMenuCadastro();
//                        break;
//                    }
//                    case 2: {
//                        main.imprimirMenuConsultar();
//                        break;
//                    }
//                    case 3:{
//                        main.imprimirMenuGerenciamento();
//                        break;
//                    }
//                    case 4:{
//                        //main.salvarArquivo();
//                        System.out.println("\nINFORMAÇÕES SALVAS!\n");
//                        break;
//                    }
//                    case 5:{
//                        //main.salvarArquivo();
//                        System.out.println("\nINFORMAÇÕES SALVAS!\nENCERRANDO PROGRAMA.\n");
//                        System.exit(0);
//                    }
//                    default:{
//                        System.out.println("\nDIGITE UMA OPÇÃO VÁLIDA!\n");
//                    }
//            }
//        }    
    }
//    public void persist(Aluno object) {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SistemaAcademicoPU");
//        EntityManager em = emf.createEntityManager();
//        em.getTransaction().begin();
//        try {
//            em.persist(object);
//            em.getTransaction().commit();
//        } catch (Exception e) {
//            em.getTransaction().rollback();
//        } finally {
//            em.close();
//            emf.close();
//        }
//    }
}
