package br.com.massao.test.schedulerjob.v1.util;

import br.com.massao.test.schedulerjob.v1.helper.JsonInput;
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
        JsonParserJacksonJobs.toClass(null);
    }


    @Test(expected = Exception.class)
    public void recusarJSonVazio() throws JsonProcessingException {
        JsonParserJacksonJobs.toClass(JsonInput.FILE_02_VAZIO);
    }


    @Test(expected = Exception.class)
    public void recusarJSonConteudoInvalido() throws JsonProcessingException {
        JsonParserJacksonJobs.toClass(JsonInput.FILE_03_INVALIDO);
    }


    @Test(expected = Exception.class)
    public void recusarJSonConteudoInvalido_TipoDadosInvalidoNoCampo() throws JsonProcessingException {
        JsonParserJacksonJobs.toClass(JsonInput.FILE_09_VALIDOS_E_INVALIDOS);
    }


    @Test
    public void jSonValido_ContendoUmJob() throws JsonProcessingException {
        final Jobs jobs = JsonParserJacksonJobs.toClass(JsonInput.FILE_11_UM_JOB_VALIDO);
        Job job = jobs.getJobs().stream().findFirst().get();

        assertEquals(1, job.getId());
        assertEquals("2019-11-10 12:00:00", job.getDeadline().format(Job.FORMATTER));
        assertEquals(2f, job.getEstimatedTime(), 0.0f);
    }


    @Test
    public void jSonValido_Contendo3Job() throws JsonProcessingException {
        final Jobs jobs = JsonParserJacksonJobs.toClass(JsonInput.FILE_01_MASSA_PROVA);
        List<Job> jobsList = jobs.getJobs().stream().collect(Collectors.toList());

        assertEquals(1, jobsList.get(0).getId());
        assertEquals(2, jobsList.get(1).getId());
        assertEquals(3, jobsList.get(2).getId());
    }


    @Test
    public void jSonValido_Contendo3JobSendo1Duplicado() throws JsonProcessingException {
        final Jobs jobs = JsonParserJacksonJobs.toClass(JsonInput.FILE_04_IDS_DUPLICADOS);
        List<Job> jobsList = jobs.getJobs().stream().collect(Collectors.toList());

        assertEquals(2, jobsList.size());

        assertEquals(1, jobsList.get(0).getId());
        assertEquals(2, jobsList.get(1).getId());
    }


    // este cenario nao ocorre na pratica
    @Test
    public void conversaoGrupoNuloParaStringEDevolverNulo() {
        final GroupsOut groups = null;
        String jSon = JsonParserJacksonJobs.toJson(groups);
        assertNull(jSon);
    }


    @Test
    public void ConversaoGrupoVazioParaStringEDevolverJSonGrupoVazio() {
        final GroupsOut groups = new GroupsOut(new ArrayList<>());
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