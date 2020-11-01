package br.com.massao.test.schedulerjob.v1.util;

import br.com.massao.test.schedulerjob.v1.helper.DateUtils;
import br.com.massao.test.schedulerjob.v1.model.input.Job;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class ComparatorDeadlineTest {

    ComparatorDeadline compData = new ComparatorDeadline();


    @Test
    public void job1AntesQueJob2() {
        LocalDateTime data1 = DateUtils.getDateTime("2019-11-01 09:00:00");
        Job job1 = new Job(1, "description 1", data1, 1);

        LocalDateTime data2    = DateUtils.getDateTime("2019-12-15 09:00:00");
        Job job2 = new Job(2, "description 2", data2, 2);

        assertEquals(-1, compData.compare(job1, job2));
    }


    @Test
    public void job1IgualJob2() {
        LocalDateTime data1    = DateUtils.getDateTime("2019-12-15 09:00:00");
        Job job1 = new Job(4, "description 1", data1, 1);
        Job job2 = new Job(2, "description 2", data1, 2);

        assertEquals(0, compData.compare(job1, job2));
    }


    @Test
    public void job1DepoisQueJob2() {
        LocalDateTime data1    = DateUtils.getDateTime("2019-12-15 09:00:00");
        Job job1 = new Job(4, "description 1", data1, 1);

        LocalDateTime data2 = DateUtils.getDateTime("2019-11-30 09:00:00");
        Job job2 = new Job(2, "description 2", data2, 2);

        assertEquals(1, compData.compare(job1, job2));
    }
}