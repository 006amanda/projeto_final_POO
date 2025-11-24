package Usuarios;

import java.util.*;

public class UsuarioIndividual extends Usuario {
    protected String ocupacao;
    protected String estadoCivil;

    public UsuarioIndividual(String nome, String cpf, String email, String contato, String ocupacao, String estadoCivil) {
        super(nome, cpf, email, contato);
        this.ocupacao = ocupacao;
        this.estadoCivil = estadoCivil;
    }

    public String getOcupacao() {
        return ocupacao;
    }

    public void setOcupacao(String ocupacao) {
        this.ocupacao = ocupacao;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    @Override
    public boolean podeVisualizar(Usuario requester) {
        return requester != null && requester.getId() == this.id;
    }

    @Override
    public boolean podeGerenciar(Usuario requester) {
        return requester != null && requester.getId() == this.id;
    }

    @Override
    public String toString() {
        return "Usuário individual ID = " + id + " | Nome = " + nome + " | CPF = " + cpf + " | Ocupação = " + ocupacao + " | Estado Civil = " + estadoCivil;
    }
}