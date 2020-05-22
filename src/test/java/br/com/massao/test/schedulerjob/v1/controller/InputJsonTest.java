package br.com.massao.test.schedulerjob.v1.controller;

import br.com.massao.test.schedulerjob.v1.bean.ExecutionWindow;
import br.com.massao.test.schedulerjob.v1.model.input.Job;
import br.com.massao.test.schedulerjob.v1.util.ArgumentsReaderFile;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

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
    public void processarInputValido() {
        Path dir = Paths.get("src", "main", "resources", "massas");
        String filename = dir + "\\01-massa_prova.json";
        String args[] = new String[] {dataInicio, dataFim, filename};

        InputJson input = new InputJson(new ArgumentsReaderFile(args));

        input.process();

        // deve retornar jobs carregados
        assertNotNull(input.getValidJobs().getJobs());
        assertFalse(input.getValidJobs().getJobs().isEmpty());

        // devem ser os id dos jobs lidos
        Collection<Job> jobs = input.getValidJobs().getJobs();
        List<Job> jobsList = jobs.stream().collect(Collectors.toList());

        assertEquals(3, jobsList.size());

        assertEquals(1, jobsList.get(0).getId());
        assertEquals(2, jobsList.get(1).getId());
        assertEquals(3, jobsList.get(2).getId());
    }


    @Test
    public void processarInputValidoTesteFiltros() {
        Path dir = Paths.get("src", "main", "resources", "massas");
        String filename = dir + "\\10-valido_expirado_acimahoras.json";
        String args[] = new String[] {dataInicio, dataFim, filename};

        InputJson input = new InputJson(new ArgumentsReaderFile(args));

        input.process();

        // deve retornar jobs carregados
        assertNotNull(input.getValidJobs().getJobs());
        assertFalse(input.getValidJobs().getJobs().isEmpty());

        // devem ser os id dos jobs lidos
        Collection<Job> jobs = input.getValidJobs().getJobs();
        List<Job> jobsList = jobs.stream().collect(Collectors.toList());

        assertEquals(3, jobsList.size());

        assertEquals(1, jobsList.get(0).getId());
        assertEquals(2, jobsList.get(1).getId());
        assertEquals(4, jobsList.get(2).getId());
    }
}