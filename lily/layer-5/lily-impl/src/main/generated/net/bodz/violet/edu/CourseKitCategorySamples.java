package net.bodz.violet.edu;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class CourseKitCategorySamples
        extends TestSampleBuilder {

    public User ownerUser;
    public CourseKitCategory parent;
    public Group ownerGroup;

    public CourseKitCategory build()
            throws Exception {
        CourseKitCategory a = new CourseKitCategory();
        a.setOwnerUser(ownerUser);
        a.setParent(parent);
        a.setOwnerGroup(ownerGroup);
        return a;
    }

}
