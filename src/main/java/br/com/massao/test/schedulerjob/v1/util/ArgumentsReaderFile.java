package br.com.massao.test.schedulerjob.v1.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ArgumentsReaderFile {
    private static final Logger LOGGER = LogManager.getLogger();

    private String startDate;
    private String endDate;

    private String json;

    public ArgumentsReaderFile(String args[]) {
        validations(args);


        // command line arguments
        for (String inputArg : args)
            LOGGER.debug("Argumento: {}", inputArg);

        String startDate = args[0];
        String endDate = args[1];

        this.startDate = startDate;
        this.endDate = endDate;
        json = readFromFile(args[2]);
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getJson() {
        return json;
    }


    private void validations(String args[]) {
        if (args == null || args.length != 3)
            throw new IllegalArgumentException("Quantidade inválida de argumentos de entrada. Por favor informar: dataInicial  dataFinal  arquivoJson");
    }


    public String readFromFile(String fileName) {
        StringBuilder sb = new StringBuilder();
        try {
            Stream<String> stream = Files.lines(Paths.get(fileName), StandardCharsets.UTF_8);
            stream.forEach(s -> sb.append(s).append("\n"));

            String contentString = sb.toString();
            LOGGER.debug("Conteudo do arquivo {} = {}", fileName, contentString);

            return contentString;

        } catch (IOException io) {
            throw new IllegalArgumentException("Erro ao ler o arquivo = " + io.getMessage());
        }
    }
}