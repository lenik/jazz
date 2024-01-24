package net.bodz.lily.schema.pub;

import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.GroupMapper;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.schema.pub.dao.PostTagTypeMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class PostTagTypeSamples
        extends TestSampleBuilder {

    public PostTagType parent;
    public Group ownerGroup;
    public User ownerUser;

    @Override
    public PostTagType build()
            throws Exception {
        PostTagType a = new PostTagType();
        a.setParent(parent);
        a.setOwnerGroup(ownerGroup);
        a.setOwnerUser(ownerUser);
        return a;
    }

    @Override
    public PostTagTypeSamples wireAny(IRandomPicker picker) {
        this.parent = picker.pickAny(PostTagTypeMapper.class, "posttag");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        return this;
    }

    @Override
    public PostTagType buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
