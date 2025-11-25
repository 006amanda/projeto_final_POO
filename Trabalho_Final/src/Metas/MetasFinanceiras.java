package Metas;

public class MetasFinanceiras {
    private String categoria;
    private double valorMeta;
    private String prazoDias;
    private double valorNoCofrinho;
    private double valorInvestido;

    public MetasFinanceiras(String categoria, double valorMeta, String prazoDias) {
        this.categoria = categoria;
        this.valorMeta = valorMeta;
        this.prazoDias = prazoDias;
        this.valorNoCofrinho = 0;
        this.valorInvestido = 0;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getValorMeta() {
        return valorMeta;
    }

    public String getPrazoDias() {
        return prazoDias;
    }

    public double getValorNoCofrinho() {
        return valorNoCofrinho;
    }

    public double getValorInvestido() {
        return valorInvestido;
    }

    public void adicionarNoCofrinho(double valor) {
        if (valor > 0) valorNoCofrinho += valor;
    }

    public void investir(double valor) {
        if (valor > 0) valorInvestido += valor;
    }

    public double getTotalAcumulado() {
        return valorNoCofrinho + valorInvestido;
    }

    public void adicionarProgresso(double valor) {
        if (valor > 0) {
            valorNoCofrinho += valor;
        }
    }
    @Override
    public String toString() {
        return STR."Categoria: \{categoria} | Meta: R$\{valorMeta} | Cofrinho: R$\{valorNoCofrinho} | Investido: R$\{valorInvestido} | Total acumulado: R$\{getTotalAcumulado()}";
    }
}