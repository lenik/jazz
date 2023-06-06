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
public abstract class _PostTalkVote_stuff
        extends CoEntity<Long> {

    private static final long serialVersionUID = 1L;

    private static final int _ord_ID = 1;
    private static final int _ord_POST_MSG_ID = _ord_ID + 1;
    private static final int _ord_USER_ID = _ord_POST_MSG_ID + 1;
    private static final int _ord_VOTE_SCORE = _ord_USER_ID + 1;

    @Id
    @NotNull
    long id;

    @NotNull
    int voteScore;

    /**  */
    @NotNull
    PostTalk postMsg;

    @NotNull
    long postMsgId;

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

    @Ordinal(_ord_VOTE_SCORE)
    @Precision(value = 10)
    @Column(name = "votes", nullable = false, precision = 10)
    public int getVoteScore() {
        return voteScore;
    }

    public void setVoteScore(int value) {
        this.voteScore = value;
    }

    /**
     *
     * @label post_msg
     * @constraint foreign key (post_msg) references lily.post_msg (id)
     */
    @NotNull
    public PostTalk getPostMsg() {
        return postMsg;
    }

    /**
     */
    public void setPostMsg(@NotNull PostTalk value) {
        this.postMsg = value;
    }

    @Ordinal(_ord_POST_MSG_ID)
    @Precision(value = 19)
    @Column(name = "post_msg", nullable = false, precision = 19)
    public synchronized long getPostMsgId() {
        if (postMsg != null) {
            return postMsg.getId();
        }
        return postMsgId;
    }

    public synchronized void setPostMsgId(long value) {
        this.postMsgId = value;
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
