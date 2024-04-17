package net.bodz.violet.schema.fab;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import net.bodz.bas.c.java.time.DateTimes;
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
        a.setSince(OffsetDateTime.parse("2024-01-04T01:39:47.333+11:37", DateTimes.ISO_OFFSET_DATE_TIME));
        a.setDeadline(OffsetDateTime.parse("2024-01-23T07:07:35.274+01:22", DateTimes.ISO_OFFSET_DATE_TIME));
        a.setPlannedQuantity(new BigDecimal("895702614020056086"));
        a.setActualQuantity(new BigDecimal("656430954773288.08"));
        a.setValidQuantity(new BigDecimal(""));
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
