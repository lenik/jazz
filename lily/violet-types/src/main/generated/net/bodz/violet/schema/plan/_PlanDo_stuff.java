package net.bodz.violet.schema.plan;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.NotNull;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.CoMessage;
import net.bodz.lily.entity.IdType;

@IdType(Long.class)
public abstract class _PlanDo_stuff
        extends CoMessage<Long> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "plando";

    public static final String FIELD_FORM_ARGUMENTS = "formargs";
    public static final String FIELD_VOTE_COUNT = "nvote";
    public static final String FIELD_PLAN_ID = "plan";
    public static final String FIELD_PARENT_ID = "parent";
    public static final String FIELD_CHANGES = "changes";

    public static final int N_FORM_ARGUMENTS = 2147483647;
    public static final int N_VOTE_COUNT = 10;
    public static final int N_PLAN_ID = 19;
    public static final int N_PARENT_ID = 19;
    public static final int N_CHANGES = 2147483647;

    private static final int _ord_FORM_ARGUMENTS = 16;
    private static final int _ord_VOTE_COUNT = _ord_FORM_ARGUMENTS + 4;
    private static final int _ord_PLAN_ID = _ord_VOTE_COUNT + 1;
    private static final int _ord_PARENT_ID = _ord_PLAN_ID + 1;
    private static final int _ord_CHANGES = _ord_PARENT_ID + 1;

    String formArguments;

    @NotNull
    int voteCount;

    String[] changes;

    /**  */
    @NotNull
    Plan plan;

    @NotNull
    long planId;

    /**  */
    PlanDo parent;

    Long parentId;

    @Ordinal(_ord_FORM_ARGUMENTS)
    @Precision(value = N_FORM_ARGUMENTS)
    @TextInput(maxLength = N_FORM_ARGUMENTS)
    @Column(name = "formargs", length = N_FORM_ARGUMENTS)
    public String getFormArguments() {
        return formArguments;
    }

    public void setFormArguments(String value) {
        this.formArguments = value;
    }

    @Ordinal(_ord_VOTE_COUNT)
    @Precision(value = 10)
    @Column(name = "nvote", nullable = false, precision = 10)
    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int value) {
        this.voteCount = value;
    }

    @Ordinal(_ord_CHANGES)
    @Precision(value = 2147483647)
    @Column(name = "changes", precision = 2147483647)
    public String[] getChanges() {
        return changes;
    }

    public void setChanges(String[] value) {
        this.changes = value;
    }

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

    /**
     *
     * @constraint foreign key (parent) references violet.plando (id)
     */
    @JoinColumn(name = "parent")
    @ManyToOne
    public PlanDo getParent() {
        return parent;
    }

    /**
     */
    public void setParent(PlanDo value) {
        this.parent = value;
    }

    @Ordinal(_ord_PARENT_ID)
    @Precision(value = N_PARENT_ID)
    @Column(name = "parent", precision = 19)
    public synchronized Long getParentId() {
        if (parent != null) {
            return parent.getId();
        }
        return parentId;
    }

    public synchronized void setParentId(Long value) {
        this.parentId = value;
    }

    public void initNotNulls() {
    }

}
