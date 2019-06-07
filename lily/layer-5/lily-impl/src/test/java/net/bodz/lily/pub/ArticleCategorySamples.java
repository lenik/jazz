package net.bodz.lily.pub;

import net.bodz.lily.test.TestSamples;

public class ArticleCategorySamples
        extends TestSamples {

    public static ArticleCategory build() {
        ArticleCategory a = new ArticleCategory();
        a.setLabel("articleCategory-1");
        a.setDescription("A articleCategory named articleCategory-1.");
        return a;
    }

}
