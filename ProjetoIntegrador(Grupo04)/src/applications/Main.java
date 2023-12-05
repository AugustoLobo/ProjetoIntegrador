package applications;

import entities.Empresa;
import java.util.Scanner;

public class Main {

    /**
     *
     * @author Augusto Lobo <augustolobo.contato@gmail.com>
     * @date 06/11/2023
     * @brief Main Class Main
     */
    private static boolean empresaCadastrada = false;

    private static String login() {
        Scanner ler = new Scanner(System.in);

        String username;
        String password;

        //Lastro do login
        do {
            System.out.println("Login: ");
            username = ler.nextLine();
            System.out.println("Senha: ");
            password = ler.nextLine();
            System.out.println(" ");

            if (username.equals("admin") && password.equals("123")) {
                return "admin";
            } else if (username.equals("lider") && password.equals("123")) {
                return "Lider";
            } else if (username.equals("funcionario") && password.equals("123")) {
                return "Funcionario";
            } else if (username.equals("sair") && password.equals("sair")) {
                System.out.println("Encerrando programa!");
                ler.close();
                System.exit(0);
            } else {
                System.out.println("Login ou senha inválidos. Por favor, tente novamente.\n");
            }
        } while (true);
    }

    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        Empresa empresa = new Empresa(" ");
        String tipoUsuario;
        boolean voltar;

        do {
            tipoUsuario = login();
            voltar = false;

            switch (tipoUsuario) {
                case "admin":
                    //Chama o método de admin
                    empresa = admin(empresa, ler);
                    break;
                case "Lider":
                    //Chama o método de líder
                    empresa = lider(empresa, ler);
                    break;
                case "Funcionario":
                    //Chama o método de funcionário
                    empresa = funcionario(empresa, ler);
                    break;
            }
        } while (!voltar);

    }

    //Método para caso tenha logado como admin
    private static Empresa admin(Empresa empresa, Scanner ler) {
        int opcao;
        boolean voltar = false;

        do {
            System.out.println("~Menu Seleção~");
            System.out.println("1 - Cadastrar empresa");
            System.out.println("2 - Cadastrar departamentos/funcionários");
            System.out.println("0 - Voltar");
            System.out.println("Escolha uma opção:");

            opcao = TratamentosEntradas.entradaInt(ler);
            System.out.println(" ");

            switch (opcao) {
                case 1:
                    if (!Main.isEmpresaCadastrada()) {
                        empresa = CadastrarEmpresa.cadastrarEmpresa(empresa, ler);
                        empresaCadastrada = true;
                    } else {
                        System.out.println("\nEmpresa já cadastrada. Não é possível cadastrar novamente.\n");
                    }
                    break;
                case 2:
                    if (Main.isEmpresaCadastrada()) {
                        empresa = CadastrarDpFun.cadastrarDepartamento(empresa, ler);
                    } else {
                        System.out.println("\nNenhuma empresa cadastrada. Cadastre a empresa primeiro.\n");
                    }
                    break;
                case 0:
                    System.out.println("Voltando ao login.\n");
                    voltar = true;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    System.out.println(" ");
            }
        } while (!voltar);
        return empresa;
    }

    //Método para caso tenha logado como líder
    private static Empresa lider(Empresa empresa, Scanner ler) {
        int opcao;
        boolean voltar = false;

        do {

            //Menu com as opções do switch-case
            System.out.println("~Menu Seleção~");
            System.out.println("1 - Cadastrar projetos/atividades/ações");
            System.out.println("2 - Mudar data final da ação");
            System.out.println("3 - Atribuir outro funcionário a uma ação");
            System.out.println("4 - Relatório");
            System.out.println("0 - Voltar");
            System.out.println("Escolha uma opção:");

            opcao = TratamentosEntradas.entradaInt(ler);
            System.out.println(" ");

            switch (opcao) {
                case 1: // Cadastrar projetos, atividades e ações
                    //Limitador para só poder cadastrar projeto caso tenha pelo menos um departamento cadastrado 
                    if (empresa != null && !empresa.getDepartamentos().isEmpty()) {
                        CadastrarProjeto.cadastrarProjetos(empresa, ler);
                        Relatorio.listarProjetos(empresa);
                    } else {
                        System.out.println("Nenhum departamento cadastrado. Cadastre um departamento primeiro.\n");
                    }
                    break;
                case 2: // Mudar a data final de uma ação
                    //Limitador para só poder mudar data final caso tenha pelo menos um projeto cadastrado
                    if (empresa != null && !empresa.getProjetos().isEmpty()) {
                        MudarDataFimAcao.mudarDataFimAcao(empresa, ler);
                    } else {
                        System.out.println("Nenhum projeto cadastrado. Cadastre um projeto primeiro.\n");
                    }
                    break;
                case 3: // Atribuir outro funcionário para uma ação
                    //Limitador para só poder mudar o funcionário caso tenha pelo menos um projeto cadastrado
                    if (empresa != null && !empresa.getProjetos().isEmpty()) {
                        MudarFuncionario.atribuirFuncionarioAcao(empresa, ler);
                    } else {
                        System.out.println("Nenhum projeto cadastrado. Cadastre um projeto primeiro.\n");
                    }
                    break;
                case 4: // Relatório
                    //Limitador para só poder ver o relatório caso tenha pelo menos um projeto cadastrado
                    if (empresa != null && !empresa.getProjetos().isEmpty()) {
                        Relatorio.listarProjetos(empresa);
                    } else {
                        System.out.println("Nenhum projeto cadastrado. Cadastre um projeto primeiro.\n");
                    }
                    break;
                case 0://Voltar
                    System.out.println("Voltando ao login.\n");
                    voltar = true;
                    break;

                default://Caso opção escolhida não esteja no menu
                    System.out.println("Opção inválida. Tente novamente.");
                    System.out.println(" ");
            }
        } while (!voltar);
        return empresa;

    }

    //Método para caso tenha logado como funcionário
    private static Empresa funcionario(Empresa empresa, Scanner ler) {
        int opcao;
        boolean voltar = false;
        do {

            //Menu com as opções do switch-case
            System.out.println("~Menu Seleção~");
            System.out.println("1 - Atualizar progresso de uma ação");
            System.out.println("2 - Tabela");
            System.out.println("3 - Relatório");
            System.out.println("0 - Voltar");
            System.out.println("Escolha uma opção:");

            opcao = TratamentosEntradas.entradaInt(ler);
            System.out.println(" ");

            switch (opcao) {

                case 1: // Atualizar progresso
                    //Limitador para só poder atualizar progresso caso tenha pelo menos um projeto cadastrado
                    if (empresa != null && !empresa.getProjetos().isEmpty()) {
                        AttProgresso.attPorcentagem(ler, empresa);
                    } else {
                        System.out.println("Nenhum projeto cadastrado. Cadastre um projeto primeiro.\n");
                    }
                    break;
                case 2: // Tabela
                    //Limitador para só poder ver a tabela caso tenha pelo menos um projeto cadastrado
                    if (empresa != null && !empresa.getProjetos().isEmpty()) {
                        Tabela.imprimirTabelaPorStatus(empresa);
                    } else {
                        System.out.println("Nenhum projeto cadastrado. Cadastre um projeto primeiro.\n");
                    }
                    break;
                case 3: // Relatório
                    //Limitador para só poder ver o relatório caso tenha pelo menos um projeto cadastrado
                    if (empresa != null && !empresa.getProjetos().isEmpty()) {
                        Relatorio.listarProjetos(empresa);
                    } else {
                        System.out.println("Nenhum projeto cadastrado. Cadastre um projeto primeiro.\n");
                    }
                    break;
                case 0://Sair
                    System.out.println("Voltando ao login.\n");
                    voltar = true;
                    break;

                default://Caso opção escolhida não esteja no menu
                    System.out.println("Opção inválida. Tente novamente.");
                    System.out.println(" ");
            }
        } while (!voltar);
        return empresa;

    }

    public static boolean isEmpresaCadastrada() {
        return empresaCadastrada;
    }

}
