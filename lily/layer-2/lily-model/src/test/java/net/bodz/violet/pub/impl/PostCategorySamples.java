package net.bodz.violet.pub.impl;

import net.bodz.violet.pub.PostCategory;

public class PostCategorySamples {

    public static PostCategory build() {
        PostCategory a = new PostCategory();
        a.setLabel("postCategory-1");
        a.setDescription("A device named postCategory-1.");
        return a;
    }

}
