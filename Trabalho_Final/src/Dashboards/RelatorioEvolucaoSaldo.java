package Dashboards;

import Lancamentos.*;

public class RelatorioEvolucaoSaldo implements Relatorios, Exportavel {
    private GestorDeLancamentos gestor;

    public RelatorioEvolucaoSaldo(GestorDeLancamentos gestor) {
        this.gestor = gestor;
    }

    @Override
    public void gerar() {
        System.out.println("EVOLUÇÃO DO SALDO");

        var lancs = gestor.listarLancamentos();

        if (lancs.isEmpty()) {
            System.out.println("Nenhum lançamento encontrado.");
            return;
        }

        double saldo = 0;

        System.out.println("Data | Saldo após operação");
        System.out.println();

        for (LancamentosFinanceiros l : lancs) {
            if (l.getTipo() == null) continue;

            if (l.getTipo().equalsIgnoreCase("receita")) {
                saldo += l.getValor();
            } else if (l.getTipo().equalsIgnoreCase("despesa")) {
                saldo -= l.getValor();
            }

            System.out.println(l.getData() + " | R$ " + saldo);
        }
    }

    @Override
    public void exportar() {
        System.out.println("Exportando evolução de saldo...");
    }
}