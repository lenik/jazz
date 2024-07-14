package net.bodz.violet.schema.plan;

import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.GroupMapper;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.schema.util.UomRow;
import net.bodz.lily.schema.util.dao.UomRowMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class PlanParameterTypeSamples
        extends TestSampleBuilder {

    public User ownerUser;
    public Group ownerGroup;
    public UomRow uom;

    @Override
    public PlanParameterType build()
            throws Exception {
        PlanParameterType a = new PlanParameterType();
        a.setOwnerUser(ownerUser);
        a.setOwnerGroup(ownerGroup);
        a.setUom(uom);
        a.setName("Jj ohajroi");
        return a;
    }

    @Override
    public PlanParameterTypeSamples wireAny(IRandomPicker picker) {
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.uom = picker.pickAny(UomRowMapper.class, "uom");
        return this;
    }

    @Override
    public PlanParameterType buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
