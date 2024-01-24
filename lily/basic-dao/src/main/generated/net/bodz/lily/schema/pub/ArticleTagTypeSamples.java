package net.bodz.lily.schema.pub;

import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.GroupMapper;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.schema.pub.dao.ArticleTagTypeMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class ArticleTagTypeSamples
        extends TestSampleBuilder {

    public ArticleTagType parent;
    public User ownerUser;
    public Group ownerGroup;

    @Override
    public ArticleTagType build()
            throws Exception {
        ArticleTagType a = new ArticleTagType();
        a.setParent(parent);
        a.setOwnerUser(ownerUser);
        a.setOwnerGroup(ownerGroup);
        return a;
    }

    @Override
    public ArticleTagTypeSamples wireAny(IRandomPicker picker) {
        this.parent = picker.pickAny(ArticleTagTypeMapper.class, "articletag");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        return this;
    }

    @Override
    public ArticleTagType buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
