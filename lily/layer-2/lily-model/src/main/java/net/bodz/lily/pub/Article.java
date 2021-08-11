package net.bodz.lily.pub;

import javax.persistence.Table;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.site.json.JsonMap;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.t.base.CoMessage;

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
    final JsonMap plugins = new JsonMap();

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

    @Override
    public void readObject(JsonObject o)
            throws ParseException {
        super.readObject(o);

        category = o.readInto("category", category, new ArticleCategory());
    }

}
