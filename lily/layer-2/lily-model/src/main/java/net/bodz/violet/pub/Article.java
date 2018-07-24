package net.bodz.violet.pub;

import javax.persistence.Table;

import net.bodz.bas.site.json.JsonMap;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.mx.CoMessage;

/**
 * 文章
 */
@Table(name = "article")
@IdType(Long.class)
public class Article
        extends CoMessage<Long> {

    private static final long serialVersionUID = 1L;

    ArticleCategory category;
    // int ntalk;
    JsonMap plugins;

    public Article() {
    }

    public ArticleCategory getCategory() {
        return category;
    }

    public void setCategory(ArticleCategory category) {
        this.category = category;
    }

    public JsonMap getPlugins() {
        return plugins;
    }

    public void setPlugins(JsonMap plugins) {
        this.plugins = plugins;
    }

}
