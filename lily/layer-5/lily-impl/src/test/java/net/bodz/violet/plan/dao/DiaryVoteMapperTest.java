package net.bodz.violet.plan.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.plan.DiaryVote;
import net.bodz.violet.plan.DiaryVoteSamples;

public class DiaryVoteMapperTest
        extends AbstractTableTest<DiaryVote, DiaryVoteMapper> {

    @Override
    public DiaryVote buildSample()
            throws Exception {
        DiaryVoteSamples a = new DiaryVoteSamples();
        return a.buildWired(tables);
    }

}
