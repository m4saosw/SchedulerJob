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

/**
 * Conversor de Jobs para string e vice versa usando a library Jackson
 */
public class JsonParserJacksonJobs {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private JsonParserJacksonJobs() {
    }

    /**
     * Converte de json para classe
     * @param str
     * @return
     * @throws JsonProcessingException
     */
    public static Jobs toClass(String str) throws JsonProcessingException {
        // para funcionar as classes data java 8
        MAPPER.registerModule(new JavaTimeModule());
        MAPPER.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        Collection<Job> jobs = MAPPER.readValue(str, new TypeReference<HashSet<Job>>() {});

        return new Jobs(jobs);
    }


    /**
     * Converte de classe para json
     * @param o
     * @return
     */
    public static String toJson(Object o) {
        if (o == null) return null;

        try {
            return MAPPER.writeValueAsString(o);

        } catch (JsonProcessingException e) {
            LOGGER.error("Erro na conversao do objeto para string = {}", e.getMessage());
            return null;
        }
    }
}
