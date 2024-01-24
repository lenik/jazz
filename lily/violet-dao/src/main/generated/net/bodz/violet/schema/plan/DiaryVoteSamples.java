package net.bodz.violet.schema.plan;

import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.schema.plan.dao.DiaryMapper;

public class DiaryVoteSamples
        extends TestSampleBuilder {

    public Diary parent;
    public User user;

    @Override
    public DiaryVote build()
            throws Exception {
        DiaryVote a = new DiaryVote();
        a.setParent(parent);
        a.setUser(user);
        a.setVoteScore(1833968484);
        return a;
    }

    @Override
    public DiaryVoteSamples wireAny(IRandomPicker picker) {
        this.parent = picker.pickAny(DiaryMapper.class, "diary");
        this.user = picker.pickAny(UserMapper.class, "user");
        return this;
    }

    @Override
    public DiaryVote buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
