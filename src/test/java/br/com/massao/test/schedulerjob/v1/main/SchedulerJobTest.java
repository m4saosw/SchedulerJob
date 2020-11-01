package br.com.massao.test.schedulerjob.v1.main;

import br.com.massao.test.schedulerjob.v1.helper.DateUtils;
import br.com.massao.test.schedulerjob.v1.helper.FileUtils;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.contrib.java.lang.system.SystemOutRule;

public class SchedulerJobTest {
    final static String DATA_INICIO = DateUtils.getDate("2019-11-10 09:00:00");
    final static String DATA_FIM = DateUtils.getDate("2019-11-11 12:00:00");

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
        String args[] = new String[] {DATA_FIM, DATA_INICIO, "arquivo"};

        exit.expectSystemExitWithStatus(1);
        SchedulerJob.main(args);
    }


    /**
     * Saida de resultado em caso de sucesso
     */
    @Test
    public void processarEntradaValida_massa01() {
        String args[] = new String[] {DATA_INICIO, DATA_FIM, FileUtils.getFileNamePath("01-massa_prova.json")};

        SchedulerJob.main(args);

        Assert.assertTrue(systemOutRule.getLog().trim().endsWith("[[1,3],[2]]"));
    }
}