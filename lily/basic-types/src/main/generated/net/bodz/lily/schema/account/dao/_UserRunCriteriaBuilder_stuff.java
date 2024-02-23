package net.bodz.lily.schema.account.dao;

import java.net.InetAddress;
import java.sql.Timestamp;

import net.bodz.lily.concrete.CoObjectCriteriaBuilder;

public class _UserRunCriteriaBuilder_stuff<self_t extends _UserRunCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    /** The user */
    public final IntegerField userId = integer("\"user\"");

    public final IntegerField score = integer("score");

    /** Last time of login */
    public final DateField<Timestamp> lastLoginTime = date("lastlog", Timestamp.class);

    /** The source IP of last login */
    public final DiscreteField<InetAddress> lastLoginIP = discrete("lastlogip", InetAddress.class);

}
