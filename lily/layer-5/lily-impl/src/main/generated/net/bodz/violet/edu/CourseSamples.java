package net.bodz.violet.edu;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class CourseSamples
        extends TestSampleBuilder {

    public User ownerUser;
    public Group ownerGroup;
    public CourseCategory category;

    public Course build()
            throws Exception {
        Course a = new Course();
        a.setOwnerUser(ownerUser);
        a.setOwnerGroup(ownerGroup);
        a.setCategory(category);
        a.setId(1473188833);
        a.setFavCount(1126603838);
        a.setVoteCount(93615037);
        a.setHateCount(1475018156);
        a.setCredit(2032094729);
        return a;
    }

}
