package br.com.massao.test.schedulerjob.v1.controller;

import br.com.massao.test.schedulerjob.v1.bean.ExecutionWindow;
import br.com.massao.test.schedulerjob.v1.model.input.Job;
import br.com.massao.test.schedulerjob.v1.model.input.Jobs;
import br.com.massao.test.schedulerjob.v1.util.ArgumentsReaderFile;
import br.com.massao.test.schedulerjob.v1.helper.DateUtils;
import br.com.massao.test.schedulerjob.v1.helper.JsonInput;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore({"javax.management.*", "jdk.internal.reflect.*"})
@PrepareForTest({OutputJsonStrategy1.class })
public class OutputJsonStrategy1Test {
    final static String DATA_INICIO = DateUtils.getDate("2019-11-10 09:00:00");
    final static String DATA_FIM    = DateUtils.getDate("2019-11-11 12:00:00");

    InputJson input;
    OutputJsonStrategy1 output;

    @Mock
    ArgumentsReaderFile reader;

    @Before
    public void setUp() {
        PowerMockito.mockStatic(Files.class);
        // MOCKS
        when(reader.getWindow()).thenReturn(new ExecutionWindow(DATA_INICIO, DATA_FIM));
    }


    private void process(String json) throws IOException {
        // MOCKS
        when(Files.lines(any(), any())).thenReturn(json.chars().mapToObj(c -> String.valueOf(c)));
        when(reader.getJson()).thenReturn(json);

        input = new InputJson(reader);
        input.process();

        output = new OutputJsonStrategy1(input);
        output.process();
    }


    @Test
    public void processarOutputDadoUmInputValido_massa01_exemploMassa() throws Exception {
        //new int id, String description, LocalDateTime deadline, float estimatedTime
        Jobs jobs = new Jobs(new ArrayList<>());
        jobs.getJobs().add(new Job(1, "Mock job 1", LocalDateTime.parse("2019-11-10 12:00:00", ExecutionWindow.FORMATTER), 2));
        jobs.getJobs().add(new Job(2, "Mock job 2", LocalDateTime.parse("2019-11-11 12:00:00", ExecutionWindow.FORMATTER), 4));
        jobs.getJobs().add(new Job(3, "Mock job 3", LocalDateTime.parse("2019-11-11 08:00:00", ExecutionWindow.FORMATTER), 6));

        jobs.getJobs().stream().forEach(job -> job.setAvailable(true));

        process(JsonInput.FILE_01_MASSA_PROVA);

        // deve retornar grupo de jobs
        assertNotNull(output.getGroupsOutString());
        assertEquals( "[[1,3],[2]]", output.getGroupsOutString());
    }


    @Test(expected = IllegalArgumentException.class)
    public void rejeitarOutputDadoUmInputInvalido_massa02_conteudoVazio() throws IOException {
        process(JsonInput.FILE_02_VAZIO);
    }


    @Test(expected = IllegalArgumentException.class)
    public void rejeitarOutputDadoUmInputInvalido_massa03_conteudoInvalido() throws IOException {
        process(JsonInput.FILE_03_INVALIDO);
    }


    @Test
    public void processarOutputDadoUmInputValido_massa04_semDuplicidades() throws IOException {
        process(JsonInput.FILE_04_IDS_DUPLICADOS);

        // deve retornar grupo de jobs
        assertNotNull(output.getGroupsOutString());
        assertEquals( "[[2,1]]", output.getGroupsOutString());
    }


    @Test
    public void processarOutputDadoUmInputValido_massa05() throws IOException {
        process(JsonInput.FILE_05_EXPIRADO_VALIDO_POSTERIOR);

        // deve retornar grupo de jobs
        assertNotNull(output.getGroupsOutString());
        assertEquals( "[[2,1]]", output.getGroupsOutString());
    }


    @Test
    public void processarOutputDadoUmInputValido_massa06() throws IOException {
        process(JsonInput.FILE_06_MINHA_TABELA);

        // deve retornar grupo de jobs
        assertNotNull(output.getGroupsOutString());
        assertEquals( "[[1,4,6,8],[3,5],[7,9,2]]", output.getGroupsOutString());
    }


    @Test
    public void processarOutputDadoUmInputValido_massa07_jSonMinificado() throws IOException {
        process(JsonInput.FILE_07_MINHA_TABELA_MESMA_LINHA);

        // deve retornar grupo de jobs
        assertNotNull(output.getGroupsOutString());
        assertEquals( "[[1,4,6,8],[3,5],[7,9,2]]", output.getGroupsOutString());
    }

    @Test
    public void processarOutputDadoUmInputValido_massa08_ordemDecrescente() throws IOException {
        process(JsonInput.FILE_08_MINHA_OUTRA_TABELA_DESCENDENTE);

        // deve retornar grupo de jobs
        assertNotNull(output.getGroupsOutString());
        assertEquals( "[[11],[10,9,8,7,6,5,4,3],[2]]", output.getGroupsOutString());
    }


    @Test(expected = IllegalArgumentException.class)
    public void rejeitarOutputDadoUmInputInvalido_massa09() throws IOException {
        process(JsonInput.FILE_09_VALIDOS_E_INVALIDOS);
    }


}