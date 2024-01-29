package net.bodz.lily.schema.pub;

import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.GroupMapper;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.schema.pub.dao.ArticleCategoryMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class ArticleCategorySamples
        extends TestSampleBuilder {

    public ArticleCategory parent;
    public Group ownerGroup;
    public User ownerUser;

    @Override
    public ArticleCategory build()
            throws Exception {
        ArticleCategory a = new ArticleCategory();
        a.setParent(parent);
        a.setOwnerGroup(ownerGroup);
        a.setOwnerUser(ownerUser);
        a.setId(1337570833);
        a.setName("h");
        a.setDepth(1900074261);
        a.setRefCount(741430112);
        return a;
    }

    @Override
    public ArticleCategorySamples wireAny(IRandomPicker picker) {
        this.parent = picker.pickAny(ArticleCategoryMapper.class, "articlecat");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        return this;
    }

    @Override
    public ArticleCategory buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
