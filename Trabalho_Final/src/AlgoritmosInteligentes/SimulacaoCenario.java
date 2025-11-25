package AlgoritmosInteligentes;

import java.util.*;
import Lancamentos.*;

public class SimulacaoCenario {

    public double simular(List<LancamentosFinanceiros> lancamentos, double percentual) {
        double totalDespesas = calcularDespesas(lancamentos);
        double aumento = totalDespesas * (percentual / 100.0);

        return aumento;
    }

    private double calcularDespesas(List<LancamentosFinanceiros> lancamentos) {
        double despesas = 0;

        for (LancamentosFinanceiros l : lancamentos) {
            if (l.getTipo().equalsIgnoreCase("despesa")) {
                despesas += l.getValor();
            }
        }
        return despesas;
    }
}