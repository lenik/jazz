package net.bodz.lily.pub;

import javax.persistence.Column;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.inet.ExternalSite;
import net.bodz.lily.template.BackrefRecord;

@IdType(Long.class)
public abstract class _ArticleBackref_stuff
        extends BackrefRecord {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "lily";
    public static final String TABLE_NAME = "article_backref";

    public static final String FIELD_ARTICLE_ID = "article";
    public static final String FIELD_SITE_ID = "site";
    public static final String FIELD_KEY = "key";
    public static final String FIELD_VALUE = "value";

    public static final int N_ARTICLE_ID = 19;
    public static final int N_SITE_ID = 10;
    public static final int N_KEY = 30;
    public static final int N_VALUE = 10;

    private static final int _ord_ARTICLE_ID = 14;
    private static final int _ord_SITE_ID = _ord_ARTICLE_ID + 1;
    private static final int _ord_KEY = _ord_SITE_ID + 1;
    private static final int _ord_VALUE = _ord_KEY + 1;

    String key;

    @NotNull
    int value;

    /**  */
    @NotNull
    Article article;

    @NotNull
    long articleId;

    /**  */
    @NotNull
    ExternalSite site;

    @NotNull
    int siteId;

    @Ordinal(_ord_KEY)
    @Precision(value = N_KEY)
    @TextInput(maxLength = N_KEY)
    @Column(name = "key", length = N_KEY)
    public String getKey() {
        return key;
    }

    public void setKey(String value) {
        this.key = value;
    }

    @Ordinal(_ord_VALUE)
    @Precision(value = 10)
    @Column(name = "value", nullable = false, precision = 10)
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
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
     * @label site
     * @constraint foreign key (site) references lily.extsite (id)
     */
    @NotNull
    public ExternalSite getSite() {
        return site;
    }

    /**
     */
    public void setSite(@NotNull ExternalSite value) {
        this.site = value;
    }

    @Ordinal(_ord_SITE_ID)
    @Precision(value = 10)
    @Column(name = "site", nullable = false, precision = 10)
    public synchronized int getSiteId() {
        if (site != null) {
            return site.getId();
        }
        return siteId;
    }

    public synchronized void setSiteId(int value) {
        this.siteId = value;
    }

    public void initNotNulls() {
    }

}
