package Contas;

import Excecoes.*;
import Metas.*;
import Usuarios.*;

public class Cofrinho extends ContaFinanceiraAbstrata {
    private MetasFinanceiras meta;

    public Cofrinho(Usuario titular) {
        super(titular, "cofrinho");
    }

    public void setMeta(MetasFinanceiras meta) {
        this.meta = meta;
    }

    public MetasFinanceiras getMeta() {
        return meta;
    }

    @Override
    public void depositar(double valor) {
        super.depositar(valor);

        if (meta != null) {
            meta.adicionarProgresso(valor);
        }
    }

    @Override
    public void sacar(double valor) throws SaldoInsuficienteException {
        if (valor <= 0) return;
        if (!validarSaldo(valor)) throw new SaldoInsuficienteException("Saldo insuficiente no Cofrinho.");
        saldo -= valor;
    }

    @Override
    public void exibirInformacoes() {
        System.out.println("Cofrinho: Número = " + numeroConta + " | Titular = " + (titular != null ? titular.getNome() : "N/A") + " | Saldo = R$" + saldo);

        if (meta != null) {
            System.out.println("-> Meta vinculada:");
            System.out.println(meta.toString());
        }
    }
}