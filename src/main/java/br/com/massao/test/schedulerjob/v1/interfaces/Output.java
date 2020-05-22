package br.com.massao.test.schedulerjob.v1.interfaces;

/**
 * Saida de dados
 */
public interface Output {

    // processa a saida para obtencao dos grupos de jobs
    void process();

    // obtem os grupos de jobs processados em formato Json
    String getGroupsOutString();
}
