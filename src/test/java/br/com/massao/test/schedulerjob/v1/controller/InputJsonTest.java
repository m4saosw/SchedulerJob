package br.com.massao.test.schedulerjob.v1.controller;

import br.com.massao.test.schedulerjob.v1.bean.ExecutionWindow;
import br.com.massao.test.schedulerjob.v1.util.ArgumentsReaderFile;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.time.LocalDateTime;

public class InputJsonTest {
    String dataInicio = LocalDateTime.parse("2019-11-10 09:00:00", ExecutionWindow.FORMATTER).format(ExecutionWindow.FORMATTER);
    String dataFim    = LocalDateTime.parse("2019-11-11 12:00:00", ExecutionWindow.FORMATTER).format(ExecutionWindow.FORMATTER);


    @Test(expected = IllegalArgumentException.class)
    public void rejeitarProcessarInputArgumentosInvalidosAMenor() {
        String args[] = new String[] {dataInicio};
        InputJson input = new InputJson(new ArgumentsReaderFile(args));

        input.process();
    }


    @Test(expected = IllegalArgumentException.class)
    public void rejeitarProcessarInputArgumentosInvalidosAMaior() {
        String args[] = new String[] {dataInicio, dataFim, "arquivo", "parametro a mais"};
        InputJson input = new InputJson(new ArgumentsReaderFile(args));

        input.process();
    }


    @Test
    @Ignore
    public void processarInputValido() {
        String fileName = "";
        String args[] = new String[] {dataInicio, dataFim, fileName};
        InputJson input = new InputJson(new ArgumentsReaderFile(args));

        input.process();

        Assert.assertNotNull(input);
    }


}