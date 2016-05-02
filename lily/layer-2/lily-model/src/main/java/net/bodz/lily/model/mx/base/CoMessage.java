package net.bodz.lily.model.mx.base;

import java.util.Date;

import net.bodz.bas.c.string.Strings;
import net.bodz.bas.db.ibatis.IncludeMapperXml;
import net.bodz.bas.meta.bean.DetailLevel;
import net.bodz.bas.meta.cache.Derived;
import net.bodz.bas.meta.decl.ItemType;
import net.bodz.bas.meta.lang.typer;
import net.bodz.bas.repr.form.NullConvertion;
import net.bodz.bas.repr.form.meta.FormInput;
import net.bodz.bas.repr.form.meta.OfGroup;
import net.bodz.bas.repr.form.meta.StdGroup;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.meta.Face;
import net.bodz.bas.t.order.IPriority;
import net.bodz.lily.entity.IId;
import net.bodz.lily.model.base.CoMomentInterval;
import net.bodz.lily.model.base.schema.CategoryDef;
import net.bodz.lily.model.base.schema.PhaseDef;
import net.bodz.lily.model.base.schema.TagDef;
import net.bodz.lily.model.base.security.User;
import net.bodz.lily.model.mixin.ClickInfo;
import net.bodz.lily.model.mixin.ParameterMap;
import net.bodz.lily.model.mixin.TagSet;
import net.bodz.lily.model.mixin.UseForm;

/**
 * @label Message
 * @label.zh 消息
 */
@IncludeMapperXml
public abstract class CoMessage<Id>
        extends CoMomentInterval<Id>
        implements IPriority, IId<Id> {

    private static final long serialVersionUID = 1L;

    public static final int N_SUBJECT = 200;

    private User op;
    private CategoryDef category;
    private PhaseDef phase;
    private String subject;
    private String text;
    private UseForm form;
    private ParameterMap parameters;
    private TagSet tags;
    private ClickInfo clickInfo;

    private Date sentTime;
    private Date receivedTime;

    public CoMessage() {
    }

    @Override
    public void instantiate() {
        super.instantiate();
        op = getOwnerUser();
        tags = new TagSet();
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

    public UseForm getForm() {
        if (form == null)
            synchronized (this) {
                if (form == null)
                    form = new UseForm();
            }
        return form;
    }

    public ParameterMap getParameters() {
        return parameters;
    }

    public void setParameters(ParameterMap parameters) {
        this.parameters = parameters;
    }

    public ClickInfo getClickInfo() {
        if (clickInfo == null)
            synchronized (this) {
                if (clickInfo == null)
                    clickInfo = new ClickInfo();
            }
        return clickInfo;
    }

    /**
     * 用于信息分类的标签列表。
     * 
     * @label Tags
     * @label.zh 标签列表
     */
    @Face("tagsinput")
    @ItemType(TagDef.class)
    @OfGroup(StdGroup.Classification.class)
    @typer.parser(TagsParser.class)
    public TagSet getTags() {
        return tags;
    }

    public void setTags(TagSet tags) {
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
