package br.com.massao.test.schedulerjob.v1.util;

import br.com.massao.test.schedulerjob.v1.model.input.Job;

import java.util.Comparator;

/**
 * Comparador de Job por data maxima execucao
 */
public class ComparatorDeadline implements Comparator<Job> {
    @Override
    public int compare(Job job1, Job job2) {
        return job1.getDeadline().compareTo(job2.getDeadline());
    }
}
