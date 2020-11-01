package br.com.massao.test.schedulerjob.v1.bean;

import br.com.massao.test.schedulerjob.v1.helper.DateUtils;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertTrue;

public class ExecutionWindowTest {
    final static String DATA_INICIO = DateUtils.getDate("2019-11-10 09:00:00");
    final static String DATA_FIM = DateUtils.getDate("2019-11-11 12:00:00");
    ExecutionWindow window;

    @Before
    public void setUp() {
        window = new ExecutionWindow(DATA_INICIO, DATA_FIM);
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
        window = new ExecutionWindow(DATA_FIM, DATA_INICIO);
    }


    @Test
    public void dataAntesDaJanela() {
        LocalDateTime dataAntes = DateUtils.getDateTime("2000-04-15 00:00:00");

        assertTrue(window.isBeforeWindow(dataAntes));
    }


    @Test
    public void dataIgualInicioJanela() {
        LocalDateTime dataIgual = DateUtils.getDateTime(DATA_INICIO);

        assertTrue(window.isBeforeWindow(dataIgual));
    }
}