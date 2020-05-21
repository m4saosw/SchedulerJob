package br.com.massao.test.schedulerjob.v1.main;

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
            // todo - leitura de entrada


            // processa saida
            LOGGER.info("Processando saida...");
            // todo - saida resultados


            LOGGER.info("Tempo de execucao = {} ms", Instant.now().toEpochMilli() - start.toEpochMilli());

        } catch (IllegalArgumentException e) {
            LOGGER.error("Ocorreu um erro de validacao de entrada de dados = {}", e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Ocorreu um erro interno desconhecido", e);
        }
    }
}