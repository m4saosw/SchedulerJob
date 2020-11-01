package br.com.massao.test.schedulerjob.v1.controller;

import br.com.massao.test.schedulerjob.v1.bean.ExecutionWindow;
import br.com.massao.test.schedulerjob.v1.model.input.Job;
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
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore({"javax.management.*", "jdk.internal.reflect.*"})
@PrepareForTest({InputJson.class })
public class InputJsonTest {
    final static String DATA_INICIO = DateUtils.getDate("2019-11-10 09:00:00");
    final static String DATA_FIM    = DateUtils.getDate("2019-11-11 12:00:00");

    InputJson input;

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
    }


    @Test(expected = IllegalArgumentException.class)
    public void rejeitarProcessarInputArgumentosInvalidosAMenor() {
        String args[] = new String[] {DATA_INICIO};
        InputJson input = new InputJson(new ArgumentsReaderFile(args));

        input.process();
    }


    @Test(expected = IllegalArgumentException.class)
    public void rejeitarProcessarInputArgumentosInvalidosAMaior() {
        String args[] = new String[] {DATA_INICIO, DATA_FIM, "arquivo", "parametro a mais"};
        InputJson input = new InputJson(new ArgumentsReaderFile(args));

        input.process();
    }


    @Test
    public void processarInputValido() throws Exception {
        process(JsonInput.FILE_01_MASSA_PROVA);

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
    public void processarInputValidoTesteFiltros() throws Exception {
        process(JsonInput.FILE_10_VALIDO_EXPIRADO_ACIMAHORAS);

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