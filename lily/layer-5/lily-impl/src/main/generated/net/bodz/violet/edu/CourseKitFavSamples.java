package net.bodz.violet.edu;

import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.edu.dao.CourseKitMapper;

public class CourseKitFavSamples
        extends TestSampleBuilder {

    public CourseKit coursekit;
    public User user;

    @Override
    public CourseKitFav build()
            throws Exception {
        CourseKitFav a = new CourseKitFav();
        a.setCoursekit(coursekit);
        a.setUser(user);
        return a;
    }

    @Override
    public CourseKitFavSamples wireAny(IRandomPicker picker) {
        this.coursekit = picker.pickAny(CourseKitMapper.class, "coursekit");
        this.user = picker.pickAny(UserMapper.class, "user");
        return this;
    }

    @Override
    public CourseKitFav buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
