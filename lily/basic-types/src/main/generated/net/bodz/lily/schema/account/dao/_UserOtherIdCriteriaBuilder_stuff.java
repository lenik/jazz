package net.bodz.lily.schema.account.dao;

import java.sql.Timestamp;

import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.lily.concrete.CoObjectCriteriaBuilder;

public class _UserOtherIdCriteriaBuilder_stuff<self_t extends _UserOtherIdCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    public final DateField<Timestamp> beginTime = date("t0", Timestamp.class);

    public final DateField<Timestamp> endTime = date("t1", Timestamp.class);

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
