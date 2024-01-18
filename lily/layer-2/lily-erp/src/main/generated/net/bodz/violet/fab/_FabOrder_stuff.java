package net.bodz.violet.fab;

import java.math.BigDecimal;

import javax.persistence.Column;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.NotNull;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.contact.Organization;
import net.bodz.lily.contact.Person;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.t.base.CoMessage;
import net.bodz.violet.plan.Plan;

@IdType(Long.class)
public abstract class _FabOrder_stuff
        extends CoMessage<Long> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "fabodr";

    public static final String FIELD_FORM_ARGUMENTS = "formargs";
    public static final String FIELD_PLAN_ID = "plan";
    public static final String FIELD_CUSTOM_ORG_ID = "custom_org";
    public static final String FIELD_CUSTOM_ID = "custom";
    public static final String FIELD_CLERK_ID = "clerk";
    public static final String FIELD_NITEM = "nitem";
    public static final String FIELD_TOTAL_QUANTITY = "sum_qty";
    public static final String FIELD_TOTAL_AMOUNT = "sum_amount";
    public static final String FIELD_TASK_COUNT = "ntask";
    public static final String FIELD_PROCESS_COUNT = "nproc";
    public static final String FIELD_TRACK_COUNT = "ntrack";

    public static final int N_FORM_ARGUMENTS = 2147483647;
    public static final int N_PLAN_ID = 19;
    public static final int N_CUSTOM_ORG_ID = 10;
    public static final int N_CUSTOM_ID = 10;
    public static final int N_CLERK_ID = 10;
    public static final int N_NITEM = 10;
    public static final int N_TOTAL_QUANTITY = 20;
    public static final int N_TOTAL_AMOUNT = 20;
    public static final int N_TASK_COUNT = 10;
    public static final int N_PROCESS_COUNT = 10;
    public static final int N_TRACK_COUNT = 10;

    private static final int _ord_FORM_ARGUMENTS = 19;
    private static final int _ord_PLAN_ID = _ord_FORM_ARGUMENTS + 1;
    private static final int _ord_CUSTOM_ORG_ID = _ord_PLAN_ID + 1;
    private static final int _ord_CUSTOM_ID = _ord_CUSTOM_ORG_ID + 1;
    private static final int _ord_CLERK_ID = _ord_CUSTOM_ID + 1;
    private static final int _ord_NITEM = _ord_CLERK_ID + 1;
    private static final int _ord_TOTAL_QUANTITY = _ord_NITEM + 1;
    private static final int _ord_TOTAL_AMOUNT = _ord_TOTAL_QUANTITY + 1;
    private static final int _ord_TASK_COUNT = _ord_TOTAL_AMOUNT + 1;
    private static final int _ord_PROCESS_COUNT = _ord_TASK_COUNT + 1;
    private static final int _ord_TRACK_COUNT = _ord_PROCESS_COUNT + 1;

    String formArguments;

    @NotNull
    int nitem;

    @NotNull
    BigDecimal totalQuantity;

    @NotNull
    BigDecimal totalAmount;

    Integer taskCount;

    Integer processCount;

    Integer trackCount;

    /**  */
    Person clerk;

    Integer clerkId;

    /**  */
    Plan plan;

    Long planId;

    /**  */
    Organization customOrg;

    Integer customOrgId;

    /**  */
    Person custom;

    Integer customId;

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

    @Ordinal(_ord_NITEM)
    @Precision(value = 10)
    @Column(name = "nitem", nullable = false, precision = 10)
    public int getNitem() {
        return nitem;
    }

    public void setNitem(int value) {
        this.nitem = value;
    }

    @Ordinal(_ord_TOTAL_QUANTITY)
    @NotNull
    @Precision(value = N_TOTAL_QUANTITY, scale = 2)
    @Column(name = "sum_qty", nullable = false, precision = 20, scale = 2)
    public BigDecimal getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(@NotNull BigDecimal value) {
        this.totalQuantity = value;
    }

    @Ordinal(_ord_TOTAL_AMOUNT)
    @NotNull
    @Precision(value = N_TOTAL_AMOUNT, scale = 2)
    @Column(name = "sum_amount", nullable = false, precision = 20, scale = 2)
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(@NotNull BigDecimal value) {
        this.totalAmount = value;
    }

    @Ordinal(_ord_TASK_COUNT)
    @Precision(value = N_TASK_COUNT)
    @Column(name = "ntask", precision = 10)
    public Integer getTaskCount() {
        return taskCount;
    }

    public void setTaskCount(Integer value) {
        this.taskCount = value;
    }

    @Ordinal(_ord_PROCESS_COUNT)
    @Precision(value = N_PROCESS_COUNT)
    @Column(name = "nproc", precision = 10)
    public Integer getProcessCount() {
        return processCount;
    }

    public void setProcessCount(Integer value) {
        this.processCount = value;
    }

    @Ordinal(_ord_TRACK_COUNT)
    @Precision(value = N_TRACK_COUNT)
    @Column(name = "ntrack", precision = 10)
    public Integer getTrackCount() {
        return trackCount;
    }

    public void setTrackCount(Integer value) {
        this.trackCount = value;
    }

    /**
     *
     * @label clerk
     * @constraint foreign key (clerk) references lily.person (id)
     */
    public Person getClerk() {
        return clerk;
    }

    /**
     */
    public void setClerk(Person value) {
        this.clerk = value;
    }

    @Ordinal(_ord_CLERK_ID)
    @Precision(value = N_CLERK_ID)
    @Column(name = "clerk", precision = 10)
    public synchronized Integer getClerkId() {
        if (clerk != null) {
            return clerk.getId();
        }
        return clerkId;
    }

    public synchronized void setClerkId(Integer value) {
        this.clerkId = value;
    }

    /**
     *
     * @label plan
     * @constraint foreign key (plan) references violet.plan (id)
     */
    public Plan getPlan() {
        return plan;
    }

    /**
     */
    public void setPlan(Plan value) {
        this.plan = value;
    }

    @Ordinal(_ord_PLAN_ID)
    @Precision(value = N_PLAN_ID)
    @Column(name = "plan", precision = 19)
    public synchronized Long getPlanId() {
        if (plan != null) {
            return plan.getId();
        }
        return planId;
    }

    public synchronized void setPlanId(Long value) {
        this.planId = value;
    }

    /**
     *
     * @label custom_org
     * @constraint foreign key (custom_org) references lily.org (id)
     */
    public Organization getCustomOrg() {
        return customOrg;
    }

    /**
     */
    public void setCustomOrg(Organization value) {
        this.customOrg = value;
    }

    @Ordinal(_ord_CUSTOM_ORG_ID)
    @Precision(value = N_CUSTOM_ORG_ID)
    @Column(name = "custom_org", precision = 10)
    public synchronized Integer getCustomOrgId() {
        if (customOrg != null) {
            return customOrg.getId();
        }
        return customOrgId;
    }

    public synchronized void setCustomOrgId(Integer value) {
        this.customOrgId = value;
    }

    /**
     *
     * @label custom
     * @constraint foreign key (custom) references lily.person (id)
     */
    public Person getCustom() {
        return custom;
    }

    /**
     */
    public void setCustom(Person value) {
        this.custom = value;
    }

    @Ordinal(_ord_CUSTOM_ID)
    @Precision(value = N_CUSTOM_ID)
    @Column(name = "custom", precision = 10)
    public synchronized Integer getCustomId() {
        if (custom != null) {
            return custom.getId();
        }
        return customId;
    }

    public synchronized void setCustomId(Integer value) {
        this.customId = value;
    }

    public void initNotNulls() {
        this.totalQuantity = BigDecimal.ZERO;
        this.totalAmount = BigDecimal.ZERO;
    }

}
