package applications;

import entities.Acao;
import entities.Atividade;
import entities.Departamento;
import entities.Empresa;
import entities.Funcionario;
import entities.Projeto;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author Augusto Lobo <augustolobo.contato@gmail.com>
 * @date 06/11/2023
 * @brief Class CadastrarProjeto
 */
public class CadastrarProjeto {

    public static Projeto cadastrarProjetos(Empresa empresa, Scanner ler) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Projeto projeto = null;
        Atividade atividade = null;
        Funcionario funcionarioAtribuido = null;
        LocalDate dataAtual = LocalDate.now();
        LocalDate dataInicialProjeto = LocalDate.now();
        LocalDate dataFinalProjeto = LocalDate.now();
        LocalDate dataInicialAtividade = LocalDate.now();
        LocalDate dataFinalAtividade = LocalDate.now();
        LocalDate dataInicialAcao = LocalDate.now();
        LocalDate dataFinalAcao = LocalDate.now();

        //Cadastrar projeto
        int numProjetos = 0;
        do {
            System.out.println("Quantos projetos serão cadastrados? (Digite 0 para cancelar)");
            numProjetos = TratamentosEntradas.entradaInt(ler);

            //Volta ao menu inicial
            if (numProjetos == 0) {
                System.out.println(" ");
                return projeto;
            }

            //Limita a quantidade de projetos que podem ser cadastrados
            if (numProjetos <= 0 || numProjetos > 20) {
                System.out.println("Insira uma quantidade válida de projetos.");
            }
            System.out.println(" ");
        } while (numProjetos <= 0 || numProjetos > 20);

        //Lastro de cadastro da quantidade de projetos desejados 
        for (int i = 0; i < numProjetos; i++) {
            System.out.println("Cadastro do projeto #" + (i + 1) + ":");
            String nomeProjeto = TratamentosEntradas.entradaString("Nome: ", ler);
            String descricaoProjeto = TratamentosEntradas.entradaString("Breve descrição do projeto: ", ler);

            do {
                dataInicialProjeto = TratamentosEntradas.entradaData("Data inicial (dd/MM/yyyy): ", fmt, ler);

                //Verifica se a data infomada está no passado
                if (dataInicialProjeto.isBefore(dataAtual)) {
                    System.out.println("Você inseriu uma data no passado.");
                    System.out.println(" ");
                }
            } while (dataInicialProjeto.isBefore(dataAtual));

            do {
                dataFinalProjeto = TratamentosEntradas.entradaData("Data final (dd/MM/yyyy): ", fmt, ler);

                //Verifica se a data final do projeto é menor que a data inicial
                if (dataFinalProjeto.isBefore(dataInicialProjeto)) {
                    System.out.println("Data final do projeto não pode ser anterior à data inicial.\n");
                }
            } while (dataFinalProjeto.compareTo(dataInicialProjeto) < 0);

            int porcentagemConclusaoProjeto = 0;

            projeto = new Projeto(nomeProjeto, descricaoProjeto, dataInicialProjeto, dataFinalProjeto, porcentagemConclusaoProjeto);

            //Cadastrar atividade
            int numAtividades = 0;
            do {
                System.out.println("Quantas atividades o projeto terá?");
                numAtividades = TratamentosEntradas.entradaInt(ler);

                //Limita a quantidade de atividades que podem ser cadastradas
                if (numAtividades <= 0 || numAtividades > 20) {
                    System.out.println("Insira uma quantidade válida de atividades.");
                }
                System.out.println(" ");
            } while (numAtividades <= 0 || numAtividades > 20);

            //Lastro de cadastro da quantidade de atividade desejadas
            for (int j = 0; j < numAtividades; j++) {
                System.out.println("Cadastro da atividade #" + (j + 1) + ":");
                String nomeAtividade = TratamentosEntradas.entradaString("Nome: ", ler);

                //Verifica se a data inicial da atividade não é menor que a data inicial ou maior que a data final do projeto
                do {
                    dataInicialAtividade = TratamentosEntradas.entradaData("Data inicial (dd/MM/yyyy): ", fmt, ler);
                    if (dataInicialAtividade.isBefore(dataInicialProjeto)) {
                        System.out.println("Data inicial da atividade não pode ser anterior à data inicial do projeto.\n");
                    }
                    if (dataInicialAtividade.isAfter(dataFinalProjeto)) {
                        System.out.println("Data inicial da atividade não pode ser posterior à data final do projeto.\n");
                    }
                } while (dataInicialAtividade.compareTo(dataInicialProjeto) < 0 || dataInicialAtividade.compareTo(dataFinalProjeto) > 0);

                //Verificar se a data final da atividade não é menor que a data inicial da atividade ou maior que a data final do projeto
                do {
                    dataFinalAtividade = TratamentosEntradas.entradaData("Data final (dd/MM/yyyy): ", fmt, ler);
                    if (dataFinalAtividade.isBefore(dataInicialAtividade)) {
                        System.out.println("Data final da atividade não pode ser anterior à data inicial.\n");
                    }
                    if (dataFinalAtividade.isAfter(dataFinalProjeto)) {
                        System.out.println("Data final da atividade não pode ser posterior à data final do projeto.\n");
                    }
                } while (dataFinalAtividade.compareTo(dataInicialAtividade) < 0 || dataFinalAtividade.compareTo(dataFinalProjeto) > 0);

                int porcentagemConclusaoAtividade = 0;

                atividade = new Atividade(nomeAtividade, dataInicialAtividade, dataFinalAtividade, porcentagemConclusaoAtividade);

                //Cadastrar ação
                int numAcoes = 0;
                do {
                    System.out.println("Quantas ações a atividade terá?");
                    numAcoes = TratamentosEntradas.entradaInt(ler);

                    //Limita a quantidade de ações que podem ser cadastradas
                    if (numAcoes <= 0 || numAcoes > 20) {
                        System.out.println("Insira uma quantidade válida de ações.");
                    }
                    System.out.println(" ");
                } while (numAcoes <= 0 || numAcoes > 20);

                //Cadastra a quantidade de ações desejadas
                for (int k = 0; k < numAcoes; k++) {
                    System.out.println("Cadastro da ação #" + (k + 1) + ":");
                    String nomeAcao;
                    boolean acaoNomeValido;

                    do {
                        nomeAcao = TratamentosEntradas.entradaString("Nome: ", ler);
                        acaoNomeValido = !acaoNomeExiste(empresa, nomeAcao) && !nomeAcaoJaCadastrado(atividade, nomeAcao);

                        //Chama o método para verificar se o nome da ação já existe
                        if (!acaoNomeValido) {
                            System.out.println("Já existe uma ação com esse nome. Por favor, escolha outro nome.");
                            System.out.println("  ");
                        }

                    } while (!acaoNomeValido);

                    //Verifica se a data inicial da ação não é menor que a data inicial ou maior que a data final da atividade
                    do {
                        dataInicialAcao = TratamentosEntradas.entradaData("Data inicial (dd/MM/yyyy): ", fmt, ler);
                        if (dataInicialAcao.isBefore(dataInicialAtividade)) {
                            System.out.println("Data inicial da ação não pode ser anterior à data inicial da atividade.\n");
                        }
                        if (dataInicialAcao.isAfter(dataFinalAtividade)) {
                            System.out.println("Data inicial da ação não pode ser posterior à data final da atividade.\n");
                        }
                    } while (dataInicialAcao.compareTo(dataInicialAtividade) < 0 || dataInicialAcao.compareTo(dataFinalAtividade) > 0);

                    //Verificar se a data final da ação não é menor que a data inicial da ação ou maior que a data final da atividade
                    do {
                        dataFinalAcao = TratamentosEntradas.entradaData("Data final (dd/MM/yyyy): ", fmt, ler);
                        if (dataFinalAcao.isBefore(dataInicialAcao)) {
                            System.out.println("Data final da ação não pode ser anterior à data inicial.\n");
                        }
                        if (dataFinalAcao.isAfter(dataFinalAtividade)) {
                            System.out.println("Data final da ação não pode ser posterior à data final da atividade.");;
                        }
                    } while (dataFinalAcao.compareTo(dataInicialAcao) < 0 || dataFinalAcao.compareTo(dataFinalAtividade) > 0);

                    System.out.println("  ");
                    System.out.println("Funcionários disponíveis para atribuição:");

                    //Mostrar lista de funionários cadastrados
                    for (Departamento departamento : empresa.getDepartamentos()) {
                        for (Funcionario funcionario : departamento.getFuncionarios()) {
                            System.out.println("Nome: " + funcionario.getNome() + " (Matrícula: " + funcionario.getMatricula() + ", Departamento: " + departamento.getNome() + ")");
                        }
                    }

                    int matriculaFuncionario;
                    do {
                        // Pedir a matrícula do funcionário para atribuição
                        System.out.println("Digite a matrícula do funcionário para atribuir à ação:");
                        matriculaFuncionario = TratamentosEntradas.entradaInt(ler);
                        System.out.println(" ");

                        // Verificar se a matrícula fornecido corresponde a um funcionário cadastrado
                        funcionarioAtribuido = encontrarFuncionario(empresa, matriculaFuncionario);
                        if (funcionarioAtribuido == null) {
                            System.out.println("Matrícula de funcionário inválido.\n");
                        }
                    } while (funcionarioAtribuido == null);
                    int porcentagemConclusaoAcao = 0;

                    Acao acao = new Acao(nomeAcao, dataInicialAcao, dataFinalAcao, porcentagemConclusaoAcao, matriculaFuncionario);
                    //Adicionar a ação à atividade
                    atividade.addAcao(acao);
                }
                //Adicionar a aticidade ao projeto
                projeto.addAtividade(atividade);
            } // Adicionar o projeto à empresa
            empresa.addProjeto(projeto);

        }
        return projeto;
    }

    //Método auxiliar para verificar se o nome da ação já existe
    private static boolean acaoNomeExiste(Empresa empresa, String nomeAcao) {
        for (Projeto projeto : empresa.getProjetos()) {
            for (Atividade atividade : projeto.getAtividades()) {
                for (Acao acao : atividade.getAcoes()) {
                    if (acao.getNome().equalsIgnoreCase(nomeAcao)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean nomeAcaoJaCadastrado(Atividade atividade, String nomeAcao) {
        for (Acao acao : atividade.getAcoes()) {
            if (acao.getNome().equalsIgnoreCase(nomeAcao)) {
                return true;
            }
        }
        return false;
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
