package net.bodz.violet.schema.edu;

import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.GroupMapper;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.schema.edu.dao.CourseCategoryMapper;

public class CourseSamples
        extends TestSampleBuilder {

    public User ownerUser;
    public Group ownerGroup;
    public CourseCategory category;

    @Override
    public Course build()
            throws Exception {
        Course a = new Course();
        a.setOwnerUser(ownerUser);
        a.setOwnerGroup(ownerGroup);
        a.setCategory(category);
        a.setFavCount(1473188833);
        a.setVoteCount(1126603838);
        a.setHateCount(93615037);
        a.setCredit(1475018156);
        return a;
    }

    @Override
    public CourseSamples wireAny(IRandomPicker picker) {
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.category = picker.pickAny(CourseCategoryMapper.class, "coursecat");
        return this;
    }

    @Override
    public Course buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
