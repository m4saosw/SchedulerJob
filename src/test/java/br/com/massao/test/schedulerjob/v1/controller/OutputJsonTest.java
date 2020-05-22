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

    InputJson input = null;
    OutputJson output = null;

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
    public void processarOutputDadoUmInputValido_massa01_exemploMassa() {
        Path dir = Paths.get("src", "main", "resources", "massas");
        String filename = dir + "\\" + "01-massa_prova.json";
        String args[] = new String[] {dataInicio, dataFim, filename};

        InputJson input = new InputJson(new ArgumentsReaderFile(args));

        input.process();

        OutputJson output = new OutputJson(input);
        output.process();

        // deve retornar grupo de jobs
        assertNotNull(output.getGroupsOutString());
        assertEquals( "[[1,3],[2]]", output.getGroupsOutString());
    }


    @Test(expected = IllegalArgumentException.class)
    public void rejeitarOutputDadoUmInputInvalido_massa02_conteudoVazio() {
        Path dir = Paths.get("src", "main", "resources", "massas");
        String filename = dir + "\\" + "02-vazio.json.json";
        String args[] = new String[] {dataInicio, dataFim, filename};

        InputJson input = new InputJson(new ArgumentsReaderFile(args));

        input.process();

        OutputJson output = new OutputJson(input);
        output.process();
    }


    @Test(expected = IllegalArgumentException.class)
    public void rejeitarOutputDadoUmInputInvalido_massa03_conteudoInvalido() {
        Path dir = Paths.get("src", "main", "resources", "massas");
        String filename = dir + "\\" + "03-invalido.json";
        String args[] = new String[] {dataInicio, dataFim, filename};

        InputJson input = new InputJson(new ArgumentsReaderFile(args));

        input.process();

        OutputJson output = new OutputJson(input);
        output.process();
    }


    @Test
    public void processarOutputDadoUmInputValido_massa04_semDuplicidades() {
        Path dir = Paths.get("src", "main", "resources", "massas");
        String filename = dir + "\\" + "04-ids_duplicados.json";
        String args[] = new String[] {dataInicio, dataFim, filename};

        InputJson input = new InputJson(new ArgumentsReaderFile(args));

        input.process();

        OutputJson output = new OutputJson(input);
        output.process();

        // deve retornar grupo de jobs
        assertNotNull(output.getGroupsOutString());
        assertEquals( "[[2,1]]", output.getGroupsOutString());
    }


    @Test
    public void processarOutputDadoUmInputValido_massa05() {
        Path dir = Paths.get("src", "main", "resources", "massas");
        String filename = dir + "\\" + "05-expirado_valido_posterior.json";
        String args[] = new String[] {dataInicio, dataFim, filename};

        InputJson input = new InputJson(new ArgumentsReaderFile(args));

        input.process();

        OutputJson output = new OutputJson(input);
        output.process();

        // deve retornar grupo de jobs
        assertNotNull(output.getGroupsOutString());
        assertEquals( "[[2,1]]", output.getGroupsOutString());
    }


    @Test
    public void processarOutputDadoUmInputValido_massa06() {
        Path dir = Paths.get("src", "main", "resources", "massas");
        String filename = dir + "\\" + "06-minha_tabela.json";
        String args[] = new String[] {dataInicio, dataFim, filename};

        InputJson input = new InputJson(new ArgumentsReaderFile(args));

        input.process();

        OutputJson output = new OutputJson(input);
        output.process();

        // deve retornar grupo de jobs
        assertNotNull(output.getGroupsOutString());
        assertEquals( "[[1,4,6,8],[3,5],[7,9,2]]", output.getGroupsOutString());
    }


    @Test
    public void processarOutputDadoUmInputValido_massa07_jSonMinificado() {
        Path dir = Paths.get("src", "main", "resources", "massas");
        String filename = dir + "\\" + "07-minha_tabela_mesma_linha.json";
        String args[] = new String[] {dataInicio, dataFim, filename};

        InputJson input = new InputJson(new ArgumentsReaderFile(args));

        input.process();

        OutputJson output = new OutputJson(input);
        output.process();

        // deve retornar grupo de jobs
        assertNotNull(output.getGroupsOutString());
        assertEquals( "[[1,4,6,8],[3,5],[7,9,2]]", output.getGroupsOutString());
    }

    @Test
    public void processarOutputDadoUmInputValido_massa08_ordemDecrescente() {
        Path dir = Paths.get("src", "main", "resources", "massas");
        String filename = dir + "\\" + "08-minha_tabela_descendente.json";
        String args[] = new String[] {dataInicio, dataFim, filename};

        InputJson input = new InputJson(new ArgumentsReaderFile(args));

        input.process();

        OutputJson output = new OutputJson(input);
        output.process();

        // deve retornar grupo de jobs
        assertNotNull(output.getGroupsOutString());
        assertEquals( "[[11],[10,9,8,7,6,5,4,3],[2]]", output.getGroupsOutString());
    }


    @Test(expected = IllegalArgumentException.class)
    public void rejeitarOutputDadoUmInputInvalido_massa09() {
        Path dir = Paths.get("src", "main", "resources", "massas");
        String filename = dir + "\\" + "09-validos_e_invalidos.json";
        String args[] = new String[] {dataInicio, dataFim, filename};

        InputJson input = new InputJson(new ArgumentsReaderFile(args));

        input.process();

        OutputJson output = new OutputJson(input);
        output.process();
    }


}