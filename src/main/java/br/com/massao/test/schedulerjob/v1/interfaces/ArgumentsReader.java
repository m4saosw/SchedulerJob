package br.com.massao.test.schedulerjob.v1.interfaces;


import br.com.massao.test.schedulerjob.v1.bean.ExecutionWindow;

public interface ArgumentsReader {

    ExecutionWindow getWindow();
    String getJson();
}
