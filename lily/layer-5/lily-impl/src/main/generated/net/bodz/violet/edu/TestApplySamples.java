package net.bodz.violet.edu;

import java.math.BigDecimal;
import java.sql.Timestamp;

import net.bodz.bas.c.java.util.Dates;
import net.bodz.lily.contact.Person;
import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class TestApplySamples
        extends TestSampleBuilder {

    public Person person;
    public Group ownerGroup;
    public TestPaper paper;
    public User ownerUser;

    public TestApply build()
            throws Exception {
        TestApply a = new TestApply();
        a.setPerson(person);
        a.setOwnerGroup(ownerGroup);
        a.setPaper(paper);
        a.setOwnerUser(ownerUser);
        a.setId(4876527568694728934L);
        a.setBeginTime(new Timestamp(Dates.ISO8601Z.parse("2022-12-12T18:07:26.928+0800").getTime()));
        a.setEndTime(new Timestamp(Dates.ISO8601Z.parse("2023-01-01T10:08:53.010+0800").getTime()));
        a.setYear(635596291);
        a.setScore(new BigDecimal("967710.74"));
        return a;
    }

}
