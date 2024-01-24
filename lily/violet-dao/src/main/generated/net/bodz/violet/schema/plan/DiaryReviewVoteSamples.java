package net.bodz.violet.schema.plan;

import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.schema.plan.dao.DiaryReviewMapper;

public class DiaryReviewVoteSamples
        extends TestSampleBuilder {

    public User user;
    public DiaryReview parent;

    @Override
    public DiaryReviewVote build()
            throws Exception {
        DiaryReviewVote a = new DiaryReviewVote();
        a.setUser(user);
        a.setParent(parent);
        a.setVoteScore(1394442012);
        return a;
    }

    @Override
    public DiaryReviewVoteSamples wireAny(IRandomPicker picker) {
        this.user = picker.pickAny(UserMapper.class, "user");
        this.parent = picker.pickAny(DiaryReviewMapper.class, "diaryrev");
        return this;
    }

    @Override
    public DiaryReviewVote buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
