package br.com.massao.test.schedulerjob.v1.main;

import br.com.massao.test.schedulerjob.v1.controller.InputJson;
import br.com.massao.test.schedulerjob.v1.controller.OutputJson;
import br.com.massao.test.schedulerjob.v1.model.input.Job;
import br.com.massao.test.schedulerjob.v1.model.output.GroupsOut;
import br.com.massao.test.schedulerjob.v1.model.output.JobOut;
import br.com.massao.test.schedulerjob.v1.model.output.JobsOut;
import br.com.massao.test.schedulerjob.v1.util.ArgumentsReaderFile;
import br.com.massao.test.schedulerjob.v1.util.JsonParserJacksonJobs;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Instant;
import java.util.ArrayList;

public class SchedulerJob {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        final Instant start = Instant.now();

        try {
            // processa entrada
            LOGGER.info("Processando entrada...");

            InputJson input = new InputJson(new ArgumentsReaderFile(args));
            input.process();
            LOGGER.info("input - jobs validos  = {}", input.getValidJobs());




            // processa saida
            LOGGER.info("Processando saida...");

            OutputJson output = new OutputJson(input);
            output.process();
            // todo - falta tratamento de negocio na saida
            //String jSonOutput = processaSaida(input);


            LOGGER.info("saida - resultados = {}", output.getGruposSaidaString());

            LOGGER.info("Tempo de execucao = {} ms", Instant.now().toEpochMilli() - start.toEpochMilli());

        } catch (IllegalArgumentException e) {
            LOGGER.error("Ocorreu um erro de validacao de entrada de dados = {}", e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Ocorreu um erro interno desconhecido", e);
        }
    }


    @Deprecated
    private static String processaSaida(InputJson input) {
        JobsOut jobsOut = new JobsOut(new ArrayList<>());
        for(Job job: input.getValidJobs().getJobs()) {
            jobsOut.getJobs().add(new JobOut(job.getId()));
        }
        GroupsOut groupsOut = new GroupsOut(new ArrayList<>());
        groupsOut.getGroups().add(jobsOut);

        return JsonParserJacksonJobs.toJson(groupsOut);
    }
}