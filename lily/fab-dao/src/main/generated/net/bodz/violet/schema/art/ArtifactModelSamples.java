package net.bodz.violet.schema.art;

import java.time.OffsetDateTime;

import net.bodz.bas.c.java.time.DateTimes;
import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.GroupMapper;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.schema.art.dao.ArtifactMapper;
import net.bodz.violet.schema.art.dao.ArtifactModelMapper;

public class ArtifactModelSamples
        extends TestSampleBuilder {

    public User ownerUser;
    public ArtifactModel obsolete;
    public Group ownerGroup;
    public Artifact artifact;

    @Override
    public ArtifactModel build()
            throws Exception {
        ArtifactModel a = new ArtifactModel();
        a.setOwnerUser(ownerUser);
        a.setObsolete(obsolete);
        a.setOwnerGroup(ownerGroup);
        a.setArtifact(artifact);
        a.setValid(false);
        a.setValidSince(OffsetDateTime.parse("2024-12-20T15:21:39.315-06:44", DateTimes.ISO_OFFSET_DATE_TIME));
        a.setValidUntil(OffsetDateTime.parse("2025-01-07T09:27:37.659-10:32", DateTimes.ISO_OFFSET_DATE_TIME));
        a.setModelName("wo; uhxuk; afuiue. iyybi? U auac");
        return a;
    }

    @Override
    public ArtifactModelSamples wireAny(IRandomPicker picker) {
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.obsolete = picker.pickAny(ArtifactModelMapper.class, "artmodel");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.artifact = picker.pickAny(ArtifactMapper.class, "art");
        return this;
    }

    @Override
    public ArtifactModel buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
