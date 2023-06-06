package net.bodz.lily.pub;

import javax.persistence.Column;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.template.CoTalk;

@IdType(Long.class)
public abstract class _ArticleTalk_stuff
        extends CoTalk {

    private static final long serialVersionUID = 1L;

    public static final int N_FORM_ARGUMENTS = 2147483647;

    private static final int _ord_YEAR = 10;
    private static final int _ord_FORM_ARGUMENTS = _ord_YEAR + 5;
    private static final int _ord_ARTICLE_ID = _ord_FORM_ARGUMENTS + 1;

    @NotNull
    int year;

    String formArguments;

    /**  */
    @NotNull
    Article article;

    @NotNull
    long articleId;

    @Ordinal(_ord_YEAR)
    @Precision(value = 10)
    @Column(name = "year", nullable = false, precision = 10)
    public int getYear() {
        return year;
    }

    public void setYear(int value) {
        this.year = value;
    }

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
