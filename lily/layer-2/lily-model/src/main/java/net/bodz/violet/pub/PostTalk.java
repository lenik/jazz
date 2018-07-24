package net.bodz.violet.pub;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.mx.CoMessage;

/**
 * 帖子讨论
 */
@Table(name = "post_msg")
@IdType(Long.class)
public class PostTalk
        extends CoMessage<Long> {

    private static final long serialVersionUID = 1L;

    Post post;
    PostTalk parent;

    public PostTalk() {
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public PostTalk getParent() {
        return parent;
    }

    public void setParent(PostTalk parent) {
        this.parent = parent;
    }

}
