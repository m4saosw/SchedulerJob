package br.com.massao.test.schedulerjob.v1.util;

import br.com.massao.test.schedulerjob.v1.helper.DateUtils;
import br.com.massao.test.schedulerjob.v1.model.input.Job;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class ComparatorDeadlineTest {

    ComparatorDeadline compData = new ComparatorDeadline();
    final static LocalDateTime DATA1 = DateUtils.getDateTime("2019-11-01 09:00:00");
    final static LocalDateTime DATA2 = DateUtils.getDateTime("2019-12-15 09:00:00");


    @Test
    public void job1AntesQueJob2() {
        Job job1 = new Job(1, "description 1", DATA1, 1);
        Job job2 = new Job(2, "description 2", DATA2, 2);

        assertEquals(-1, compData.compare(job1, job2));
    }


    @Test
    public void job1IgualJob2() {
        Job job1 = new Job(4, "description 1", DATA1, 1);
        Job job2 = new Job(2, "description 2", DATA1, 2);

        assertEquals(0, compData.compare(job1, job2));
    }


    @Test
    public void job1DepoisQueJob2() {
        Job job1 = new Job(4, "description 1", DATA2, 1);
        Job job2 = new Job(2, "description 2", DATA1, 2);

        assertEquals(1, compData.compare(job1, job2));
    }
}