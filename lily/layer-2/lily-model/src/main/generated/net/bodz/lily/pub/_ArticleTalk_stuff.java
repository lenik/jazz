package net.bodz.lily.pub;

import javax.persistence.Column;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.meta.TypeParamType;
import net.bodz.lily.meta.TypeParameters;
import net.bodz.lily.template.CoTalk;

@TypeParameters({ TypeParamType.THIS_TYPE })
@IdType(Long.class)
public abstract class _ArticleTalk_stuff<this_t extends _ArticleTalk_stuff<this_t>>
        extends CoTalk<this_t> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "lily";
    public static final String TABLE_NAME = "article_msg";

    public static final String FIELD_FORM_ARGUMENTS = "formargs";
    public static final String FIELD_ARTICLE_ID = "article";

    public static final int N_FORM_ARGUMENTS = 2147483647;
    public static final int N_ARTICLE_ID = 19;

    private static final int _ord_FORM_ARGUMENTS = 15;
    private static final int _ord_ARTICLE_ID = _ord_FORM_ARGUMENTS + 1;

    String formArguments;

    /**  */
    @NotNull
    Article article;

    @NotNull
    long articleId;

    @Ordinal(_ord_FORM_ARGUMENTS)
    @Precision(value = N_FORM_ARGUMENTS)
    @TextInput(maxLength = N_FORM_ARGUMENTS)
    @Column(name = "formargs", length = N_FORM_ARGUMENTS)
    public String getFormArguments() {
        return formArguments;
    }

    public void setFormArguments(String value) {
        this.formArguments = value;
    }

    /**
     *
     * @label article
     * @constraint foreign key (article) references lily.article (id)
     */
    @NotNull
    public Article getArticle() {
        return article;
    }

    /**
     */
    public void setArticle(@NotNull Article value) {
        this.article = value;
    }

    @Ordinal(_ord_ARTICLE_ID)
    @Precision(value = 19)
    @Column(name = "article", nullable = false, precision = 19)
    public synchronized long getArticleId() {
        if (article != null) {
            if (article.getId() == null)
                return 0L;
            return article.getId();
        }
        return articleId;
    }

    public synchronized void setArticleId(long value) {
        this.articleId = value;
    }

    public void initNotNulls() {
    }

}
