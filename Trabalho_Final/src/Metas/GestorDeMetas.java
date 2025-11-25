package Metas;

import java.util.*;

public class GestorDeMetas {
    private List<MetasFinanceiras> metas = new ArrayList<>();

    public void adicionarMeta(MetasFinanceiras meta) {
        metas.add(meta);
    }

    public List<MetasFinanceiras> listarMetas() {
        return metas;
    }

    public void registrarProgresso(int index, double valor) {
        if (index >= 0 && index < metas.size()) {
            metas.get(index).adicionarProgresso(valor);
        }
    }
}