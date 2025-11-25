package AlgoritmosInteligentes;

import java.util.*;
import Lancamentos.*;

public class CalculadoraFinanceira {
    protected EstrategiaCalculo estrategia;

    public EstrategiaCalculo getEstrategia() {
        return estrategia;
    }

    public void setEstrategia(EstrategiaCalculo estrategia) {
        this.estrategia = estrategia;
    }

    public double realizarCalculo(List<LancamentosFinanceiros> lancamentos){
        if(estrategia == null){
            throw new IllegalStateException("Nenhuma estratégia definida.");
        }
        return estrategia.calcular(lancamentos);
    }
}