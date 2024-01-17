package net.bodz.lily.inet;

import net.bodz.lily.inet.dao.ExternalSiteMapper;
import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class ExternalSiteSamples
        extends TestSampleBuilder {

    public ExternalSite parent;
    public Group ownerGroup;
    public User ownerUser;

    @Override
    public ExternalSite build()
            throws Exception {
        ExternalSite a = new ExternalSite();
        a.setParent(parent);
        a.setOwnerGroup(ownerGroup);
        a.setOwnerUser(ownerUser);
        a.setId(369647515);
        a.setDepth(283320390);
        a.setUrlfmt("ueca qko uxa*Afguo");
        a.setBonus(1684163652);
        a.setCount(2075354756);
        return a;
    }

    @Override
    public ExternalSiteSamples wireAny(IRandomPicker picker) {
        this.parent = picker.pickAny(ExternalSiteMapper.class, "extsite");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        return this;
    }

    @Override
    public ExternalSite buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
