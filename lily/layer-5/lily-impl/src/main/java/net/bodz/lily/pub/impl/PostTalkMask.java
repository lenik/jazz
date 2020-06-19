package net.bodz.lily.pub.impl;

import net.bodz.lily.t.base.CoMessageMask;

/**
 * @see net.bodz.lily.pub.PostTalk
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
