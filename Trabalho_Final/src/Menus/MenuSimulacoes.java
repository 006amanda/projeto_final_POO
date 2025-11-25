package Menus;

import java.util.*;
import AlgoritmosInteligentes.*;
import Lancamentos.*;

public class MenuSimulacoes {
    private static GestorDeLancamentos gestor = MenuLancamentos.getGestor();
    public static void setGestor(GestorDeLancamentos g) {
        gestor = g;
    }

    public static void exibir() {
        Scanner sc = new Scanner(System.in);
        int op = 0;

        if (gestor == null) gestor = MenuLancamentos.getGestor();

        while (op != 4) {
            System.out.println("1 - Projeção de saldo futuro");
            System.out.println("2 - Sugestão de economia");
            System.out.println("3 - Simulação de cenário");
            System.out.println("4 - Voltar");
            System.out.print("Escolha uma opção: ");

            try {
                op = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                op = 0;
            }

            System.out.println();

            if (op == 1) {
                executarProjecao(sc);
            }
            else if (op == 2) {
                executarSugestaoEconomia();
            }
            else if (op == 3) {
                executarSimulacaoCenario(sc);
            }
            else if (op == 4) {
                System.out.println("Voltando...");
                return;
            }
        }
    }

    private static void executarProjecao(Scanner sc) {
        System.out.print("Informe o valor inicial: R$");
        double valorInicial = Double.parseDouble(sc.nextLine());

        System.out.print("Informe a taxa mensal: ");
        double taxa = Double.parseDouble(sc.nextLine());

        System.out.print("Informe o número de meses: ");
        int meses = Integer.parseInt(sc.nextLine());

        ProjecaoSaldoFuturo proj = new ProjecaoSaldoFuturo(valorInicial, taxa, meses);

        CalculadoraFinanceira calc = new CalculadoraFinanceira();
        calc.setEstrategia(proj);

        double resultado = calc.realizarCalculo(new ArrayList<>());
        System.out.println("Valor projetado após " + meses + " meses: R$" + String.format("%.2f", resultado));
    }

    private static void executarSugestaoEconomia() {
        if (gestor == null) return;

        List<LancamentosFinanceiros> lista = gestor.listarLancamentos();
        if (lista.isEmpty()) {
            System.out.println("Nenhum lançamento disponível.");
            return;
        }

        SugestaoEconomia estrategia = new SugestaoEconomia();

        CalculadoraFinanceira calc = new CalculadoraFinanceira();
        calc.setEstrategia(estrategia);

        double valor = calc.realizarCalculo(lista);

        System.out.println("Total sugerido em cortes de gastos: R$" + String.format("%.2f", valor));
    }

    private static void executarSimulacaoCenario(Scanner sc) {
        if (gestor == null) return;

        List<LancamentosFinanceiros> lista = gestor.listarLancamentos();
        if (lista.isEmpty()) {
            System.out.println("Nenhum lançamento disponível.");
            return;
        }

        System.out.print("Informe o percentual de variação nas despesas: ");
        double percentual = Double.parseDouble(sc.nextLine());

        SimulacaoCenario sim = new SimulacaoCenario();
        double economiaOuPerda = sim.simular(lista, percentual);

        if (economiaOuPerda > 0) {
            System.out.println("Você teria economizado: R$" + String.format("%.2f", economiaOuPerda));
        } else {
            System.out.println("Você teria gastado a mais: R$" + String.format("%.2f", Math.abs(economiaOuPerda)));
        }
    }
}