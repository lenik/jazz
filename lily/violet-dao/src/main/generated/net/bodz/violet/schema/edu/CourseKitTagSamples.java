package net.bodz.violet.schema.edu;

import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.GroupMapper;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.schema.edu.dao.CourseKitTagMapper;

public class CourseKitTagSamples
        extends TestSampleBuilder {

    public User ownerUser;
    public Group ownerGroup;
    public CourseKitTag parent;

    @Override
    public CourseKitTag build()
            throws Exception {
        CourseKitTag a = new CourseKitTag();
        a.setOwnerUser(ownerUser);
        a.setOwnerGroup(ownerGroup);
        a.setParent(parent);
        return a;
    }

    @Override
    public CourseKitTagSamples wireAny(IRandomPicker picker) {
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.parent = picker.pickAny(CourseKitTagMapper.class, "coursekittag");
        return this;
    }

    @Override
    public CourseKitTag buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
