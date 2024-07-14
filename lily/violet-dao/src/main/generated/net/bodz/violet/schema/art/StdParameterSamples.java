package net.bodz.violet.schema.art;

import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.GroupMapper;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.schema.util.UomRow;
import net.bodz.lily.schema.util.dao.UomRowMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class StdParameterSamples
        extends TestSampleBuilder {

    public Group ownerGroup;
    public UomRow uom;
    public User ownerUser;

    @Override
    public StdParameter build()
            throws Exception {
        StdParameter a = new StdParameter();
        a.setOwnerGroup(ownerGroup);
        a.setUom(uom);
        a.setOwnerUser(ownerUser);
        a.setName("Mhmva");
        return a;
    }

    @Override
    public StdParameterSamples wireAny(IRandomPicker picker) {
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.uom = picker.pickAny(UomRowMapper.class, "uom");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        return this;
    }

    @Override
    public StdParameter buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
