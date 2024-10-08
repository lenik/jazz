package net.bodz.violet.schema.edu;

import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.GroupMapper;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.schema.edu.dao.CourseKitCategoryMapper;

public class CourseKitCategorySamples
        extends TestSampleBuilder {

    public User ownerUser;
    public CourseKitCategory parent;
    public Group ownerGroup;

    @Override
    public CourseKitCategory build()
            throws Exception {
        CourseKitCategory a = new CourseKitCategory();
        a.setOwnerUser(ownerUser);
        a.setParent(parent);
        a.setOwnerGroup(ownerGroup);
        a.setName("xba.");
        return a;
    }

    @Override
    public CourseKitCategorySamples wireAny(IRandomPicker picker) {
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.parent = picker.pickAny(CourseKitCategoryMapper.class, "coursekitcat");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        return this;
    }

    @Override
    public CourseKitCategory buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
