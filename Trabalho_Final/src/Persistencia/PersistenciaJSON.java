package Persistencia;

public class PersistenciaJSON extends PersistenciaDados {
    @Override
    public void salvar() {
        System.out.println("Salvando dados em JSON...");
    }

    @Override
    public void carregar() {
        System.out.println("Carregando dados do JSON...");
    }
}
