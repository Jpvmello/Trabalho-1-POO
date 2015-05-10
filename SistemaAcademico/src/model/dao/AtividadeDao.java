package model.dao;

import java.util.List;
import model.pojo.Atividade;
import model.pojo.Nota;

/**
 *
 * @author Mônicka
 */
public interface AtividadeDao {
    
    List<Atividade> obterTodas();
    Atividade obterAtividade(String id);
    int indiceAtividade(String id);
    Boolean remover(Atividade atividade);
    Boolean salvar(Atividade atividade);
    void constarLancamentoDeNotas(Atividade atividade);
    Boolean adicaoValida(Atividade atividade, Nota nota);
    Boolean adicionaNota(Atividade atividade, Nota nota);
    Nota retornaNota (Atividade atividade, Nota nota);
}