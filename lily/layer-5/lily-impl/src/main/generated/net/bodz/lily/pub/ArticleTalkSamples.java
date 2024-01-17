package net.bodz.lily.pub;

import net.bodz.lily.pub.dao.ArticleMapper;
import net.bodz.lily.pub.dao.ArticleTalkMapper;
import net.bodz.lily.schema.FormDef;
import net.bodz.lily.schema.dao.FormDefMapper;
import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class ArticleTalkSamples
        extends TestSampleBuilder {

    public FormDef form;
    public Article article;
    public ArticleTalk parent;
    public User op;

    @Override
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

    @Override
    public ArticleTalkSamples wireAny(IRandomPicker picker) {
        this.form = picker.pickAny(FormDefMapper.class, "_form");
        this.article = picker.pickAny(ArticleMapper.class, "article");
        this.parent = picker.pickAny(ArticleTalkMapper.class, "article_msg");
        this.op = picker.pickAny(UserMapper.class, "user");
        return this;
    }

    @Override
    public ArticleTalk buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
