package net.bodz.violet.edu;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class CourseKitSamples
        extends TestSampleBuilder {

    public User ownerUser;
    public CourseKitCategory category;
    public Course course;
    public Group ownerGroup;

    public CourseKit build()
            throws Exception {
        CourseKit a = new CourseKit();
        a.setOwnerUser(ownerUser);
        a.setCategory(category);
        a.setCourse(course);
        a.setOwnerGroup(ownerGroup);
        a.setId(1053548262);
        a.setFavCount(346408877);
        a.setVoteCount(381095999);
        a.setHateCount(1611854171);
        return a;
    }

}
