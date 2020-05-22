package br.com.massao.test.schedulerjob.v1.controller;

import br.com.massao.test.schedulerjob.v1.interfaces.Input;
import br.com.massao.test.schedulerjob.v1.interfaces.Output;
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

/**
 * Processador de saida de dados
 * Classifica os jobs em grupos conforme regras de negocio
 * Envia para o log a saida do processamento da entrada no formato JSon
 */
public class OutputJsonStrategy1 implements Output {
    private static final Logger LOGGER = LogManager.getLogger();

    private Input input;
    private String groupsOutString;


    private OutputJsonStrategy1() {
        this.groupsOutString = "";
    }


    public OutputJsonStrategy1(Input input) {
        this();
        this.input = input;
    }


    @Override
    public String getGroupsOutString() {
        return groupsOutString;
    }


    /**
     * Gera o json de saida com base num algoritmo de marcacao de jobs já utilizados, evita a reanalise
     * Tecnica iterativa, sem recursividade para otimizar recursos
     */
    @Override
    public void process() {
        List<Job> validJobs = new ArrayList<>(this.input.getValidJobs().getJobs()); // Conversao de Set para List

        sortByDate(validJobs); // ordena os jobs por prioridade de data ascendente
        LOGGER.debug("ordenado = {}", validJobs);

        GroupsOut groupsOut = new GroupsOut(new ArrayList<>()); // Grupos de saida
        List<JobsOut> groups = (List<JobsOut>) groupsOut.getGroups(); // Variavel com o casting para lista para facilitar

        int groupIndex = 0;
        Boolean createGroup = true; // controla se deve ser criado um novo grupo

        // para cada job valido da lista
        for (int i = 0; i < validJobs.size(); i++) {
            float totalHoursGroup = 0; // controla se ja atingiu o limite de horas do grupo

            // cria um novo grupo se for necessario
            if (createGroup)
                addNewGroup(groups);

            createGroup = false;

            // percorre o restante dos jobs da lista
            for (int j = i; j < validJobs.size(); j++) {

                Job job = validJobs.get(j); // obtem o job da posicao atual
                if (!job.isAvailable()) continue;

                // avalia as regras de negocio para aceitar o job no grupo
                if (jobFitInMaxHours(totalHoursGroup, job)) {
                    totalHoursGroup = accumulateHoursOfJob(totalHoursGroup, job);

                    addJobToNewGroup(groupIndex, job, groups);
                    createGroup = true;
                }

                // interromper a inclusao de jobs se atingido o maximo de horas
                if (isFullWindow(totalHoursGroup)) break;
            }

            groupIndex++;
        }

        // remove o ultimo grupo vazio se houver
        removeLastEmptyGroup(groups);

        LOGGER.debug("groups = {}", groupsOut);

        // converte o objeto de grupos para formato json
        this.groupsOutString = JsonParserJacksonJobs.toJson(groupsOut);
    }


    /**
     * Maximo de horas da janela de horas atingido?
     * @param totalHoursGroup
     * @return
     */
    private boolean isFullWindow(float totalHoursGroup) {
        return totalHoursGroup == InputJson.MAX_HOURS;
    }


    /**
     * Soma as horas do job no total
     * @param totalHoursGroup
     * @param job
     * @return
     */
    private float accumulateHoursOfJob(float totalHoursGroup, Job job) {
        totalHoursGroup += job.getEstimatedTime();
        return totalHoursGroup;
    }


    /**
     * Adiciona o job ao grupo
     * @param groupIndex
     * @param job
     * @param groups
     */
    private void addJobToNewGroup(int groupIndex, Job job, List<JobsOut> groups) {
        job.setAvailable(false);
        groups.get(groupIndex).getJobs().add(new JobOut(job.getId()));
    }


    /**
     * Job esta dentro do numero maximo de horas da janela?
     * @param totalHoursGroup
     * @param job
     * @return
     */
    private boolean jobFitInMaxHours(float totalHoursGroup, Job job) {
        return job.getEstimatedTime() + totalHoursGroup <= InputJson.MAX_HOURS;
    }


    /**
     * Cria um novo grupo
     * @param groups
     */
    private void addNewGroup(List<JobsOut> groups) {
        groups.add(new JobsOut(new ArrayList<>()));
    }


    /**
     * Remove ultimo grupo vazio
     * @param groups
     */
    private void removeLastEmptyGroup(List<JobsOut> groups) {
        int last = groups.size() - 1;
        if (! groups.isEmpty() && groups.get(last).getJobs().isEmpty())
            groups.remove(last);
    }


    /**
     * Ordena jobs por data maxima de conclusao
     * @param jobs
     */
    private void sortByDate(List<Job> jobs) {
        jobs.sort(new ComparatorDeadline());
    }


}
