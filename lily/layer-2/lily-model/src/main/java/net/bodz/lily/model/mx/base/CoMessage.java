package net.bodz.lily.model.mx.base;

import java.util.Date;
import java.util.List;
import java.util.Map;

import net.bodz.bas.c.string.Strings;
import net.bodz.bas.repr.form.meta.OfGroup;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.t.order.IPriority;

import net.bodz.lily.model.base.CoMomentInterval;
import net.bodz.lily.model.base.schema.AttributeDef;
import net.bodz.lily.model.base.schema.CategoryDef;
import net.bodz.lily.model.base.schema.FormDef;
import net.bodz.lily.model.base.schema.PhaseDef;
import net.bodz.lily.model.base.schema.TagDef;
import net.bodz.lily.model.base.security.User;

/**
 * @label Message
 * @label.zh 消息
 */
public class CoMessage
        extends CoMomentInterval
        implements IPriority, IVotable, ILikable {

    private static final long serialVersionUID = 1L;

    public static final int N_SUBJECT = 200;

    private long id;
    private User op;
    private CategoryDef category;
    private String subject;
    private String text;
    private FormDef form;
    private String formArgs;
    private List<TagDef> tags;
    private Map<AttributeDef, String> attributes;

    private Date sentTime;
    private Date receivedTime;
    private PhaseDef phase;

    private Integer voteUps;
    private Integer voteDowns;
    private List<Voter> voters;
    private Voter iVoter;

    private int likerCount;
    private List<Liker> likers;
    private Liker iLiker;

    private Integer readCount;

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    /**
     * @label Original Poster
     * @label.zh 发起人
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
     * @label.zh 主题
     * @placeholder 输入主题
     */
    @TextInput(maxLength = N_SUBJECT)
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
     * @label.zh 正文
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
     * @label.zh 正文预览
     */
    @OfGroup(CoMessage.class)
    public String getTextPreview() {
        return Strings.ellipsis(getText(), 50);
    }

    /**
     * @label Category
     * @label.zh 类别
     */
    @OfGroup(CoMessage.class)
    public CategoryDef getCategory() {
        return category;
    }

    public void setCategory(CategoryDef category) {
        this.category = category;
    }

    /**
     * @label Phase
     * @label.zh 阶段
     */
    @OfGroup(CoMessage.class)
    public PhaseDef getPhase() {
        return phase;
    }

    public void setPhase(PhaseDef phase) {
        this.phase = phase;
    }

    /**
     * @label Form
     * @label.zh 表单
     */
    @OfGroup(CoMessage.class)
    public FormDef getForm() {
        return form;
    }

    public void setForm(FormDef form) {
        this.form = form;
    }

    /**
     * @label Form Arguments
     * @label.zh 表单参数
     * @placeholder 输入表单参数
     */
    @OfGroup(CoMessage.class)
    public String getFormArgs() {
        return formArgs;
    }

    public void setFormArgs(String formArgs) {
        this.formArgs = formArgs;
    }

    /**
     * @label Attributes
     * @label.zh 属性
     */
    @OfGroup(CoMessage.class)
    public Map<AttributeDef, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<AttributeDef, String> attributes) {
        this.attributes = attributes;
    }

    /**
     * @label Tags
     * @label.zh 标签列表
     */
    public List<TagDef> getTags() {
        return tags;
    }

    public void setTags(List<TagDef> tags) {
        this.tags = tags;
    }

    /**
     * @label Sent Time
     * @label.zh 发送时间
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
     * @label.zh 接收时间
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
     * @label.zh 赞
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
     * @label.zh 踩
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
     * @label.zh 票数
     */
    @OfGroup(IVotable.class)
    @Override
    public int getVoteCount() {
        int nUp = voteUps == null ? 0 : voteUps;
        int nDown = voteDowns == null ? 0 : voteDowns;
        return nUp - nDown;
    }

    /**
     * @label Voters
     * @label.zh 投票人
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
     * @label.zh 我的投票
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
     * @label.zh 收藏数
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
     * @label.zh 收藏人
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
     * @label.zh 我的收藏
     */
    @OfGroup(ILikable.class)
    public Liker getiLiker() {
        return iLiker;
    }

    public void setiLiker(Liker iLiker) {
        this.iLiker = iLiker;
    }

    /**
     * @label Read Count
     * @label.zh 阅读数
     */
    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(80);
        sb.append(id);
        sb.append(" - ");
        sb.append(subject);
        if (op != null) {
            sb.append("@");
            sb.append(op.getLabel());
        }
        return sb.toString();
    }

}
