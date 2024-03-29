package net.bodz.violet.schema.plan;

import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.GroupMapper;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class DiaryPhaseSamples
        extends TestSampleBuilder {

    public User ownerUser;
    public Group ownerGroup;

    @Override
    public DiaryPhase build()
            throws Exception {
        DiaryPhase a = new DiaryPhase();
        a.setOwnerUser(ownerUser);
        a.setOwnerGroup(ownerGroup);
        return a;
    }

    @Override
    public DiaryPhaseSamples wireAny(IRandomPicker picker) {
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        return this;
    }

    @Override
    public DiaryPhase buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
