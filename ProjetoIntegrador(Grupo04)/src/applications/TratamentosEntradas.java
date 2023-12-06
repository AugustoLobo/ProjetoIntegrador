package applications;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 *
 * @author Augusto Lobo <augustolobo.contato@gmail.com>
 * @date 06/11/2023
 * @brief Class TratamentosEntradas
 */
public class TratamentosEntradas {

//Tratamentos para entradas de Inteiros
    public static int entradaInt(Scanner ler) {
        while (true) {
            try {
                return Integer.parseInt(ler.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Erro!");
                System.out.println("Insira um número inteiro.\n");
            }
        }
    }

    //Tratamento para entradas de Datas
    public static LocalDate entradaData(String prompt, DateTimeFormatter fmt, Scanner ler) {
        while (true) {
            try {
                System.out.println(prompt);
                String input = ler.nextLine();
                return LocalDate.parse(input, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            } catch (DateTimeParseException e) {
                System.out.println("Erro!");
                System.out.println("Insira a data no formato correto. (dd/MM/yyyy)\n");
            }
        }
    }

    //Tratamento para entradas de Strings
    public static String entradaString(String mensagem, Scanner ler) {
        String entrada;

        do {
            System.out.print(mensagem);
            entrada = ler.nextLine().trim(); // Remove espaços em branco no início e no final

            if (entrada.isEmpty()) {
                System.out.println("Erro!");
                System.out.println("Insira uma entrada válida.");
                System.out.println(" ");
            }

        } while (entrada.isEmpty());

        return entrada;
    }
}
