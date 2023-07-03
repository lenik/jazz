package net.bodz.violet.shop;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Id;

import org.joda.time.DateTime;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.contact.Organization;
import net.bodz.lily.contact.Person;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;
import net.bodz.lily.schema.FormDef;
import net.bodz.lily.security.User;
import net.bodz.violet.plan.Plan;

@IdType(Long.class)
public abstract class _SalesOrder_stuff
        extends CoEntity<Long> {

    private static final long serialVersionUID = 1L;

    public static final int N_SUBJECT = 200;
    public static final int N_OP_ID = 10;
    public static final int N_RAW_TEXT = 2147483647;
    public static final int N_FORM_ID = 10;
    public static final int N_FORM_ARGUMENTS = 2147483647;
    public static final int N_CATEGORY_ID = 10;
    public static final int N_PHASE_ID = 10;
    public static final int N_PREVIOUS_ORDER_ID = 19;
    public static final int N_PLAN_ID = 19;
    public static final int N_CUSTOMER_ORG_ID = 10;
    public static final int N_CUSTOMER_ID = 10;
    public static final int N_TOTAL_QUANTITY = 20;
    public static final int N_TOTAL_AMOUNT = 20;

    private static final int _ord_ID = 1;
    private static final int _ord_BEGIN_TIME = 12;
    private static final int _ord_END_TIME = _ord_BEGIN_TIME + 1;
    private static final int _ord_YEAR = _ord_END_TIME + 1;
    private static final int _ord_SUBJECT = _ord_YEAR + 1;
    private static final int _ord_OP_ID = _ord_SUBJECT + 1;
    private static final int _ord_RAW_TEXT = _ord_OP_ID + 1;
    private static final int _ord_FORM_ID = _ord_RAW_TEXT + 1;
    private static final int _ord_FORM_ARGUMENTS = _ord_FORM_ID + 1;
    private static final int _ord_CATEGORY_ID = _ord_FORM_ARGUMENTS + 2;
    private static final int _ord_PHASE_ID = _ord_CATEGORY_ID + 1;
    private static final int _ord_PREVIOUS_ORDER_ID = _ord_PHASE_ID + 1;
    private static final int _ord_PLAN_ID = _ord_PREVIOUS_ORDER_ID + 1;
    private static final int _ord_CUSTOMER_ORG_ID = _ord_PLAN_ID + 1;
    private static final int _ord_CUSTOMER_ID = _ord_CUSTOMER_ORG_ID + 1;
    private static final int _ord_LENGTH = _ord_CUSTOMER_ID + 1;
    private static final int _ord_TOTAL_QUANTITY = _ord_LENGTH + 1;
    private static final int _ord_TOTAL_AMOUNT = _ord_TOTAL_QUANTITY + 1;

    @Id
    @NotNull
    long id;

    DateTime beginTime;

    DateTime endTime;

    @NotNull
    int year;

    @NotNull
    String subject;

    String rawText;

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
    FormDef form;

    Integer formId;

    /**  */
    SalesPhase phase;

    Integer phaseId;

    /**  */
    Organization customerOrg;

    Integer customerOrgId;

    /**  */
    SalesOrder previousOrder;

    Long previousOrderId;

    /** (User Account) */
    User op;

    Integer opId;

    /**  */
    Plan plan;

    Long planId;

    /**  */
    SalesCategory category;

    Integer categoryId;

    @Override
    public Long id() {
        return getId();
    }

    @Override
    public void id(Long id) {
        setId(id);
    }

    @Id
    @Ordinal(_ord_ID)
    @Precision(value = 19)
    @Column(name = "id", nullable = false, precision = 19)
    public long getId() {
        return id;
    }

    public void setId(long value) {
        this.id = value;
    }

    @Ordinal(_ord_BEGIN_TIME)
    @Precision(value = 35, scale = 6)
    @Column(name = "t0", precision = 35, scale = 6)
    public DateTime getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(DateTime value) {
        this.beginTime = value;
    }

    @Ordinal(_ord_END_TIME)
    @Precision(value = 35, scale = 6)
    @Column(name = "t1", precision = 35, scale = 6)
    public DateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(DateTime value) {
        this.endTime = value;
    }

    @Ordinal(_ord_YEAR)
    @Precision(value = 10)
    @Column(name = "year", nullable = false, precision = 10)
    public int getYear() {
        return year;
    }

    public void setYear(int value) {
        this.year = value;
    }

    @Ordinal(_ord_SUBJECT)
    @NotNull
    @Precision(value = N_SUBJECT)
    @TextInput(maxLength = N_SUBJECT)
    @Column(name = "subject", nullable = false, length = N_SUBJECT)
    public String getSubject() {
        return subject;
    }

    public void setSubject(@NotNull String value) {
        this.subject = value;
    }

    @Ordinal(_ord_RAW_TEXT)
    @Precision(value = N_RAW_TEXT)
    @TextInput(maxLength = N_RAW_TEXT)
    @Column(name = "text", length = N_RAW_TEXT)
    public String getRawText() {
        return rawText;
    }

    public void setRawText(String value) {
        this.rawText = value;
    }

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
     * @label customer
     * @constraint foreign key (customer) references lily.person (id)
     */
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
     * @label form
     * @constraint foreign key (form) references lily._form (id)
     */
    public FormDef getForm() {
        return form;
    }

    /**
     */
    public void setForm(FormDef value) {
        this.form = value;
    }

    @Ordinal(_ord_FORM_ID)
    @Precision(value = N_FORM_ID)
    @Column(name = "form", precision = 10)
    public synchronized Integer getFormId() {
        if (form != null) {
            return form.getId();
        }
        return formId;
    }

    public synchronized void setFormId(Integer value) {
        this.formId = value;
    }

    /**
     *
     * @label phase
     * @constraint foreign key (phase) references violet.salephase (id)
     */
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
     * @label customer_org
     * @constraint foreign key (customer_org) references lily.org (id)
     */
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
     * @label prev
     * @constraint foreign key (prev) references violet.saleodr (id)
     */
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
     * {inheritDoc User}
     * User Account
     *
     * @label op
     * @constraint foreign key (op) references lily.user (id)
     */
    public User getOp() {
        return op;
    }

    /**
     * User Account
     */
    public void setOp(User value) {
        this.op = value;
    }

    @Ordinal(_ord_OP_ID)
    @Precision(value = N_OP_ID)
    @Column(name = "op", precision = 10)
    public synchronized Integer getOpId() {
        if (op != null) {
            return op.getId();
        }
        return opId;
    }

    public synchronized void setOpId(Integer value) {
        this.opId = value;
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
     * @label cat
     * @constraint foreign key (cat) references violet.salecat (id)
     */
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
        this.subject = "";
        this.totalQuantity = BigDecimal.ZERO;
        this.totalAmount = BigDecimal.ZERO;
    }

}
