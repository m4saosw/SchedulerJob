package br.com.massao.test.schedulerjob.v1.controller;

import br.com.massao.test.schedulerjob.v1.interfaces.ArgumentsReader;
import br.com.massao.test.schedulerjob.v1.interfaces.Input;
import br.com.massao.test.schedulerjob.v1.model.input.Job;
import br.com.massao.test.schedulerjob.v1.model.input.Jobs;
import br.com.massao.test.schedulerjob.v1.util.JsonParserJacksonJobs;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.stream.Collectors;

/**
 * Processador de entrada de dados
 * Faz a leitura dos argumentos via linha de comando e carrega o arquivo em formato JSon
 */
public class InputJson implements Input {
    public static final float MAX_HOURS = 8;
    private static final Logger LOGGER = LogManager.getLogger();

    private ArgumentsReader reader;
    private Jobs validJobs;


    private InputJson() {
        this.validJobs = new Jobs(Collections.emptyList());// evitar NullPointer
    }


    public InputJson(ArgumentsReader reader) {
        this();
        this.reader = reader;
    }


    @Override
    public void process() {
        Jobs jobs = toClassInput(reader.getJson());
        this.validJobs = filter(jobs);
    }

    /**
     * Converte de string para objeto
     * @param jSon
     * @return
     */
    private Jobs toClassInput(String jSon) {
        try {
            return JsonParserJacksonJobs.toClass(jSon);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Erro na conversao dos jobs em formato json", e.getCause());
        }
    }


    public ArgumentsReader getReader() {
        return reader;
    }


    @Override
    public Jobs getValidJobs() {
        return validJobs;
    }

    /**
     * Filtra os jobs conforme regras de negocio
     * @param jobs
     * @return
     */
    private Jobs filter(Jobs jobs) {
        Collection<Job> jobsFiltered = jobs.getJobs().stream()
                .filter(job -> {
                    if (this.reader.getWindow().isBeforeWindow(job.getDeadline())) {
                        LOGGER.warn("Job descartado - expirado em relação a janela de data: {}", job);
                        return false;
                    } else if (!job.isEstimatedTimeLessThan(MAX_HOURS)) {
                        LOGGER.warn("Job descartado - fora do limite de {} horas: {}", MAX_HOURS, job);
                        return false;
                    } else
                        return true;
                })
                .collect(Collectors.toList());

        // HashSet para eliminar duplicidades
        Jobs newJobs = new Jobs(new HashSet<>());
        newJobs.getJobs().addAll(jobsFiltered);

        return newJobs;
    }


    @Override
    public String toString() {
        return "InputJson{" +
                "reader=" + reader +
                ", validJobs=" + validJobs +
                '}';
    }
}