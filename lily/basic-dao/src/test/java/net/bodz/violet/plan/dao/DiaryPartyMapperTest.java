package net.bodz.violet.plan.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.plan.DiaryParty;
import net.bodz.violet.plan.DiaryPartySamples;

public class DiaryPartyMapperTest
        extends AbstractTableTest<DiaryParty, DiaryPartyMapper> {

    @Override
    public DiaryParty buildSample()
            throws Exception {
        DiaryPartySamples a = new DiaryPartySamples();
        return a.buildWired(tables);
    }

}
