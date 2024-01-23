package net.bodz.lily.geo;

import net.bodz.lily.geo.dao.ZoneCategoryMapper;
import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
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
        a.setId(1687097473);
        a.setDepth(387711147);
        a.setRefCount(173348754);
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
