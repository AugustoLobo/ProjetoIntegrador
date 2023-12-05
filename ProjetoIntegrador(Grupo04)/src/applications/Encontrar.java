package applications;

import entities.Acao;
import entities.Atividade;
import entities.Departamento;
import entities.Empresa;
import entities.Funcionario;
import entities.Projeto;

public class Encontrar {

    public static Departamento encontrarDepartamento(Empresa empresa, String nomeDepartamento) {
        for (Departamento departamento : empresa.getDepartamentos()) {
            if (departamento.getNome().equals(nomeDepartamento)) {
                return departamento;
            }
        }
        return null;
    }

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

    public static Projeto encontrarProjeto(Empresa empresa, String nomeProjeto) {
        for (Projeto projeto : empresa.getProjetos()) {
            if (projeto.getNome().equals(nomeProjeto)) {
                return projeto;
            }
        }
        return null;
    }

    public static Atividade encontrarAtividade(Empresa empresa, String nomeAtividade) {
        for (Projeto projeto : empresa.getProjetos()) {
            for (Atividade atividade : projeto.getAtividades()) {
                if (atividade.getNome().equals(nomeAtividade)) {
                    return atividade;
                }
            }
        }
        return null;
    }

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
}
