package Persistencia;

public class PersistenciaArquivoTXT extends PersistenciaDados {
    @Override
    public void salvar() {
        System.out.println("Salvando dados no arquivo TXT...");
    }

    @Override
    public void carregar() {
        System.out.println("Carregando dados do arquivo TXT...");
    }
}
