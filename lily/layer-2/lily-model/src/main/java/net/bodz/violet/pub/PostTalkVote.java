package net.bodz.violet.pub;

import javax.persistence.Table;

import net.bodz.lily.template.VoteRecord;

@Table(name = "post_msg_vote")
public class PostTalkVote
        extends VoteRecord {

    private static final long serialVersionUID = 1L;

    PostTalk talk;

    public PostTalkVote() {
    }

    public PostTalk getTalk() {
        return talk;
    }

    public void setTalk(PostTalk talk) {
        this.talk = talk;
    }

}
