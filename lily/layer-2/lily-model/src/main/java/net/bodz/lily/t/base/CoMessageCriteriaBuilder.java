package net.bodz.lily.t.base;

import java.time.ZonedDateTime;

import net.bodz.lily.model.base.CoMomentIntervalCriteriaBuilder;

public abstract class CoMessageCriteriaBuilder<self_t extends CoMessageCriteriaBuilder<self_t>>
        extends CoMomentIntervalCriteriaBuilder<self_t> {

    public final IntegerField formId = integer("form");
    public final IntegerField opId = integer("op");
    public final IntegerField categoryId = integer("cat");
    public final IntegerField phaseId = integer("phase");

    // public final IntegerField tagId = integer("tag");

    public final DateField<ZonedDateTime> mailDate = date("t0");

    public final IntegerField voteCount = integer("nvote");
    public final IntegerField likerCount = integer("nlike");
    public final IntegerField readCount = integer("nread");

    // public final DateField<LocalDate> date = date("t0", LocalDate.class);
    // public final DateField<LocalTime> time = date("t0", LocalTime.class);

    public final LongField id = _long("id");

}
