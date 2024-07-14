package net.bodz.violet.schema.plan;

import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.GroupMapper;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.schema.util.UomRow;
import net.bodz.lily.schema.util.dao.UomRowMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class PlanDoParameterTypeSamples
        extends TestSampleBuilder {

    public UomRow uom;
    public User ownerUser;
    public Group ownerGroup;

    @Override
    public PlanDoParameterType build()
            throws Exception {
        PlanDoParameterType a = new PlanDoParameterType();
        a.setUom(uom);
        a.setOwnerUser(ownerUser);
        a.setOwnerGroup(ownerGroup);
        a.setName("e. glpru'eto_ouaxim");
        return a;
    }

    @Override
    public PlanDoParameterTypeSamples wireAny(IRandomPicker picker) {
        this.uom = picker.pickAny(UomRowMapper.class, "uom");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        return this;
    }

    @Override
    public PlanDoParameterType buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
