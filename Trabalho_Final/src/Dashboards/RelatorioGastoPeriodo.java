package Dashboards;

import Lancamentos.*;

public class RelatorioGastoPeriodo implements Relatorios, Exportavel {
    private GestorDeLancamentos gestor;

    public RelatorioGastoPeriodo(GestorDeLancamentos gestor) {
        this.gestor = gestor;
    }

    @Override
    public void gerar() {
        System.out.println("RELATÓRIO DE GASTOS POR PERÍODO");

        var lancs = gestor.listarLancamentos();

        if (lancs.isEmpty()) {
            System.out.println("Nenhum lançamento encontrado.");
            return;
        }

        boolean encontrou = false;

        for (LancamentosFinanceiros l : lancs) {
            if ("despesa".equalsIgnoreCase(l.getTipo())) {
                encontrou = true;
                System.out.println("Data: " + l.getData() + " | Categoria: " + l.getCategoria() + " | Valor: R$ " + l.getValor());
            }
        }

        if (!encontrou) {
            System.out.println("Nenhuma despesa registrada no período.");
        }
    }

    @Override
    public void exportar() {
        System.out.println("Exportando relatório de gastos por período...");
    }
}