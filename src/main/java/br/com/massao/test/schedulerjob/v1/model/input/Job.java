package br.com.massao.test.schedulerjob.v1.model.input;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "ID",
        "Descrição",
        "Data Máxima de conclusão",
        "Tempo estimado"
})
public class Job implements Comparable<Job> {
    private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

    @JsonProperty("ID")
    private int id;

    @JsonProperty("Descrição")
    private String description;

    @JsonProperty("Data Máxima de conclusão")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Job.DATE_PATTERN, timezone = "Brazil/East")
    private LocalDateTime deadline;

    @JsonProperty("Tempo estimado")
    private float estimatedTime;

    private Job() {
    }

    /**
     * @param estimatedTime
     * @param deadline
     * @param id
     * @param description
     */
    public Job(int id, String description, LocalDateTime deadline, float estimatedTime) {
        this.id = id;
        this.description = description;
        this.deadline = deadline;
        this.estimatedTime = estimatedTime;
    }


    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public float getEstimatedTime() {
        return estimatedTime;
    }


    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                ", estimatedTime=" + estimatedTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Job that = (Job) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public int compareTo(Job o) {
        return this.id - o.id;
    }

 }

