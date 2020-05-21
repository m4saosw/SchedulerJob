package br.com.massao.test.schedulerjob.v1.util;

import br.com.massao.test.schedulerjob.v1.model.input.Job;
import br.com.massao.test.schedulerjob.v1.model.input.Jobs;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;
import java.util.HashSet;

public class JsonParserJacksonJobs {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private JsonParserJacksonJobs() {
    }

    public static Jobs toClass(String str) throws JsonProcessingException {
        // para funcionar as classes data java 8
        MAPPER.registerModule(new JavaTimeModule());
        MAPPER.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        Collection<Job> jobs = MAPPER.readValue(str, new TypeReference<HashSet<Job>>() {});

        return new Jobs(jobs);
    }
}
