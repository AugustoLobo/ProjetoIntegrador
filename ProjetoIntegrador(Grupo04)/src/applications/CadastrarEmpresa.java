package applications;

import entities.Empresa;
import java.util.Scanner;

/**
 *
 * @author Augusto Lobo <augustolobo.contato@gmail.com>
 * @date 24/11/2023
 * @brief Class CadastrarEmpresa
 */
public class CadastrarEmpresa {

    public static Empresa cadastrarEmpresa(Empresa empresa, Scanner ler) {

        //Cadastrar empresa
        String nomeEmpresa = TratamentosEntradas.entradaString("Nome da empresa (Digite 0 para cancelar): ", ler);
        System.out.println(" ");

        //Volta ao menu inicial
        if ("0".equals(nomeEmpresa)) {
            System.out.println(" ");
            return empresa;
        }

        empresa = new Empresa(nomeEmpresa);
        return empresa;
    }
}
