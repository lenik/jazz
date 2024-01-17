package net.bodz.violet.store;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.store.dao.RegionTagMapper;

public class RegionTagSamples
        extends TestSampleBuilder {

    public User ownerUser;
    public RegionTag parent;
    public Group ownerGroup;

    @Override
    public RegionTag build()
            throws Exception {
        RegionTag a = new RegionTag();
        a.setOwnerUser(ownerUser);
        a.setParent(parent);
        a.setOwnerGroup(ownerGroup);
        return a;
    }

    @Override
    public RegionTagSamples wireAny(IRandomPicker picker) {
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.parent = picker.pickAny(RegionTagMapper.class, "regiontag");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        return this;
    }

    @Override
    public RegionTag buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
