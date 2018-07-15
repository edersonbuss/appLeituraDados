package com.edersonbuss.appleituradados;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.edersonbuss.appleituradados.model.Venda;

/**
 * Classe para testes unitarios de {@link Application}.
 *
 * @author Ederson Buss
 */
public class ApplicationTest {

    @InjectMocks
    private final Application applicationSpy = Mockito.spy(Application.class);

    @Before
    public void setUpBefore() {
        MockitoAnnotations.initMocks(Application.class);
    }

    /**
     * Testa o metodo {@link Application#listarVendas}.
     */
    @Test
    public void listarVendasTest() {
        final List<String> linhasArquivo = new ArrayList<>();
        linhasArquivo.add("001ç1234567891234çPedroç50000");
        linhasArquivo.add("001ç3245678865434çPauloç40000.99");
        linhasArquivo.add("002ç2345675434544345çJose da SilvaçRural");
        linhasArquivo.add("002ç2345675433444345çEduardo PereiraçRural");
        linhasArquivo.add("003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro");
        linhasArquivo.add("003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo");
        final List<Venda> actual = this.applicationSpy.listarVendas(linhasArquivo);
        Assert.assertEquals(2, actual.size());
    }

    /**
     * Testa o metodo converterLinhasParaVendasTest
     */
    @Test
    public void converterLinhasParaVendasTest() {
        final List<String> linhasArquivo = new ArrayList<>();
        linhasArquivo.add("003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro");
        linhasArquivo.add("003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo");
        final List<Venda> actual = this.applicationSpy.converterLinhasParaVendas(linhasArquivo);
        Assert.assertEquals(2, actual.size());
    }

}
