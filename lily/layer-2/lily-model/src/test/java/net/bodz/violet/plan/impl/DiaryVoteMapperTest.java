package net.bodz.violet.plan.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.security.User;
import net.bodz.lily.security.impl.UserMapper;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.plan.Diary;
import net.bodz.violet.plan.DiaryVote;
import net.bodz.violet.plan.DiaryVoteSamples;

public class DiaryVoteMapperTest
        extends AbstractMapperTest<DiaryVote, DiaryVoteMask, DiaryVoteMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public DiaryVote buildSample() {
        Diary diary = tables.pickAny(DiaryMapper.class, "diary");
        User user = tables.pickAny(UserMapper.class, "user");
        return DiaryVoteSamples.build(diary, user);
    }

}
