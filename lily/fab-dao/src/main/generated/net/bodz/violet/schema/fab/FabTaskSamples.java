package net.bodz.violet.schema.fab;

import java.time.OffsetDateTime;

import net.bodz.bas.c.java.time.DateTimes;
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
        a.setSince(OffsetDateTime.parse("2023-12-10T01:08:34.002+07:08", DateTimes.ISO_OFFSET_DATE_TIME));
        a.setDeadline(OffsetDateTime.parse("2023-12-15T09:56:14.711-11:02", DateTimes.ISO_OFFSET_DATE_TIME));
        a.setProcessCount(812979726);
        a.setTrackCount(1365439518);
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
