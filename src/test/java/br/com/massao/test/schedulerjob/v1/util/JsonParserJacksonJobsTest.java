package br.com.massao.test.schedulerjob.v1.util;

import br.com.massao.test.schedulerjob.v1.model.input.Jobs;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;

public class JsonParserJacksonJobsTest {

    @Test(expected = Exception.class)
    public void recusarJSonNulo() throws JsonProcessingException {
        String jSon = null;
        Jobs jobs = JsonParserJacksonJobs.toClass(jSon);
    }


    @Test(expected = Exception.class)
    public void recusarJSonVazio() throws JsonProcessingException {
        String jSon = "";
        JsonParserJacksonJobs.toClass(jSon);
    }

    @Test(expected = Exception.class)
    public void recusarJSonConteudoInvalido() throws JsonProcessingException {
        String jSon = "este e um conteudo invalido";
        JsonParserJacksonJobs.toClass(jSon);
    }

    @Test(expected = Exception.class)
    public void recusarJSonConteudoInvalido_TipoDadosInvalidoNoCampo() throws JsonProcessingException {
        String jSon = "";
        JsonParserJacksonJobs.toClass(jSon);
    }


    @Test
    public void aceitarJSonValido_UmJob() throws JsonProcessingException {
        /*String jSon = "";
        Jobs jobs = JsonParserJacksonJobs.toClass(jSon);*/
        // todo - pendente
    }

    @Test
    public void aceitarJSonValido_VariosJob() throws JsonProcessingException {
        /*String jSon = "";
        Jobs jobs = JsonParserJacksonJobs.toClass(jSon);*/
        // todo - pendente
    }


}