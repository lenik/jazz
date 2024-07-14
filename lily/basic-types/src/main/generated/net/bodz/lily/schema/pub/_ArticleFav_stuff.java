package net.bodz.lily.schema.pub;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.FavRecord;
import net.bodz.lily.entity.IdType;

@IdType(Long.class)
public abstract class _ArticleFav_stuff
        extends FavRecord {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "lily";
    public static final String TABLE_NAME = "article_fav";

    public static final String FIELD_ARTICLE_ID = "article";

    public static final int N_ARTICLE_ID = 19;

    private static final int _ord_ARTICLE_ID = 2;

    /**  */
    @NotNull
    Article article;

    @NotNull
    long articleId;

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

    public void initNotNulls() {
    }

}
