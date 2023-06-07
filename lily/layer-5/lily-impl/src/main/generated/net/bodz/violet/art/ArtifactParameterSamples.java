package net.bodz.violet.art;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class ArtifactParameterSamples
        extends TestSampleBuilder {

    public Group ownerGroup;
    public User ownerUser;

    public ArtifactParameter build()
            throws Exception {
        ArtifactParameter a = new ArtifactParameter();
        a.setOwnerGroup(ownerGroup);
        a.setOwnerUser(ownerUser);
        a.setDummy(350540066);
        return a;
    }

}
