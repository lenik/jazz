package net.bodz.violet.schema.plan;

import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.GroupMapper;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class DiaryParameterSamples
        extends TestSampleBuilder {

    public Group ownerGroup;
    public User ownerUser;

    @Override
    public DiaryParameter build()
            throws Exception {
        DiaryParameter a = new DiaryParameter();
        a.setOwnerGroup(ownerGroup);
        a.setOwnerUser(ownerUser);
        a.setDummy(807287720);
        return a;
    }

    @Override
    public DiaryParameterSamples wireAny(IRandomPicker picker) {
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        return this;
    }

    @Override
    public DiaryParameter buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
