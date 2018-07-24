package net.bodz.violet.pub.impl;

import net.bodz.lily.model.mx.CoMessageMask;

/**
 * @see net.bodz.violet.pub.PostTalk
 */
public class PostTalkMask
        extends CoMessageMask {

    Long postId;
    Long parentId;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

}
