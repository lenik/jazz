package net.bodz.lily.pub;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class ArticleParameterTypeSamples
        extends TestSampleBuilder {

    public User ownerUser;
    public Group ownerGroup;

    @Override
    public ArticleParameterType build()
            throws Exception {
        ArticleParameterType a = new ArticleParameterType();
        a.setOwnerUser(ownerUser);
        a.setOwnerGroup(ownerGroup);
        a.setDummy(1237522762);
        return a;
    }

    @Override
    public ArticleParameterTypeSamples wireAny(IRandomPicker picker) {
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        return this;
    }

    @Override
    public ArticleParameterType buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
