package com.edersonbuss.appleituradados;

import com.edersonbuss.appleituradados.exceptions.AppLeituraDadosException;
import com.edersonbuss.appleituradados.factories.ResumoFactory;
import com.edersonbuss.appleituradados.factories.VendaFactory;
import com.edersonbuss.appleituradados.model.ItemVenda;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import com.edersonbuss.appleituradados.model.Resumo;
import com.edersonbuss.appleituradados.model.TipoDados;
import com.edersonbuss.appleituradados.model.Venda;
import com.edersonbuss.appleituradados.util.Constants;
import com.edersonbuss.appleituradados.util.FileUtil;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ederson Buss
 */
public class Application {

    private final static Logger LOGGER = Logger.getLogger(Application.class.getName());

    public static void main(final String[] args) throws IOException {
        final Path diretorioIn = Paths.get(Constants.PATH_DIRETORIO_IN);
        FileUtil.criarDiretorios();
        Arrays.asList(diretorioIn.toFile().listFiles())
                .stream()
                .filter(f -> f.getName().endsWith(Constants.EXTENSAO_ARQUIVO_A_PROCESSAR))
                .forEach((arquivo) -> {
                    final Application app = new Application();
                    app.processarArquivo(arquivo);
                });
        JOptionPane.showMessageDialog(null, "Arquivo Processado");
    }

    protected void processarArquivo(final File arquivo) {
        Resumo resumo = null;
        try {
            resumo = this.processarResumo(arquivo);
            this.salvarResumoArquivo(resumo);
        } catch (AppLeituraDadosException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    protected void salvarResumoArquivo(final Resumo resumo) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(resumo.getPathArquivoDestino().toFile()))) {
            bufferedWriter.write("Quantidade de clientes no arquivo de entrada: " + resumo.getQuantidadeClientes());
            bufferedWriter.newLine();
            bufferedWriter.write("Quantidade de vendedor no arquivo de entrada: " + resumo.getQuantidadeVendedores());
            bufferedWriter.newLine();
            bufferedWriter.write("ID da venda mais cara: " + resumo.getIdVendaMaiorValor());
            bufferedWriter.newLine();
            bufferedWriter.write("O pior vendedor: " + resumo.getNomeVendedorMenorValor());
        } catch (final IOException ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }
    }

    protected Resumo processarResumo(final File arquivo) throws AppLeituraDadosException {
        int lastIndex = arquivo.getName().lastIndexOf(Constants.EXTENSAO_DAT);
        String nomeArquivo = arquivo.getName().substring(0, lastIndex) + Constants.EXTENSAO_ARQUIVO_PROCESSADO;
        final Path pathArquivoDestino = Paths.get(Constants.PATH_DIRETORIO_OUT).resolve(nomeArquivo);
        List<String> linhasArquivo = new ArrayList<>();
        try {
            linhasArquivo = Files.readAllLines(arquivo.toPath());
        } catch (final IOException ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
            throw new AppLeituraDadosException(ex.toString());
        }
        return montaResumo(linhasArquivo, pathArquivoDestino);
    }

    private Resumo montaResumo(List<String> linhasArquivo, final Path pathArquivoDestino) {
        final Long quantidadeClientes = linhasArquivo.stream().filter(TipoDados.CLIENTE::verificarTipoDado).count();
        final Long quantidadeVendedores = linhasArquivo.stream().filter(TipoDados.VENDEDOR::verificarTipoDado).count();
        final List<Venda> vendas = this.listarVendas(linhasArquivo);
        final Long idVendaMaiorValor = getIdVendaMaiorValor(vendas);
        final String nomeVendedorMenosVendeu = getNomePiorVendedor(vendas);
        return ResumoFactory.createResumo(quantidadeClientes, quantidadeVendedores, idVendaMaiorValor, nomeVendedorMenosVendeu, pathArquivoDestino);
    }

    private Long getIdVendaMaiorValor(List<Venda> vendas) {
        BigDecimal maiorValor = BigDecimal.ZERO;
        Long idVendaMaiorValor = 0L;
        for (Venda venda : vendas) {
            BigDecimal totalVenda = getTotalVenda(venda);
            if (maiorValor.compareTo(totalVenda) <= 0) {
                idVendaMaiorValor = venda.getId();
                maiorValor = totalVenda;
            }
        }
        return idVendaMaiorValor;
    }

    private String getNomePiorVendedor(List<Venda> vendas) {
        BigDecimal piorPrecoVenda = getTotalVenda(vendas.get(0));
        Venda piorVendedor = vendas.get(0);
        for (Venda venda : vendas) {
            if (piorPrecoVenda.compareTo(getTotalVenda(venda)) < 0) {
            } else {
                piorPrecoVenda = getTotalVenda(venda);
                piorVendedor = venda;
            }
        }
        return piorVendedor.getSalesmanName();
    }

    private BigDecimal getTotalVenda(Venda venda) {
        BigDecimal totalVenda = BigDecimal.ZERO;
        for (ItemVenda item : venda.getItens()) {
            totalVenda = totalVenda.add(item.getPrice());
        }
        return totalVenda;
    }

    /**
     * Filtra as linhas do arquivo lido, selecionando todas as vendas.
     *
     * @param linhasArquivo
     * @return
     */
    protected List<Venda> listarVendas(final List<String> linhasArquivo) {
        final List<String> linhasVendas = linhasArquivo
                .stream()
                .filter(TipoDados.VENDA::verificarTipoDado)
                .collect(Collectors.toList());
        return this.converterLinhasParaVendas(linhasVendas)
                .stream()
                .collect(Collectors.toList());
    }

    protected List<Venda> converterLinhasParaVendas(final List<String> linhas) {
        final List<Venda> vendas = new ArrayList<>();
        linhas.forEach((linha) -> {
            try {
                vendas.add(VendaFactory.criarVenda(linha));
            } catch (final Exception ex) {
                LOGGER.log(Level.SEVERE, ex.toString(), ex);
            } finally {
            }
        });
        return vendas;
    }

}
