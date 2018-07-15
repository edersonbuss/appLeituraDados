package com.edersonbuss.appleituradados.model;

import java.io.Serializable;
import java.util.List;

public class Venda implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String salesmanName;
    private List<ItemVenda> itens;
    //003çSale IDç[Item ID-Item Quantity-Item Price]çSalesman name

    public Venda() {
    }

    public Venda(Long id, String salesmanName, List<ItemVenda> itens) {
        this.id = id;
        this.salesmanName = salesmanName;
        this.itens = itens;
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    public void setItens(List<ItemVenda> itens) {
        this.itens = itens;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getSalesmanName() {
        return salesmanName;
    }

    public void setSalesmanName(String salesmanName) {
        this.salesmanName = salesmanName;
    }

    public enum Campos {

        ID(1),
        ITEM_VENDA(2),
        NOME_VENDEDOR(3);

        private final int index;

        private Campos(final int index) {
            this.index = index;
        }

        public int getIndex() {
            return this.index;
        }

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((this.id == null) ? 0 : this.id.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Venda)) {
            return false;
        }
        final Venda other = (Venda) obj;
        if (this.id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!this.id.equals(other.id)) {
            return false;
        }
        return true;
    }

}
