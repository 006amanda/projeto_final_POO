package Usuarios;

public class Grupo extends Usuario {
    protected String tipoGrupo;
    protected String membros;
    protected String responsavel;

    public Grupo(String nome, String cpf, String email, String contato, String tipoGrupo, String membros, String responsavel) {
        super(nome, cpf, email, contato);
        this.tipoGrupo = tipoGrupo;
        this.membros = membros;
        this.responsavel = responsavel;
    }

    public String getTipoGrupo() {
        return tipoGrupo;
    }

    public void setTipoGrupo(String tipoGrupo) {
        this.tipoGrupo = tipoGrupo;
    }

    public String getMembros() {
        return membros;
    }

    public void setMembros(String membros) {
        this.membros = membros;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }
}
