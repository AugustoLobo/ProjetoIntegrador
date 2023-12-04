package entities;

import enums.Status;
import java.time.LocalDate;

/**
 *
 * @author Augusto Lobo <augustolobo.contato@gmail.com>
 * @date 06/11/2023
 * @brief Class Acao
 */
public class Acao {

    private String nome;
    private LocalDate dataInicial;
    private LocalDate dataFinal;
    private int porcentagemConclusao;
    private int matriculaFuncionario;
    private Status status;

    public Acao(String nome, LocalDate dataInicial, LocalDate dataFinal, int porcentagemConclusao, int matriculaFuncionario) {
        this.nome = nome;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.porcentagemConclusao = porcentagemConclusao;
        this.matriculaFuncionario = matriculaFuncionario;
        this.status = Status.NAO_INICIADA;
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

    public int getMatriculaFuncionario() {
        return matriculaFuncionario;
    }

    public void setMatriculaFuncionario(int matriculaFuncionario) {
        this.matriculaFuncionario = matriculaFuncionario;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void attPorcentagem(int novaPorcentagem) {
        this.porcentagemConclusao = novaPorcentagem;
        if (novaPorcentagem > 0 && novaPorcentagem < 100) {
            this.status = Status.EM_ANDAMENTO;
        } else if (novaPorcentagem == 100) {
            this.status = Status.CONCLUIDA;
        } else {
            System.out.println("Entrada inválida!");
            System.out.println("Entrada deve ser de 0 à 100");
        }
        
    }
}
