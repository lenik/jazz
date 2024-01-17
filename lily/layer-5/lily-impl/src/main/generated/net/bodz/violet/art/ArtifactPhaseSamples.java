package net.bodz.violet.art;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class ArtifactPhaseSamples
        extends TestSampleBuilder {

    public User ownerUser;
    public Group ownerGroup;

    @Override
    public ArtifactPhase build()
            throws Exception {
        ArtifactPhase a = new ArtifactPhase();
        a.setOwnerUser(ownerUser);
        a.setOwnerGroup(ownerGroup);
        return a;
    }

    @Override
    public ArtifactPhaseSamples wireAny(IRandomPicker picker) {
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        return this;
    }

    @Override
    public ArtifactPhase buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
