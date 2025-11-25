package Menus;

import java.util.*;
import Lancamentos.*;
import Contas.*;
import Usuarios.*;
import Notificacoes.*;

public class MenuLancamentos {
    private static GestorDeLancamentos gestor = new GestorDeLancamentos();
    protected ContaFinanceira conta;

    private static NotificadorConsole notificador = new NotificadorConsole();

    public static GestorDeLancamentos getGestor() {
        return gestor;
    }

    public ContaFinanceira getConta() { return conta; }
    public void setConta(ContaFinanceira conta) { this.conta = conta; }

    public static void exibir() {
        Scanner sc = new Scanner(System.in);
        int op = 0;

        while (op != 4) {
            System.out.println("1 - Criar lançamento");
            System.out.println("2 - Transferência entre contas");
            System.out.println("3 - Listar lançamentos");
            System.out.println("4 - Voltar");
            System.out.print("Escolha uma opção: ");

            try {
                op = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                op = 0;
            }

            System.out.println();

            if (op == 1) {
                criarLancamentoGenerico(sc);
            }
            else if (op == 2) {
                criarTransferencia(sc);
            }
            else if (op == 3) {
                gestor.exibirTodos();
            }
            else if (op == 4) {
                System.out.println("Voltando ao menu anterior...\n");
                return;
            }
            else {
                System.out.println("Opção inválida!");
            }
        }
    }

    private static void criarLancamentoGenerico(Scanner sc) {
        Usuario executor = selecionarUsuarioExecutor(sc);
        if (executor == null) return;

        System.out.print("Despesa ou receita? ");
        String tipo = sc.nextLine().trim().toLowerCase();
        if (!tipo.equals("despesa") && !tipo.equals("receita")) return;

        System.out.print("Categoria: ");
        String categoria = sc.nextLine();

        System.out.print("Valor: R$");
        double valor;
        try {
            valor = Double.parseDouble(sc.nextLine());
            if (valor <= 0) return;
        } catch (Exception e) { return; }

        System.out.print("Data: ");
        String data = sc.nextLine();

        UsuarioIndividual pagador = selecionarUsuarioIndividual(sc);
        if (pagador == null) return;

        if (!pagador.podeGerenciar(executor)) {
            System.out.println("Você não tem permissão para lançar despesas em nome deste usuário.");
            return;
        }

        ContaFinanceira conta = selecionarContaDoUsuario(sc, pagador);
        if (conta == null) return;

        System.out.println("Inserir beneficiário:");
        System.out.println("1 - Escolher usuário individual");
        System.out.println("2 - Digitar nome");
        System.out.print("Escolha: ");

        String esc = sc.nextLine();
        Usuario beneficiarioUsuario = null;
        String beneficiarioNome = null;

        if (esc.equals("1")) {
            beneficiarioUsuario = selecionarUsuarioIndividual(sc);
            if (beneficiarioUsuario == null) return;
        } else if (esc.equals("2")) {
            System.out.print("Nome do beneficiário: ");
            beneficiarioNome = sc.nextLine();
        }

        LancamentosFinanceiros lanc = new LancamentosFinanceiros();
        lanc.setTipo(tipo);
        lanc.setCategoria(categoria);
        lanc.setValor(valor);
        lanc.setData(data);
        lanc.setPagador(pagador);
        lanc.setBeneficiario(beneficiarioUsuario);
        lanc.setBeneficiarioNome(beneficiarioNome);
        lanc.setConta(conta);

        try {
            if (tipo.equals("despesa")) conta.sacar(valor);
            else conta.depositar(valor);

            gestor.adicionarLancamento(lanc);
            System.out.println("Lançamento realizado!");

            notificador.notificar(
                    "Novo lançamento: " + tipo.toUpperCase() + " | Categoria: " + categoria + " | Valor: R$ " + valor + " | Usuário: " + pagador.getNome()
            );

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void criarTransferencia(Scanner sc) {
        Usuario executor = selecionarUsuarioExecutor(sc);
        if (executor == null) return;

        UsuarioIndividual pagador = selecionarUsuarioIndividual(sc);
        if (pagador == null) return;

        if (!pagador.podeGerenciar(executor)) {
            System.out.println("Você não tem permissão para transferir dessa conta.");
            return;
        }

        ContaFinanceira origem = selecionarContaDoUsuario(sc, pagador);
        if (origem == null) return;

        ContaFinanceira destino = selecionarContaGlobal(sc);
        if (destino == null) return;

        System.out.print("Valor da transferência: R$");
        double valor;

        try {
            valor = Double.parseDouble(sc.nextLine());
            if (valor <= 0) return;
        } catch (Exception e) { return; }

        try {
            origem.transferir(destino, valor);

            LancamentosFinanceiros saida = new LancamentosFinanceiros();
            saida.setTipo("despesa");
            saida.setCategoria("transferência");
            saida.setValor(valor);
            saida.setData(java.time.LocalDate.now().toString());
            saida.setPagador(pagador);
            saida.setConta(origem);

            LancamentosFinanceiros entrada = new LancamentosFinanceiros();
            entrada.setTipo("receita");
            entrada.setCategoria("transferência");
            entrada.setValor(valor);
            entrada.setData(java.time.LocalDate.now().toString());
            entrada.setConta(destino);

            gestor.adicionarLancamento(saida);
            gestor.adicionarLancamento(entrada);

            System.out.println("Transferência concluída!");

            notificador.notificar(
                    "Transferência: R$ " + valor + " | De conta " + origem.getNumeroConta() + " -> " + destino.getNumeroConta()
            );

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static UsuarioIndividual selecionarUsuarioIndividual(Scanner sc) {
        List<UsuarioIndividual> lista = MenuUsuarios.usuariosIndividuais;
        if (lista.isEmpty()) return null;

        System.out.println("Usuários individuais:");
        for (UsuarioIndividual u : lista)
            System.out.println("ID = " + u.getId() + " | Nome = " + u.getNome());

        System.out.print("ID ou 0 para cancelar: ");
        int id;

        try { id = Integer.parseInt(sc.nextLine()); }
        catch (Exception e) { return null; }

        if (id == 0) return null;

        for (UsuarioIndividual u : lista)
            if (u.getId() == id) return u;

        return null;
    }

    private static ContaFinanceira selecionarContaDoUsuario(Scanner sc, Usuario usuario) {
        List<Object> contas = usuario.getContas();
        if (contas == null || contas.isEmpty()) return null;

        for (Object o : contas)
            if (o instanceof ContaFinanceira)
                ((ContaFinanceira) o).exibirInformacoes();

        System.out.print("Número da conta: ");
        int numero;

        try { numero = Integer.parseInt(sc.nextLine()); }
        catch (Exception e) { return null; }

        for (Object o : contas) {
            ContaFinanceira c = (ContaFinanceira) o;
            if (c.getNumeroConta() == numero) return c;
        }
        return null;
    }

    private static ContaFinanceira selecionarContaGlobal(Scanner sc) {
        for (UsuarioIndividual u : MenuUsuarios.usuariosIndividuais)
            for (Object o : u.getContas())
                if (o instanceof ContaFinanceira)
                    ((ContaFinanceira) o).exibirInformacoes();

        for (Grupo g : MenuUsuarios.grupos)
            for (Object o : g.getContas())
                if (o instanceof ContaFinanceira)
                    ((ContaFinanceira) o).exibirInformacoes();

        System.out.print("Número da conta: ");
        int numero;

        try { numero = Integer.parseInt(sc.nextLine()); }
        catch (Exception e) { return null; }

        for (UsuarioIndividual u : MenuUsuarios.usuariosIndividuais)
            for (Object o : u.getContas()) {
                ContaFinanceira c = (ContaFinanceira) o;
                if (c.getNumeroConta() == numero) return c;
            }

        for (Grupo g : MenuUsuarios.grupos)
            for (Object o : g.getContas()) {
                ContaFinanceira c = (ContaFinanceira) o;
                if (c.getNumeroConta() == numero) return c;
            }

        return null;
    }

    private static Usuario selecionarUsuarioExecutor(Scanner sc) {
        System.out.println("USUÁRIOS DISPONÍVEIS:");

        for (UsuarioIndividual u : MenuUsuarios.usuariosIndividuais)
            System.out.println("ID = " + u.getId() + " | Nome = " + u.getNome());

        for (Grupo g : MenuUsuarios.grupos)
            System.out.println("ID = " + g.getId() + " | Grupo = " + g.getTipoGrupo());

        System.out.print("ID do executor: ");
        int id;

        try { id = Integer.parseInt(sc.nextLine()); }
        catch (Exception e) { return null; }

        for (UsuarioIndividual u : MenuUsuarios.usuariosIndividuais)
            if (u.getId() == id) return u;

        for (Grupo g : MenuUsuarios.grupos)
            if (g.getId() == id) return g;

        return null;
    }
}