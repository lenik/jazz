package net.bodz.lily.model.mx.base;

import java.util.Date;
import java.util.List;
import java.util.Map;

import net.bodz.bas.c.string.Strings;
import net.bodz.bas.t.order.IPriority;

import net.bodz.lily.model.base.CoEntity;
import net.bodz.lily.model.base.security.User;

public class CoMessage
        extends CoEntity
        implements IPriority {

    private static final long serialVersionUID = 1L;

    public static final int N_SUBJECT = 200;

    long id;

    /** original poster */
    private User op;
    private String subject;
    private String text;
    private Map<String, String> attributes;
    private List<Object> attachments;

    private int voteUps;
    private int voteDowns;
    private int favorites;

    private Date sentTime;
    private Date receivedTime;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    /**
     * @label Original Poster
     * @label.zh.cn 发起人
     */
    public User getOp() {
        return op;
    }

    public void setOp(User op) {
        this.op = op;
    }

    /**
     * @label Subject
     * @label.zh.cn 主题
     * @placeholder 输入主题
     */
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        if (subject == null)
            throw new NullPointerException("subject");
        this.subject = subject;
    }

    /**
     * @label Text
     * @label.zh.cn 正文
     */
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    /**
     * @label Text Preview
     * @label.zh.cn 正文预览
     */
    public String getTextPreview() {
        return Strings.ellipsis(getText(), 50);
    }

    /**
     * @label Sent Time
     * @label.zh.cn 发送时间
     */
    public Date getSentTime() {
        return sentTime;
    }

    public void setSentTime(Date sentTime) {
        this.sentTime = sentTime;
    }

    /**
     * @label Received Time
     * @label.zh.cn 接收时间
     */
    public Date getReceivedTime() {
        return receivedTime;
    }

    public void setReceivedTime(Date receivedTime) {
        this.receivedTime = receivedTime;
    }

    /**
     * @label Vote Ups
     * @label.zh.cn 赞
     */
    public int getVoteUps() {
        return voteUps;
    }

    public void setVoteUps(int voteUps) {
        this.voteUps = voteUps;
    }

    /**
     * @label Vote Downs
     * @label.zh.cn 踩
     */
    public int getVoteDowns() {
        return voteDowns;
    }

    public void setVoteDowns(int voteDowns) {
        this.voteDowns = voteDowns;
    }

    /**
     * @label Favorite Count
     * @label.zh.cn 收藏
     */
    public int getFavorites() {
        return favorites;
    }

    public void setFavorites(int favorites) {
        this.favorites = favorites;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public List<Object> getAttachments() {
        return attachments;
    }

}
