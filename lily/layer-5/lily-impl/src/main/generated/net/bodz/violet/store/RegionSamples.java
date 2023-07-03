package net.bodz.violet.store;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.violet.art.Artifact;
import net.bodz.violet.art.ArtifactCategory;
import net.bodz.violet.art.Dim3d;
import net.bodz.violet.art.Dim3dSamples;

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

}
