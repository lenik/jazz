package net.bodz.lily.pub;

import net.bodz.lily.test.TestSamples;

public class ArticleSamples
        extends TestSamples {

    public static Article build(ArticleCategory category) {
        Article a = new Article();
        a.setSubject("article-1");
        a.setRawText("A article named article-1.");
        a.setCategory(category);
        return a;
    }

}
