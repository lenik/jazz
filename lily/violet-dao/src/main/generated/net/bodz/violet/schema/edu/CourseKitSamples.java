package net.bodz.violet.schema.edu;

import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.GroupMapper;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.schema.edu.dao.CourseKitCategoryMapper;
import net.bodz.violet.schema.edu.dao.CourseMapper;

public class CourseKitSamples
        extends TestSampleBuilder {

    public User ownerUser;
    public CourseKitCategory category;
    public Course course;
    public Group ownerGroup;

    @Override
    public CourseKit build()
            throws Exception {
        CourseKit a = new CourseKit();
        a.setOwnerUser(ownerUser);
        a.setCategory(category);
        a.setCourse(course);
        a.setOwnerGroup(ownerGroup);
        a.setFavCount(1053548262);
        a.setVoteCount(346408877);
        a.setHateCount(381095999);
        return a;
    }

    @Override
    public CourseKitSamples wireAny(IRandomPicker picker) {
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.category = picker.pickAny(CourseKitCategoryMapper.class, "coursekitcat");
        this.course = picker.pickAny(CourseMapper.class, "course");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        return this;
    }

    @Override
    public CourseKit buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
