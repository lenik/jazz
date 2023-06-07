package net.bodz.violet.edu;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class CourseKitTagSamples
        extends TestSampleBuilder {

    public User ownerUser;
    public Group ownerGroup;
    public CourseKitTag parent;

    public CourseKitTag build()
            throws Exception {
        CourseKitTag a = new CourseKitTag();
        a.setOwnerUser(ownerUser);
        a.setOwnerGroup(ownerGroup);
        a.setParent(parent);
        return a;
    }

}
