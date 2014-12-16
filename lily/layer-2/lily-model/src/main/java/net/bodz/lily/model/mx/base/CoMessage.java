package net.bodz.lily.model.mx.base;

import java.util.Date;
import java.util.List;
import java.util.Map;

import net.bodz.bas.c.string.Strings;
import net.bodz.bas.repr.form.meta.OfGroup;
import net.bodz.bas.t.order.IPriority;

import net.bodz.lily.model.base.CoEntity;
import net.bodz.lily.model.base.security.User;

/**
 * @label Message
 * @label.zh.cn 消息
 */
public class CoMessage
        extends CoEntity
        implements IPriority, IVotable, ILikable {

    private static final long serialVersionUID = 1L;

    public static final int N_SUBJECT = 200;

    long id;

    private User op;
    private String subject;
    private String text;
    private Map<String, String> attributes;
    private Date sentTime;
    private Date receivedTime;

    private Integer voteUps;
    private Integer voteDowns;
    private List<Voter> voters;
    private Voter iVoter;

    private Integer likerCount;
    private List<Liker> likers;
    private Liker iLiker;

    private Integer readCount;

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
    @OfGroup(CoMessage.class)
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
    @OfGroup(CoMessage.class)
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
    @OfGroup(CoMessage.class)
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
    @OfGroup(CoMessage.class)
    public String getTextPreview() {
        return Strings.ellipsis(getText(), 50);
    }

    /**
     * @label Attributes
     * @label.zh.cn 属性
     */
    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    /**
     * @label Sent Time
     * @label.zh.cn 发送时间
     */
    @OfGroup(CoMessage.class)
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
    @OfGroup(CoMessage.class)
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
    @OfGroup(IVotable.class)
    public Integer getVoteUps() {
        return voteUps;
    }

    public void setVoteUps(Integer voteUps) {
        this.voteUps = voteUps;
    }

    /**
     * @label Vote Downs
     * @label.zh.cn 踩
     */
    @OfGroup(IVotable.class)
    public Integer getVoteDowns() {
        return voteDowns;
    }

    public void setVoteDowns(Integer voteDowns) {
        this.voteDowns = voteDowns;
    }

    /**
     * @label Vote Count
     * @label.zh.cn 票数
     */
    @OfGroup(IVotable.class)
    @Override
    public int getVoteCount() {
        return voteUps - voteDowns;
    }

    /**
     * @label Voters
     * @label.zh.cn 投票人
     */
    @OfGroup(IVotable.class)
    @Override
    public List<Voter> getVoters() {
        return voters;
    }

    public void setVoters(List<Voter> voters) {
        this.voters = voters;
    }

    /**
     * @label I Voter
     * @label.zh.cn 我的投票
     */
    @OfGroup(IVotable.class)
    public Voter getiVoter() {
        return iVoter;
    }

    public void setiVoter(Voter iVoter) {
        this.iVoter = iVoter;
    }

    /**
     * @label Liker Count
     * @label.zh.cn 收藏数
     */
    @OfGroup(ILikable.class)
    public int getLikerCount() {
        return likerCount;
    }

    public void setLikerCount(int likerCount) {
        this.likerCount = likerCount;
    }

    /**
     * @label Likers
     * @label.zh.cn 收藏人
     */
    @OfGroup(ILikable.class)
    @Override
    public List<Liker> getLikers() {
        return likers;
    }

    public void setLikers(List<Liker> likers) {
        this.likers = likers;
    }

    /**
     * @label I Liker
     * @label.zh.cn 我的收藏
     */
    @OfGroup(ILikable.class)
    public Liker getiLiker() {
        return iLiker;
    }

    public void setiLiker(Liker iLiker) {
        this.iLiker = iLiker;
    }

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

}
