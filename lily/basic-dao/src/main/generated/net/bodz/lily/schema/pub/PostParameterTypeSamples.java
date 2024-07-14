package net.bodz.lily.schema.pub;

import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.GroupMapper;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.schema.util.UomRow;
import net.bodz.lily.schema.util.dao.UomRowMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class PostParameterTypeSamples
        extends TestSampleBuilder {

    public Group ownerGroup;
    public UomRow uom;
    public User ownerUser;

    @Override
    public PostParameterType build()
            throws Exception {
        PostParameterType a = new PostParameterType();
        a.setOwnerGroup(ownerGroup);
        a.setUom(uom);
        a.setOwnerUser(ownerUser);
        return a;
    }

    @Override
    public PostParameterTypeSamples wireAny(IRandomPicker picker) {
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.uom = picker.pickAny(UomRowMapper.class, "uom");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        return this;
    }

    @Override
    public PostParameterType buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
