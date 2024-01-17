package net.bodz.violet.plan;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.plan.dao.DiaryTagMapper;

public class DiaryTagSamples
        extends TestSampleBuilder {

    public User ownerUser;
    public DiaryTag parent;
    public Group ownerGroup;

    @Override
    public DiaryTag build()
            throws Exception {
        DiaryTag a = new DiaryTag();
        a.setOwnerUser(ownerUser);
        a.setParent(parent);
        a.setOwnerGroup(ownerGroup);
        return a;
    }

    @Override
    public DiaryTagSamples wireAny(IRandomPicker picker) {
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.parent = picker.pickAny(DiaryTagMapper.class, "diarytag");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        return this;
    }

    @Override
    public DiaryTag buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
