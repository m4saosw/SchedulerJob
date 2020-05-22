package br.com.massao.test.schedulerjob.v1.interfaces;

import br.com.massao.test.schedulerjob.v1.model.input.Jobs;

public interface Input {

    Jobs getValidJobs();
    void process();
}
