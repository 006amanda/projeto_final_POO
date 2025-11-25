package Contas;

import Excecoes.SaldoInsuficienteException;
import Metas.*;
import Usuarios.*;

public class CarteiraInvestimento extends ContaFinanceiraAbstrata {
    private MetasFinanceiras meta;

    public CarteiraInvestimento(Usuario titular) {
        super(titular, "carteira de investimento");
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
        if (!validarSaldo(valor)) throw new SaldoInsuficienteException("Saldo insuficiente na Carteira.");
        saldo -= valor;
    }

    public void aplicarRendimento(double taxa) {
        if (taxa <= 0) return;
        double rendimento = saldo * taxa;
        saldo += rendimento;

        if (meta != null) {
            meta.adicionarProgresso(rendimento);
        }
    }

    @Override
    public void exibirInformacoes() {
        System.out.println("Carteira de Investimento: Número = " + numeroConta + " | Titular = " + (titular != null ? titular.getNome() : "N/A") + " | Saldo = R$" + saldo);

        if (meta != null) {
            System.out.println("-> Meta vinculada:");
            System.out.println(meta.toString());
        }
    }
}