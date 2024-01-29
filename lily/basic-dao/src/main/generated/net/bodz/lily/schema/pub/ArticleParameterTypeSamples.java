package net.bodz.lily.schema.pub;

import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.GroupMapper;
import net.bodz.lily.schema.account.dao.UserMapper;
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
        a.setName("Lb; du-p, U uxea.");
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
