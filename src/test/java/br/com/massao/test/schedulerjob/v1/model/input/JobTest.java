package br.com.massao.test.schedulerjob.v1.model.input;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class JobTest {

    @Test
    public void job1AntesQueJob2() {
        LocalDateTime data1 = LocalDateTime.parse("2019-11-01 09:00:00", Job.FORMATTER);
        Job job1 = new Job(1, "description 1", data1, 1);

        LocalDateTime data2    = LocalDateTime.parse("2019-12-15 09:00:00", Job.FORMATTER);
        Job job2 = new Job(2, "description 2", data2, 2);

        Assert.assertEquals(-1, job1.compareTo(job2));
    }


    @Test
    public void job1DepoisQueJob2() {
        LocalDateTime data1    = LocalDateTime.parse("2019-12-15 09:00:00", Job.FORMATTER);
        Job job1 = new Job(1, "description 1", data1, 1);

        LocalDateTime data2 = LocalDateTime.parse("2019-11-30 09:00:00", Job.FORMATTER);
        Job job2 = new Job(2, "description 2", data2, 2);

        Assert.assertEquals(1, job1.compareTo(job2));
    }
}