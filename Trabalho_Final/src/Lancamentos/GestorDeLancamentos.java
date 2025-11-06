package Lancamentos;
import java.util.*;

public class GestorDeLancamentos {
    private List<LancamentosFinanceiros> lancamentos = new ArrayList<>();
    public void adicionarLancamento(LancamentosFinanceiros lancamento) {
        lancamentos.add(lancamento);
    }
    public List<LancamentosFinanceiros> listarLancamentos() {
        return lancamentos;
    }
}
