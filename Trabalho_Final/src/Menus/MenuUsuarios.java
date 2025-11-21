package Menus;

import java.util.Scanner;

public class MenuUsuarios {
    public static void exibir(){
        Scanner sc = new Scanner(System.in);
        int op = 0;

        while(op != 3){
            System.out.println("1 - Cadastrar Usuário Individual");
            System.out.println("2 - Cadastrar Grupo");
            System.out.println("3 - Voltar");
            System.out.print("Escolha uma opção: ");
            op = sc.nextInt();

            if(op == 1){
                System.out.println("Cadastro individual");
                break;
            }
            else if(op == 2){
                System.out.println("Cadastro de grupo");
                break;
            }
            else if(op == 3){
                System.out.println("Voltando ao menu principal...");
                break;
            }
            else{
                System.out.println("Opção inválida!");
            }
        }
    }
}
