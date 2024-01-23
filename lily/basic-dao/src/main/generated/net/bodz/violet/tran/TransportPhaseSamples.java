package net.bodz.violet.tran;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class TransportPhaseSamples
        extends TestSampleBuilder {

    public Group ownerGroup;
    public User ownerUser;

    @Override
    public TransportPhase build()
            throws Exception {
        TransportPhase a = new TransportPhase();
        a.setOwnerGroup(ownerGroup);
        a.setOwnerUser(ownerUser);
        return a;
    }

    @Override
    public TransportPhaseSamples wireAny(IRandomPicker picker) {
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        return this;
    }

    @Override
    public TransportPhase buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
