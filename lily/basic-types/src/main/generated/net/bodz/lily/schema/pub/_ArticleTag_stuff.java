package net.bodz.lily.schema.pub;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.IdEntity;
import net.bodz.lily.entity.IdType;

@IdType(Integer.class)
public abstract class _ArticleTag_stuff
        extends IdEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "lily";
    public static final String TABLE_NAME = "article_tag";

    public static final String FIELD_ARTICLE_ID = "article";
    public static final String FIELD_TAG_ID = "tag";

    public static final int N_ARTICLE_ID = 19;
    public static final int N_TAG_ID = 10;

    private static final int _ord_ARTICLE_ID = 5;
    private static final int _ord_TAG_ID = _ord_ARTICLE_ID + 1;

    /**  */
    @NotNull
    ArticleTagType tag;

    @NotNull
    int tagId;

    /**  */
    @NotNull
    Article article;

    @NotNull
    long articleId;

    /**
     *
     * @constraint foreign key (tag) references lily.articletag (id)
     */
    @JoinColumn(name = "tag")
    @ManyToOne
    @NotNull
    public ArticleTagType getTag() {
        return tag;
    }

    /**
     */
    public void setTag(@NotNull ArticleTagType value) {
        this.tag = value;
    }

    @Ordinal(_ord_TAG_ID)
    @Precision(value = 10)
    @Column(name = "tag", nullable = false, precision = 10)
    public synchronized int getTagId() {
        if (tag != null) {
            if (tag.getId() == null)
                return 0;
            return tag.getId();
        }
        return tagId;
    }

    public synchronized void setTagId(int value) {
        this.tagId = value;
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

    public void initNotNulls() {
    }

}
