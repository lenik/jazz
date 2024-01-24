package net.bodz.violet.schema.store;

import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.GroupMapper;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.schema.art.Artifact;
import net.bodz.violet.schema.art.ArtifactCategory;
import net.bodz.violet.schema.art.Dim3d;
import net.bodz.violet.schema.art.Dim3dSamples;
import net.bodz.violet.schema.art.dao.ArtifactCategoryMapper;
import net.bodz.violet.schema.art.dao.ArtifactMapper;
import net.bodz.violet.schema.store.dao.RegionCategoryMapper;
import net.bodz.violet.schema.store.dao.RegionLevelMapper;
import net.bodz.violet.schema.store.dao.RegionMapper;
import net.bodz.violet.schema.store.dao.RegionTagMapper;

public class RegionSamples
        extends TestSampleBuilder {

    public ArtifactCategory forCat;
    public Region proto;
    public RegionTag tag;
    public RegionCategory category;
    public Region parent;
    public RegionLevel level;
    public Artifact artifact;
    public User ownerUser;
    public Artifact material;
    public Group ownerGroup;
    public Artifact forArt;

    public Dim3d bbox;
    public Dim3d position;

    @Override
    public Region build()
            throws Exception {
        Region a = new Region();
        a.setForCat(forCat);
        a.setProto(proto);
        a.setTag(tag);
        a.setCategory(category);
        a.setParent(parent);
        a.setLevel(level);
        a.setArtifact(artifact);
        a.setOwnerUser(ownerUser);
        a.setMaterial(material);
        a.setOwnerGroup(ownerGroup);
        a.setForArt(forArt);
        a.setCode("Msib jz");
        a.setPath("buge ugs, a afuptb*g! ake co qoouu. so*obuvg_jeqad, ked;");
        a.setHeight(2078674628);
        a.setBbox(new Dim3dSamples().build());
        a.setPosition(new Dim3dSamples().build());
        return a;
    }

    @Override
    public RegionSamples wireAny(IRandomPicker picker) {
        this.forCat = picker.pickAny(ArtifactCategoryMapper.class, "artcat");
        this.proto = picker.pickAny(RegionMapper.class, "region");
        this.tag = picker.pickAny(RegionTagMapper.class, "regiontag");
        this.category = picker.pickAny(RegionCategoryMapper.class, "regioncat");
        this.parent = picker.pickAny(RegionMapper.class, "region");
        this.level = picker.pickAny(RegionLevelMapper.class, "regionlevel");
        this.artifact = picker.pickAny(ArtifactMapper.class, "art");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.material = picker.pickAny(ArtifactMapper.class, "art");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.forArt = picker.pickAny(ArtifactMapper.class, "art");
        return this;
    }

    @Override
    public Region buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
