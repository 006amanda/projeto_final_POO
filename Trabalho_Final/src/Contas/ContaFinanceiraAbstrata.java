package Contas;

import Excecoes.*;
import Usuarios.*;

public abstract class ContaFinanceiraAbstrata implements ContaFinanceira {
    protected static int contadorNumeros = 1001;
    protected int numeroConta;
    protected double saldo;
    protected Usuario titular;
    protected String tipoConta;

    public ContaFinanceiraAbstrata(Usuario titular, String tipoConta) {
        this.numeroConta = contadorNumeros++;
        this.saldo = 0.0;
        this.titular = titular;
        this.tipoConta = tipoConta;
    }

    @Override
    public int getNumeroConta() {
        return numeroConta;
    }

    @Override
    public double getSaldo() {
        return saldo;
    }

    @Override
    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
        }
    }

    @Override
    public void transferir(ContaFinanceira destino, double valor) throws SaldoInsuficienteException {
        if (destino == null) return;

        this.sacar(valor);
        destino.depositar(valor);
    }

    @Override
    public boolean validarSaldo(double valor) {
        return saldo >= valor;
    }

    @Override
    public void exibirInformacoes() {
        System.out.println("Conta = " + numeroConta + " | Tipo = " + tipoConta + " | Titular = " + (titular != null ? titular.getNome() : "N/A") + " | Saldo = R$" + saldo);
    }

}