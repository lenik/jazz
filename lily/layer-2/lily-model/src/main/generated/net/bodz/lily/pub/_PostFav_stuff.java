package net.bodz.lily.pub;

import javax.persistence.Column;
import javax.persistence.Id;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.validate.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;
import net.bodz.lily.security.User;

@IdType(Long.class)
public abstract class _PostFav_stuff
        extends CoEntity<Long> {

    private static final long serialVersionUID = 1L;

    private static final int _ord_ID = 1;
    private static final int _ord_POST_ID = _ord_ID + 1;
    private static final int _ord_USER_ID = _ord_POST_ID + 1;

    @Id
    @NotNull
    long id;

    /**  */
    @NotNull
    Post post;

    @NotNull
    long postId;

    /** (User Account) */
    @NotNull
    User user;

    @NotNull
    int userId;

    @Override
    public Long id() {
        return getId();
    }

    @Override
    public void id(Long id) {
        setId(id);
    }

    @Id
    @Ordinal(_ord_ID)
    @Precision(value = 19)
    @Column(name = "id", nullable = false, precision = 19)
    public long getId() {
        return id;
    }

    public void setId(long value) {
        this.id = value;
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

    /**
     * {inheritDoc User}
     * User Account
     *
     * @label user
     * @constraint foreign key (user) references lily.user (id)
     */
    @NotNull
    public User getUser() {
        return user;
    }

    /**
     * User Account
     */
    public void setUser(@NotNull User value) {
        this.user = value;
    }

    @Ordinal(_ord_USER_ID)
    @Precision(value = 10)
    @Column(name = "user", nullable = false, precision = 10)
    public synchronized int getUserId() {
        if (user != null) {
            if (user.getId() == null)
                return 0;
            return user.getId();
        }
        return userId;
    }

    public synchronized void setUserId(int value) {
        this.userId = value;
    }

    public void initNotNulls() {
    }

}
