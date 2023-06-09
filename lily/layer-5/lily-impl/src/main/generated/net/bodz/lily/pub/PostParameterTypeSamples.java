package net.bodz.lily.pub;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class PostParameterTypeSamples
        extends TestSampleBuilder {

    public Group ownerGroup;
    public User ownerUser;

    public PostParameterType build()
            throws Exception {
        PostParameterType a = new PostParameterType();
        a.setOwnerGroup(ownerGroup);
        a.setOwnerUser(ownerUser);
        a.setDummy(329238087);
        return a;
    }

}
