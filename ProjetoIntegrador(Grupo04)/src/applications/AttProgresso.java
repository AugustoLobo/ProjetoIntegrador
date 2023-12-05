package applications;

import entities.Acao;
import entities.Atividade;
import entities.Empresa;
import entities.Projeto;
import java.util.Scanner;

/**
 *
 * @author Augusto Lobo <augustolobo.contato@gmail.com>
 * @date 06/11/2023
 * @brief Class Case5
 */
public class AttProgresso {

    public static void attPorcentagem(Scanner ler, Empresa empresa) {
        int novaPorcentagem = 0;

        //Atualizar porcentagem da ação (entrada)
        System.out.println("\nInforme o nome da Ação que deseja atualizar: (Digite 0 para cancelar)");
        String nomeAcao = ler.nextLine();

        // Volta ao menu inicial
        if ("0".equals(nomeAcao)) {
            System.out.println(" ");
            return;
        }
        for (Projeto projeto : empresa.getProjetos()) {
            for (Atividade atividade : projeto.getAtividades()) {
                for (Acao acao : atividade.getAcoes()) {
                    if (acao.getNome().equalsIgnoreCase(nomeAcao)) {
                        do {
                            System.out.print("\nNovo percentual de conclusão da Ação: ");
                            novaPorcentagem = TratamentosEntradas.entradaInt(ler);
                            if (novaPorcentagem < 0 || novaPorcentagem > 100) {
                                System.out.println("O percetual deve ser de 0 à 100.");
                            }
                        } while (novaPorcentagem < 0 || novaPorcentagem > 100);

                        acao.attPorcentagem(novaPorcentagem);

                        // Atualizar porcentagem da atividade
                        atividade.attPorcentagem();

                        // Atualizar porcentagem do projeto
                        projeto.attPorcentagem();

                        System.out.println("\nPorcentagens atualizadas com sucesso!\n");
                        return;
                    }
                }
            }
        }
        //Mensagem para caso a ação digita não exista
        System.out.println("Ação não encontrada!\n");
    }
}
