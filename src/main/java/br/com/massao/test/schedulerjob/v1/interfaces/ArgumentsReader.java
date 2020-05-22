package br.com.massao.test.schedulerjob.v1.interfaces;


import br.com.massao.test.schedulerjob.v1.bean.ExecutionWindow;

/**
 * Leitor de argumentos
 */
public interface ArgumentsReader {

    // obtem a janela de execucao lida
    ExecutionWindow getWindow();

    // obtem o json lido
    String getJson();
}
