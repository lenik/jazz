package net.bodz.lily.pub;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class PostCategorySamples
        extends TestSampleBuilder {

    public User ownerUser;
    public PostCategory parent;
    public Group ownerGroup;

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

}
