package br.com.massao.test.schedulerjob.v1.main;

import br.com.massao.test.schedulerjob.v1.controller.InputJson;
import br.com.massao.test.schedulerjob.v1.controller.OutputJson;
import br.com.massao.test.schedulerjob.v1.interfaces.ArgumentsReader;
import br.com.massao.test.schedulerjob.v1.interfaces.Input;
import br.com.massao.test.schedulerjob.v1.interfaces.Output;
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

            ArgumentsReader reader = new ArgumentsReaderFile(args);
            Input input = new InputJson(reader);
            input.process();

            LOGGER.info("input - janela exec = {}", reader.getWindow());
            LOGGER.info("input - jobsValidos = {}", input.getValidJobs());

            // processa saida
            LOGGER.info("Processando saida...");

            Output output = new OutputJson(input);
            output.process();

            LOGGER.info("saida - resultados = {}", output.getGroupsOutString());

        } catch (IllegalArgumentException e) {
            LOGGER.error("Ocorreu um erro de validacao de entrada de dados = {}", e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            LOGGER.error("Ocorreu um erro interno desconhecido", e);
            System.exit(1);
        }

        LOGGER.info("Tempo de execucao = {} ms", Instant.now().toEpochMilli() - start.toEpochMilli());
    }
}