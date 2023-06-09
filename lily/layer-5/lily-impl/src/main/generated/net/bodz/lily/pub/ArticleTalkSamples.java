package net.bodz.lily.pub;

import net.bodz.lily.schema.FormDef;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class ArticleTalkSamples
        extends TestSampleBuilder {

    public FormDef form;
    public Article article;
    public ArticleTalk parent;
    public User op;

    public ArticleTalk build()
            throws Exception {
        ArticleTalk a = new ArticleTalk();
        a.setForm(form);
        a.setArticle(article);
        a.setParent(parent);
        a.setOp(op);
        a.setFormArguments("Kej; geybe, S jnh, ae, euall, dioy; d*hku u, cwaej'uagx guku, i");
        return a;
    }

}
