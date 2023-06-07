package net.bodz.violet.plan;

import javax.persistence.Column;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.validate.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.template.FavRecord;

@IdType(Long.class)
public abstract class _PlanFav_stuff
        extends FavRecord {

    private static final long serialVersionUID = 1L;

    private static final int _ord_PLAN_ID = 2;

    /**  */
    @NotNull
    Plan plan;

    @NotNull
    long planId;

    /**
     *
     * @label plan
     * @constraint foreign key (plan) references violet.plan (id)
     */
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
