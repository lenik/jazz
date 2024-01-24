package net.bodz.violet.schema.edu;

import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.GroupMapper;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.schema.edu.dao.CourseCategoryMapper;

public class CourseCategorySamples
        extends TestSampleBuilder {

    public CourseCategory parent;
    public Group ownerGroup;
    public User ownerUser;

    @Override
    public CourseCategory build()
            throws Exception {
        CourseCategory a = new CourseCategory();
        a.setParent(parent);
        a.setOwnerGroup(ownerGroup);
        a.setOwnerUser(ownerUser);
        return a;
    }

    @Override
    public CourseCategorySamples wireAny(IRandomPicker picker) {
        this.parent = picker.pickAny(CourseCategoryMapper.class, "coursecat");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        return this;
    }

    @Override
    public CourseCategory buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
