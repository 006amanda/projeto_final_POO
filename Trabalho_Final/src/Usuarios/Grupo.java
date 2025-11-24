package Usuarios;

import java.util.*;

public class Grupo extends Usuario {
    protected List<UsuarioIndividual> membros;
    protected UsuarioIndividual responsavel;
    protected String tipoGrupo;

    public Grupo(UsuarioIndividual responsavel, String tipoGrupo) {
        super(responsavel.getNome(), responsavel.getCpf(),
                responsavel.getEmail(), responsavel.getContato());
        this.tipoGrupo = tipoGrupo;
        this.membros = new ArrayList<>();
        this.responsavel = responsavel;
        this.membros.add(responsavel);
    }

    public List<UsuarioIndividual> getMembros() {
        return membros;
    }

    public UsuarioIndividual getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(UsuarioIndividual novoResp) {
        if (novoResp == null)
            return;

        if (!membros.contains(novoResp)) {
            membros.add(novoResp);
        }
        this.responsavel = novoResp;
        this.nome = novoResp.getNome();
        this.cpf = novoResp.getCpf();
        this.email = novoResp.getEmail();
        this.contato = novoResp.getContato();
    }

    public boolean adicionarMembro(UsuarioIndividual u, Usuario requester) {
        if (!podeGerenciar(requester)) return false;
        if (u == null) return false;
        if (!membros.contains(u)) {
            membros.add(u);
            return true;
        }
        return false;
    }

    public boolean removerMembro(UsuarioIndividual u, Usuario requester) {
        if (!podeGerenciar(requester)) return false;
        if (u == null) return false;
        if (u.equals(responsavel)) return false;
        return membros.remove(u);
    }

    public boolean isMembro(Usuario u) {
        if (!(u instanceof UsuarioIndividual)) return false;
        return membros.contains((UsuarioIndividual) u);
    }

    public String getTipoGrupo() {
        return tipoGrupo;
    }

    public void setTipoGrupo(String tipoGrupo) {
        this.tipoGrupo = tipoGrupo;
    }

    @Override
    public boolean podeVisualizar(Usuario requester) {
        if (requester == null)
            return false;

        if (requester.getId() == this.responsavel.getId()) return true;

        for (UsuarioIndividual m : membros) {
            if (m.getId() == requester.getId()) return true;
        }

        return false;
    }

    @Override
    public boolean podeGerenciar(Usuario requester) {
        if (requester == null) return false;
        return requester.getId() == this.responsavel.getId();
    }

    @Override
    public String toString() {
        return "Grupo ID = " + id + " | Tipo = " + tipoGrupo + " | Admin = " + responsavel.getNome() + " | Membros = " + membros.size();
    }
}