package net.bodz.violet.store;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class StorePhaseSamples
        extends TestSampleBuilder {

    public User ownerUser;
    public Group ownerGroup;

    @Override
    public StorePhase build()
            throws Exception {
        StorePhase a = new StorePhase();
        a.setOwnerUser(ownerUser);
        a.setOwnerGroup(ownerGroup);
        return a;
    }

    @Override
    public StorePhaseSamples wireAny(IRandomPicker picker) {
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        return this;
    }

    @Override
    public StorePhase buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
