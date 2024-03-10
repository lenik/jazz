package net.bodz.violet.schema.plan;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.FavRecord;
import net.bodz.lily.entity.IdType;

@IdType(Long.class)
public abstract class _PlanFav_stuff
        extends FavRecord {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "plan_fav";

    public static final String FIELD_PLAN_ID = "plan";

    public static final int N_PLAN_ID = 19;

    private static final int _ord_PLAN_ID = 2;

    /**  */
    @NotNull
    Plan plan;

    @NotNull
    long planId;

    /**
     *
     * @constraint foreign key (plan) references violet.plan (id)
     */
    @JoinColumn(name = "plan")
    @ManyToOne
    @NotNull
    public Plan getPlan() {
        return plan;
    }

    /**
     */
    public void setPlan(@NotNull Plan value) {
        this.plan = value;
    }

    @Ordinal(_ord_PLAN_ID)
    @Precision(value = 19)
    @Column(name = "plan", nullable = false, precision = 19)
    public synchronized long getPlanId() {
        if (plan != null) {
            if (plan.getId() == null)
                return 0L;
            return plan.getId();
        }
        return planId;
    }

    public synchronized void setPlanId(long value) {
        this.planId = value;
    }

    public void initNotNulls() {
    }

}
