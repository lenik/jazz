package net.bodz.violet.pub;

import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSamples;

public class PostTalkSamples
        extends TestSamples {

    public static PostTalk build(User op, Post post, PostTalk parent) {
        PostTalk a = new PostTalk();
        a.setSubject("postTalk-1");
        a.setText("A postTalk named postTalk-1.");
        a.setOp(op);
        a.setPost(post);
        if (random.nextInt(100) < 30)
            a.setParent(parent);
        return a;
    }

}
