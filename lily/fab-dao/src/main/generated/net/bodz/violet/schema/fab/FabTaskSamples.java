package net.bodz.violet.schema.fab;

import net.bodz.bas.c.java.util.Dates;
import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.GroupMapper;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.schema.fab.dao.FabOrderMapper;

public class FabTaskSamples
        extends TestSampleBuilder {

    public FabOrder order;
    public Group ownerGroup;
    public User ownerUser;

    @Override
    public FabTask build()
            throws Exception {
        FabTask a = new FabTask();
        a.setOrder(order);
        a.setOwnerGroup(ownerGroup);
        a.setOwnerUser(ownerUser);
        a.setProcessCount(1393305289);
        a.setTrackCount(736708852);
        return a;
    }

    @Override
    public FabTaskSamples wireAny(IRandomPicker picker) {
        this.order = picker.pickAny(FabOrderMapper.class, "fabodr");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        return this;
    }

    @Override
    public FabTask buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
