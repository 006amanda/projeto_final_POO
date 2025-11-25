package Contas;

import Excecoes.*;
import Usuarios.*;

public class ContaDigital extends ContaFinanceiraAbstrata {
    public ContaDigital(Usuario titular) {
        super(titular, "Conta digital");
    }

    @Override
    public void sacar(double valor) throws SaldoInsuficienteException {
        if (valor <= 0)
            return;

        if (!validarSaldo(valor)) {
            throw new SaldoInsuficienteException("Saldo insuficiente.");
        }
        saldo -= valor;
    }

    @Override
    public void exibirInformacoes() {
        System.out.println("Conta digital = " + numeroConta + " | Titular = " + titular.getNome() + " | Saldo = R$" + saldo);
        System.out.println();
    }
}