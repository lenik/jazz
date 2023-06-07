package net.bodz.violet.asset;

import net.bodz.lily.contact.OrgUnit;
import net.bodz.lily.contact.Organization;
import net.bodz.lily.contact.Person;
import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.violet.art.Artifact;
import net.bodz.violet.store.Region;

public class AssetSamples
        extends TestSampleBuilder {

    public Person person;
    public Group group;
    public Group ownerGroup;
    public User ownerUser;
    public Organization org;
    public OrgUnit orgUnit;
    public Artifact artifact;
    public Region region;
    public User user;

    public Asset build()
            throws Exception {
        Asset a = new Asset();
        a.setPerson(person);
        a.setGroup(group);
        a.setOwnerGroup(ownerGroup);
        a.setOwnerUser(ownerUser);
        a.setOrg(org);
        a.setOrgUnit(orgUnit);
        a.setArtifact(artifact);
        a.setRegion(region);
        a.setUser(user);
        return a;
    }

}
