package entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Augusto Lobo <augustolobo.contato@gmail.com>
 * @date 06/11/2023
 * @brief Class Empresa
 */
public class Empresa {

    private String nome;
    private List<Departamento> departamentos;
    private List<Projeto> projetos;

    public Empresa(String nome) {
        this.nome = nome;
        this.departamentos = new ArrayList<>();
        this.projetos = new ArrayList<>();
    }

    public void addDepartamento(Departamento departamento) {
        departamentos.add(departamento);
    }

    public void addProjeto(Projeto projeto) {
        projetos.add(projeto);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Departamento> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<Departamento> departamentos) {
        this.departamentos = departamentos;
    }

    public List<Projeto> getProjetos() {
        return projetos;
    }

    public void setProjetos(List<Projeto> projetos) {
        this.projetos = projetos;
    }

}
