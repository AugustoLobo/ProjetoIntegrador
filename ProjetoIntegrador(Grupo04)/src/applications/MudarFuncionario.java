package applications;

import entities.Acao;
import entities.Atividade;
import entities.Departamento;
import entities.Empresa;
import entities.Funcionario;
import entities.Projeto;
import java.util.Scanner;

/**
 *
 * @author Augusto Lobo <augustolobo.contato@gmail.com>
 * @date 08/11/2023
 * @brief Class MudarFuncionario
 */
public class MudarFuncionario {

    public static void atribuirFuncionarioAcao(Empresa empresa, Scanner ler) {
        System.out.println("Digite o nome da ação (Digite 0 para cancelar): ");
        String nomeAcao = ler.nextLine();

        // Volta ao menu inicial
        if ("0".equals(nomeAcao)) {
            System.out.println(" ");
            return;
        }

        //Chama método auxiliar
        Acao acao = encontrarAcao(empresa, nomeAcao);
        if (acao == null) {
            System.out.println("Ação não encontrada.");
            return;
        }

        // Listar os funcionários disponíveis para atribuição
        System.out.println("\nFuncionários disponíveis para atribuição:");

        for (Departamento departamento : empresa.getDepartamentos()) {
            for (Funcionario funcionario : departamento.getFuncionarios()) {
                System.out.println("Matrícula: " + funcionario.getMatricula() + ", Nome: " + funcionario.getNome());
            }
        }

        // Pedir a matrícula do funcionário para atribuição
        System.out.println("Digite a matrícula do funcionário para atribuir à ação:");
        int matriculaFuncionario = ler.nextInt();
        ler.nextLine();

        // Verificar se a matrícula fornecido corresponde a um funcionário cadastrado
        Funcionario funcionarioAtribuido = encontrarFuncionario(empresa, matriculaFuncionario);
        if (funcionarioAtribuido == null) {
            System.out.println("ID de funcionário inválido. Atribuição cancelada.");
            return;
        }

        // Atribuir o funcionário à ação
        acao.setMatriculaFuncionario(matriculaFuncionario);

        System.out.println("\nFuncionário atribuído à ação com sucesso.");
        System.out.println(" ");
    }
    
    //Método auxiliar para encontrar ação
    public static Acao encontrarAcao(Empresa empresa, String nomeAcao) {
        for (Projeto projeto : empresa.getProjetos()) {
            for (Atividade atividade : projeto.getAtividades()) {
                for (Acao acao : atividade.getAcoes()) {
                    if (acao.getNome().equals(nomeAcao)) {
                        return acao;
                    }
                }
            }
        }
        return null;
    }
    
    //Método auxiliar para encontrar funcionário
    public static Funcionario encontrarFuncionario(Empresa empresa, int matriculaFuncionario) {
        for (Departamento departamento : empresa.getDepartamentos()) {
            for (Funcionario funcionario : departamento.getFuncionarios()) {
                if (funcionario.getMatricula() == matriculaFuncionario) {
                    return funcionario;
                }
            }
        }
        return null;
    }
}