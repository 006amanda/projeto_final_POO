package Lancamentos;

import java.util.*;

public class GestorDeLancamentos {
    private List<LancamentosFinanceiros> lancamentos = new ArrayList<>();
    public void adicionarLancamento(LancamentosFinanceiros l) {
        lancamentos.add(l);
        System.out.println("Lançamento registrado! ID = " + l.getId());
    }

    public List<LancamentosFinanceiros> listarLancamentos() {
        return lancamentos;
    }

    public void exibirTodos() {
        if (lancamentos.isEmpty()) {
            System.out.println("Nenhum lançamento registrado.");
            return;
        }
        for (LancamentosFinanceiros l : lancamentos) {
            System.out.println(l.toString());
        }
    }
}