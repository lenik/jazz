package net.bodz.violet.fab;

import java.sql.Timestamp;

import org.joda.time.DateTime;

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
        a.setBeginTime(new DateTime(Dates.ISO8601Z.parse("2024-01-02T05:11:36.840+0800").getTime()));
        a.setEndTime(new DateTime(Dates.ISO8601Z.parse("2023-12-15T20:58:14.711+0800").getTime()));
        a.setYear(736708852);
        a.setSince(new Timestamp(Dates.ISO8601Z.parse("2023-12-20T12:03:52.795+0800").getTime()));
        a.setDeadline(new Timestamp(Dates.ISO8601Z.parse("2024-01-21T12:32:18.093+0800").getTime()));
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
