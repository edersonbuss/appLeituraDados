/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edersonbuss.appleituradados.factories;

import com.edersonbuss.appleituradados.model.ItemVenda;
import com.edersonbuss.appleituradados.model.Venda;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Ederson
 */
public class VendaFactory {

    public static Venda criarVenda(String linha) {
        //003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro
        String[] linhaVenda = linha.split("ç");
        final Long id = Long.valueOf(linhaVenda[Venda.Campos.ID.getIndex()]);
        List<ItemVenda> listaItens = criarListaItens(linhaVenda[Venda.Campos.ITEM_VENDA.getIndex()]);
        final String nomeVendedor = linhaVenda[Venda.Campos.NOME_VENDEDOR.getIndex()];
        Venda venda = new Venda(id, nomeVendedor, listaItens);
        return venda;
    }

    private static List<ItemVenda> criarListaItens(String linhaItemVenda) {
        //[1-10-100,2-30-2.50,3-40-3.10]
        List<ItemVenda> listaItens = new ArrayList();
        List<String> items = Arrays.asList(linhaItemVenda.replace("[", "").replace("]", "").split(","));
        items.forEach((item) -> {
            listaItens.add(criarItemVenda(item));
        });
        return listaItens;
    }

    public static ItemVenda criarItemVenda(String linha) {
        //1-10-100
        String[] linhaItemVenda = linha.split("-");
        final Long idItem = Long.valueOf(linhaItemVenda[ItemVenda.Campos.ID_ITEM.getIndex()]);
        final Long quantidadeItem = Long.valueOf(linhaItemVenda[ItemVenda.Campos.QUANTITY.getIndex()]);
        BigDecimal precoItem = new BigDecimal(linhaItemVenda[ItemVenda.Campos.UNITY_PRICE.getIndex()]);
        ItemVenda itemVenda = new ItemVenda(idItem, quantidadeItem, precoItem);
        return itemVenda;
    }

}
