package net.bodz.violet.plan;

import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class DiaryReviewVoteSamples
        extends TestSampleBuilder {

    public User user;
    public DiaryReview parent;

    public DiaryReviewVote build()
            throws Exception {
        DiaryReviewVote a = new DiaryReviewVote();
        a.setUser(user);
        a.setParent(parent);
        a.setVoteScore(1394442012);
        return a;
    }

}
