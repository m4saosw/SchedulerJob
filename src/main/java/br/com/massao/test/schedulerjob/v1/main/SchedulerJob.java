package br.com.massao.test.schedulerjob.v1.main;

import br.com.massao.test.schedulerjob.v1.bean.ExecutionWindow;
import br.com.massao.test.schedulerjob.v1.model.input.Job;
import br.com.massao.test.schedulerjob.v1.model.input.Jobs;
import br.com.massao.test.schedulerjob.v1.model.output.GroupsOut;
import br.com.massao.test.schedulerjob.v1.model.output.JobOut;
import br.com.massao.test.schedulerjob.v1.model.output.JobsOut;
import br.com.massao.test.schedulerjob.v1.util.ArgumentsReaderFile;
import br.com.massao.test.schedulerjob.v1.util.JsonParserJacksonJobs;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class SchedulerJob {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        final Instant start = Instant.now();

        SchedulerJob schedulerJob = new SchedulerJob();

        try {
            // processa entrada
            LOGGER.info("Processando entrada...");
            // todo - leitura de entrada
            ArgumentsReaderFile input = new ArgumentsReaderFile(args);

            LOGGER.info("entrada - janela exec = {} {}", input.getStartDate(), input.getEndDate());

            Jobs jobsIn = JsonParserJacksonJobs.toClass(input.getJson());
            LOGGER.info("entrada - jobs entrada  = {}", jobsIn.getJobs());

            // tratamento de dados entrada
            ExecutionWindow window = new ExecutionWindow(input.getStartDate(), input.getEndDate());
            Jobs jobsInTratado = schedulerJob.filter(window, jobsIn);
            LOGGER.info("entrada - jobs validos  = {}", jobsInTratado.getJobs());




            // processa saida
            LOGGER.info("Processando saida...");


            // todo - falta tratamento de negocio na saida
            JobsOut jobsOut = new JobsOut(new ArrayList<>());
            for(Job job: jobsIn.getJobs()) {
                jobsOut.getJobs().add(new JobOut(job.getId()));
            }
            GroupsOut groupsOut = new GroupsOut(new ArrayList<>());
            groupsOut.getGroups().add(jobsOut);

            String jSonOutput = JsonParserJacksonJobs.toJson(groupsOut);



            LOGGER.info("saida - resultados = {}", jSonOutput);

            LOGGER.info("Tempo de execucao = {} ms", Instant.now().toEpochMilli() - start.toEpochMilli());

        } catch (IllegalArgumentException e) {
            LOGGER.error("Ocorreu um erro de validacao de entrada de dados = {}", e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Ocorreu um erro interno desconhecido", e);
        }
    }


    private Jobs filter(ExecutionWindow window, Jobs jobs) {
        // HashSet para eliminar duplicidades
        Jobs newJobs = new Jobs(new HashSet<>());

        // todo - substituir por java8
        for (Job job : jobs.getJobs()) {
            if (window.isBeforeWindow(job.getDeadline()))
                LOGGER.warn("Job descartado - expirado em relação a janela de data: {}", job);
            else if (job.getEstimatedTime() > 8)
                LOGGER.warn("Job descartado - fora do limite de {} horas: {}", 8, job);
            else
                newJobs.getJobs().add(job);
        }
        return newJobs;
    }
}