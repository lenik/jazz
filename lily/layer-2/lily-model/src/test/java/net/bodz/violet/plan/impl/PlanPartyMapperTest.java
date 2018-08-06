package net.bodz.violet.plan.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.contact.Organization;
import net.bodz.lily.contact.Person;
import net.bodz.lily.contact.impl.OrganizationMapper;
import net.bodz.lily.contact.impl.PersonMapper;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.plan.Plan;
import net.bodz.violet.plan.PlanParty;
import net.bodz.violet.plan.PlanPartySamples;

public class PlanPartyMapperTest
        extends AbstractMapperTest<PlanParty, PlanPartyMask, PlanPartyMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public PlanParty buildSample() {
        Plan plan = tables.pickAny(PlanMapper.class, "plan");
        Person person = tables.pickAny(PersonMapper.class, "person");
        Organization org = tables.pickAny(OrganizationMapper.class, "org");
        return PlanPartySamples.build(plan, person, org);
    }

}
