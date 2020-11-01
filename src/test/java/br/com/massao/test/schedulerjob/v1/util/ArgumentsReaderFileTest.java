package br.com.massao.test.schedulerjob.v1.util;

import br.com.massao.test.schedulerjob.v1.helper.DateUtils;
import br.com.massao.test.schedulerjob.v1.helper.FileUtils;
import br.com.massao.test.schedulerjob.v1.helper.JsonInput;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ArgumentsReaderFileTest {

    ArgumentsReaderFile reader = null;
    final static String DATA_INICIO = DateUtils.getDate("2019-11-10 09:00:00");
    final static String DATA_FIM = DateUtils.getDate("2019-11-11 12:00:00");


    @Before
    public void setUp() {

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
        String filename = FileUtils.getFileNamePath("01-massa_prova.json");
        String args[] = new String[] {DATA_INICIO, DATA_FIM, filename};

        String jsonString = JsonInput.FILE_01_MASSA_PROVA;

        reader = new ArgumentsReaderFile(args);

        Assert.assertEquals(jsonString + "\n", reader.getJson());
    }
}