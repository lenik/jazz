package net.bodz.violet.plan;

import javax.persistence.Table;

import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.lily.contact.Organization;
import net.bodz.lily.contact.Person;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

@Table(name = "plan_party")
@IdType(Long.class)
public class PlanParty
        extends CoEntity<Long> {

    private static final long serialVersionUID = 1L;

    public static final int N_DESCRIPTION = 60;

    Plan plan;
    Person person;
    Organization org;

    public PlanParty() {
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Organization getOrg() {
        return org;
    }

    public void setOrg(Organization org) {
        this.org = org;
    }

    @TextInput(maxLength = N_DESCRIPTION)
    @Override
    public String getDescription() {
        return super.getDescription();
    }

}
