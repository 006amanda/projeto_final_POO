package Excecoes;

public class CategoriaNaoEncontradaException extends Excecoes{
    public CategoriaNaoEncontradaException(String msg) {
        super("Categoria não encontrada.");
    }
}
