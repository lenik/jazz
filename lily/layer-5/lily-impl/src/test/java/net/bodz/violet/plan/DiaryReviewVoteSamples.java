package net.bodz.violet.plan;

import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSamples;

public class DiaryReviewVoteSamples
        extends TestSamples {

    public static DiaryReviewVote build(DiaryReview review, User user) {
        DiaryReviewVote a = new DiaryReviewVote();
        a.setReview(review);
        a.setUser(user);
        a.setVotes(random.nextInt(3) - 1);
        return a;
    }

}
