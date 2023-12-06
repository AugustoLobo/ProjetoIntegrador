package applications;

import entities.Departamento;
import entities.Empresa;
import entities.Funcionario;
import java.util.Scanner;

/**
 *
 * @author Augusto Lobo <augustolobo.contato@gmail.com>
 * @date 07/11/2023
 * @brief Class CadastrarDpFun
 */
public class CadastrarDepartamento {

    public static Empresa cadastrarDepartamento(Empresa empresa, Scanner ler) {
        int numDepartamentos = 0;
        int numFuncionarios = 0;

        //Cadastrar departamentos
        do {
            System.out.print("Quantidade de departamentos à cadastrar (Digite 0 para cancelar): ");
            numDepartamentos = TratamentosEntradas.entradaInt(ler);

            //Volta ao menu inicial
            if (numDepartamentos == 0) {
                System.out.println(" ");
                return empresa;
            }

            //Limita a quuantidade de departamentos permitidos
            if (numDepartamentos <= 0 || numDepartamentos > 20) {
                System.out.println("Insira uma quantidade válida de departamentos.");
            }
            System.out.println(" ");
        } while (numDepartamentos <= 0 || numDepartamentos > 20);

        //Lastro de cadastro da quantidade desejada de departamantos
        for (int i = 0; i < numDepartamentos; i++) {
            String nomeDepartamento;
            boolean departamentoNomeValido;

            do {
                nomeDepartamento = TratamentosEntradas.entradaString("Nome do departamento #" + (i + 1) + ":", ler);
                departamentoNomeValido = !departamentoNomeExiste(empresa, nomeDepartamento);

                //Chamada do método para verificar se o departamento já existe
                if (!departamentoNomeValido) {
                    System.out.println("Já existe um departamento com esse nome. Por favor, escolha outro nome.");
                    System.out.println("  ");
                }

            } while (!departamentoNomeValido);

            Departamento departamento = new Departamento(nomeDepartamento);

            //Cadastrar funionários
            do {
                System.out.print("Quantidade de funcionários do departamento #" + (i + 1) + ":");
                numFuncionarios = TratamentosEntradas.entradaInt(ler);

                //Limita a quantidade de funcionários por departamentos
                if (numFuncionarios <= 0 || numFuncionarios > 20) {
                    System.out.println("Insira uma quantidade válida de funcionários.");
                }
                System.out.println(" ");
            } while (numFuncionarios <= 0 || numFuncionarios > 20);

            //Lastro de cadastro da quantidade de funcionários desejados 
            for (int j = 0; j < numFuncionarios; j++) {
                String nomeFuncionario = TratamentosEntradas.entradaString("Nome do funcionário #" + (j + 1) + ":", ler);

                int matriculaFuncionario;
                boolean matriculaValida;

                do {
                    System.out.print("Matrícula do funcionário #" + (j + 1) + ":");
                    matriculaFuncionario = TratamentosEntradas.entradaInt(ler);
                    matriculaValida = !matriculaExiste(empresa, matriculaFuncionario);

                    //Chamada do método para verificar se a matrícula já existe
                    if (!matriculaValida) {
                        System.out.println("Já existe um funcionário com essa matrícula.");
                        System.out.println(" ");
                    }
                } while (!matriculaValida);
                System.out.println(" ");

                Funcionario funcionario = new Funcionario(nomeFuncionario, matriculaFuncionario);

                //Adiciona funcionário ao departamento
                departamento.addFuncionario(funcionario);
            }

            //Adiciona departamento à empresa
            empresa.addDepartamento(departamento);
        }

        return empresa;
    }

    //Método auxiliar para verificar se o nome do departamento já existe
    private static boolean departamentoNomeExiste(Empresa empresa, String nomeDepartamento) {
        for (Departamento departamento : empresa.getDepartamentos()) {
            if (departamento.getNome().equalsIgnoreCase(nomeDepartamento)) {
                return true;
            }
        }
        return false;
    }

    //Método auxiliar para verificar se a matrícula já existe
    private static boolean matriculaExiste(Empresa empresa, int matricula) {
        for (Departamento departamento : empresa.getDepartamentos()) {
            for (Funcionario funcionario : departamento.getFuncionarios()) {
                if (funcionario.getMatricula() == matricula) {
                    return true;
                }
            }
        }
        return false;
    }

}