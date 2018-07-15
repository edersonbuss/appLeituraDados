/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edersonbuss.appleituradados.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Ederson
 */
public class ItemVenda implements Serializable {

    private static final long serialVersionUID = 1L;
    //ç[Item ID-Item Quantity-Item Price]
    private Long idItem;
    private Long quantity;
    private BigDecimal unitPrice;

    public ItemVenda() {
    }

    public ItemVenda(Long idItem, Long quantity, BigDecimal unitPrice) {
        this.idItem = idItem;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public Long getIdItem() {
        return idItem;
    }

    public void setIdItem(Long idItem) {
        this.idItem = idItem;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getPrice() {
        return unitPrice.multiply(new BigDecimal(quantity));
    }

    public enum Campos {
        ID_ITEM(0),
        QUANTITY(1),
        UNITY_PRICE(2);

        private final int index;

        private Campos(final int index) {
            this.index = index;
        }

        public int getIndex() {
            return this.index;
        }

    }

}
