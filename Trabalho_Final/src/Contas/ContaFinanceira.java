package Contas;

import Excecoes.SaldoInsuficienteException;

public interface ContaFinanceira {
    int getNumeroConta();
    double getSaldo();
    void depositar(double valor);
    void sacar(double valor) throws SaldoInsuficienteException;
    void transferir(ContaFinanceira destino, double valor) throws SaldoInsuficienteException;
    boolean validarSaldo(double valor);
    void exibirInformacoes();
}