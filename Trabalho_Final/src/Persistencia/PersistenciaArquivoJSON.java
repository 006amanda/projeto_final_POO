package Persistencia;

public class PersistenciaArquivoJSON extends PersistenciaDados {
    @Override
    public void salvar() {
        System.out.println("Salvando dados no arquivo JSON...");
    }

    @Override
    public void carregar() {
        System.out.println("Carregando dados do arquivo JSON...");
    }
}
