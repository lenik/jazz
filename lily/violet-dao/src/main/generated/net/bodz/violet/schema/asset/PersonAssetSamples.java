package net.bodz.violet.schema.asset;

import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.GroupMapper;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.schema.contact.Person;
import net.bodz.lily.schema.contact.dao.PersonMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.schema.art.Artifact;
import net.bodz.violet.schema.art.dao.ArtifactMapper;
import net.bodz.violet.schema.store.Region;
import net.bodz.violet.schema.store.dao.RegionMapper;

public class PersonAssetSamples
        extends TestSampleBuilder {

    public Group ownerGroup;
    public Artifact artifact;
    public User ownerUser;
    public Region region;
    public Person owner;

    @Override
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

    @Override
    public PersonAssetSamples wireAny(IRandomPicker picker) {
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.artifact = picker.pickAny(ArtifactMapper.class, "art");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.region = picker.pickAny(RegionMapper.class, "region");
        this.owner = picker.pickAny(PersonMapper.class, "person");
        return this;
    }

    @Override
    public PersonAsset buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
