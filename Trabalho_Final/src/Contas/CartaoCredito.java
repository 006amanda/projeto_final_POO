package Contas;

import Excecoes.SaldoInsuficienteException;
import Usuarios.Usuario;

public class CartaoCredito extends ContaFinanceiraAbstrata {
    protected double limiteCredito;
    protected double faturaAtual;

    public CartaoCredito(Usuario titular, double limiteCredito) {
        super(titular, "Cartão de crédito");
        this.limiteCredito = limiteCredito;
        this.faturaAtual = 0;
    }

    @Override
    public void sacar(double valor) throws SaldoInsuficienteException {
        if (valor <= 0) return;

        double disponivel = limiteCredito - faturaAtual;

        if (valor > disponivel) {
            throw new SaldoInsuficienteException("Limite do cartão insuficiente.");
        }
        faturaAtual += valor;
    }

    @Override
    public void depositar(double valor) {
        if (valor <= 0) return;

        if (valor >= faturaAtual) {
            double sobra = valor - faturaAtual;
            faturaAtual = 0;
            saldo += sobra;
        } else {
            faturaAtual -= valor;
        }
    }

    public void pagarFatura(ContaFinanceira fonte) throws SaldoInsuficienteException {
        if (fonte == null || faturaAtual <= 0)
            return;
        fonte.sacar(faturaAtual);
        this.depositar(faturaAtual);
    }

    @Override
    public void exibirInformacoes() {
        System.out.println("Cartão de crédito = " + numeroConta + " | Titular = " + titular.getNome() + " | Fatura = " + faturaAtual + " | Limite = " + limiteCredito + " | Saldon Pós-Pago = " + saldo);
    }
}