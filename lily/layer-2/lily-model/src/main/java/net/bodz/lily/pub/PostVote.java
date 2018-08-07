package net.bodz.lily.pub;

import javax.persistence.Table;

import net.bodz.lily.template.VoteRecord;

@Table(name = "post_vote")
public class PostVote
        extends VoteRecord {

    private static final long serialVersionUID = 1L;

    Post post;

    public PostVote() {
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

}
