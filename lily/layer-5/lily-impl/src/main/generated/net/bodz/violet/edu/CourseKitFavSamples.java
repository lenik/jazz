package net.bodz.violet.edu;

import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class CourseKitFavSamples
        extends TestSampleBuilder {

    public CourseKit coursekit;
    public User user;

    public CourseKitFav build()
            throws Exception {
        CourseKitFav a = new CourseKitFav();
        a.setCoursekit(coursekit);
        a.setUser(user);
        return a;
    }

}
