package Persistencia;

public class PersistenciaFactory {
    public static PersistenciaDados criar(String tipo) {
        if(tipo.equalsIgnoreCase("txt")) {
            return new PersistenciaTXT();
        } else if(tipo.equalsIgnoreCase("json")) {
            return new PersistenciaJSON();
        }
        return null;
    }
}
