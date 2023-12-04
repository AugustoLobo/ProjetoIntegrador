package applications;

import entities.Acao;
import entities.Atividade;
import entities.Departamento;
import entities.Empresa;
import entities.Funcionario;
import entities.Projeto;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Augusto Lobo <augustolobo.contato@gmail.com>
 * @date 06/11/2023
 * @brief Class Relatorio
 */
public class Relatorio {

    private static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void listarProjetos(Empresa empresa) {

        //Lista as informações do projeto
        System.out.println("Lista de Projetos de " + empresa.getNome() + ":");
        for (Projeto projeto : empresa.getProjetos()) {
            System.out.println("Nome: " + projeto.getNome());
            System.out.println("Descrição: " + projeto.getDescricao());
            System.out.println("Data Inicial: " + projeto.getDataInicial().format(fmt));
            System.out.println("Data Final: " + projeto.getDataFinal().format(fmt));
            System.out.println("Progresso: " + projeto.getPorcentagemConclusao() + "%\n");
            System.out.println(" Atividades:");

            //Lista as informações da atividade
            for (Atividade atividade : projeto.getAtividades()) {
                System.out.println("   Nome: " + atividade.getNome());
                System.out.println("   Data Inicial: " + atividade.getDataInicial().format(fmt));
                System.out.println("   Data Final: " + atividade.getDataFinal().format(fmt));
                System.out.println("   Progresso: " + atividade.getPorcentagemConclusao() + "%\n");
                System.out.println("    Ações:");

                //Lista as informações da ação
                for (Acao acao : atividade.getAcoes()) {
                    System.out.println("      Nome: " + acao.getNome());
                    System.out.println("      Data Inicial: " + acao.getDataInicial().format(fmt));
                    System.out.println("      Data Final: " + acao.getDataFinal().format(fmt));
                    System.out.println("      Funcionário Atribuído: " + encontrarFuncionario(empresa, acao.getMatriculaFuncionario()));
                    System.out.println("      Progresso: " + acao.getPorcentagemConclusao() + "%" + "(" + acao.getStatus() + ")");
                    mostrarDuracaoPorStatus(acao, projeto);

                    System.out.println();
                }
            }
        }
    }

    // Método auxiliar para encontrar o funcionário pela matrícula
    private static String encontrarFuncionario(Empresa empresa, int matriculaFuncionario) {
        for (Departamento departamento : empresa.getDepartamentos()) {
            for (Funcionario funcionario : departamento.getFuncionarios()) {
                if (funcionario.getMatricula() == matriculaFuncionario) {
                    return "Nome: " + funcionario.getNome() + " (Matrícula: " + funcionario.getMatricula() + ", Departamento: " + departamento.getNome() + ")";
                }
            }
        }
        return "Funcionário não encontrado.";
    }

    //Método para verificar quanto tempo/pecentual de quanto tempo cada ação ficou em cada status
    private static void mostrarDuracaoPorStatus(Acao acao, Projeto projeto) {
        LocalDate projetoInicio = projeto.getDataInicial();
        LocalDate projetoFim = projeto.getDataFinal();

        LocalDate acaoInicio = acao.getDataInicial();
        LocalDate acaoFim = acao.getDataFinal();

        long umDia = 1;

        long projetoDuracao = (projetoFim.toEpochDay() - projetoInicio.toEpochDay()) * umDia;

        long DiasNaoIniciada = (acaoInicio.toEpochDay() - projetoInicio.toEpochDay()) * umDia;
        long DiasEmAndamento = (acaoFim.toEpochDay() - acaoInicio.toEpochDay()) * umDia;
        long DiasConcluida = (projetoFim.toEpochDay() - acaoFim.toEpochDay()) * umDia;

        double naoIniciadaPercentagem = ((double) DiasNaoIniciada / projetoDuracao) * 100;
        double emAndamentoPercentagem = ((double) DiasEmAndamento / projetoDuracao) * 100;
        double concluidaPercentagem = ((double) DiasConcluida / projetoDuracao) * 100;

        System.out.println(" ");
        System.out.println("      Dias em 'NAO_INICIADA': " + DiasNaoIniciada + " dias (" + naoIniciadaPercentagem + "%)");
        System.out.println("      Dias em 'EM_ANDAMENTO': " + DiasEmAndamento + " dias (" + emAndamentoPercentagem + "%)");
        System.out.println("      Dias em 'CONCLUIDA': " + DiasConcluida + " dias (" + concluidaPercentagem + "%)");
    }

}
