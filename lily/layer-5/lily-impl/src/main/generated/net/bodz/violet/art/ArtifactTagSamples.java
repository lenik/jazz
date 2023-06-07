package net.bodz.violet.art;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class ArtifactTagSamples
        extends TestSampleBuilder {

    public User ownerUser;
    public Group ownerGroup;
    public ArtifactTag parent;

    public ArtifactTag build()
            throws Exception {
        ArtifactTag a = new ArtifactTag();
        a.setOwnerUser(ownerUser);
        a.setOwnerGroup(ownerGroup);
        a.setParent(parent);
        return a;
    }

}
