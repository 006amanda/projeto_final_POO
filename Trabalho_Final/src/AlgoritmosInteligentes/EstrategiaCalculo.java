package AlgoritmosInteligentes;

import java.util.*;
import Lancamentos.LancamentosFinanceiros;

public interface EstrategiaCalculo {
    double calcular(List<LancamentosFinanceiros> lancamentos);
}