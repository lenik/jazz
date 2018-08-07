package net.bodz.lily.pub;

import javax.persistence.Table;

import net.bodz.lily.template.VoteRecord;

@Table(name = "article_msg_vote")
public class ArticleTalkVote
        extends VoteRecord {

    private static final long serialVersionUID = 1L;

    ArticleTalk talk;

    public ArticleTalkVote() {
    }

    public ArticleTalk getTalk() {
        return talk;
    }

    public void setTalk(ArticleTalk talk) {
        this.talk = talk;
    }

}
