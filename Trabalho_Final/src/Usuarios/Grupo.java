package Usuarios;

import java.util.*;

public class Grupo extends Usuario {
    protected List<UsuarioIndividual> membros;
    protected int responsavelId;
    protected String tipoGrupo;

    public Grupo(UsuarioIndividual responsavel, String tipoGrupo) {
        super(responsavel.getNome(), responsavel.getCpf(),
                responsavel.getEmail(), responsavel.getContato());
        this.tipoGrupo = tipoGrupo;
        this.membros = new ArrayList<>();
        this.responsavelId = responsavel.getId();
        this.membros.add(responsavel);
    }

    public List<UsuarioIndividual> getMembros() {
        return membros;
    }

    public UsuarioIndividual getResponsavel() {
        for (UsuarioIndividual u : membros) {
            if (u.getId() == responsavelId) return u;
        }
        return null;
    }

    public void setResponsavel(UsuarioIndividual novoResp) {
        if (novoResp == null) return;

        if (!membros.contains(novoResp)) {
            membros.add(novoResp);
        }
        this.responsavelId = novoResp.getId();

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
        if (u.getId() == responsavelId) return false;
        return membros.remove(u);
    }

    public boolean isMembro(Usuario u) {
        if (!(u instanceof UsuarioIndividual ui)) return false;
        return membros.contains(ui);
    }

    public String getTipoGrupo() {
        return tipoGrupo;
    }

    public void setTipoGrupo(String tipoGrupo) {
        this.tipoGrupo = tipoGrupo;
    }

    @Override
    public boolean podeVisualizar(Usuario requester) {
        if (requester == null) return false;
        if (requester.getId() == responsavelId) return true;
        return isMembro(requester);
    }

    @Override
    public boolean podeGerenciar(Usuario requester) {
        return requester != null && requester.getId() == this.responsavelId;
    }

    @Override
    public String toString() {
        UsuarioIndividual admin = getResponsavel();
        return "Grupo ID = " + id + " | Tipo = " + tipoGrupo + " | Admin = " + (admin != null ? admin.getNome() : "N/A") + " | Membros = " + membros.size();
    }
}