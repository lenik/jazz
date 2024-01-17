package net.bodz.violet.art;

import org.joda.time.DateTime;

import net.bodz.bas.c.java.util.Dates;
import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.art.dao.ArtifactMapper;
import net.bodz.violet.art.dao.ArtifactModelMapper;

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
        a.setId(1149710993);
        a.setBeginTime(new DateTime(Dates.ISO8601Z.parse("2023-12-20T22:05:39.315+0800").getTime()));
        a.setEndTime(new DateTime(Dates.ISO8601Z.parse("2023-12-15T00:42:26.186+0800").getTime()));
        a.setYear(808423222);
        a.setValid(false);
        a.setModel("wo; uhxuk; afuiue. iyybi? U auac");
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
