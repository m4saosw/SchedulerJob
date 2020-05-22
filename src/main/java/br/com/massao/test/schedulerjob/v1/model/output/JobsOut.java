package br.com.massao.test.schedulerjob.v1.model.output;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Collection;

/**
 * Jobs de saida
 */
public class JobsOut {
    @JsonValue
    private Collection<JobOut> jobs;

    public JobsOut(Collection<JobOut> jobs) {
        this.jobs = jobs;
    }

    public Collection<JobOut> getJobs() {
        return jobs;
    }


    @Override
    public String toString() {
        return "JobsOut{" +
                "jobs=" + jobs +
                '}';
    }
}
