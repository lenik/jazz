package net.bodz.violet.fab;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.joda.time.DateTime;

import net.bodz.bas.c.java.util.Dates;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.art.ArtifactModel;
import net.bodz.violet.art.dao.ArtifactModelMapper;
import net.bodz.violet.fab.dao.FabTaskMapper;

public class FabTaskItemSamples
        extends TestSampleBuilder {

    public FabTask task;
    public ArtifactModel model;

    @Override
    public FabTaskItem build()
            throws Exception {
        FabTaskItem a = new FabTaskItem();
        a.setTask(task);
        a.setModel(model);
        a.setId(9043012724999244598L);
        a.setBeginTime(new DateTime(Dates.ISO8601Z.parse("2024-01-19T09:42:13.389+0800").getTime()));
        a.setEndTime(new DateTime(Dates.ISO8601Z.parse("2023-12-17T11:35:03.274+0800").getTime()));
        a.setYear(340671280);
        a.setDeadline(new Timestamp(Dates.ISO8601Z.parse("2024-01-13T04:10:23.736+0800").getTime()));
        a.setStatus("uieom zuwlgu gmie&ruu&voair vii efv@qba xpik, ukreot; uy");
        a.setQuantity(new BigDecimal("3420"));
        a.setTrackCount(671027086);
        return a;
    }

    @Override
    public FabTaskItemSamples wireAny(IRandomPicker picker) {
        this.task = picker.pickAny(FabTaskMapper.class, "fabtask");
        this.model = picker.pickAny(ArtifactModelMapper.class, "artmodel");
        return this;
    }

    @Override
    public FabTaskItem buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
