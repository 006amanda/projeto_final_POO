package Persistencia;

public class PersistenciaTXT extends PersistenciaDados {
    @Override
    public void salvar() {
        System.out.println("Salvando dados em TXT...");
    }

    @Override
    public void carregar() {
        System.out.println("Carregando dados do TXT...");
    }
}