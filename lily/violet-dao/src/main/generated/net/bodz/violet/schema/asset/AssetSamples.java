package net.bodz.violet.schema.asset;

import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.GroupMapper;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.schema.contact.OrgUnit;
import net.bodz.lily.schema.contact.Organization;
import net.bodz.lily.schema.contact.Person;
import net.bodz.lily.schema.contact.dao.OrgUnitMapper;
import net.bodz.lily.schema.contact.dao.OrganizationMapper;
import net.bodz.lily.schema.contact.dao.PersonMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.schema.art.Artifact;
import net.bodz.violet.schema.art.dao.ArtifactMapper;
import net.bodz.violet.schema.store.Region;
import net.bodz.violet.schema.store.dao.RegionMapper;

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

    @Override
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

    @Override
    public AssetSamples wireAny(IRandomPicker picker) {
        this.person = picker.pickAny(PersonMapper.class, "person");
        this.group = picker.pickAny(GroupMapper.class, "group");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.org = picker.pickAny(OrganizationMapper.class, "org");
        this.orgUnit = picker.pickAny(OrgUnitMapper.class, "orgunit");
        this.artifact = picker.pickAny(ArtifactMapper.class, "art");
        this.region = picker.pickAny(RegionMapper.class, "region");
        this.user = picker.pickAny(UserMapper.class, "user");
        return this;
    }

    @Override
    public Asset buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
