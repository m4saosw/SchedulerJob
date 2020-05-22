package br.com.massao.test.schedulerjob.v1.model.input;


import com.fasterxml.jackson.annotation.*;

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

    @JsonIgnore
    private boolean available;


    // construtor padrao obrigatorio para o jackson
    private Job() {
        this.available = true;
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

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }


    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                ", estimatedTime=" + FORMATTER.format(deadline) +
                ", available=" + available +
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


    public boolean isEstimatedTimeLessThan(float maxHours) {
        return (this.estimatedTime >= 0  &&  this.estimatedTime <= maxHours);
    }


    @Override
    public int compareTo(Job other) {
        //return this.id - other.id;  nao seguro https://javarevisited.blogspot.com/2011/11/how-to-override-compareto-method-in.html
        return (this.id < other.id ) ? -1: (this.id > other.id) ? 1 : 0;
    }
}