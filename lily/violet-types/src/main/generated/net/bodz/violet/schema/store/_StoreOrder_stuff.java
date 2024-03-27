package net.bodz.violet.schema.store;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.NotNull;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.CoEntity;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.contact.OrgUnit;
import net.bodz.lily.schema.contact.Organization;
import net.bodz.lily.schema.contact.Person;
import net.bodz.lily.schema.meta.FormDef;
import net.bodz.violet.schema.plan.Plan;

@IdType(Long.class)
public abstract class _StoreOrder_stuff
        extends CoEntity<Long> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "storeodr";

    public static final String FIELD_ID = "id";
    public static final String FIELD_BEGIN_TIME = "t0";
    public static final String FIELD_END_TIME = "t1";
    public static final String FIELD_YEAR = "year";
    public static final String FIELD_SUBJECT = "subject";
    public static final String FIELD_OP_ID = "op";
    public static final String FIELD_RAW_TEXT = "text";
    public static final String FIELD_FORM_ID = "form";
    public static final String FIELD_FORM_ARGUMENTS = "formargs";
    public static final String FIELD_CATEGORY_ID = "cat";
    public static final String FIELD_PHASE_ID = "phase";
    public static final String FIELD_PREV_ID = "prev";
    public static final String FIELD_PLAN_ID = "plan";
    public static final String FIELD_ORG_ID = "org";
    public static final String FIELD_ORG_UNIT_ID = "ou";
    public static final String FIELD_PERSON_ID = "person";
    public static final String FIELD_LENGTH = "length";
    public static final String FIELD_TOTAL_QUANTITY = "sum_qty";
    public static final String FIELD_TOTAL_AMOUNT = "sum_amount";

    public static final int N_ID = 19;
    public static final int N_BEGIN_TIME = 35;
    public static final int N_END_TIME = 35;
    public static final int N_YEAR = 10;
    public static final int N_SUBJECT = 200;
    public static final int N_OP_ID = 10;
    public static final int N_RAW_TEXT = 2147483647;
    public static final int N_FORM_ID = 10;
    public static final int N_FORM_ARGUMENTS = 2147483647;
    public static final int N_CATEGORY_ID = 10;
    public static final int N_PHASE_ID = 10;
    public static final int N_PREV_ID = 19;
    public static final int N_PLAN_ID = 19;
    public static final int N_ORG_ID = 10;
    public static final int N_ORG_UNIT_ID = 10;
    public static final int N_PERSON_ID = 10;
    public static final int N_LENGTH = 10;
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
    private static final int _ord_CATEGORY_ID = _ord_FORM_ARGUMENTS + 1;
    private static final int _ord_PHASE_ID = _ord_CATEGORY_ID + 1;
    private static final int _ord_PREV_ID = _ord_PHASE_ID + 1;
    private static final int _ord_PLAN_ID = _ord_PREV_ID + 1;
    private static final int _ord_ORG_ID = _ord_PLAN_ID + 1;
    private static final int _ord_ORG_UNIT_ID = _ord_ORG_ID + 1;
    private static final int _ord_PERSON_ID = _ord_ORG_UNIT_ID + 1;
    private static final int _ord_LENGTH = _ord_PERSON_ID + 1;
    private static final int _ord_TOTAL_QUANTITY = _ord_LENGTH + 1;
    private static final int _ord_TOTAL_AMOUNT = _ord_TOTAL_QUANTITY + 1;

    @Id
    @NotNull
    long id;

    OffsetDateTime beginTime;

    OffsetDateTime endTime;

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
    Person person;

    Integer personId;

    /**  */
    Plan plan;

    Long planId;

    /**  */
    @NotNull
    StorePhase phase;

    @NotNull
    int phaseId;

    /**  */
    @NotNull
    StoreCategory category;

    @NotNull
    int categoryId;

    /**  */
    StoreOrder prev;

    Long prevId;

    /** (User Account) */
    User op;

    Integer opId;

    /**  */
    FormDef form;

    Integer formId;

    /**  */
    Organization org;

    Integer orgId;

    /**  */
    OrgUnit orgUnit;

    Integer orgUnitId;

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
    public OffsetDateTime getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(OffsetDateTime value) {
        this.beginTime = value;
    }

    @Ordinal(_ord_END_TIME)
    @Precision(value = 35, scale = 6)
    @Column(name = "t1", precision = 35, scale = 6)
    public OffsetDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(OffsetDateTime value) {
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
     * @constraint foreign key (person) references lily.person (id)
     */
    @JoinColumn(name = "person")
    @ManyToOne
    public Person getPerson() {
        return person;
    }

    /**
     */
    public void setPerson(Person value) {
        this.person = value;
    }

    @Ordinal(_ord_PERSON_ID)
    @Precision(value = N_PERSON_ID)
    @Column(name = "person", precision = 10)
    public synchronized Integer getPersonId() {
        if (person != null) {
            return person.getId();
        }
        return personId;
    }

    public synchronized void setPersonId(Integer value) {
        this.personId = value;
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
     * @constraint foreign key (phase) references violet.storephase (id)
     */
    @JoinColumn(name = "phase")
    @ManyToOne
    @NotNull
    public StorePhase getPhase() {
        return phase;
    }

    /**
     */
    public void setPhase(@NotNull StorePhase value) {
        this.phase = value;
    }

    @Ordinal(_ord_PHASE_ID)
    @Precision(value = 10)
    @Column(name = "phase", nullable = false, precision = 10)
    public synchronized int getPhaseId() {
        if (phase != null) {
            if (phase.getId() == null)
                return 0;
            return phase.getId();
        }
        return phaseId;
    }

    public synchronized void setPhaseId(int value) {
        this.phaseId = value;
    }

    /**
     *
     * @constraint foreign key (cat) references violet.storecat (id)
     */
    @JoinColumn(name = "cat")
    @ManyToOne
    @NotNull
    public StoreCategory getCategory() {
        return category;
    }

    /**
     */
    public void setCategory(@NotNull StoreCategory value) {
        this.category = value;
    }

    @Ordinal(_ord_CATEGORY_ID)
    @Precision(value = 10)
    @Column(name = "cat", nullable = false, precision = 10)
    public synchronized int getCategoryId() {
        if (category != null) {
            if (category.getId() == null)
                return 0;
            return category.getId();
        }
        return categoryId;
    }

    public synchronized void setCategoryId(int value) {
        this.categoryId = value;
    }

    /**
     *
     * @constraint foreign key (prev) references violet.storeodr (id)
     */
    @JoinColumn(name = "prev")
    @ManyToOne
    public StoreOrder getPrev() {
        return prev;
    }

    /**
     */
    public void setPrev(StoreOrder value) {
        this.prev = value;
    }

    @Ordinal(_ord_PREV_ID)
    @Precision(value = N_PREV_ID)
    @Column(name = "prev", precision = 19)
    public synchronized Long getPrevId() {
        if (prev != null) {
            return prev.getId();
        }
        return prevId;
    }

    public synchronized void setPrevId(Long value) {
        this.prevId = value;
    }

    /**
     * {inheritDoc User}
     * User Account
     *
     * @constraint foreign key (op) references lily.user (id)
     */
    @JoinColumn(name = "op")
    @ManyToOne
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
     * @constraint foreign key (form) references lily._form (id)
     */
    @JoinColumn(name = "form")
    @ManyToOne
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
     * @constraint foreign key (org) references lily.org (id)
     */
    @JoinColumn(name = "org")
    @ManyToOne
    public Organization getOrg() {
        return org;
    }

    /**
     */
    public void setOrg(Organization value) {
        this.org = value;
    }

    @Ordinal(_ord_ORG_ID)
    @Precision(value = N_ORG_ID)
    @Column(name = "org", precision = 10)
    public synchronized Integer getOrgId() {
        if (org != null) {
            return org.getId();
        }
        return orgId;
    }

    public synchronized void setOrgId(Integer value) {
        this.orgId = value;
    }

    /**
     *
     * @constraint foreign key (ou) references lily.orgunit (id)
     */
    @JoinColumn(name = "ou")
    @ManyToOne
    public OrgUnit getOrgUnit() {
        return orgUnit;
    }

    /**
     */
    public void setOrgUnit(OrgUnit value) {
        this.orgUnit = value;
    }

    @Ordinal(_ord_ORG_UNIT_ID)
    @Precision(value = N_ORG_UNIT_ID)
    @Column(name = "ou", precision = 10)
    public synchronized Integer getOrgUnitId() {
        if (orgUnit != null) {
            return orgUnit.getId();
        }
        return orgUnitId;
    }

    public synchronized void setOrgUnitId(Integer value) {
        this.orgUnitId = value;
    }

    public void initNotNulls() {
        this.subject = "";
        this.totalQuantity = BigDecimal.ZERO;
        this.totalAmount = BigDecimal.ZERO;
    }

}
