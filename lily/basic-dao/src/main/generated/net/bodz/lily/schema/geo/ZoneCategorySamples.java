package net.bodz.lily.schema.geo;

import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.GroupMapper;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.schema.geo.dao.ZoneCategoryMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class ZoneCategorySamples
        extends TestSampleBuilder {

    public ZoneCategory parent;
    public Group ownerGroup;
    public User ownerUser;

    @Override
    public ZoneCategory build()
            throws Exception {
        ZoneCategory a = new ZoneCategory();
        a.setParent(parent);
        a.setOwnerGroup(ownerGroup);
        a.setOwnerUser(ownerUser);
        a.setName("Rboa*diu.");
        return a;
    }

    @Override
    public ZoneCategorySamples wireAny(IRandomPicker picker) {
        this.parent = picker.pickAny(ZoneCategoryMapper.class, "zonecat");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        return this;
    }

    @Override
    public ZoneCategory buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
