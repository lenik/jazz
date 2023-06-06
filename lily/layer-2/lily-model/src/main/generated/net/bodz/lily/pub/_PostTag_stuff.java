package net.bodz.lily.pub;

import javax.persistence.Column;
import javax.persistence.Id;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.validate.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

@IdType(Integer.class)
public abstract class _PostTag_stuff
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    private static final int _ord_ID = 1;
    private static final int _ord_POST_ID = _ord_ID + 4;
    private static final int _ord_TAG_ID = _ord_POST_ID + 1;

    @Id
    @NotNull
    int id;

    /**  */
    @NotNull
    PostTagType tag;

    @NotNull
    int tagId;

    /**  */
    @NotNull
    Post post;

    @NotNull
    long postId;

    @Override
    public Integer id() {
        return getId();
    }

    @Override
    public void id(Integer id) {
        setId(id);
    }

    @Id
    @Ordinal(_ord_ID)
    @Precision(value = 10)
    @Column(name = "id", nullable = false, precision = 10)
    public int getId() {
        return id;
    }

    public void setId(int value) {
        this.id = value;
    }

    /**
     *
     * @label tag
     * @constraint foreign key (tag) references lily.posttag (id)
     */
    @NotNull
    public PostTagType getTag() {
        return tag;
    }

    /**
     */
    public void setTag(@NotNull PostTagType value) {
        this.tag = value;
    }

    @Ordinal(_ord_TAG_ID)
    @Precision(value = 10)
    @Column(name = "tag", nullable = false, precision = 10)
    public synchronized int getTagId() {
        if (tag != null) {
            return tag.getId();
        }
        return tagId;
    }

    public synchronized void setTagId(int value) {
        this.tagId = value;
    }

    /**
     *
     * @label post
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
