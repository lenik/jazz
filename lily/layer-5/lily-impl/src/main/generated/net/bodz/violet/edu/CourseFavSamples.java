package net.bodz.violet.edu;

import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class CourseFavSamples
        extends TestSampleBuilder {

    public User user;
    public Course course;

    public CourseFav build()
            throws Exception {
        CourseFav a = new CourseFav();
        a.setUser(user);
        a.setCourse(course);
        return a;
    }

}
