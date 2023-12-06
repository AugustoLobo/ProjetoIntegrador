package applications;

import entities.Acao;
import entities.Atividade;
import entities.Departamento;
import entities.Empresa;
import entities.Funcionario;
import entities.Projeto;
import static enums.Status.CONCLUIDA;
import static enums.Status.EM_ANDAMENTO;
import static enums.Status.NAO_INICIADA;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Augusto Lobo <augustolobo.contato@gmail.com>
 * @date 28/11/2023
 * @brief Class Tabela
 */
public class Tabela {

    private static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void imprimirTabelaPorStatus(Empresa empresa) {
        List<Acao> naoIniciadas = new ArrayList<>();
        List<Acao> emAndamento = new ArrayList<>();
        List<Acao> concluidas = new ArrayList<>();
        for (Projeto projeto : empresa.getProjetos()) {
            for (Atividade atividade : projeto.getAtividades()) {
                for (Acao acao : atividade.getAcoes()) {
                    switch (acao.getStatus()) {
                        case NAO_INICIADA:
                            naoIniciadas.add(acao);
                            break;
                        case EM_ANDAMENTO:
                            emAndamento.add(acao);
                            break;
                        case CONCLUIDA:
                            concluidas.add(acao);
                            break;
                        default:
                            break;
                    }
                }
            }
        }

        imprimirTabela("NÃO INICIADAS", naoIniciadas, empresa);
        imprimirTabela("EM ANDAMENTO", emAndamento, empresa);
        imprimirTabela("CONCLUÍDAS", concluidas, empresa);
    }

    private static void imprimirTabela(String titulo, List<Acao> acoes, Empresa empresa) {
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(titulo);
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-20s %-20s %-20s %-20s %-50s\n", "Nome", "Data Inicial", "Data Final", "Funcionário", "Progresso");

        for (Acao acao : acoes) {
            System.out.printf("%-20s %-20s %-20s %-20s %-50s\n",
                    acao.getNome(),
                    acao.getDataInicial().format(fmt),
                    acao.getDataFinal().format(fmt),
                    encontrarFuncionario(empresa, acao.getMatriculaFuncionario()),
                    acao.getPorcentagemConclusao() + "%");
        }

        System.out.println(" ");
    }

    //Método axiliar para encontrar funcionário
    private static String encontrarFuncionario(Empresa empresa, int matriculaFuncionario) {
        for (Departamento departamento : empresa.getDepartamentos()) {
            for (Funcionario funcionario : departamento.getFuncionarios()) {
                if (funcionario.getMatricula() == matriculaFuncionario) {
                    return "Nome: " + funcionario.getNome() + " (Matrícula: " + funcionario.getMatricula() + ", DP: " + departamento.getNome() + ")";
                }
            }
        }
        return "Funcionário não encontrado.";
    }
}
