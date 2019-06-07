package net.bodz.lily.pub;

import net.bodz.lily.test.TestSamples;

public class PostCategorySamples
        extends TestSamples {

    public static PostCategory build() {
        PostCategory a = new PostCategory();
        a.setLabel("postCategory-1");
        a.setDescription("A postCategory named postCategory-1.");
        return a;
    }

}
