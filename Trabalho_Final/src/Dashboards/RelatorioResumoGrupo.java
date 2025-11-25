package Dashboards;

import Usuarios.*;

public class RelatorioResumoGrupo implements Relatorios, Exportavel {
    private Grupo grupo;
    public RelatorioResumoGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    @Override
    public void gerar() {
        System.out.println("RESUMO DO GRUPO");

        System.out.println("Tipo: " + grupo.getTipoGrupo());
        System.out.println("Responsável: " + grupo.getResponsavel().getNome());
        System.out.println("Membros: ");

        if (grupo.getMembros().isEmpty()) {
            System.out.println("Nenhum membro no grupo.");
            return;
        }

        for (UsuarioIndividual m : grupo.getMembros()) {
            System.out.println(" - " + m.getNome());
        }
    }

    @Override
    public void exportar() {
        System.out.println("Exportando resumo do grupo...");
    }
}