package net.bodz.violet.plan.dao;

import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.plan.DiaryVote;
import net.bodz.violet.plan.DiaryVoteSamples;

public class DiaryVoteMapperTest
        extends AbstractTableTest<DiaryVote, DiaryVoteCriteriaBuilder, DiaryVoteMapper> {

    @Override
    public DiaryVote buildSample()
            throws Exception {
        DiaryVoteSamples a = new DiaryVoteSamples();
        a.parent = tables.pickAny(DiaryMapper.class, "diary");
        a.user = tables.pickAny(UserMapper.class, "user");
        return a.build();
    }

}
