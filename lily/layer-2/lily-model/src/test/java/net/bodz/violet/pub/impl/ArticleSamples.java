package net.bodz.violet.pub.impl;

import net.bodz.violet.pub.Article;

public class ArticleSamples {

    public static Article build() {
        Article a = new Article();
        a.setLabel("article-1");
        a.setDescription("A device named article-1.");
        return a;
    }

}
