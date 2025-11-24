package MenuPrincipal;

import java.util.*;
import Menus.*;
import Lancamentos.*;

public class MenuPrincipal {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int opc = 0;
        while(opc != 7) {
            System.out.println("SISTEMA FINANCEIRO");
            System.out.println("1 - Usuários");
            System.out.println("2 - Contas");
            System.out.println("3 - Lançamentos");
            System.out.println("4 - Metas");
            System.out.println("5 - Relatórios");
            System.out.println("6 - Simulações");
            System.out.println("7 - Sair");
            System.out.print("Escolha uma opção: ");
            opc = sc.nextInt();
            System.out.println();

            if (opc == 1) {
                System.out.println("Usuários");
                MenuUsuarios.exibir();
            }
            else if(opc == 2){
                System.out.println("Contas");
                MenuContas.exibir();
            }
            else if(opc == 3){
                System.out.println("Lançamentos");
                MenuLancamentos.exibir();
            }
            else if(opc == 4){
                System.out.println("Metas");
                MenuMetas.exibir();
            }
            else if(opc == 5){
                System.out.println("Relatórios");
                GestorDeLancamentos gestor = MenuLancamentos.getGestor();
                MenuRelatorios.setGestor(gestor);
                MenuRelatorios.exibir();
            }
            else if(opc == 6){
                System.out.println("Simulações");
                MenuSimulacoes.exibir();
            }
            else if (opc == 7) {
                System.out.println("Saindo do sistema...");
            } else {
                System.out.println("Opção inválida! ");
            }
        }
    }
}