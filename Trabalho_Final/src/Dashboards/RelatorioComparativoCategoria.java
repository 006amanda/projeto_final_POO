package Dashboards;

import Lancamentos.*;
import java.util.*;

public class RelatorioComparativoCategoria implements Relatorios, Exportavel {
    private GestorDeLancamentos gestor;
    public RelatorioComparativoCategoria(GestorDeLancamentos gestor) {
        this.gestor = gestor;
    }

    @Override
    public void gerar() {
        System.out.println("COMPARATIVO POR CATEGORIA");

        List<LancamentosFinanceiros> lancs = gestor.listarLancamentos();
        if (lancs.isEmpty()) {
            System.out.println("Nenhum lançamento encontrado.");
            return;
        }

        List<String> categorias = new ArrayList<>();
        List<Double> valores = new ArrayList<>();

        for (LancamentosFinanceiros l : lancs) {
            if ("despesa".equalsIgnoreCase(l.getTipo())) {

                String categoria = l.getCategoria();
                double valor = l.getValor();

                int index = categorias.indexOf(categoria);

                if (index == -1) {
                    categorias.add(categoria);
                    valores.add(valor);
                } else {
                    valores.set(index, valores.get(index) + valor);
                }
            }
        }

        if (categorias.isEmpty()) {
            System.out.println("Nenhuma despesa registrada.");
            return;
        }

        System.out.println("Categoria -> Total Gasto");
        System.out.println();

        for (int i = 0; i < categorias.size(); i++) {
            System.out.println(categorias.get(i) + " -> R$ " + valores.get(i));
        }
    }

    @Override
    public void exportar() {
        System.out.println("Exportando comparativo por categoria...");
    }
}