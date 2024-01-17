package net.bodz.violet.fab;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.joda.time.DateTime;

import net.bodz.bas.c.java.util.Dates;
import net.bodz.lily.contact.OrgUnit;
import net.bodz.lily.contact.dao.OrgUnitMapper;
import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.fab.dao.FabProcessMapper;

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
        a.setId(2961489878358405846L);
        a.setBeginTime(new DateTime(Dates.ISO8601Z.parse("2024-01-22T18:48:46.633+0800").getTime()));
        a.setEndTime(new DateTime(Dates.ISO8601Z.parse("2024-01-01T01:09:08.371+0800").getTime()));
        a.setYear(1499594658);
        a.setSince(new Timestamp(Dates.ISO8601Z.parse("2024-01-19T11:36:19.345+0800").getTime()));
        a.setDeadline(new Timestamp(Dates.ISO8601Z.parse("2023-12-30T09:59:22.505+0800").getTime()));
        a.setPlannedQuantity(new BigDecimal("26140200.60"));
        a.setActualQuantity(new BigDecimal("6356564309547732"));
        a.setValidQuantity(new BigDecimal("8085073"));
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
