package net.bodz.violet.pub.impl;

import net.bodz.violet.pub.Post;

public class PostSamples {

    public static Post build() {
        Post a = new Post();
        a.setLabel("post-1");
        a.setDescription("A device named post-1.");
        return a;
    }

}
