package net.bodz.violet.schema.fab;

import java.math.BigDecimal;
import java.sql.Timestamp;

import net.bodz.bas.c.java.util.Dates;
import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.GroupMapper;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.schema.contact.OrgUnit;
import net.bodz.lily.schema.contact.dao.OrgUnitMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.schema.fab.dao.FabProcessMapper;

public class FabTrackSamples
        extends TestSampleBuilder {

    public OrgUnit orgUnit;
    public Group ownerGroup;
    public User ownerUser;
    public FabProcess process;

    @Override
    public FabTrack build()
            throws Exception {
        FabTrack a = new FabTrack();
        a.setOrgUnit(orgUnit);
        a.setOwnerGroup(ownerGroup);
        a.setOwnerUser(ownerUser);
        a.setProcess(process);
        a.setSince(new Timestamp(Dates.ISO8601Z.parse("2024-01-03T14:02:47.333+0800").getTime()));
        a.setDeadline(new Timestamp(Dates.ISO8601Z.parse("2024-01-07T09:31:35.301+0800").getTime()));
        a.setPlannedQuantity(new BigDecimal("1122895"));
        a.setActualQuantity(new BigDecimal("26140200.60"));
        a.setValidQuantity(new BigDecimal("6356564309547732"));
        return a;
    }

    @Override
    public FabTrackSamples wireAny(IRandomPicker picker) {
        this.orgUnit = picker.pickAny(OrgUnitMapper.class, "orgunit");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.process = picker.pickAny(FabProcessMapper.class, "fabproc");
        return this;
    }

    @Override
    public FabTrack buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
