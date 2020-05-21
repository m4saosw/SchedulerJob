package br.com.massao.test.schedulerjob.v1.model.input;

import java.util.Collection;
import java.util.Collections;

public class Jobs {
    private Collection<Job> jobs;

    private Jobs() {
        this.jobs = Collections.emptyList();
    }

    public Jobs(Collection<Job> jobs) {
        //this();
        this.jobs = jobs;
    }

    public Collection<Job> getJobs() {
        return jobs;
    }

    @Override
    public String toString() {
        return "Jobs{" +
                "jobs=" + jobs +
                '}';
    }
}
