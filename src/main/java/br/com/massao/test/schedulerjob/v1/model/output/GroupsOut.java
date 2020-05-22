package br.com.massao.test.schedulerjob.v1.model.output;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Collection;

/**
 * Grupos de saida
 */
public class GroupsOut {
    @JsonValue
    private Collection<JobsOut> groups;

    public GroupsOut(Collection<JobsOut> groups) {
        this.groups = groups;
    }

    public Collection<JobsOut> getGroups() {
        return groups;
    }


    @Override
    public String toString() {
        return "GroupsOut{" +
                "groups=" + groups +
                '}';
    }
}
