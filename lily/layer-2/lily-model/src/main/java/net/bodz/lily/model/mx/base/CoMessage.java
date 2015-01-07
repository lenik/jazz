package net.bodz.lily.model.mx.base;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.bodz.bas.c.string.Strings;
import net.bodz.bas.http.ctx.CurrentHttpService;
import net.bodz.bas.meta.bean.DetailLevel;
import net.bodz.bas.meta.cache.Derived;
import net.bodz.bas.repr.form.NullConvertion;
import net.bodz.bas.repr.form.meta.FormInput;
import net.bodz.bas.repr.form.meta.OfGroup;
import net.bodz.bas.repr.form.meta.StdGroup;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.t.order.IPriority;

import net.bodz.lily.model.base.CoMomentInterval;
import net.bodz.lily.model.base.IId;
import net.bodz.lily.model.base.schema.AttributeDef;
import net.bodz.lily.model.base.schema.CategoryDef;
import net.bodz.lily.model.base.schema.FormDef;
import net.bodz.lily.model.base.schema.PhaseDef;
import net.bodz.lily.model.base.schema.TagDef;
import net.bodz.lily.model.base.security.LoginContext;
import net.bodz.lily.model.base.security.User;

/**
 * @label Message
 * @label.zh 消息
 */
public class CoMessage<Id>
        extends CoMomentInterval<Id>
        implements IPriority, IVotable, ILikable, IId<Id> {

    private static final long serialVersionUID = 1L;

    public static final int N_SUBJECT = 200;

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

    public CoMessage() {
        HttpSession session = CurrentHttpService.getSessionOpt();
        if (session != null) {
            LoginContext loginContext = (LoginContext) session.getAttribute(LoginContext.ATTRIBUTE_KEY);
            if (loginContext != null) {
                op = loginContext.user;
            }
        }
    }

    /**
     * @label Original Poster
     * @label.zh 发起人
     */
    public User getOp() {
        return op;
    }

    public void setOp(User op) {
        this.op = op;
    }

    /**
     * @label Subject
     * @label.zh 标题
     * @placeholder 输入标题…
     */
    @FormInput(nullconv = NullConvertion.NONE)
    @TextInput(maxLength = N_SUBJECT)
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
     * @placeholder 输入正文…
     */
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
    @Derived
    public String getTextPreview() {
        return Strings.ellipsis(getText(), 50);
    }

    /**
     * @label Category
     * @label.zh 类别
     */
    @OfGroup(StdGroup.Classification.class)
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
    @OfGroup(StdGroup.Status.class)
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
    @OfGroup(StdGroup.Metadata.class)
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
    @OfGroup(StdGroup.Metadata.class)
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
    @OfGroup(StdGroup.Content.class)
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
    @OfGroup(StdGroup.Classification.class)
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
    @OfGroup(StdGroup.Status.class)
    @DetailLevel(DetailLevel.EXPERT)
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
    @OfGroup(StdGroup.Status.class)
    @DetailLevel(DetailLevel.EXPERT)
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
    @OfGroup(StdGroup.Ranking.class)
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
    @OfGroup(StdGroup.Ranking.class)
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
    @OfGroup(StdGroup.Ranking.class)
    @Derived
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
    @OfGroup(StdGroup.Ranking.class)
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
    @OfGroup(StdGroup.Ranking.class)
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
    @OfGroup(StdGroup.Ranking.class)
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
    @OfGroup(StdGroup.Ranking.class)
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
    @OfGroup(StdGroup.Ranking.class)
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
    @OfGroup(StdGroup.Ranking.class)
    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(80);
        sb.append(getId());
        sb.append(" - ");
        sb.append(subject);
        if (op != null) {
            sb.append("@");
            sb.append(op.getLabel());
        }
        return sb.toString();
    }

}
