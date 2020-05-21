package br.com.massao.test.schedulerjob.v1.controller;

import br.com.massao.test.schedulerjob.v1.model.input.Job;
import br.com.massao.test.schedulerjob.v1.model.output.GroupsOut;
import br.com.massao.test.schedulerjob.v1.model.output.JobOut;
import br.com.massao.test.schedulerjob.v1.model.output.JobsOut;
import br.com.massao.test.schedulerjob.v1.util.JsonParserJacksonJobs;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class OutputJson {
    private static final Logger LOGGER = LogManager.getLogger();

    private InputJson inputJson;

    private String gruposSaidaString;

    private OutputJson() {
        this.gruposSaidaString = "";
    }

    public OutputJson(InputJson inputJson) {
        this();
        this.inputJson = inputJson;
    }

    public String getGruposSaidaString() {
        return gruposSaidaString;
    }

    public void process() {
        JobsOut jobsOut = new JobsOut(new ArrayList<>());
        for(Job job: inputJson.getValidJobs().getJobs()) {
            jobsOut.getJobs().add(new JobOut(job.getId()));
        }
        GroupsOut groupsOut = new GroupsOut(new ArrayList<>());
        groupsOut.getGroups().add(jobsOut);

        this.gruposSaidaString = JsonParserJacksonJobs.toJson(groupsOut);
    }

}
