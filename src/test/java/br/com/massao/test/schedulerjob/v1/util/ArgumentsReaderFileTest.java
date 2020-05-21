package br.com.massao.test.schedulerjob.v1.util;

import br.com.massao.test.schedulerjob.v1.bean.ExecutionWindow;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

public class ArgumentsReaderFileTest {
    ArgumentsReaderFile reader = null;
    String dataInicio = LocalDateTime.parse("2019-11-10 09:00:00", ExecutionWindow.FORMATTER).format(ExecutionWindow.FORMATTER);
    String dataFim = LocalDateTime.parse("2019-11-11 12:00:00", ExecutionWindow.FORMATTER).format(ExecutionWindow.FORMATTER);

    @Before
    public void setUp() throws Exception {

    }


    @Test(expected = IllegalArgumentException.class)
    public void recusarArgumentoNulo() {
        String args[] = new String[] {};
        reader = new ArgumentsReaderFile(args);
    }


    @Test(expected = IllegalArgumentException.class)
    public void recusarArgumentoMenorQue3() {
        String args[] = new String[] {"1"};
        reader = new ArgumentsReaderFile(args);
    }


    @Test(expected = IllegalArgumentException.class)
    public void recusarArgumentoMaiorQue3() {
        String args[] = new String[] {"1", "2", "3", "4"};
        reader = new ArgumentsReaderFile(args);
    }


    @Test
    public void lerConteudoDoArquivo() {
        Path dir = Paths.get("src", "main", "resources", "massas");
        String filename = dir + "\\01-massa_prova.json";
        String args[] = new String[] {dataInicio, dataFim, filename};

        reader = new ArgumentsReaderFile(args);
        String json = reader.readFromFile(filename);

        Assert.assertTrue(!json.isEmpty());
    }
}