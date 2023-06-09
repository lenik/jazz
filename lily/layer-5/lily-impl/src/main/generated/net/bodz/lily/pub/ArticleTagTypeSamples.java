package net.bodz.lily.pub;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class ArticleTagTypeSamples
        extends TestSampleBuilder {

    public ArticleTagType parent;
    public User ownerUser;
    public Group ownerGroup;

    public ArticleTagType build()
            throws Exception {
        ArticleTagType a = new ArticleTagType();
        a.setParent(parent);
        a.setOwnerUser(ownerUser);
        a.setOwnerGroup(ownerGroup);
        return a;
    }

}
