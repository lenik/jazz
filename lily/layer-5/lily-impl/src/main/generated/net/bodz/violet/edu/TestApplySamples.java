package net.bodz.violet.edu;

import java.math.BigDecimal;

import org.joda.time.DateTime;

import net.bodz.bas.c.java.util.Dates;
import net.bodz.lily.contact.Person;
import net.bodz.lily.contact.dao.PersonMapper;
import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.edu.dao.TestPaperMapper;

public class TestApplySamples
        extends TestSampleBuilder {

    public Person person;
    public Group ownerGroup;
    public TestPaper paper;
    public User ownerUser;

    @Override
    public TestApply build()
            throws Exception {
        TestApply a = new TestApply();
        a.setPerson(person);
        a.setOwnerGroup(ownerGroup);
        a.setPaper(paper);
        a.setOwnerUser(ownerUser);
        a.setId(4876527568694728934L);
        a.setBeginTime(new DateTime(Dates.ISO8601Z.parse("2023-12-12T18:07:26.928+0800").getTime()));
        a.setEndTime(new DateTime(Dates.ISO8601Z.parse("2024-01-01T10:08:53.010+0800").getTime()));
        a.setYear(635596291);
        a.setScore(new BigDecimal("967710.74"));
        return a;
    }

    @Override
    public TestApplySamples wireAny(IRandomPicker picker) {
        this.person = picker.pickAny(PersonMapper.class, "person");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.paper = picker.pickAny(TestPaperMapper.class, "testpaper");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        return this;
    }

    @Override
    public TestApply buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
