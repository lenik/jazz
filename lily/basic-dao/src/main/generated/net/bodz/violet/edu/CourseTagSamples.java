package net.bodz.violet.edu;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.edu.dao.CourseTagMapper;

public class CourseTagSamples
        extends TestSampleBuilder {

    public Group ownerGroup;
    public CourseTag parent;
    public User ownerUser;

    @Override
    public CourseTag build()
            throws Exception {
        CourseTag a = new CourseTag();
        a.setOwnerGroup(ownerGroup);
        a.setParent(parent);
        a.setOwnerUser(ownerUser);
        return a;
    }

    @Override
    public CourseTagSamples wireAny(IRandomPicker picker) {
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.parent = picker.pickAny(CourseTagMapper.class, "coursetag");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        return this;
    }

    @Override
    public CourseTag buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
