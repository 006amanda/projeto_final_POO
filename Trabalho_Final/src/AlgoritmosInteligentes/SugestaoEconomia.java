package AlgoritmosInteligentes;

import java.util.*;
import Lancamentos.*;

public class SugestaoEconomia implements EstrategiaCalculo {
    @Override
    public double calcular(List<LancamentosFinanceiros> lancamentos) {
        double sugestao = 0;

        for (LancamentosFinanceiros l : lancamentos) {
            if (l.getTipo().equalsIgnoreCase("despesa")) {
                double v = l.getValor();
                if (v > 0 && v <= 50) {
                    sugestao += v;
                }
            }
        }
        return sugestao;
    }
}