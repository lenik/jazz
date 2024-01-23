package net.bodz.lily.geo;

import net.bodz.lily.geo.dao.ZoneCategoryMapper;
import net.bodz.lily.geo.dao.ZoneMapper;
import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class ZoneSamples
        extends TestSampleBuilder {

    public Zone parent;
    public User ownerUser;
    public Group ownerGroup;
    public ZoneCategory category;

    @Override
    public Zone build()
            throws Exception {
        Zone a = new Zone();
        a.setParent(parent);
        a.setOwnerUser(ownerUser);
        a.setOwnerGroup(ownerGroup);
        a.setCategory(category);
        a.setId(8479118);
        a.setCode("Ei_okave");
        a.setCountry("");
        a.setDepth(185323507);
        a.setTelCode("oueuqooo.");
        a.setPostCode("");
        return a;
    }

    @Override
    public ZoneSamples wireAny(IRandomPicker picker) {
        this.parent = picker.pickAny(ZoneMapper.class, "zone");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.category = picker.pickAny(ZoneCategoryMapper.class, "zonecat");
        return this;
    }

    @Override
    public Zone buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
