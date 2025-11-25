package Lancamentos;

import Contas.*;
import Usuarios.*;

public class LancamentosFinanceiros {
    protected int id;
    protected String tipo;
    protected String categoria;
    protected double valor;
    protected String data;
    protected Usuario pagador;
    protected Usuario beneficiario;
    protected ContaFinanceira conta;
    protected String recorrencia;
    protected String beneficiarioNome;
    protected static int contador = 1;

    public LancamentosFinanceiros() {
        this.id = contador++;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }

    public Usuario getPagador() { return pagador; }
    public void setPagador(Usuario pagador) { this.pagador = pagador; }

    public Usuario getBeneficiario() { return beneficiario; }
    public void setBeneficiario(Usuario beneficiario) { this.beneficiario = beneficiario; }

    public ContaFinanceira getConta() { return conta; }
    public void setConta(ContaFinanceira conta) { this.conta = conta; }

    public String getRecorrencia() { return recorrencia; }
    public void setRecorrencia(String recorrencia) { this.recorrencia = recorrencia; }

    public static int getContador() { return contador; }
    public static void setContador(int contador) { LancamentosFinanceiros.contador = contador; }

    public String getBeneficiarioNome() { return beneficiarioNome; }
    public void setBeneficiarioNome(String beneficiarioNome) { this.beneficiarioNome = beneficiarioNome; }

    @Override
    public String toString() {

        String finalBeneficiario;

        if (beneficiario != null) {
            finalBeneficiario = beneficiario.getNome();
        }
        else if (beneficiarioNome != null && !beneficiarioNome.isEmpty()) {
            finalBeneficiario = beneficiarioNome;
        }
        else {
            finalBeneficiario = "N/A";
        }

        return "ID = " + id + " | Tipo = " + tipo + " | Categoria = " + categoria + " | Valor = R$" + valor + " | Data = " + data + " | Pagador = " + (pagador != null ? pagador.getNome() : "N/A") + " | Beneficiário = " + finalBeneficiario + " | Conta = " + (conta != null ? conta.getNumeroConta() : "N/A");
    }
}