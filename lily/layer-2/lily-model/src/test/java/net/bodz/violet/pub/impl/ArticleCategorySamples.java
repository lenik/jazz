package net.bodz.violet.pub.impl;

import net.bodz.violet.pub.ArticleCategory;

public class ArticleCategorySamples {

    public static ArticleCategory build() {
        ArticleCategory a = new ArticleCategory();
        a.setLabel("articleCategory-1");
        a.setDescription("A device named articleCategory-1.");
        return a;
    }

}
