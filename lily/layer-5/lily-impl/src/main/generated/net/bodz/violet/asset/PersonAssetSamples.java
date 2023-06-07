package net.bodz.violet.asset;

import net.bodz.lily.contact.Person;
import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.violet.art.Artifact;
import net.bodz.violet.store.Region;

public class PersonAssetSamples
        extends TestSampleBuilder {

    public Group ownerGroup;
    public Artifact artifact;
    public User ownerUser;
    public Region region;
    public Person owner;

    public PersonAsset build()
            throws Exception {
        PersonAsset a = new PersonAsset();
        a.setOwnerGroup(ownerGroup);
        a.setArtifact(artifact);
        a.setOwnerUser(ownerUser);
        a.setRegion(region);
        a.setOwner(owner);
        return a;
    }

}
