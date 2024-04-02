package net.bodz.violet.schema.fab.dao;

import java.time.OffsetDateTime;

import net.bodz.lily.concrete.CoEventCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.violet.schema.fab.FabTrack;

@ForEntityType(FabTrack.class)
public class _FabTrackCriteriaBuilder_stuff<self_t extends _FabTrackCriteriaBuilder_stuff<self_t>>
        extends CoEventCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final LongField processId = _long("proc");

    public final DateField<OffsetDateTime> since = date("since", OffsetDateTime.class);

    public final DateField<OffsetDateTime> deadline = date("deadline", OffsetDateTime.class);

    public final BigDecimalField plannedQuantity = bigDecimal("qty_plan");

    public final BigDecimalField actualQuantity = bigDecimal("qty_actual");

    public final BigDecimalField validQuantity = bigDecimal("qty_valid");

    public final IntegerField orgUnitId = integer("ou");

}
