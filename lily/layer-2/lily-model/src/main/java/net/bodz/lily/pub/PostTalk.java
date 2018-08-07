package net.bodz.lily.pub;

import javax.persistence.Table;

import net.bodz.lily.template.CoTalk;

/**
 * 帖子讨论
 */
@Table(name = "post_msg")
public class PostTalk
        extends CoTalk<PostTalk> {

    private static final long serialVersionUID = 1L;

    Post post;

    public PostTalk() {
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

}
