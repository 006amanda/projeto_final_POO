package Factory;

import Contas.*;
import Usuarios.*;

public class ContaFactory {
    public static ContaFinanceira criarConta(String tipo, Usuario titular) {
        if (tipo == null || titular == null) {
            return null;
        }

        if (tipo == null || titular == null) {
            return null;
        }

        tipo = tipo.toLowerCase();

        if (tipo.equals("conta corrente")) {
            return new ContaCorrente(titular, 500);

        } else if (tipo.equals("conta digital")) {
            return new ContaDigital(titular);

        } else if (tipo.equals("cartão") || tipo.equals("cartão de crédito")) {
            return new CartaoCredito(titular, 1000);

        } else if (tipo.equals("cofrinho")) {
            return new Cofrinho(titular);

        } else if (tipo.equals("investimento") || tipo.equals("carteira de investimento")) {
            return new CarteiraInvestimento(titular);

        } else {

            System.out.println("Tipo de conta inválido: " + tipo);
            return null;
        }
    }
}