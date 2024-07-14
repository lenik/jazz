package net.bodz.lily.schema.pub;

import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.GroupMapper;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.schema.util.UomRow;
import net.bodz.lily.schema.util.dao.UomRowMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class ArticleParameterTypeSamples
        extends TestSampleBuilder {

    public User ownerUser;
    public UomRow uom;
    public Group ownerGroup;

    @Override
    public ArticleParameterType build()
            throws Exception {
        ArticleParameterType a = new ArticleParameterType();
        a.setOwnerUser(ownerUser);
        a.setUom(uom);
        a.setOwnerGroup(ownerGroup);
        return a;
    }

    @Override
    public ArticleParameterTypeSamples wireAny(IRandomPicker picker) {
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.uom = picker.pickAny(UomRowMapper.class, "uom");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        return this;
    }

    @Override
    public ArticleParameterType buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
