package net.bodz.lily.schema.pub;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.NotNull;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.IdEntity;
import net.bodz.lily.entity.IdType;

@IdType(Integer.class)
public abstract class _ArticleParameter_stuff
        extends IdEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "lily";
    public static final String TABLE_NAME = "article_parm";

    public static final String FIELD_ARTICLE_ID = "article";
    public static final String FIELD_PARAMETER_ID = "parm";
    public static final String FIELD_IVAL = "ival";
    public static final String FIELD_FVAL = "fval";
    public static final String FIELD_SVAL = "sval";

    public static final int N_ARTICLE_ID = 19;
    public static final int N_PARAMETER_ID = 10;
    public static final int N_IVAL = 10;
    public static final int N_FVAL = 17;
    public static final int N_SVAL = 250;

    private static final int _ord_ARTICLE_ID = 5;
    private static final int _ord_PARAMETER_ID = _ord_ARTICLE_ID + 1;
    private static final int _ord_IVAL = _ord_PARAMETER_ID + 1;
    private static final int _ord_FVAL = _ord_IVAL + 1;
    private static final int _ord_SVAL = _ord_FVAL + 1;

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
    ArticleParameterType parameter;

    @NotNull
    int parameterId;

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
     * @constraint foreign key (article) references lily.article (id)
     */
    @JoinColumn(name = "article")
    @ManyToOne
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
     * @constraint foreign key (parm) references lily.articleparm (id)
     */
    @JoinColumn(name = "parm")
    @ManyToOne
    @NotNull
    public ArticleParameterType getParameter() {
        return parameter;
    }

    /**
     */
    public void setParameter(@NotNull ArticleParameterType value) {
        this.parameter = value;
    }

    @Ordinal(_ord_PARAMETER_ID)
    @Precision(value = 10)
    @Column(name = "parm", nullable = false, precision = 10)
    public synchronized int getParameterId() {
        if (parameter != null) {
            if (parameter.getId() == null)
                return 0;
            return parameter.getId();
        }
        return parameterId;
    }

    public synchronized void setParameterId(int value) {
        this.parameterId = value;
    }

    public void initNotNulls() {
    }

}
