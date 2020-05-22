package br.com.massao.test.schedulerjob.v1.controller;

import br.com.massao.test.schedulerjob.v1.model.input.Job;
import br.com.massao.test.schedulerjob.v1.model.output.GroupsOut;
import br.com.massao.test.schedulerjob.v1.model.output.JobOut;
import br.com.massao.test.schedulerjob.v1.model.output.JobsOut;
import br.com.massao.test.schedulerjob.v1.util.ComparatorDeadline;
import br.com.massao.test.schedulerjob.v1.util.JsonParserJacksonJobs;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class OutputJson {
    private static final Logger LOGGER = LogManager.getLogger();

    private InputJson input;
    private String groupsOutString;


    private OutputJson() {
        this.groupsOutString = "";
    }


    public OutputJson(InputJson input) {
        this();
        this.input = input;
    }


    public String getGroupsOutString() {
        return groupsOutString;
    }


    public void process() {
        List<Job> validJobs = new ArrayList<>(this.input.getValidJobs().getJobs()); // Conversao de Set para List

        sortByDate(validJobs);
        LOGGER.debug("ordenado = {}", validJobs);

        GroupsOut groupsOut = new GroupsOut(new ArrayList<>());
        List<JobsOut> groups = (List<JobsOut>) groupsOut.getGroups(); // Variavel com o casting para lista para facilitar

        int groupIndex = 0;
        boolean createGroup = true;

        // para cada job valido da lista
        for (int i = 0; i < validJobs.size(); i++) {
            float sum = 0;

            if (createGroup)
                addNewGroup(groups);

            createGroup = false;

            // percorre o restante dos jobs da lista
            for (int j = i; j < validJobs.size(); j++) {

                Job job = validJobs.get(j);
                if (!job.isAvailable()) continue;

                if (jobFitInMaxHours(sum, job)) {
                    sum += job.getEstimatedTime();

                    addJobToNewGroup(groupIndex, job, groups);
                    createGroup = true;
                }

                // interromper a inclusao de jobs se atingido o maximo de horas
                if (sum == InputJson.MAX_HOURS) break;
            }

            groupIndex++;
        }

        // remove o ultimo grupo vazio se houver
        removeLastEmptyGroup(groups);

        LOGGER.debug("groups = {}", groupsOut);

        this.groupsOutString = JsonParserJacksonJobs.toJson(groupsOut);
    }


    private void addJobToNewGroup(int groupIndex, Job job, List<JobsOut> groups) {
        job.setAvailable(false);
        groups.get(groupIndex).getJobs().add(new JobOut(job.getId()));
    }


    private boolean jobFitInMaxHours(float sum, Job job) {
        return job.getEstimatedTime() + sum <= InputJson.MAX_HOURS;
    }


    private void addNewGroup(List<JobsOut> groups) {
        groups.add(new JobsOut(new ArrayList<>()));
    }


    private void removeLastEmptyGroup(List<JobsOut> groups) {
        int last = groups.size() - 1;
        if (! groups.isEmpty() && groups.get(last).getJobs().isEmpty())
            groups.remove(last);
    }


    private void sortByDate(List<Job> jobs) {
        jobs.sort(new ComparatorDeadline());
    }


}