package view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import model.dao.AlunoDaoImpl;
import model.dao.AulaDaoImpl;
import model.dao.Dao;
import model.dao.DisciplinaDaoImpl;
import model.dao.ProfessorDaoImpl;
import model.dao.TurmaDaoImpl;
import model.pojo.Aluno;
import model.pojo.Atividade;
import model.pojo.Aula;
import model.pojo.Disciplina;
import model.pojo.Falta;
import model.pojo.Nota;
import model.pojo.Professor;
import model.pojo.Turma;

public class TurmaView {    
    
    private static Scanner scanner = new Scanner (System.in);
    private static Dao alunoDao = AlunoDaoImpl.getInstancia();
    private static Dao aulaDao = AulaDaoImpl.getInstancia();
    private static Dao disciplinaDao = DisciplinaDaoImpl.getInstancia();
    private static Dao professorDao = ProfessorDaoImpl.getInstancia();
    private static Dao turmaDao = TurmaDaoImpl.getInstancia();
    
    public Boolean cadastrar () {
        System.out.println("CADASTRO DE TURMAS");
        System.out.println("Disciplina (ID: nome):");
        Disciplina disciplina = (Disciplina) this.obterCadastrado(disciplinaDao);
        if (disciplina == null)
            return false;
        System.out.println("Professor (ID: CPF):");
        Professor professor = this.obterCadastrado(disciplina);
        if (professor == null)
            return false;
        
        System.out.println("\nCadastre uma nova turma:\n");
        String id = this.validarId();
        if (id == null)
            return false;
        System.out.println("Ano: ");
        Integer ano = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Período: ");
        Integer periodo = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Número de vagas: ");
        Integer numeroDeVagas = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\nPara as próximas etapas do cadastro, "
                + "entre com o código identificador (ID) do item procurado.");
        
        System.out.println("\nDetermine as aulas a serem adicionadas.");
        System.out.println("(Novas aulas podem ser adicionadas a qualquer momento a partir da opção"
                + " \"GERENCIAR TURMAS E DISCIPLINAS\" no menu principal)");
        List<Aula> listaAula = (List<Aula>) this.montarListaDeCadastrados(disciplina, aulaDao);
        
        System.out.println("\nDetermine as alunos (ID: CPF) a serem matriculados.");
        System.out.println("(Novos alunos podem ser matriculados a qualquer momento a partir da opção"
                + " \"GERENCIAR TURMAS E DISCIPLINAS\" no menu principal)");
        List<Aluno> listaAluno = (List<Aluno>) this.montarListaDeCadastrados(disciplina, alunoDao);
        Turma turma = new Turma (id, ano, periodo, numeroDeVagas, disciplina, professor, listaAula, listaAluno);
        
        disciplina.getTurma().add(turma);
        return turmaDao.inserir(turma);
    }
    
    public String validarId () {
        while (true) {
            System.out.println("ID (\"cancelar\" para cancelar): ");
            String id = scanner.nextLine();
            if (id.equals("cancelar"))
                break;
            if (turmaDao.indice(id) <= -1)
                return id;
            else
                System.out.println("\nUMA TURMA COM ESTE ID JÁ ESTÁ CADASTRADA! TENTE NOVAMENTE!\n");
        }
        return null;
    }
    
    public Boolean matricularAluno(){
        System.out.println("MATRÍCULA DE ALUNOS\nMatricule um aluno:\n");
        System.out.println("Informe o CPF do aluno: ");
        Aluno aluno = (Aluno) alunoDao.obter(scanner.nextLine());
        if (aluno != null) {
            System.out.println("Informe a turma na qual será efetuada a matrícula: ");
            Turma turma = (Turma) turmaDao.obter(scanner.nextLine());
            if(turma != null)
                if (turma.adicionarAluno(aluno))
                    return true;
                else
                    if (turma.getNumeroDeVagas() > turma.getAluno().size())
                        System.out.println("\nESTE(A) ALUNO(A) JÁ ESTÁ MATRICULADO(A) EM UMA TURMA"
                                + " DESTA DISCIPLINA!");
                    else
                        System.out.println("\nNÃO HÁ VAGAS DISPONÍVEIS NESTA TURMA!");
            else
                System.out.println("\nTURMA NÃO ENCONTRADA!");
        }
        else
            System.out.println("\nALUNO NÃO ENCONTRADO!");
        return false;
    }
    
    public Boolean listarAlunos () {
        Boolean sucesso = false;
        System.out.println("\nIdentifique a turma procurada: ");
        System.out.println("* Disciplina: ");
        String disciplina = scanner.nextLine();
        System.out.println("* Ano: ");
        Integer ano = scanner.nextInt();
        scanner.nextLine();
        System.out.println("* Período: ");
        Integer periodo = scanner.nextInt();
        scanner.nextLine();
        List<Turma> listaTurma = (List<Turma>) turmaDao.obterTodos();
        if (listaTurma.size() > 0)
            for (Turma turma: listaTurma) {
                if (turma.getDisciplina().getNome().equals(disciplina))
                    if (turma.getAno().equals(ano))
                        if (turma.getPeriodo().equals(periodo)) {
                            System.out.println("\nTURMA " + turma.getId1() + ":");
                            if (turma.todasAsNotasLancadas() && turma.faltasLancadas())
                                for (Aluno aluno: turma.getAluno())
                                    this.imprimirSituacaoAluno(aluno, turma);
                            else
                                System.out.println("AS NOTAS/FALTAS CORRESPONDENTES À TURMA NÃO"
                                        + " FORAM LANÇADAS PARA TODAS AS ATIVIDADES/ALUNOS!\n");
                            sucesso = true;
                        }
            }
        return sucesso;
    }
    
    public Boolean consultarSituacaoAluno(){
        System.out.println("Informe o CPF do aluno: ");
        Aluno aluno = (Aluno) alunoDao.obter(scanner.nextLine());
        if (aluno != null) {
            System.out.println("Informe o nome da disciplina: ");
            Disciplina disciplina = (Disciplina) disciplinaDao.obter(scanner.nextLine());
            if(disciplina != null){
                Turma turma = disciplina.turmaQueContem(aluno);
                if (turma != null) {
                    if (turma.todasAsNotasLancadas() && turma.faltasLancadas()) {
                        this.imprimirSituacaoAluno(aluno, turma);
                        Collections.sort(aluno.getFalta(), new Falta());
                        Falta faltaConsultada = aluno.getFalta().get(Collections.binarySearch(aluno.getFalta(),
                                new Falta (null, null, turma), new Falta()));
                        if(((faltaConsultada.getFalta()/disciplina.getCargaHoraria())<=0.25)
                                && (aluno.notaFinal(turma)>=6))
                            System.out.println("\nALUNO APROVADO!\n");
                        else
                            System.out.println("\nALUNO REPROVADO!\n");
                    }
                    else
                        System.out.println("\nO ALUNO SOLICITADO NÃO TEVE SEU REGISTRO DE FALTAS"
                                + " ATUALIZADO OU SUAS NOTAS LANÇADAS EM TODAS AS ATIVIDADES"
                                + " PARA ESTA DISCIPLINA.\n");
                    return true;
                }
            }
        }
        return false;
    }
    
    public void imprimirSituacaoAluno (Aluno aluno, Turma turma) {
        System.out.println("\nAluno: " + aluno.getNome());
        System.out.println("Notas:");
        for (Atividade atividade: turma.getAtividade()) {
            Collections.sort(atividade.getNota(), new Nota());
            Integer indiceNota = Collections.binarySearch(atividade.getNota(),
                    new Nota (null, null, aluno, null), new Nota());
            System.out.println(" *" + atividade.getNome() + ": " + 
                    atividade.getNota().get(indiceNota).getNota());
        }
        System.out.println(" *FINAL: " + aluno.notaFinal(turma));
        Collections.sort(aluno.getFalta(), new Falta());
        Integer indiceFalta = Collections.binarySearch(aluno.getFalta(),
                    new Falta (null, null, turma), new Falta());
        System.out.println("Faltas: " + aluno.getFalta().get(indiceFalta).getFalta());
    }
    
    public Object obterCadastrado (Dao dao) {    
        while (true) {
            System.out.println("ID (\"cancelar\" para cancelar): ");
            String entrada = scanner.nextLine();
            if (entrada.equals("cancelar"))
                break;
            Object objeto = dao.obter(entrada);
            if (objeto != null)
                return objeto;
            else
                System.out.println("\nITEM NÃO CADASTRADO! TENTE NOVAMENTE.\n");
        }
        return null;
    }
    
    public Professor obterCadastrado (Disciplina disciplina) {
        Professor professor = null;
        while ((professor = (Professor) this.obterCadastrado(professorDao)) != null) {
            if (professor.getDisciplina().contains(disciplina))
                break;
            else
                System.out.println("\nESTE PROFESSOR NÃO ESTÁ VINCULADO À ESTA DISCIPLINA! "
                        + "TENTE NOVAMENTE!");
        }
        return professor;
    }
    
    public List<? extends Object> montarListaDeCadastrados (Disciplina disciplina, Dao dao) {
        List<Object> listaObjeto = new ArrayList<>();
        while (true) {
            Boolean possivelAdicionar = true;
            System.out.println("\nContinuar? ");
            System.out.println("Digite \"sim\" para continuar ou qualquer outro para não: ");
            if (scanner.nextLine().equals("sim")) {
                Object objeto = this.obterCadastrado(dao);
                if (objeto != null) {
                    if (dao instanceof AlunoDaoImpl) {
                        Aluno aluno = (Aluno) objeto;
                        if (disciplina.turmaQueContem(aluno) != null) {
                            System.out.println("\nESTE ALUNO JÁ ESTÁ MATRICULADO EM UMA TURMA"
                                    + " DESTA DISCIPLINA!");
                            possivelAdicionar = false;
                        }
                    }
                }
                else
                    possivelAdicionar = false;
                if (possivelAdicionar)
                    listaObjeto.add(objeto);
            }
            else
                break;
        }
        return listaObjeto;
    }
    
    public Boolean atribuirAula () {
        System.out.println("Informe o ID da turma: ");
        Turma turma = (Turma) turmaDao.obter(scanner.nextLine());
        if(turma != null) {
            System.out.println("Informe o ID da aula a ser atribuída à turma: ");
            Aula aula = (Aula) aulaDao.obter(scanner.nextLine());
            if (aula != null)
                if (turma.adicionarAula(aula))
                    return true;
                else
                    System.out.println("\nESTA AULA JÁ FOI ATRIBUÍDA À ESTA TURMA ANTERIORMENTE!");
            else
                System.out.println("\nREGISTRO DE AULA NÃO ENCONTRADO!");
        }
        else
            System.out.println("\nTURMA NÃO ENCONTRADA!");
        return false;
    }
}
