package Notificacoes;

public class NotificadorConsole implements Notificacao {
    @Override
    public void notificar(String msg) {
        System.out.println(msg);
    }
}