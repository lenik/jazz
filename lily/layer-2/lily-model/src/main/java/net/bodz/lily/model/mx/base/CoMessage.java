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
    }

    @Override
    public void create() {
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
     * <p lang="zh">
     * 信息的标题。
     * 
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
     * <p lang="zh">
     * 信息的正文。
     * 
     * @label Text
     * @label.zh 正文
     * @placeholder 输入正文…
     */
    @TextInput(multiLine = true, html = true)
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
     * <p lang="zh">
     * 信息的类别。
     * 
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
     * <p lang="zh">
     * 正文的形式。
     * 
     * @label Form
     * @label.zh 形式
     */
    @OfGroup(StdGroup.Metadata.class)
    public FormDef getForm() {
        return form;
    }

    public void setForm(FormDef form) {
        this.form = form;
    }

    /**
     * <p lang="zh">
     * 形式的可选参数。
     * 
     * @label Form Arguments
     * @label.zh 形式参数
     * @placeholder 输入形式参数
     */
    @OfGroup(StdGroup.Metadata.class)
    public String getFormArgs() {
        return formArgs;
    }

    public void setFormArgs(String formArgs) {
        this.formArgs = formArgs;
    }

    /**
     * 自定义属性。
     * 
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
     * 用于信息分类的标签列表。
     * 
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
     * 消息的发送时间。
     * 
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
     * 消息的接受时间。
     * 
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
     * 正投票的计数，说明多少人赞成、支持了这条信息。
     * 
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
     * 负投票的计数，说明多少人对这条信息表达了反对、扔鸡蛋的态度。
     * 
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
     * 有效投票的计数，和<code>正投票 - 负投票</code>相等。
     * 
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
     * 参与投票的信息列表。
     * 
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
     * 说明这条信息被多少人收藏了。
     * 
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
     * 这条信息被阅读的次数。
     * 
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
