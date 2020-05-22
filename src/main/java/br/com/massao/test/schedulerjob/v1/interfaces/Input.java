package br.com.massao.test.schedulerjob.v1.interfaces;

import br.com.massao.test.schedulerjob.v1.model.input.Jobs;

/**
 * Entrada de dados
 */
public interface Input {

    // processa a entrada para obtencao dos jobs validos
    void process();

    // obtem os jobs processados na entrada
    Jobs getValidJobs();
}
