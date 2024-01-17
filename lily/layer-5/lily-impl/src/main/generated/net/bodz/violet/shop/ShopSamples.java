package net.bodz.violet.shop;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class ShopSamples
        extends TestSampleBuilder {

    public Group ownerGroup;
    public User ownerUser;

    @Override
    public Shop build()
            throws Exception {
        Shop a = new Shop();
        a.setOwnerGroup(ownerGroup);
        a.setOwnerUser(ownerUser);
        a.setId(1588425089);
        a.setCode("Xoc! qj plol; xyi u, ujp-poesr");
        a.setHydm(1929075191);
        return a;
    }

    @Override
    public ShopSamples wireAny(IRandomPicker picker) {
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        return this;
    }

    @Override
    public Shop buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
