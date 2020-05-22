package br.com.massao.test.schedulerjob.v1.util;

import br.com.massao.test.schedulerjob.v1.bean.ExecutionWindow;
import br.com.massao.test.schedulerjob.v1.interfaces.ArgumentsReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class ArgumentsReaderFile implements ArgumentsReader {
    private static final Logger LOGGER = LogManager.getLogger();

    private ExecutionWindow window;
    private String json;

    public ArgumentsReaderFile(String[] args) {
        validations(args);


        // command line arguments
        Arrays.stream(args).forEach(arg -> LOGGER.debug("Argumento: {}", arg));

        String startDate = args[0];
        String endDate = args[1];

        this.window = new ExecutionWindow(startDate, endDate);
        json = readFromFile(args[2]);
    }


    @Override
    public ExecutionWindow getWindow() {
        return window;
    }


    @Override
    public String getJson() {
        return json;
    }


    private void validations(String[] args) {
        if (args == null || args.length != 3)
            throw new IllegalArgumentException("Quantidade inválida de argumentos de entrada. Por favor informar: dataInicial  dataFinal  arquivoJson");
    }


    public String readFromFile(String fileName) {
        StringBuilder sb = new StringBuilder();

        // try-with-resources para ser auto-closeable  https://mkyong.com/java/java-8-should-we-close-the-stream-after-use/
        try (Stream<String> stream = Files.lines(Paths.get(fileName), StandardCharsets.UTF_8)) {
            stream.forEach(s -> sb.append(s).append("\n"));

            String contentString = sb.toString();
            LOGGER.debug("Conteudo do arquivo {} = {}", fileName, contentString);

            return contentString;

        } catch (IOException io) {
            throw new IllegalArgumentException("Erro ao ler o arquivo = " + io.getMessage());
        }
    }
}