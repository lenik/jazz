package net.bodz.lily.pub;

import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSamples;

public class ArticleTalkSamples
        extends TestSamples {

    public static ArticleTalk build(Article article, User op) {
        ArticleTalk a = new ArticleTalk();
        a.setSubject("articleTalk-1");
        a.setText("A articleTalk named articleTalk-1.");
        a.setArticle(article);
        a.setOp(op);
        return a;
    }

}
