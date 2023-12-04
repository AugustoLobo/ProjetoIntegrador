package entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Augusto Lobo <augustolobo.contato@gmail.com>
 * @date 06/11/2023
 * @brief Class Atividade
 */
public class Atividade {

    private String nome;
    private LocalDate dataInicial;
    private LocalDate dataFinal;
    private int porcentagemConclusao;
    private List<Acao> acoes;

    public Atividade(String nome, LocalDate dataInicial, LocalDate dataFinal, int porcentagemConclusao) {
        this.nome = nome;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.porcentagemConclusao = porcentagemConclusao;
        this.acoes = new ArrayList<>();
    }

    public void addAcao(Acao acao) {
        acoes.add(acao);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public List<Acao> getAcoes() {
        return acoes;
    }

    public void setAcoes(List<Acao> acoes) {
        this.acoes = acoes;
    }

    public void attPorcentagem() {
        int totalAcoes = acoes.size();
        int porcentagemTotal = 0;

        for (Acao acao : acoes) {
            porcentagemTotal += acao.getPorcentagemConclusao();
        }

        if (totalAcoes > 0) {
            porcentagemConclusao = porcentagemTotal / totalAcoes;
        }
    }
}
