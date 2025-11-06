package Excecoes;

public class SaldoInsuficienteException extends Exception {
    public SaldoInsuficienteException(String msg) {
        super("Saldo insuficiente.");
    }
}
