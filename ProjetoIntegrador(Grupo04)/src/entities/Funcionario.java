package entities;

/**
 *
 * @author Augusto Lobo <augustolobo.contato@gmail.com>
 * @date 07/11/2023
 * @brief Class Funcionario
 */
public class Funcionario {

    private String nome;
    private int matricula;

    public Funcionario(String nome, int matricula) {
        this.nome = nome;
        this.matricula = matricula;
    }

    public int getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
