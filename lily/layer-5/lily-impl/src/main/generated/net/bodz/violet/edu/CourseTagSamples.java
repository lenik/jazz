package net.bodz.violet.edu;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class CourseTagSamples
        extends TestSampleBuilder {

    public Group ownerGroup;
    public CourseTag parent;
    public User ownerUser;

    public CourseTag build()
            throws Exception {
        CourseTag a = new CourseTag();
        a.setOwnerGroup(ownerGroup);
        a.setParent(parent);
        a.setOwnerUser(ownerUser);
        return a;
    }

}
