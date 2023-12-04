package entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Augusto Lobo <augustolobo.contato@gmail.com>
 * @date 06/11/2023
 * @brief Class Projeto
 */
public class Projeto {

    private String nome;
    private String descricao;
    private LocalDate dataInicial;
    private LocalDate dataFinal;
    private int porcentagemConclusao;
    private List<Atividade> atividades;

    public Projeto(String nome, String descricao, LocalDate dataInicial, LocalDate dataFinal, int porcentagemConclusao) {
        this.nome = nome;
        this.descricao = descricao;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.porcentagemConclusao = porcentagemConclusao;
        this.atividades = new ArrayList<>();
    }

    public void addAtividade(Atividade atividade) {
        atividades.add(atividade);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(LocalDate dataInicial) {
        this.dataInicial = dataInicial;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

    public int getPorcentagemConclusao() {
        return porcentagemConclusao;
    }

    public void setPorcentagemConclusao(int porcentagemConclusao) {
        this.porcentagemConclusao = porcentagemConclusao;
    }

    public List<Atividade> getAtividades() {
        return atividades;
    }

    public void setAtividades(List<Atividade> atividades) {
        this.atividades = atividades;
    }

    public void attPorcentagem() {
        int totalAtividades = atividades.size();
        int porcentagemTotal = 0;

        for (Atividade atividade : atividades) {
            porcentagemTotal += atividade.getPorcentagemConclusao();
        }

        if (totalAtividades > 0) {
            porcentagemConclusao = porcentagemTotal / totalAtividades;
        }
    }
}
