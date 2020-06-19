package net.bodz.lily.pub;

import javax.persistence.Table;

import net.bodz.bas.site.json.JsonMap;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.t.base.CoMessage;

/**
 * 帖子
 */
@Table(name = "post")
@IdType(Long.class)
public class Post
        extends CoMessage<Long> {

    private static final long serialVersionUID = 1L;

    PostCategory category;
    Post parent;
    // int ntalk;
    JsonMap plugins;

    public Post() {
    }

    public PostCategory getCategory() {
        return category;
    }

    public void setCategory(PostCategory category) {
        this.category = category;
    }

    public Post getParent() {
        return parent;
    }

    public void setParent(Post parent) {
        this.parent = parent;
    }

    public JsonMap getPlugins() {
        return plugins;
    }

    public void setPlugins(JsonMap plugins) {
        this.plugins = plugins;
    }

}
