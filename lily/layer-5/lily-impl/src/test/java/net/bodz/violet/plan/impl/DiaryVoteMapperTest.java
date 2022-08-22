package net.bodz.violet.plan.impl;

import net.bodz.lily.security.User;
import net.bodz.lily.security.impl.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.plan.Diary;
import net.bodz.violet.plan.DiaryVote;
import net.bodz.violet.plan.DiaryVoteSamples;

public class DiaryVoteMapperTest
        extends AbstractTableTest<DiaryVote, DiaryVoteMask, DiaryVoteMapper> {

    @Override
    public DiaryVote buildSample() {
        Diary diary = tables.pickAny(DiaryMapper.class, "diary");
        User user = tables.pickAny(UserMapper.class, "user");
        return DiaryVoteSamples.build(diary, user);
    }

}
