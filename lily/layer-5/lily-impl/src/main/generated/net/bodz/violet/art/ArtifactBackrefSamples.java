package net.bodz.violet.art;

import net.bodz.lily.inet.ExternalSite;
import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class ArtifactBackrefSamples
        extends TestSampleBuilder {

    public ExternalSite site;
    public Group ownerGroup;
    public Artifact artifact;
    public User ownerUser;

    public ArtifactBackref build()
            throws Exception {
        ArtifactBackref a = new ArtifactBackref();
        a.setSite(site);
        a.setOwnerGroup(ownerGroup);
        a.setArtifact(artifact);
        a.setOwnerUser(ownerUser);
        a.setKey("Ict");
        a.setValue(412334812);
        return a;
    }

}
