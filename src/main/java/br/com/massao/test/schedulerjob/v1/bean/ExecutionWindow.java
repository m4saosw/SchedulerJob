package br.com.massao.test.schedulerjob.v1.bean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Controle da janela de execucao com horario inicial e final
 */
public class ExecutionWindow {
    private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public ExecutionWindow(String startDate, String endDate) {
        if (startDate == null || endDate == null  || startDate.isEmpty() || endDate.isEmpty())
            throw new IllegalArgumentException("Data inválida!");

        this.startDate = toDate(startDate);
        this.endDate = toDate(endDate);

        if (!isValidWindow())
            throw new IllegalArgumentException("Janela de datas inválida!");
    }

    private LocalDateTime toDate(String data) {
        return LocalDateTime.parse(data, FORMATTER);
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    @Override
    public String toString() {
        return "ExecutionWindow{" +
                "startDate=" + startDate.format(FORMATTER) +
                ", endDate=" + endDate.format(FORMATTER) +
                '}';
    }


    /**
     * Janela valida?
     * @return
     */
    public boolean isValidWindow() {
        return this.startDate.isBefore(this.endDate);
    }


    /**
     * Data informada menor ou igual a janela?
     * @param data
     * @return
     */
    public boolean isBeforeWindow(LocalDateTime data) {
        // data igual ou anterior ao inicio da janela
        return (data.compareTo(this.getStartDate()) <= 0);
    }
}