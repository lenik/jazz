package net.bodz.lily.pub;

import javax.persistence.Table;

import net.bodz.lily.template.FavRecord;

@Table(name = "post_fav")
public class PostFav
        extends FavRecord {

    private static final long serialVersionUID = 1L;

    Post post;

    public PostFav() {
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

}
