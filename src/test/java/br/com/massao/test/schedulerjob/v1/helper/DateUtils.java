package br.com.massao.test.schedulerjob.v1.helper;

import br.com.massao.test.schedulerjob.v1.bean.ExecutionWindow;

import java.time.LocalDateTime;

public class DateUtils {
    public static String getDate(String date) {
        return getDateTime(date).format(ExecutionWindow.FORMATTER);
    }

    public static LocalDateTime getDateTime(String date) {
        return LocalDateTime.parse(date, ExecutionWindow.FORMATTER);
    }
}
