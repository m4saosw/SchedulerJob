package br.com.massao.test.schedulerjob.v1.main;

import br.com.massao.test.schedulerjob.v1.controller.InputJson;
import br.com.massao.test.schedulerjob.v1.controller.OutputJson;
import br.com.massao.test.schedulerjob.v1.util.ArgumentsReaderFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Instant;

public class SchedulerJob {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        final Instant start = Instant.now();

        try {
            // processa entrada
            LOGGER.info("Processando entrada...");

            InputJson input = new InputJson(new ArgumentsReaderFile(args));
            input.process();
            LOGGER.info("input - janela exec = {}", input.getReader().getWindow());
            LOGGER.info("input - jobsValidos = {}", input.getValidJobs());

            // processa saida
            LOGGER.info("Processando saida...");

            OutputJson output = new OutputJson(input);
            output.process();

            LOGGER.info("saida - resultados = {}", output.getGroupsOutString());

            LOGGER.info("Tempo de execucao = {} ms", Instant.now().toEpochMilli() - start.toEpochMilli());

        } catch (IllegalArgumentException e) {
            LOGGER.error("Ocorreu um erro de validacao de entrada de dados = {}", e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            LOGGER.error("Ocorreu um erro interno desconhecido", e);
            System.exit(1);
        }
    }
}