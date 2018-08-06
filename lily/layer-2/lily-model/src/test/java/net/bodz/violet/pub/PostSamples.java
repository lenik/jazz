package net.bodz.violet.pub;

import net.bodz.lily.test.TestSamples;

public class PostSamples
        extends TestSamples {

    public static Post build(PostCategory category, Post parent) {
        Post a = new Post();
        a.setSubject("post-1");
        a.setText("A post named post-1.");
        a.setCategory(category);
        if (random.nextInt(100) < 30)
            a.setParent(parent);
        return a;
    }

}
