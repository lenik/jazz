package net.bodz.lily.pub;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class ArticleCategorySamples
        extends TestSampleBuilder {

    public ArticleCategory parent;
    public Group ownerGroup;
    public User ownerUser;

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

}
