package net.bodz.violet.schema.edu;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import net.bodz.bas.c.java.util.DateTimes;
import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.GroupMapper;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.schema.contact.Person;
import net.bodz.lily.schema.contact.dao.PersonMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.schema.edu.dao.TestPaperMapper;

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
        a.setBeginTime(ZonedDateTime.parse("2023-12-12 13:07:26", DateTimes.D10T8));
        a.setEndTime(ZonedDateTime.parse("2023-12-25 03:26:43", DateTimes.D10T8));
        a.setYear(1243013733);
        a.setScore(new BigDecimal("71"));
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
