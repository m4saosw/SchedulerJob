package br.com.massao.test.schedulerjob.v1.controller;

import br.com.massao.test.schedulerjob.v1.bean.ExecutionWindow;
import br.com.massao.test.schedulerjob.v1.model.input.Job;
import br.com.massao.test.schedulerjob.v1.model.input.Jobs;
import br.com.massao.test.schedulerjob.v1.util.ArgumentsReaderFile;
import br.com.massao.test.schedulerjob.v1.util.JsonParserJacksonJobs;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.HashSet;

public class InputJson {
    public static final float MAX_HOURS = 8;

    private static final Logger LOGGER = LogManager.getLogger();

    private ArgumentsReaderFile reader;

    private ExecutionWindow window;

    private Jobs validJobs;


    private InputJson() {
        this.validJobs = new Jobs(Collections.emptyList());// evitar NullPointer
    }

    public InputJson(ArgumentsReaderFile reader) {
        this();
        this.reader = reader;
        this.window = new ExecutionWindow(reader.getStartDate(), reader.getEndDate());
    }


    public void process() {
        Jobs jobs = toClassInput(reader.getJson());
        this.validJobs = filter(jobs);
    }

    private Jobs toClassInput(String jSon) {
        try {
            return JsonParserJacksonJobs.toClass(jSon);
        } catch (JsonProcessingException e) {
            //LOGGER.error("Erro na conversao dos jobs em formato json: {}", e.getMessage());
            throw new IllegalArgumentException("Erro na conversao dos jobs em formato json", e.getCause());
        }
    }


    public ArgumentsReaderFile getReader() {
        return reader;
    }

    public ExecutionWindow getWindow() {
        return window;
    }


    public Jobs getValidJobs() {
        return validJobs;
    }



    private Jobs filter(Jobs jobs) {
        // HashSet para eliminar duplicidades
        Jobs newJobs = new Jobs(new HashSet<>());

        // todo - substituir por java8
        for (Job job : jobs.getJobs()) {
            if (this.window.isBeforeWindow(job.getDeadline()))
                LOGGER.warn("Job descartado - expirado em relação a janela de data: {}", job);
            else if (!job.isEstimatedTimeLessThan(MAX_HOURS))
                LOGGER.warn("Job descartado - fora do limite de {} horas: {}", MAX_HOURS, job);
            else
                newJobs.getJobs().add(job);
        }
        return newJobs;
    }


    @Override
    public String toString() {
        return "InputJson{" +
                "reader=" + reader +
                ", window=" + window +
                ", validJobs=" + validJobs +
                '}';
    }
}
