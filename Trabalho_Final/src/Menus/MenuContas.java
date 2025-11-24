package Menus;

import java.util.*;
import Contas.*;
import Factory.ContaFactory;
import Usuarios.*;
import Excecoes.SaldoInsuficienteException;
import Lancamentos.*;

public class MenuContas {
    public static void exibir() {
        Scanner sc = new Scanner(System.in);
        int op = 0;

        while (op != 7) {
            System.out.println("1 - Criar Conta");
            System.out.println("2 - Listar Contas");
            System.out.println("3 - Depositar");
            System.out.println("4 - Sacar");
            System.out.println("5 - Transferir");
            System.out.println("6 - Excluir Conta");
            System.out.println("7 - Voltar");
            System.out.print("Escolha uma opção: ");

            try { op = Integer.parseInt(sc.nextLine()); }
            catch (Exception e) { op = 0; }

            System.out.println();

            if (op == 1) {
                criarConta(sc);
            }
            else if (op == 2) {
                listarContas();
            }
            else if (op == 3) {
                operarDeposito(sc);
            }
            else if (op == 4) {
                operarSaque(sc);
            }
            else if (op == 5) {
                operarTransferencia(sc);
            }
            else if (op == 6) {
                excluirConta(sc);
            }
            else if (op == 7) {
                System.out.println("Voltando ao menu principal...");
                break;
            }
            else {
                System.out.println("Opção inválida!");
            }
        }
    }

    private static void criarConta(Scanner sc) {
        Usuario executor = selecionarUsuarioExecutor(sc);
        if (executor == null) return;

        Usuario titular = selecionarTitular(sc);
        if (titular == null) return;

        if (!titular.podeGerenciar(executor)) return;

        System.out.print("Digite o tipo da conta: ");
        String tipo = sc.nextLine();

        ContaFinanceira conta = ContaFactory.criarConta(tipo, titular);
        if (conta == null) return;

        titular.adicionarConta(conta);
        conta.exibirInformacoes();
    }

    private static void listarContas() {
        for (UsuarioIndividual u : MenuUsuarios.usuariosIndividuais) {
            System.out.println("Usuário: " + u.getNome() + " (ID = " + u.getId() + ")");
            for (Object o : u.getContas())
                if (o instanceof ContaFinanceira)
                    ((ContaFinanceira) o).exibirInformacoes();
        }

        for (Grupo g : MenuUsuarios.grupos) {
            System.out.println("Grupo: " + g.getTipoGrupo() + " (Admin = " + g.getResponsavel().getNome() + ")");
            for (Object o : g.getContas())
                if (o instanceof ContaFinanceira)
                    ((ContaFinanceira) o).exibirInformacoes();
        }
    }

    private static void operarDeposito(Scanner sc) {
        Usuario executor = selecionarUsuarioExecutor(sc);
        if (executor == null) return;

        ContaFinanceira destino = selecionarContaGlobal(sc);
        if (destino == null) return;

        System.out.print("Valor a depositar: R$");
        double valor;

        try { valor = Double.parseDouble(sc.nextLine()); }
        catch (Exception e) { return; }

        if (valor <= 0) return;

        destino.depositar(valor);
        registrarDeposito(destino, valor);
        destino.exibirInformacoes();
    }

    private static void operarSaque(Scanner sc) {
        Usuario executor = selecionarUsuarioExecutor(sc);
        if (executor == null) return;

        ContaFinanceira conta = selecionarContaGlobal(sc);
        if (conta == null) return;

        Usuario titular = obterTitularDaConta(conta);
        if (titular == null) return;

        if (!titular.podeGerenciar(executor)) return;

        System.out.print("Valor a sacar: R$");
        double valor;

        try { valor = Double.parseDouble(sc.nextLine()); }
        catch (Exception e) { return; }

        if (valor <= 0) return;

        try {
            conta.sacar(valor);
            registrarSaque(conta, titular, valor);
            conta.exibirInformacoes();
        }
        catch (SaldoInsuficienteException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void operarTransferencia(Scanner sc) {
        Usuario executor = selecionarUsuarioExecutor(sc);
        if (executor == null) return;

        ContaFinanceira origem = selecionarContaGlobal(sc);
        if (origem == null) return;

        Usuario titularOrigem = obterTitularDaConta(origem);
        if (titularOrigem == null) return;

        if (!titularOrigem.podeGerenciar(executor)) return;

        ContaFinanceira destino = selecionarContaGlobal(sc);
        if (destino == null) return;

        System.out.print("Valor da transferência: R$");
        double valor;

        try { valor = Double.parseDouble(sc.nextLine()); }
        catch (Exception e) { return; }

        if (valor <= 0) return;

        try {
            origem.transferir(destino, valor);
            registrarTransferencia(origem, destino, titularOrigem, valor);
            origem.exibirInformacoes();
            destino.exibirInformacoes();
        }
        catch (SaldoInsuficienteException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void excluirConta(Scanner sc) {
        Usuario executor = selecionarUsuarioExecutor(sc);
        if (executor == null) return;

        ContaFinanceira conta = selecionarContaGlobal(sc);
        if (conta == null) return;

        Usuario titular = obterTitularDaConta(conta);
        if (titular == null) return;

        if (!titular.podeGerenciar(executor)) return;

        titular.getContas().remove(conta);
        registrarExclusaoConta(titular);

        System.out.println("Conta excluída com sucesso.");
    }

    private static void registrarExclusaoConta(Usuario titular) {
        LancamentosFinanceiros lanc = new LancamentosFinanceiros();
        lanc.setTipo("informativo");
        lanc.setCategoria("conta excluída");
        lanc.setValor(0);
        lanc.setData(java.time.LocalDate.now().toString());
        lanc.setPagador(titular);
        lanc.setConta(null);
        MenuLancamentos.getGestor().adicionarLancamento(lanc);
    }

    private static void registrarDeposito(ContaFinanceira destino, double valor) {
        LancamentosFinanceiros lanc = new LancamentosFinanceiros();
        lanc.setTipo("receita");
        lanc.setCategoria("depósito");
        lanc.setValor(valor);
        lanc.setData(java.time.LocalDate.now().toString());
        lanc.setConta(destino);
        MenuLancamentos.getGestor().adicionarLancamento(lanc);
    }

    private static void registrarSaque(ContaFinanceira conta, Usuario titular, double valor) {
        LancamentosFinanceiros lanc = new LancamentosFinanceiros();
        lanc.setTipo("despesa");
        lanc.setCategoria("saque");
        lanc.setValor(valor);
        lanc.setData(java.time.LocalDate.now().toString());
        lanc.setPagador(titular);
        lanc.setConta(conta);
        MenuLancamentos.getGestor().adicionarLancamento(lanc);
    }

    private static void registrarTransferencia(ContaFinanceira origem, ContaFinanceira destino, Usuario pagador, double valor) {
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

        MenuLancamentos.getGestor().adicionarLancamento(saida);
        MenuLancamentos.getGestor().adicionarLancamento(entrada);
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

    private static Usuario obterTitularDaConta(ContaFinanceira conta) {
        for (UsuarioIndividual u : MenuUsuarios.usuariosIndividuais)
            for (Object o : u.getContas()) {
                ContaFinanceira c = (ContaFinanceira) o;
                if (c.getNumeroConta() == conta.getNumeroConta()) return u;
            }

        for (Grupo g : MenuUsuarios.grupos)
            for (Object o : g.getContas()) {
                ContaFinanceira c = (ContaFinanceira) o;
                if (c.getNumeroConta() == conta.getNumeroConta()) return g;
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

    private static Usuario selecionarTitular(Scanner sc) {
        System.out.print("ID do titular: ");
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