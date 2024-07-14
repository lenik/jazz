package net.bodz.violet.schema.shop;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.CoMessage;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.schema.contact.Organization;
import net.bodz.lily.schema.contact.Person;
import net.bodz.violet.schema.plan.Plan;

@IdType(Long.class)
public abstract class _SalesOrder_stuff
        extends CoMessage<Long> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "saleodr";

    public static final String FIELD_FORM_ARGUMENTS = "formargs";
    public static final String FIELD_CATEGORY_ID = "cat";
    public static final String FIELD_PHASE_ID = "phase";
    public static final String FIELD_PREVIOUS_ORDER_ID = "prev";
    public static final String FIELD_PLAN_ID = "plan";
    public static final String FIELD_CUSTOMER_ORG_ID = "customer_org";
    public static final String FIELD_CUSTOMER_ID = "customer";
    public static final String FIELD_LENGTH = "length";
    public static final String FIELD_TOTAL_QUANTITY = "sum_qty";
    public static final String FIELD_TOTAL_AMOUNT = "sum_amount";

    public static final int N_FORM_ARGUMENTS = 2147483647;
    public static final int N_CATEGORY_ID = 10;
    public static final int N_PHASE_ID = 10;
    public static final int N_PREVIOUS_ORDER_ID = 19;
    public static final int N_PLAN_ID = 19;
    public static final int N_CUSTOMER_ORG_ID = 10;
    public static final int N_CUSTOMER_ID = 10;
    public static final int N_LENGTH = 10;
    public static final int N_TOTAL_QUANTITY = 20;
    public static final int N_TOTAL_AMOUNT = 20;

    private static final int _ord_FORM_ARGUMENTS = 19;
    private static final int _ord_CATEGORY_ID = _ord_FORM_ARGUMENTS + 2;
    private static final int _ord_PHASE_ID = _ord_CATEGORY_ID + 1;
    private static final int _ord_PREVIOUS_ORDER_ID = _ord_PHASE_ID + 1;
    private static final int _ord_PLAN_ID = _ord_PREVIOUS_ORDER_ID + 1;
    private static final int _ord_CUSTOMER_ORG_ID = _ord_PLAN_ID + 1;
    private static final int _ord_CUSTOMER_ID = _ord_CUSTOMER_ORG_ID + 1;
    private static final int _ord_LENGTH = _ord_CUSTOMER_ID + 1;
    private static final int _ord_TOTAL_QUANTITY = _ord_LENGTH + 1;
    private static final int _ord_TOTAL_AMOUNT = _ord_TOTAL_QUANTITY + 1;

    String formArguments;

    @NotNull
    int length;

    @NotNull
    BigDecimal totalQuantity;

    @NotNull
    BigDecimal totalAmount;

    /**  */
    Person customer;

    Integer customerId;

    /**  */
    SalesPhase phase;

    Integer phaseId;

    /**  */
    Organization customerOrg;

    Integer customerOrgId;

    /**  */
    SalesOrder previousOrder;

    Long previousOrderId;

    /**  */
    Plan plan;

    Long planId;

    /**  */
    SalesCategory category;

    Integer categoryId;

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

    @Ordinal(_ord_LENGTH)
    @Precision(value = 10)
    @Column(name = "length", nullable = false, precision = 10)
    public int getLength() {
        return length;
    }

    public void setLength(int value) {
        this.length = value;
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

    /**
     *
     * @constraint foreign key (customer) references lily.person (id)
     */
    @JoinColumn(name = "customer")
    @ManyToOne
    public Person getCustomer() {
        return customer;
    }

    /**
     */
    public void setCustomer(Person value) {
        this.customer = value;
    }

    @Ordinal(_ord_CUSTOMER_ID)
    @Precision(value = N_CUSTOMER_ID)
    @Column(name = "customer", precision = 10)
    public synchronized Integer getCustomerId() {
        if (customer != null) {
            return customer.getId();
        }
        return customerId;
    }

    public synchronized void setCustomerId(Integer value) {
        this.customerId = value;
    }

    /**
     *
     * @constraint foreign key (phase) references violet.salephase (id)
     */
    @JoinColumn(name = "phase")
    @ManyToOne
    public SalesPhase getPhase() {
        return phase;
    }

    /**
     */
    public void setPhase(SalesPhase value) {
        this.phase = value;
    }

    @Ordinal(_ord_PHASE_ID)
    @Precision(value = N_PHASE_ID)
    @Column(name = "phase", precision = 10)
    public synchronized Integer getPhaseId() {
        if (phase != null) {
            return phase.getId();
        }
        return phaseId;
    }

    public synchronized void setPhaseId(Integer value) {
        this.phaseId = value;
    }

    /**
     *
     * @constraint foreign key (customer_org) references lily.org (id)
     */
    @JoinColumn(name = "customer_org")
    @ManyToOne
    public Organization getCustomerOrg() {
        return customerOrg;
    }

    /**
     */
    public void setCustomerOrg(Organization value) {
        this.customerOrg = value;
    }

    @Ordinal(_ord_CUSTOMER_ORG_ID)
    @Precision(value = N_CUSTOMER_ORG_ID)
    @Column(name = "customer_org", precision = 10)
    public synchronized Integer getCustomerOrgId() {
        if (customerOrg != null) {
            return customerOrg.getId();
        }
        return customerOrgId;
    }

    public synchronized void setCustomerOrgId(Integer value) {
        this.customerOrgId = value;
    }

    /**
     *
     * @constraint foreign key (prev) references violet.saleodr (id)
     */
    @JoinColumn(name = "prev")
    @ManyToOne
    public SalesOrder getPreviousOrder() {
        return previousOrder;
    }

    /**
     */
    public void setPreviousOrder(SalesOrder value) {
        this.previousOrder = value;
    }

    @Ordinal(_ord_PREVIOUS_ORDER_ID)
    @Precision(value = N_PREVIOUS_ORDER_ID)
    @Column(name = "prev", precision = 19)
    public synchronized Long getPreviousOrderId() {
        if (previousOrder != null) {
            return previousOrder.getId();
        }
        return previousOrderId;
    }

    public synchronized void setPreviousOrderId(Long value) {
        this.previousOrderId = value;
    }

    /**
     *
     * @constraint foreign key (plan) references violet.plan (id)
     */
    @JoinColumn(name = "plan")
    @ManyToOne
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
     * @constraint foreign key (cat) references violet.salecat (id)
     */
    @JoinColumn(name = "cat")
    @ManyToOne
    public SalesCategory getCategory() {
        return category;
    }

    /**
     */
    public void setCategory(SalesCategory value) {
        this.category = value;
    }

    @Ordinal(_ord_CATEGORY_ID)
    @Precision(value = N_CATEGORY_ID)
    @Column(name = "cat", precision = 10)
    public synchronized Integer getCategoryId() {
        if (category != null) {
            return category.getId();
        }
        return categoryId;
    }

    public synchronized void setCategoryId(Integer value) {
        this.categoryId = value;
    }

    public void initNotNulls() {
        this.totalQuantity = BigDecimal.ZERO;
        this.totalAmount = BigDecimal.ZERO;
    }

}
