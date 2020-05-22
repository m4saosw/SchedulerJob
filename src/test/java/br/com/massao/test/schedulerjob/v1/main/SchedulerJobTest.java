package br.com.massao.test.schedulerjob.v1.main;

import br.com.massao.test.schedulerjob.v1.bean.ExecutionWindow;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.contrib.java.lang.system.SystemOutRule;


import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

public class SchedulerJobTest {
    String dataInicio = LocalDateTime.parse("2019-11-10 09:00:00", ExecutionWindow.FORMATTER).format(ExecutionWindow.FORMATTER);
    String dataFim    = LocalDateTime.parse("2019-11-11 12:00:00", ExecutionWindow.FORMATTER).format(ExecutionWindow.FORMATTER);

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    /**
     * Uso de api de apoio para testes junit de system out e exit status
     * https://stefanbirkner.github.io/system-rules/index.html
     */


    /**
     * Saida de aplicacao em caso de erro de validacao
     */
    @Test
    public void rejeitarProcessarDadoUmInputInvalido_JanelaInvalida() {
        Path dir = Paths.get("src", "main", "resources", "massas");
        String filename = dir + "\\01-massa_prova.json";
        String args[] = new String[] {dataFim, dataInicio, filename};

        exit.expectSystemExitWithStatus(1);
        SchedulerJob.main(args);
    }


    /**
     * Saida de resultado em caso de sucesso
     */
    @Test
    public void processarEntradaValida_massa01() {
        Path dir = Paths.get("src", "main", "resources", "massas");
        String filename = dir + "\\01-massa_prova.json";
        String args[] = new String[] {dataInicio, dataFim, filename};

        SchedulerJob.main(args);

        Assert.assertTrue(systemOutRule.getLog().trim().endsWith("[[1,3],[2]]"));
    }
}