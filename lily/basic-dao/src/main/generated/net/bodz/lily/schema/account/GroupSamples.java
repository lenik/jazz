package net.bodz.lily.schema.account;

import net.bodz.lily.schema.account.dao.GroupMapper;
import net.bodz.lily.schema.account.dao.GroupTypeMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class GroupSamples
        extends TestSampleBuilder {

    public Group parent;
    public GroupType type;

    @Override
    public Group build()
            throws Exception {
        Group a = new Group();
        a.setParent(parent);
        a.setType(type);
        return a;
    }

    @Override
    public GroupSamples wireAny(IRandomPicker picker) {
        this.parent = picker.pickAny(GroupMapper.class, "group");
        this.type = picker.pickAny(GroupTypeMapper.class, "grouptype");
        return this;
    }

    @Override
    public Group buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
