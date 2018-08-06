package net.bodz.violet.pub;

import javax.persistence.Table;

import net.bodz.lily.template.BackrefRecord;

@Table(name = "post_backref")
public class PostBackref
        extends BackrefRecord {

    private static final long serialVersionUID = 1L;

    Post post;

    public PostBackref() {
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

}
