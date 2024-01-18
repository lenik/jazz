package net.bodz.violet.fab;

import java.time.ZonedDateTime;

import net.bodz.bas.c.java.util.DateTimes;
import net.bodz.bas.c.java.util.Dates;
import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.fab.dao.FabOrderMapper;

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
        a.setId(8256499091221121113L);
        a.setBeginTime(ZonedDateTime.parse("2024-01-02 08:11:36", DateTimes.D10T8));
        a.setEndTime(ZonedDateTime.parse("2023-12-23 05:21:31", DateTimes.D10T8));
        a.setYear(812979726);
        a.setProcessCount(1299055126);
        a.setTrackCount(432615440);
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
