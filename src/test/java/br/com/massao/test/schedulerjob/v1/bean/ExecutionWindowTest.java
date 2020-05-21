package br.com.massao.test.schedulerjob.v1.bean;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertTrue;

public class ExecutionWindowTest {
    String dataInicio = LocalDateTime.parse("2019-11-10 09:00:00", ExecutionWindow.FORMATTER).format(ExecutionWindow.FORMATTER);
    String dataFim = LocalDateTime.parse("2019-11-11 12:00:00", ExecutionWindow.FORMATTER).format(ExecutionWindow.FORMATTER);
    ExecutionWindow window;

    @Before
    public void setUp() throws Exception {
        window = new ExecutionWindow(dataInicio, dataFim);
    }


    @Test(expected = IllegalArgumentException.class)
    public void recusarJanelaVazia() {
        window = new ExecutionWindow("", "");
    }


    @Test(expected = IllegalArgumentException.class)
    public void recusarJanelaNula() {
        window = new ExecutionWindow(null, "");
    }


    @Test(expected = IllegalArgumentException.class)
    public void recusarDataFimAntesDataInicio() {
        window = new ExecutionWindow(dataFim, dataInicio);
    }


    @Test
    public void aceitarDataAntesDaJanela() {
        LocalDateTime dataAntes = LocalDateTime.parse("2000-04-15 00:00:00", ExecutionWindow.FORMATTER);

        assertTrue(window.isBeforeWindow(dataAntes));
    }


    @Test
    public void aceitarDataIgualInicioJanela() {
        LocalDateTime dataIgual = LocalDateTime.parse(dataInicio, ExecutionWindow.FORMATTER);

        assertTrue(window.isBeforeWindow(dataIgual));
    }
}