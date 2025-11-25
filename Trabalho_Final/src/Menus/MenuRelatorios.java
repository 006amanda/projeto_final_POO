package Menus;

import Dashboards.*;
import Lancamentos.*;
import Usuarios.*;
import java.util.*;

public class MenuRelatorios {
    private static GestorDeLancamentos gestor = MenuLancamentos.getGestor();

    public static void setGestor(GestorDeLancamentos g) {
        gestor = g;
    }

    public static void exibir() {
        Scanner sc = new Scanner(System.in);
        int op = 0;

        while (op != 6) {
            System.out.println("1 - Gastos por período");
            System.out.println("2 - Ranking de despesas");
            System.out.println("3 - Comparativo por categoria");
            System.out.println("4 - Evolução do saldo");
            System.out.println("5 - Resumo de grupo");
            System.out.println("6 - Voltar");
            System.out.print("Escolha uma opção: ");

            try {
                op = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                op = 0;
            }

            System.out.println();

            if (op == 1) {
                Relatorios r = new RelatorioGastoPeriodo(gestor);
                r.gerar();
            }
            else if (op == 2) {
                Relatorios r = new RelatorioRankingDespesas(gestor);
                r.gerar();
            }
            else if (op == 3) {
                Relatorios r = new RelatorioComparativoCategoria(gestor);
                r.gerar();
            }
            else if (op == 4) {
                Relatorios r = new RelatorioEvolucaoSaldo(gestor);
                r.gerar();
            }
            else if (op == 5) {
                Grupo g = selecionarGrupo(sc);
                if (g != null) {
                    Relatorios r = new RelatorioResumoGrupo(g);
                    r.gerar();
                }
            }
            else if (op == 6) {
                System.out.println("Voltando ao menu principal...");
                return;
            }
        }
    }

    private static Grupo selecionarGrupo(Scanner sc) {
        if (MenuUsuarios.grupos.isEmpty()) {
            System.out.println("Nenhum grupo cadastrado.");
            return null;
        }

        System.out.println("GRUPOS DISPONÍVEIS:");
        for (Grupo g : MenuUsuarios.grupos) {
            System.out.println("ID = " + g.getId() + " | Tipo = " + g.getTipoGrupo() + " | Admin = " + g.getResponsavel().getNome());
        }

        System.out.print("Digite o ID do grupo: ");

        int id;
        try {
            id = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            return null;
        }

        for (Grupo g : MenuUsuarios.grupos) {
            if (g.getId() == id) return g;
        }

        System.out.println("Grupo não encontrado.");
        return null;
    }
}