package br.com.massao.test.schedulerjob.v1.controller;

import br.com.massao.test.schedulerjob.v1.bean.ExecutionWindow;
import br.com.massao.test.schedulerjob.v1.util.ArgumentsReaderFile;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class OutputJsonTest {
    String dataInicio = LocalDateTime.parse("2019-11-10 09:00:00", ExecutionWindow.FORMATTER).format(ExecutionWindow.FORMATTER);
    String dataFim    = LocalDateTime.parse("2019-11-11 12:00:00", ExecutionWindow.FORMATTER).format(ExecutionWindow.FORMATTER);


    @Test(expected = IllegalArgumentException.class)
    public void rejeitarOutputDadoUmInputInvalido_JanelaInvalida() {
        Path dir = Paths.get("src", "main", "resources", "massas");
        String filename = dir + "\\01-massa_prova.json";
        String args[] = new String[] {dataFim, dataInicio, filename};

        InputJson input = new InputJson(new ArgumentsReaderFile(args));

        input.process();

        OutputJson output = new OutputJson(input);
        output.process();
    }


    @Test
    public void processarOutputDadoUmInputValido() {
        Path dir = Paths.get("src", "main", "resources", "massas");
        String filename = dir + "\\01-massa_prova.json";
        String args[] = new String[] {dataInicio, dataFim, filename};

        InputJson input = new InputJson(new ArgumentsReaderFile(args));

        input.process();

        OutputJson output = new OutputJson(input);
        output.process();

        // deve retornar grupo de jobs
        assertNotNull(output.getGruposSaidaString());
        assertEquals( "[[1,2,3]]", output.getGruposSaidaString());
    }
}