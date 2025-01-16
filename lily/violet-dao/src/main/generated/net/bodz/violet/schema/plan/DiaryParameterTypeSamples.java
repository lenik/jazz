package net.bodz.violet.schema.plan;

import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.GroupMapper;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.schema.util.UomRow;
import net.bodz.lily.schema.util.dao.UomRowMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class DiaryParameterTypeSamples
        extends TestSampleBuilder {

    public Group ownerGroup;
    public UomRow uom;
    public User ownerUser;

    @Override
    public DiaryParameterType build()
            throws Exception {
        DiaryParameterType a = new DiaryParameterType();
        a.setOwnerGroup(ownerGroup);
        a.setUom(uom);
        a.setOwnerUser(ownerUser);
        return a;
    }

    @Override
    public DiaryParameterTypeSamples wireAny(IRandomPicker picker) {
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.uom = picker.pickAny(UomRowMapper.class, "uom");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        return this;
    }

    @Override
    public DiaryParameterType buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
