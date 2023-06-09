package net.bodz.lily.pub;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class ArticleParameterTypeSamples
        extends TestSampleBuilder {

    public User ownerUser;
    public Group ownerGroup;

    public ArticleParameterType build()
            throws Exception {
        ArticleParameterType a = new ArticleParameterType();
        a.setOwnerUser(ownerUser);
        a.setOwnerGroup(ownerGroup);
        a.setDummy(1237522762);
        return a;
    }

}
