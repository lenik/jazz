package net.bodz.violet.art;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class ArtifactPhaseSamples
        extends TestSampleBuilder {

    public User ownerUser;
    public Group ownerGroup;

    public ArtifactPhase build()
            throws Exception {
        ArtifactPhase a = new ArtifactPhase();
        a.setOwnerUser(ownerUser);
        a.setOwnerGroup(ownerGroup);
        return a;
    }

}
