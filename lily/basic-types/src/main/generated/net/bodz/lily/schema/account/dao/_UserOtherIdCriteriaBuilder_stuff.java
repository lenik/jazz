package net.bodz.lily.schema.account.dao;

import java.time.OffsetDateTime;

import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.lily.concrete.CoImagedCriteriaBuilder;

public class _UserOtherIdCriteriaBuilder_stuff<self_t extends _UserOtherIdCriteriaBuilder_stuff<self_t>>
        extends CoImagedCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    public final DateField<OffsetDateTime> beginTime = date("t0", OffsetDateTime.class);

    public final DateField<OffsetDateTime> endTime = date("t1", OffsetDateTime.class);

    public final IntegerField year = integer("\"year\"");

    /** The declaring user */
    public final IntegerField userId = integer("\"user\"");

    /** Type of Open ID */
    public final IntegerField typeId = integer("\"type\"");

    /** The identity data */
    public final StringField otherId = string("oid");

    /** The authentication data */
    public final DiscreteField<JsonVariant> auth = discrete("auth", JsonVariant.class);

}
