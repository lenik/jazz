package net.bodz.lily.model.mx.base;

import java.util.Date;
import java.util.List;
import java.util.Map;

import net.bodz.bas.t.order.IPriority;

import net.bodz.lily.model.base.CoEntity;
import net.bodz.lily.model.base.security.User;

public class Message
        extends CoEntity
        implements IPriority, IVotable {

    private static final long serialVersionUID = 1L;

    public static final int N_SUBJECT = 200;

    /** original poster */
    private User op;
    private String subject;
    private String text;
    private Map<String, String> attributes;
    private List<Object> attachments;

    private int votes;

    private Date sentTime;
    private Date receivedTime;

    public Date getSentTime() {
        return sentTime;
    }

    public void setSentTime(Date sentTime) {
        this.sentTime = sentTime;
    }

    public Date getReceivedTime() {
        return receivedTime;
    }

    public void setReceivedTime(Date receivedTime) {
        this.receivedTime = receivedTime;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        if (subject == null)
            throw new NullPointerException("subject");
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int getVotes() {
        return votes;
    }

    @Override
    public void setVotes(int votes) {
        this.votes = votes;
    }

}
