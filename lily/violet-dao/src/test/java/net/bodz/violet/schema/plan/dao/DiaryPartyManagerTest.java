package net.bodz.violet.schema.plan.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.plan.DiaryParty;
import net.bodz.violet.schema.plan.DiaryPartySamples;

public class DiaryPartyManagerTest
        extends AbstractManagerTest<DiaryParty, DiaryPartyMapper, DiaryPartyManager> {

    @Override
    public DiaryParty buildSample()
            throws Exception {
        DiaryPartySamples a = new DiaryPartySamples();
        return a.buildWired(tables);
    }

}
