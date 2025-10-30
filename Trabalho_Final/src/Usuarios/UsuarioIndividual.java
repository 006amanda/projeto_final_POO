package Usuarios;

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
}
