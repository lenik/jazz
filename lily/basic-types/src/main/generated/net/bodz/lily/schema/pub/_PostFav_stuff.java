package net.bodz.lily.schema.pub;

import javax.persistence.Column;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.FavRecord;
import net.bodz.lily.entity.IdType;

@IdType(Long.class)
public abstract class _PostFav_stuff
        extends FavRecord {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "lily";
    public static final String TABLE_NAME = "post_fav";

    public static final String FIELD_POST_ID = "post";

    public static final int N_POST_ID = 19;

    private static final int _ord_POST_ID = 2;

    /**  */
    @NotNull
    Post post;

    @NotNull
    long postId;

    /**
     *
     * @constraint foreign key (post) references lily.post (id)
     */
    @NotNull
    public Post getPost() {
        return post;
    }

    /**
     */
    public void setPost(@NotNull Post value) {
        this.post = value;
    }

    @Ordinal(_ord_POST_ID)
    @Precision(value = 19)
    @Column(name = "post", nullable = false, precision = 19)
    public synchronized long getPostId() {
        if (post != null) {
            if (post.getId() == null)
                return 0L;
            return post.getId();
        }
        return postId;
    }

    public synchronized void setPostId(long value) {
        this.postId = value;
    }

    public void initNotNulls() {
    }

}
