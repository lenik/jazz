package net.bodz.violet.plan;

import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSamples;

public class DiaryVoteSamples
        extends TestSamples {

    public static DiaryVote build(Diary diary, User user) {
        DiaryVote a = new DiaryVote();
        a.setLabel("diaryVote-1");
        a.setDescription("A diaryVote named diaryVote-1.");
        a.setDiary(diary);
        a.setUser(user);
        a.setVotes(random.nextInt(3) - 1);
        return a;
    }

}
