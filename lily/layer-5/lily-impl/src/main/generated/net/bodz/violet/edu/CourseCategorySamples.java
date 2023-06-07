package net.bodz.violet.edu;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class CourseCategorySamples
        extends TestSampleBuilder {

    public CourseCategory parent;
    public Group ownerGroup;
    public User ownerUser;

    public CourseCategory build()
            throws Exception {
        CourseCategory a = new CourseCategory();
        a.setParent(parent);
        a.setOwnerGroup(ownerGroup);
        a.setOwnerUser(ownerUser);
        return a;
    }

}
