package com.edersonbuss.appleituradados.model;

import java.io.Serializable;
import java.nio.file.Path;

/**
 * Classe que representa o resumo o relatorio de um arquivo processado.
 *
 * @author Ederson Buss
 */
public class Resumo implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long quantidadeClientes;
    private Long quantidadeVendedores;
    private Long idVendaMaiorValor;
    private String nomeVendedorMenorValor;
    private Path pathArquivoDestino;

    public Resumo() {
    }

    public Resumo(final Long quantidadeClientes, final Long quantidadeVendedores, final Long idVendaMaiorValor,
            final String nomeVendedorMenorValor, final Path pathArquivoDestino) {
        this.quantidadeClientes = quantidadeClientes;
        this.quantidadeVendedores = quantidadeVendedores;
        this.idVendaMaiorValor = idVendaMaiorValor;
        this.nomeVendedorMenorValor = nomeVendedorMenorValor;
        this.pathArquivoDestino = pathArquivoDestino;
    }

    public Long getQuantidadeClientes() {
        return this.quantidadeClientes;
    }

    public void setQuantidadeClientes(final Long quantidadeClientes) {
        this.quantidadeClientes = quantidadeClientes;
    }

    public Long getQuantidadeVendedores() {
        return this.quantidadeVendedores;
    }

    public void setQuantidadeVendedores(final Long quantidadeVendedores) {
        this.quantidadeVendedores = quantidadeVendedores;
    }

    public Long getIdVendaMaiorValor() {
        return this.idVendaMaiorValor;
    }

    public void setIdVendaMaiorValor(final Long idVendaMaiorValor) {
        this.idVendaMaiorValor = idVendaMaiorValor;
    }

    public String getNomeVendedorMenorValor() {
        return nomeVendedorMenorValor;
    }

    public void setNomeVendedorMenorValor(final String nomeVendedorMenorValor) {
        this.nomeVendedorMenorValor = nomeVendedorMenorValor;
    }

    public Path getPathArquivoDestino() {
        return this.pathArquivoDestino;
    }

    public void setPathArquivoDestino(final Path pathArquivoDestino) {
        this.pathArquivoDestino = pathArquivoDestino;
    }

}
