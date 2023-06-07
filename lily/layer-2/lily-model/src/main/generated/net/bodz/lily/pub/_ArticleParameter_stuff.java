package net.bodz.lily.pub;

import javax.persistence.Column;
import javax.persistence.Id;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

@IdType(Integer.class)
public abstract class _ArticleParameter_stuff
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public static final int N_IVAL = 10;
    public static final int N_FVAL = 17;
    public static final int N_SVAL = 250;

    private static final int _ord_ID = 1;
    private static final int _ord_ARTICLE_ID = _ord_ID + 4;
    private static final int _ord_PARM_ID = _ord_ARTICLE_ID + 1;
    private static final int _ord_IVAL = _ord_PARM_ID + 1;
    private static final int _ord_FVAL = _ord_IVAL + 1;
    private static final int _ord_SVAL = _ord_FVAL + 1;

    @Id
    @NotNull
    int id;

    Integer ival;

    Double fval;

    String sval;

    /**  */
    @NotNull
    Article article;

    @NotNull
    long articleId;

    /**  */
    @NotNull
    ArticleParameterType parm;

    @NotNull
    int parmId;

    @Override
    public Integer id() {
        return getId();
    }

    @Override
    public void id(Integer id) {
        setId(id);
    }

    @Id
    @Ordinal(_ord_ID)
    @Precision(value = 10)
    @Column(name = "id", nullable = false, precision = 10)
    public int getId() {
        return id;
    }

    public void setId(int value) {
        this.id = value;
    }

    @Ordinal(_ord_IVAL)
    @Precision(value = N_IVAL)
    @Column(name = "ival", precision = 10)
    public Integer getIval() {
        return ival;
    }

    public void setIval(Integer value) {
        this.ival = value;
    }

    @Ordinal(_ord_FVAL)
    @Precision(value = N_FVAL, scale = 17)
    @Column(name = "fval", precision = 17, scale = 17)
    public Double getFval() {
        return fval;
    }

    public void setFval(Double value) {
        this.fval = value;
    }

    @Ordinal(_ord_SVAL)
    @Precision(value = N_SVAL)
    @TextInput(maxLength = N_SVAL)
    @Column(name = "sval", length = N_SVAL)
    public String getSval() {
        return sval;
    }

    public void setSval(String value) {
        this.sval = value;
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

    /**
     *
     * @label parm
     * @constraint foreign key (parm) references lily.articleparm (id)
     */
    @NotNull
    public ArticleParameterType getParm() {
        return parm;
    }

    /**
     */
    public void setParm(@NotNull ArticleParameterType value) {
        this.parm = value;
    }

    @Ordinal(_ord_PARM_ID)
    @Precision(value = 10)
    @Column(name = "parm", nullable = false, precision = 10)
    public synchronized int getParmId() {
        if (parm != null) {
            if (parm.getId() == null)
                return 0;
            return parm.getId();
        }
        return parmId;
    }

    public synchronized void setParmId(int value) {
        this.parmId = value;
    }

    public void initNotNulls() {
    }

}