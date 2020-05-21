package br.com.massao.test.schedulerjob.v1.util;

import br.com.massao.test.schedulerjob.v1.model.input.Job;
import br.com.massao.test.schedulerjob.v1.model.input.Jobs;
import br.com.massao.test.schedulerjob.v1.model.output.GroupsOut;
import br.com.massao.test.schedulerjob.v1.model.output.JobOut;
import br.com.massao.test.schedulerjob.v1.model.output.JobsOut;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class JsonParserJacksonJobsTest {

    @Test(expected = Exception.class)
    public void recusarJSonNulo() throws JsonProcessingException {
        String jSon = null;
        JsonParserJacksonJobs.toClass(jSon);
    }


    @Test(expected = Exception.class)
    public void recusarJSonVazio() throws JsonProcessingException {
        String jSon = "";
        JsonParserJacksonJobs.toClass(jSon);
    }


    @Test(expected = Exception.class)
    public void recusarJSonConteudoInvalido() throws JsonProcessingException {
        String jSon = "este e um conteudo invalido";
        JsonParserJacksonJobs.toClass(jSon);
    }


    @Test(expected = Exception.class)
    public void recusarJSonConteudoInvalido_TipoDadosInvalidoNoCampo() throws JsonProcessingException {
        String jSon = "[\n" +
                "  {\n" +
                "    \"ID\": 1,\n" +
                "    \"Descrição\": \"Importação de arquivos de fundos\",\n" +
                "    \"Data Máxima de conclusão\": \"2019-11-10 12:00:00\",\n" +
                "    \"Tempo estimado\": Este e um conteudo invalido no campo tempo\n" +
                "  }\n" +
                "]";
        JsonParserJacksonJobs.toClass(jSon);
    }


    @Test
    public void jSonValido_ContendoUmJob() throws JsonProcessingException {
        String jSon = "[\n" +
                "  {\n" +
                "    \"ID\": 1,\n" +
                "    \"Descrição\": \"Importação de arquivos de fundos\",\n" +
                "    \"Data Máxima de conclusão\": \"2019-11-10 12:00:00\",\n" +
                "    \"Tempo estimado\": 2\n" +
                "  }\n" +
                "]";
        Jobs jobs = JsonParserJacksonJobs.toClass(jSon);
        Job job = jobs.getJobs().stream().findFirst().get();

        assertEquals(1, job.getId());
        assertEquals("2019-11-10 12:00:00", job.getDeadline().format(Job.FORMATTER));
        assertEquals(2f, job.getEstimatedTime(), 0.0f);
    }


    @Test
    public void jSonValido_Contendo3Job() throws JsonProcessingException {
        String jSon = "[\n" +
                "  {\n" +
                "    \"ID\": 1,\n" +
                "    \"Descrição\": \"Importação de arquivos de fundos\",\n" +
                "    \"Data Máxima de conclusão\": \"2019-11-10 12:00:00\",\n" +
                "    \"Tempo estimado\": 2\n" +
                "  },\n" +
                "  {\n" +
                "    \"ID\": 2,\n" +
                "    \"Descrição\": \"Importação de dados da Base Legada\",\n" +
                "    \"Data Máxima de conclusão\": \"2019-11-11 12:00:00\",\n" +
                "    \"Tempo estimado\": 4\n" +
                "  },\n" +
                "  {\n" +
                "    \"ID\": 3,\n" +
                "    \"Descrição\": \"Importação de dados de integração\",\n" +
                "    \"Data Máxima de conclusão\": \"2019-11-11 08:00:00\",\n" +
                "    \"Tempo estimado\": 6\n" +
                "  }\n" +
                "]";
        Jobs jobs = JsonParserJacksonJobs.toClass(jSon);
        List<Job> jobsList = jobs.getJobs().stream().collect(Collectors.toList());

        assertEquals(1, jobsList.get(0).getId());
        assertEquals(2, jobsList.get(1).getId());
        assertEquals(3, jobsList.get(2).getId());
    }


    // este cenario nao ocorre na pratica
    @Test
    public void conversaoGrupoNuloParaStringEDevolverNulo() {
        GroupsOut groups = null;
        String jSon = JsonParserJacksonJobs.toJson(groups);
        assertNull(jSon);
    }


    @Test
    public void ConversaoGrupoVazioParaStringEDevolverJSonGrupoVazio() {
        GroupsOut groups = new GroupsOut(new ArrayList<>());
        String jSon = JsonParserJacksonJobs.toJson(groups);
        assertEquals("[]", jSon);
    }


    @Test
    public void conversao1JobParaJson() {
        JobsOut jobs = new JobsOut(new ArrayList<>());
        jobs.getJobs().add(new JobOut(1));

        GroupsOut groups = new GroupsOut(new ArrayList<>());
        groups.getGroups().add(jobs);

        String jSon = JsonParserJacksonJobs.toJson(groups);
        assertEquals("[[1]]", jSon);
    }


    @Test
    public void conversao2JobsMesmoGrupoParaJson() {
        JobsOut jobsGroup1 = new JobsOut(new ArrayList<>());
        jobsGroup1.getJobs().add(new JobOut(1));
        jobsGroup1.getJobs().add(new JobOut(3));

        GroupsOut groups = new GroupsOut(new ArrayList<>());
        groups.getGroups().add(jobsGroup1);

        String jSon = JsonParserJacksonJobs.toJson(groups);
        assertEquals("[[1,3]]", jSon);
    }


    @Test
    public void conversao2JobsMesmoGrupoE1OutroGrupoParaJson() {
        JobsOut jobsGroup1 = new JobsOut(new ArrayList<>());
        jobsGroup1.getJobs().add(new JobOut(1));
        jobsGroup1.getJobs().add(new JobOut(3));

        JobsOut jobsGroup2 = new JobsOut(new ArrayList<>());
        jobsGroup2.getJobs().add(new JobOut(2));

        GroupsOut groups = new GroupsOut(new ArrayList<>());
        groups.getGroups().add(jobsGroup1);
        groups.getGroups().add(jobsGroup2);

        String jSon = JsonParserJacksonJobs.toJson(groups);
        assertEquals("[[1,3],[2]]", jSon);
    }
}