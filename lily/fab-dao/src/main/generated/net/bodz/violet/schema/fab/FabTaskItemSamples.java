package net.bodz.violet.schema.fab;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import net.bodz.bas.c.java.time.DateTimes;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.schema.art.ArtifactModel;
import net.bodz.violet.schema.art.dao.ArtifactModelMapper;
import net.bodz.violet.schema.fab.dao.FabTaskMapper;

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
        a.setDeadline(OffsetDateTime.parse("2024-12-07T08:35:29.527-06:33", DateTimes.ISO_OFFSET_DATE_TIME));
        a.setStatus("uieom zuwlgu gmie&ruu&voair vii efv@qba xpik, ukreot; uy");
        a.setQuantity(new BigDecimal("63007342019.83"));
        a.setTrackCount(2079769895);
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
