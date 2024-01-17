package net.bodz.lily.pub;

import net.bodz.lily.pub.dao.ArticleCategoryMapper;
import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
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
