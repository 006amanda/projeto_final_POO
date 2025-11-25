package Menus;

import java.util.*;
import Usuarios.*;

public class MenuUsuarios {
    public static List<UsuarioIndividual> usuariosIndividuais = new ArrayList<>();
    public static List<Grupo> grupos = new ArrayList<>();

    public static void exibir() {
        Scanner sc = new Scanner(System.in);
        int op = 0;

        while (op != 4) {
            System.out.println("1 - Cadastrar Usuário Individual");
            System.out.println("2 - Cadastrar Grupo");
            System.out.println("3 - Excluir Usuário");
            System.out.println("4 - Voltar");
            System.out.print("Escolha uma opção: ");

            try {
                op = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                op = 0;
            }

            System.out.println();

            if (op == 1) {
                cadastrarIndividual(sc);
            }
            else if (op == 2) {
                cadastrarGrupo(sc);
            }
            else if (op == 3) {
                excluirUsuario(sc);
            }
            else if (op == 4) {
                return;
            }
        }
    }

    private static void cadastrarIndividual(Scanner sc) {
        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("CPF: ");
        String cpf = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Contato: ");
        String contato = sc.nextLine();

        System.out.print("Ocupação: ");
        String ocup = sc.nextLine();

        System.out.print("Estado civil: ");
        String estCivil = sc.nextLine();

        UsuarioIndividual ui = new UsuarioIndividual(nome, cpf, email, contato, ocup, estCivil);
        usuariosIndividuais.add(ui);

        System.out.println();
        System.out.println("Usuário cadastrado com ID = " + ui.getId());
        System.out.println();
    }

    private static void cadastrarGrupo(Scanner sc) {
        if (usuariosIndividuais.isEmpty()) {
            System.out.println("Nenhum usuário individual cadastrado para ser admin.");
            return;
        }

        System.out.println("Selecione o admin do grupo:");
        UsuarioIndividual admin = selecionarIndividual(sc);
        System.out.println();
        if (admin == null) return;

        System.out.print("Tipo do grupo: ");
        String tipo = sc.nextLine();
        System.out.println();

        Grupo g = new Grupo(admin, tipo);

        System.out.print("Quantos membros adicionar? ");
        int qtd;

        try {
            qtd = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            return;
        }

        System.out.println();

        for (int i = 0; i < qtd; i++) {
            UsuarioIndividual m = selecionarIndividual(sc);
            if (m != null) g.adicionarMembro(m, admin);
        }

        grupos.add(g);
        System.out.println("Grupo criado com ID = " + g.getId());
    }

    private static void excluirUsuario(Scanner sc) {
        System.out.println("Digite o ID do executor: ");
        Usuario executor = selecionarUsuario(sc);
        System.out.println();

        if (executor == null) return;

        System.out.println("Digite o ID do usuário a excluir: ");
        Usuario alvo = selecionarUsuario(sc);
        System.out.println();

        if (alvo == null) return;

        if (alvo instanceof Grupo) {
            System.out.println("Não é possível excluir grupos.");
            return;
        }

        if (executor instanceof UsuarioIndividual && alvo instanceof UsuarioIndividual) {
            if (!executor.equals(alvo)) {
                System.out.println("Você só pode excluir a si mesmo.");
                return;
            }

            usuariosIndividuais.remove(alvo);
            System.out.println("Usuário removido: " + alvo.getNome());
            System.out.println();
            return;
        }

        if (executor instanceof Grupo) {
            Grupo g = (Grupo) executor;

            if (!g.isMembro(alvo)) {
                System.out.println("O usuário não pertence a este grupo.");
                return;
            }

            if (alvo.equals(g.getResponsavel())) {
                System.out.println("O admin não pode se excluir do grupo.");
                return;
            }

            g.removerMembro((UsuarioIndividual) alvo, g.getResponsavel());
            System.out.println("Usuário removido do grupo.");
            return;
        }

        System.out.println("Você não tem permissão para excluir este usuário.");
    }

    private static UsuarioIndividual selecionarIndividual(Scanner sc) {
        System.out.println("Usuários individuais:");

        for (UsuarioIndividual u : usuariosIndividuais)
            System.out.println("ID = " + u.getId() + " | Nome = " + u.getNome());

        System.out.print("ID: ");

        try {
            int id = Integer.parseInt(sc.nextLine());
            for (UsuarioIndividual u : usuariosIndividuais)
                if (u.getId() == id) return u;
        } catch (Exception ignored) {}

        System.out.println("Usuário não encontrado.");
        return null;
    }

    private static Usuario selecionarUsuario(Scanner sc) {
        System.out.println("Todos usuários:");

        for (UsuarioIndividual u : usuariosIndividuais)
            System.out.println("ID = " + u.getId() + " | Nome = " + u.getNome());

        for (Grupo g : grupos)
            System.out.println("ID = " + g.getId() + " | Grupo = " + g.getTipoGrupo());

        System.out.print("ID: ");

        try {
            int id = Integer.parseInt(sc.nextLine());

            for (UsuarioIndividual u : usuariosIndividuais)
                if (u.getId() == id) return u;

            for (Grupo gp : grupos)
                if (gp.getId() == id) return gp;

        } catch (Exception ignored) {}
        System.out.println("ID não encontrado.");
        return null;
    }
}