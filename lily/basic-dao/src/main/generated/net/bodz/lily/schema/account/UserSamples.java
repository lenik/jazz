package net.bodz.lily.schema.account;

import net.bodz.lily.schema.account.dao.GroupMapper;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.schema.account.dao.UserTypeMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class UserSamples
        extends TestSampleBuilder {

    public Group primaryGroup;
    public User referer;
    public UserType type;

    @Override
    public User build()
            throws Exception {
        User a = new User();
        a.setPrimaryGroup(primaryGroup);
        a.setReferer(referer);
        a.setType(type);
        return a;
    }

    @Override
    public UserSamples wireAny(IRandomPicker picker) {
        this.primaryGroup = picker.pickAny(GroupMapper.class, "group");
        this.referer = picker.pickAny(UserMapper.class, "user");
        this.type = picker.pickAny(UserTypeMapper.class, "usertype");
        return this;
    }

    @Override
    public User buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
