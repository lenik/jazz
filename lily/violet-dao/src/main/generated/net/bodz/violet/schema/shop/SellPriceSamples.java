package net.bodz.violet.schema.shop;

import java.math.BigDecimal;

import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.GroupMapper;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.schema.art.Artifact;
import net.bodz.violet.schema.art.dao.ArtifactMapper;

public class SellPriceSamples
        extends TestSampleBuilder {

    public Artifact artifact;
    public Group ownerGroup;
    public User ownerUser;

    @Override
    public SellPrice build()
            throws Exception {
        SellPrice a = new SellPrice();
        a.setArtifact(artifact);
        a.setOwnerGroup(ownerGroup);
        a.setOwnerUser(ownerUser);
        a.setId(165217238);
        a.setCode("aamt.");
        a.setPrice(new BigDecimal("88.34"));
        return a;
    }

    @Override
    public SellPriceSamples wireAny(IRandomPicker picker) {
        this.artifact = picker.pickAny(ArtifactMapper.class, "art");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        return this;
    }

    @Override
    public SellPrice buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
