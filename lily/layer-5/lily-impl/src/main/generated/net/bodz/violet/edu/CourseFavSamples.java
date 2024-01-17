package net.bodz.violet.edu;

import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.edu.dao.CourseMapper;

public class CourseFavSamples
        extends TestSampleBuilder {

    public User user;
    public Course course;

    @Override
    public CourseFav build()
            throws Exception {
        CourseFav a = new CourseFav();
        a.setUser(user);
        a.setCourse(course);
        return a;
    }

    @Override
    public CourseFavSamples wireAny(IRandomPicker picker) {
        this.user = picker.pickAny(UserMapper.class, "user");
        this.course = picker.pickAny(CourseMapper.class, "course");
        return this;
    }

    @Override
    public CourseFav buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
