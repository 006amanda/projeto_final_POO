package Usuarios;

import java.util.*;
import Metas.*;

public abstract class Usuario {
    protected String nome;
    protected String cpf;
    protected int id;
    protected String email;
    protected String contato;
    protected static int contadorIds = 1;

    protected List<Object> contas;
    protected List<Object> lancamentos;
    protected List<MetasFinanceiras> metas = new ArrayList<>();

    public Usuario(String nome, String cpf, String email, String contato) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.contato = contato;
        this.id = contadorIds++;
        this.contas = new ArrayList<>();
        this.lancamentos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getContato() {
        return contato;
    }

    public void adicionarConta(Object conta) {
        if (contas == null) contas = new ArrayList<>();
        contas.add(conta);
    }

    public List<Object> getContas() {
        return contas;
    }

    public void registrarLancamento(Object lancamento) {
        if (lancamentos == null) lancamentos = new ArrayList<>();
        lancamentos.add(lancamento);
    }

    public List<Object> getLancamentos() {
        return lancamentos;
    }

    public void adicionarMeta(MetasFinanceiras meta) {
        if (meta == null) return;
        metas.add(meta);
    }

    public List<MetasFinanceiras> getMetas() {
        return metas;
    }

    public boolean removerMeta(int index) {
        if (index < 0 || index >= metas.size()) return false;
        metas.remove(index);
        return true;
    }

    public boolean podeVisualizar(Usuario requester) {
        return requester != null && requester.equals(this);
    }

    public boolean podeGerenciar(Usuario requester) {
        return requester != null && requester.equals(this);
    }

    @Override
    public String toString() {
        return STR."Usuario: ID = \{id} | Nome = \{nome} | CPF = \{cpf} | Email = \{email}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;

        if (!(o instanceof Usuario other)) return false;

        return this.id == other.id;
    }
}