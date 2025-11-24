package Excecoes;

public class UsuarioSemPermissaoException extends Excecoes{
    public UsuarioSemPermissaoException(String msg) {
        super("Usuário sem permissão.");
    }
}