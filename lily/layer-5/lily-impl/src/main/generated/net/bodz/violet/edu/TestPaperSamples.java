package net.bodz.violet.edu;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.edu.dao.CourseMapper;

public class TestPaperSamples
        extends TestSampleBuilder {

    public Course course;
    public Group ownerGroup;
    public User ownerUser;

    @Override
    public TestPaper build()
            throws Exception {
        TestPaper a = new TestPaper();
        a.setCourse(course);
        a.setOwnerGroup(ownerGroup);
        a.setOwnerUser(ownerUser);
        a.setId(475111829);
        a.setTimeout(597639516);
        a.setTotalscore(2015078958);
        return a;
    }

    @Override
    public TestPaperSamples wireAny(IRandomPicker picker) {
        this.course = picker.pickAny(CourseMapper.class, "course");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        return this;
    }

    @Override
    public TestPaper buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
