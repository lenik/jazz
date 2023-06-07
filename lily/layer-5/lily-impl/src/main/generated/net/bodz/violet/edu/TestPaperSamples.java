package net.bodz.violet.edu;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class TestPaperSamples
        extends TestSampleBuilder {

    public Course course;
    public Group ownerGroup;
    public User ownerUser;

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

}
