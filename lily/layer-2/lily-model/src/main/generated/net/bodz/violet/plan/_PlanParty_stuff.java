package net.bodz.violet.plan;

import javax.persistence.Column;
import javax.persistence.Id;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.validate.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.contact.Organization;
import net.bodz.lily.contact.Person;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

@IdType(Long.class)
public abstract class _PlanParty_stuff
        extends CoEntity<Long> {

    private static final long serialVersionUID = 1L;

    public static final int N_PERSON_ID = 10;
    public static final int N_ORG_ID = 10;

    private static final int _ord_ID = 1;
    private static final int _ord_PLAN_ID = _ord_ID + 1;
    private static final int _ord_PERSON_ID = _ord_PLAN_ID + 1;
    private static final int _ord_ORG_ID = _ord_PERSON_ID + 1;

    @Id
    @NotNull
    long id;

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

    /**
     *
     * @label person
     * @constraint foreign key (person) references lily.person (id)
     */
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

    /**
     *
     * @label org
     * @constraint foreign key (org) references lily.org (id)
     */
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
