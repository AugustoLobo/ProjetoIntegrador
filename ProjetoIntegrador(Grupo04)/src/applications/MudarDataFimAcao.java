package applications;

import entities.Acao;
import entities.Atividade;
import entities.Empresa;
import entities.Projeto;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author Augusto Lobo <augustolobo.contato@gmail.com>
 * @date 06/11/2023
 * @brief Class MudarDataFimAcao
 */
public class MudarDataFimAcao {

    public static void mudarDataFimAcao(Empresa empresa, Scanner ler) {
        System.out.println("Digite o nome da ação: ");
        String nomeAcao = ler.nextLine();

        // Volta ao menu inicial
        if ("0".equals(nomeAcao)) {
            System.out.println(" ");
            return;
        }

        
        Acao acao = encontrarAcao(empresa, nomeAcao, ler);
        if (acao == null) {
            System.out.println("Ação não encontrada.");
            return;
        }

    }

    //Método auxiliar para encontrar a ação
    public static Acao encontrarAcao(Empresa empresa, String nomeAcao, Scanner ler) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate novaDataFim;
        
        for (Projeto projeto : empresa.getProjetos()) {
            for (Atividade atividade : projeto.getAtividades()) {
                for (Acao acao : atividade.getAcoes()) {
                    if (acao.getNome().equals(nomeAcao)) {
                        do {
                        novaDataFim = TratamentosEntradas.entradaData("Nova data final da ação (dd/MM/yyyy): ", fmt, ler);
                        if (novaDataFim.isBefore(acao.getDataInicial())) {
                            System.out.println("Data final da ação não pode ser anterior à data inicial.\n");
                        }
                       
                    } while (novaDataFim.compareTo(acao.getDataInicial()) < 0 );
                        
                        acao.setDataFinal(novaDataFim);

                        if (novaDataFim.compareTo(atividade.getDataFinal()) > 0) {
                            atividade.setDataFinal(novaDataFim);

                            if (novaDataFim.compareTo(projeto.getDataFinal()) > 0) {
                                projeto.setDataFinal(novaDataFim);
                            }
                        }
                        System.out.println("Data de fim da ação alterada com sucesso.\n");
                        return acao;
                    }
                }

            }
        }
        return null;
    }
}