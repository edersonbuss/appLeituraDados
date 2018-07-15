package com.edersonbuss.appleituradados.model;

public enum TipoDados {

    VENDEDOR("001"),
    CLIENTE("002"),
    VENDA("003");

    final String idTipoDado;

    private TipoDados(final String idTipoDado) {
        this.idTipoDado = idTipoDado;
    }

    public boolean verificarTipoDado(final String dado) {
        return dado.startsWith(this.idTipoDado);
    }

    public String getIdTipoDado() {
        return this.idTipoDado;
    }

}
