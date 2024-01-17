package net.bodz.violet.plan.dao;

import net.bodz.lily.contact.dao.OrganizationMapper;
import net.bodz.lily.contact.dao.PersonMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.plan.PlanParty;
import net.bodz.violet.plan.PlanPartySamples;

public class PlanPartyMapperTest
        extends AbstractTableTest<PlanParty, PlanPartyCriteriaBuilder, PlanPartyMapper> {

    @Override
    public PlanParty buildSample()
            throws Exception {
        PlanPartySamples a = new PlanPartySamples();
        a.person = tables.pickAny(PersonMapper.class, "person");
        a.plan = tables.pickAny(PlanMapper.class, "plan");
        a.org = tables.pickAny(OrganizationMapper.class, "org");
        return a.build();
    }

}
