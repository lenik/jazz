package net.bodz.violet.schema.edu;

import java.math.BigDecimal;

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
        a.setScore(new BigDecimal("8578803"));
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
