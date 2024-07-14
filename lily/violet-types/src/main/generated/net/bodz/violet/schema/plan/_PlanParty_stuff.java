package net.bodz.violet.schema.plan;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.IdEntity;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.schema.contact.Organization;
import net.bodz.lily.schema.contact.Person;

@IdType(Long.class)
public abstract class _PlanParty_stuff
        extends IdEntity<Long> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "plan_party";

    public static final String FIELD_PLAN_ID = "plan";
    public static final String FIELD_PERSON_ID = "person";
    public static final String FIELD_ORG_ID = "org";

    public static final int N_PLAN_ID = 19;
    public static final int N_PERSON_ID = 10;
    public static final int N_ORG_ID = 10;

    private static final int _ord_PLAN_ID = 2;
    private static final int _ord_PERSON_ID = _ord_PLAN_ID + 1;
    private static final int _ord_ORG_ID = _ord_PERSON_ID + 1;

    /**  */
    Person person;

    Integer personId;

    /**  */
    @NotNull
    Plan plan;

    @NotNull
    long planId;

    /**  */
    Organization org;

    Integer orgId;

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

    public void initNotNulls() {
    }

}
