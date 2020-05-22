package br.com.massao.test.schedulerjob.v1.main;

import br.com.massao.test.schedulerjob.v1.controller.InputJson;
import br.com.massao.test.schedulerjob.v1.controller.OutputJsonStrategy1;
import br.com.massao.test.schedulerjob.v1.interfaces.ArgumentsReader;
import br.com.massao.test.schedulerjob.v1.interfaces.Input;
import br.com.massao.test.schedulerjob.v1.interfaces.Output;
import br.com.massao.test.schedulerjob.v1.util.ArgumentsReaderFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Instant;

/**
 * Classe principal executavel SchedulerJob
 * Aciona os processadores de entrada e saida de dados
 */
public class SchedulerJob {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        final Instant start = Instant.now();

        try {
            processOutput(getInput(args));

        } catch (IllegalArgumentException e) {
            LOGGER.error("Ocorreu um erro de validacao de entrada de dados = {}", e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            LOGGER.error("Ocorreu um erro interno desconhecido", e);
            System.exit(1);
        }

        LOGGER.debug("Tempo de execucao = {} ms", Instant.now().toEpochMilli() - start.toEpochMilli());
    }


    /**
     * Cria um objeto de entrada dado os parametros informados pelo usuario
     * @param args
     * @return
     */
    private static Input getInput(String[] args) {
        // processa entrada
        LOGGER.info("Processando entrada...");

        ArgumentsReader reader = new ArgumentsReaderFile(args);
        Input input = new InputJson(reader);
        input.process();

        LOGGER.info("input - janela exec = {}", reader.getWindow());
        LOGGER.info("input - jobsValidos = {}", input.getValidJobs());
        return input;
    }


    /**
     * Processa uma saida dado uma entrada
     * @param input
     */
    private static void processOutput(Input input) {
        // processa saida
        LOGGER.info("Processando saida...");

        Output output = new OutputJsonStrategy1(input);
        output.process();

        LOGGER.info("saida - resultados = {}", output.getGroupsOutString());

        // gera resultados na saida padrao
        System.out.println(output.getGroupsOutString());
    }
}