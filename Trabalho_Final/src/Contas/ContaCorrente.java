package Contas;

import Excecoes.*;
import Usuarios.*;

public class ContaCorrente extends ContaFinanceiraAbstrata {
    protected double limiteChequeEspecial;

    public ContaCorrente(Usuario titular, double limiteChequeEspecial) {
        super(titular, "Conta corrente");
        this.limiteChequeEspecial = limiteChequeEspecial;
    }

    @Override
    public void sacar(double valor) throws SaldoInsuficienteException {
        if (valor <= 0) return;
        double disponivel = saldo + limiteChequeEspecial;
        if (valor > disponivel) {
            throw new SaldoInsuficienteException("Saldo insuficiente.");
        }
        saldo -= valor;
    }

    public double getLimiteChequeEspecial() {
        return limiteChequeEspecial;
    }

    public void setLimiteChequeEspecial(double limiteChequeEspecial) {
        this.limiteChequeEspecial = limiteChequeEspecial;
    }

    @Override
    public void exibirInformacoes() {
        System.out.println("Conta corrente: Número = " + numeroConta + " | Titular = " + (titular != null ? titular.getNome() : "N/A") + " | Saldo = R$" + saldo + " | Limite do cheque = R$" + limiteChequeEspecial);
    }
}