/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edersonbuss.appleituradados.factories;

import com.edersonbuss.appleituradados.model.Resumo;
import java.nio.file.Path;

/**
 *
 * @author Ederson
 */
public class ResumoFactory {

    public static Resumo createResumo(final Long quantidadeClientes, final Long quantidadeVendedores,
            final Long idVendaMaiorValor, final String nomeVendedorMenorValor, final Path pathArquivoDestino) {
        return new Resumo(quantidadeClientes, quantidadeVendedores, idVendaMaiorValor, nomeVendedorMenorValor, pathArquivoDestino);
    }

}
