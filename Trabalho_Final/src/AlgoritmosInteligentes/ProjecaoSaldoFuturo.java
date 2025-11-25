package AlgoritmosInteligentes;

import java.util.*;
import Lancamentos.*;

public class ProjecaoSaldoFuturo implements EstrategiaCalculo {
    private double valorInicial;
    private double taxaMensal; // taxa em percentual, ex: 0.5 = 0,5%
    private int meses;

    public ProjecaoSaldoFuturo(double valorInicial, double taxaMensal, int meses) {
        this.valorInicial = valorInicial;

        if (taxaMensal > 1) {
            this.taxaMensal = taxaMensal / 100.0;
        } else {
            this.taxaMensal = taxaMensal / 100.0;
        }
        this.meses = meses;
    }

    @Override
    public double calcular(List<LancamentosFinanceiros> lancamentos) {
        double montante = valorInicial;

        for (int i = 0; i < meses; i++) {
            montante *= (1 + taxaMensal);
        }
        return montante;
    }
}