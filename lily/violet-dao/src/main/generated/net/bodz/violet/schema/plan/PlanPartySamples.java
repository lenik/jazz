package net.bodz.violet.schema.plan;

import net.bodz.lily.schema.contact.Organization;
import net.bodz.lily.schema.contact.Person;
import net.bodz.lily.schema.contact.dao.OrganizationMapper;
import net.bodz.lily.schema.contact.dao.PersonMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.schema.plan.dao.PlanMapper;

public class PlanPartySamples
        extends TestSampleBuilder {

    public Person person;
    public Plan plan;
    public Organization org;

    @Override
    public PlanParty build()
            throws Exception {
        PlanParty a = new PlanParty();
        a.setPerson(person);
        a.setPlan(plan);
        a.setOrg(org);
        return a;
    }

    @Override
    public PlanPartySamples wireAny(IRandomPicker picker) {
        this.person = picker.pickAny(PersonMapper.class, "person");
        this.plan = picker.pickAny(PlanMapper.class, "plan");
        this.org = picker.pickAny(OrganizationMapper.class, "org");
        return this;
    }

    @Override
    public PlanParty buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
