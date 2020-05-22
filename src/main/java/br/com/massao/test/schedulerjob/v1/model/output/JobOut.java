package br.com.massao.test.schedulerjob.v1.model.output;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Job de saida
 */
public class JobOut {
    @JsonValue
    private int id;

    public JobOut(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "JobOut{" +
                "id=" + id +
                '}';
    }
}
