package net.bodz.violet.plan.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.plan.DiaryParty;
import net.bodz.violet.plan.DiaryPartySamples;

public class DiaryPartyManagerTest
        extends AbstractManagerTest<DiaryParty, DiaryPartyMapper, DiaryPartyManager> {

    @Override
    public DiaryParty buildSample()
            throws Exception {
        DiaryPartySamples a = new DiaryPartySamples();
        return a.buildWired(tables);
    }

}
