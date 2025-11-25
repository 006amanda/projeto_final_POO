package Menus;

import java.util.*;
import Usuarios.*;
import Metas.*;
import Contas.*;

public class MenuMetas {
    public static void exibir() {
        Scanner sc = new Scanner(System.in);
        int op = 0;

        while (op != 5) {
            System.out.println("1 - Criar meta");
            System.out.println("2 - Listar metas");
            System.out.println("3 - Adicionar progresso");
            System.out.println("4 - Excluir meta");
            System.out.println("5 - Voltar");
            System.out.print("Escolha uma opção: ");

            try {
                op = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                op = 0;
            }

            System.out.println();

            if (op == 1) {
                criarMeta(sc);
            }
            else if (op == 2) {
                listarMetas(sc);
            }
            else if (op == 3) {
                adicionarProgresso(sc);
            }
            else if (op == 4) {
                excluirMeta(sc);
            }
            else if (op == 5) {
                return;
            }
        }
    }

    private static Usuario selecionarUsuario(Scanner sc) {
        System.out.println("Usuários:");

        for (UsuarioIndividual u : MenuUsuarios.usuariosIndividuais)
            System.out.println("ID = " + u.getId() + " | Nome = " + u.getNome());

        for (Grupo g : MenuUsuarios.grupos)
            System.out.println("ID = " + g.getId() + " | Grupo = " + g.getTipoGrupo());

        System.out.print("ID: ");

        int id;
        try {
            id = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            return null;
        }

        for (UsuarioIndividual u : MenuUsuarios.usuariosIndividuais)
            if (u.getId() == id) return u;

        for (Grupo g : MenuUsuarios.grupos)
            if (g.getId() == id) return g;

        return null;
    }

    private static void criarMeta(Scanner sc) {
        System.out.println("Selecione o usuário alvo da meta:");
        Usuario alvo = selecionarUsuario(sc);
        if (alvo == null) return;

        System.out.println("Selecione o executor:");
        Usuario executor = selecionarUsuario(sc);
        if (executor == null) return;

        if (!alvo.podeGerenciar(executor)) {
            System.out.println("Sem permissão.");
            return;
        }

        System.out.print("Categoria da meta: ");
        String categoria = sc.nextLine();

        System.out.print("Valor objetivo: R$");
        double valor;

        try { valor = Double.parseDouble(sc.nextLine()); }
        catch (Exception e) { return; }

        System.out.print("Prazo: ");
        String prazo = sc.nextLine();

        MetasFinanceiras meta = new MetasFinanceiras(categoria, valor, prazo);
        alvo.adicionarMeta(meta);

        System.out.println("Meta criada.");
    }

    private static void listarMetas(Scanner sc) {
        Usuario u = selecionarUsuario(sc);
        if (u == null) return;

        if (u.getMetas().isEmpty()) {
            System.out.println("Nenhuma meta.");
            return;
        }

        System.out.println("Metas:");
        int i = 1;
        for (MetasFinanceiras m : u.getMetas()) {
            System.out.println(i + " - " + m);
            i++;
        }
    }

    private static void adicionarProgresso(Scanner sc) {
        System.out.println("Selecione o usuário alvo:");
        Usuario alvo = selecionarUsuario(sc);
        if (alvo == null) return;

        System.out.println("Selecione o executor:");
        Usuario executor = selecionarUsuario(sc);
        if (executor == null) return;

        if (!alvo.podeGerenciar(executor)) {
            System.out.println("Sem permissão.");
            return;
        }

        if (alvo.getMetas().isEmpty()) {
            System.out.println("Nenhuma meta cadastrada.");
            return;
        }

        int i = 1;
        for (MetasFinanceiras m : alvo.getMetas()) {
            System.out.println(i + " - " + m.getCategoria());
            i++;
        }

        System.out.print("Escolha a meta: ");
        int escolha;

        try { escolha = Integer.parseInt(sc.nextLine()); }
        catch (Exception e) { return; }

        if (escolha < 1 || escolha > alvo.getMetas().size()) return;

        MetasFinanceiras meta = alvo.getMetas().get(escolha - 1);

        System.out.print("Valor a adicionar: R$");
        double valor;

        try { valor = Double.parseDouble(sc.nextLine()); }
        catch (Exception e) { return; }

        System.out.println("1 - Adicionar ao Cofrinho");
        System.out.println("2 - Adicionar à Carteira de Investimentos");

        String tipo = sc.nextLine();

        if (tipo.equals("1")) meta.adicionarNoCofrinho(valor);
        else if (tipo.equals("2")) meta.investir(valor);

        System.out.println("Progresso adicionado.");
    }

    private static void excluirMeta(Scanner sc) {
        System.out.println("Selecione o usuário alvo:");
        Usuario alvo = selecionarUsuario(sc);
        if (alvo == null) return;

        System.out.println("Selecione o executor:");
        Usuario executor = selecionarUsuario(sc);
        if (executor == null) return;

        if (!alvo.podeGerenciar(executor)) {
            System.out.println("Sem permissão.");
            return;
        }

        if (alvo.getMetas().isEmpty()) {
            System.out.println("Nenhuma meta para excluir.");
            return;
        }

        int i = 1;
        for (MetasFinanceiras m : alvo.getMetas()) {
            System.out.println(i + " - " + m.getCategoria());
            i++;
        }

        System.out.print("Escolha a meta para excluir: ");
        int escolha;

        try { escolha = Integer.parseInt(sc.nextLine()); }
        catch (Exception e) { return; }

        if (escolha < 1 || escolha > alvo.getMetas().size()) return;

        MetasFinanceiras meta = alvo.getMetas().remove(escolha - 1);

        System.out.println("Meta '" + meta.getCategoria() + "' excluída.");
    }
}