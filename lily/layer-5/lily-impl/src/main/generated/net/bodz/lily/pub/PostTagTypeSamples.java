package net.bodz.lily.pub;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class PostTagTypeSamples
        extends TestSampleBuilder {

    public PostTagType parent;
    public Group ownerGroup;
    public User ownerUser;

    public PostTagType build()
            throws Exception {
        PostTagType a = new PostTagType();
        a.setParent(parent);
        a.setOwnerGroup(ownerGroup);
        a.setOwnerUser(ownerUser);
        return a;
    }

}
