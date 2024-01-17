package net.bodz.violet.art;

import net.bodz.lily.inet.ExternalSite;
import net.bodz.lily.inet.dao.ExternalSiteMapper;
import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.art.dao.ArtifactMapper;

public class ArtifactBackrefSamples
        extends TestSampleBuilder {

    public ExternalSite site;
    public Group ownerGroup;
    public Artifact artifact;
    public User ownerUser;

    @Override
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

    @Override
    public ArtifactBackrefSamples wireAny(IRandomPicker picker) {
        this.site = picker.pickAny(ExternalSiteMapper.class, "extsite");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.artifact = picker.pickAny(ArtifactMapper.class, "art");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        return this;
    }

    @Override
    public ArtifactBackref buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
