package MenuPrincipal;

import java.util.*;
import Persistencia.PersistenciaDados;
import Persistencia.PersistenciaFactory;

public class MenuPrincipal {
    public static void main(String[] args){

        // INÍCIO DA PERSISTÊNCIA
        PersistenciaDados persistTxt = PersistenciaFactory.criar("txt");
        PersistenciaDados persistJson = PersistenciaFactory.criar("json");
        persistTxt.carregar();
        persistJson.carregar();

        // MENU
        Scanner sc = new Scanner(System.in);
        int opc = 0;

        while(opc != 7){
            System.out.println("SISTEMA FINANCEIRO: ");
            System.out.println("1 - Usuários");
            System.out.println("2 - Contas");
            System.out.println("3 - Lançamentos");
            System.out.println("4 - Metas");
            System.out.println("5 - Relatórios");
            System.out.println("6 - Simulações");
            System.out.println("7 - Sair");
            System.out.print("Escolha uma opção: ");
            opc = sc.nextInt();
        }

        persistTxt.salvar();
        persistJson.salvar();
        System.out.println("Os dados foram salvos com sucesso.");
    }
}
