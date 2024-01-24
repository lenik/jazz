package net.bodz.lily.schema.pub;

import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.GroupMapper;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.schema.pub.dao.PostCategoryMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class PostCategorySamples
        extends TestSampleBuilder {

    public User ownerUser;
    public PostCategory parent;
    public Group ownerGroup;

    @Override
    public PostCategory build()
            throws Exception {
        PostCategory a = new PostCategory();
        a.setOwnerUser(ownerUser);
        a.setParent(parent);
        a.setOwnerGroup(ownerGroup);
        a.setId(953371837);
        a.setDepth(360554752);
        a.setRefCount(571495083);
        return a;
    }

    @Override
    public PostCategorySamples wireAny(IRandomPicker picker) {
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.parent = picker.pickAny(PostCategoryMapper.class, "postcat");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        return this;
    }

    @Override
    public PostCategory buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
