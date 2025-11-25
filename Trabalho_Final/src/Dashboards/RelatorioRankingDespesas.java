package Dashboards;

import Lancamentos.*;
import java.util.*;

public class RelatorioRankingDespesas implements Relatorios, Exportavel {
    private GestorDeLancamentos gestor;
    public RelatorioRankingDespesas(GestorDeLancamentos gestor) {
        this.gestor = gestor;
    }

    @Override
    public void gerar() {
        System.out.println("RANKING DE DESPESAS");

        List<LancamentosFinanceiros> lista = new ArrayList<>();

        for (LancamentosFinanceiros l : gestor.listarLancamentos()) {
            if ("despesa".equalsIgnoreCase(l.getTipo())) {
                lista.add(l);
            }
        }

        if (lista.isEmpty()) {
            System.out.println("Nenhuma despesa encontrada.");
            return;
        }

        lista.sort((a, b) -> Double.compare(b.getValor(), a.getValor()));

        System.out.println("Categoria | Valor");
        System.out.println();

        for (LancamentosFinanceiros l : lista) {
            System.out.println(l.getCategoria() + " | R$ " + l.getValor());
        }
    }

    @Override
    public void exportar() {
        System.out.println("Exportando ranking de despesas...");
    }
}