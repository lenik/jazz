package net.bodz.lily.t.base;

import java.util.Date;

import net.bodz.bas.c.string.Strings;
import net.bodz.bas.db.ibatis.IncludeMapperXml;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.meta.bean.DetailLevel;
import net.bodz.bas.meta.cache.Derived;
import net.bodz.bas.repr.form.NullConvertion;
import net.bodz.bas.repr.form.meta.FormInput;
import net.bodz.bas.repr.form.meta.OfGroup;
import net.bodz.bas.repr.form.meta.StdGroup;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.site.file.UploadHint;
import net.bodz.bas.t.order.IPriority;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.entity.IId;
import net.bodz.lily.model.base.CoMomentInterval;
import net.bodz.lily.schema.ParameterMap;
import net.bodz.lily.security.User;
import net.bodz.lily.t.struct.UseForm;
import net.bodz.lily.t.struct.UserClickInfo;
import net.bodz.lily.template.RichProperties;

/**
 * @label Message
 * @label.zh 消息
 */
@IncludeMapperXml
@UploadHint
public abstract class CoMessage<Id>
        extends CoMomentInterval<Id>
        implements
            IPriority,
            IId<Id> {

    private static final long serialVersionUID = 1L;

    public static final int N_SUBJECT = 200;

    private Id id;
    private User op;
    private String subject;
    private String text;
    private UseForm form;
    private ParameterMap parameters;
    private UserClickInfo clickInfo;

    private Date sentTime;
    private Date receivedTime;

    private RichProperties properties = createProperties();

    public CoMessage() {
    }

    @Override
    public void reinit() {
        super.reinit();
        op = getOwnerUser();
    }

    @Override
    public final Id id() {
        return getId();
    }

    @Override
    public final void id(Id id) {
        setId(id);
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
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

    public UserClickInfo getClickInfo() {
        if (clickInfo == null)
            synchronized (this) {
                if (clickInfo == null)
                    clickInfo = new UserClickInfo();
            }
        return clickInfo;
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

    protected RichProperties createProperties() {
        return new RichProperties();
    }

    @Override
    public RichProperties getProperties() {
        return properties;
    }

    public void setProperties(RichProperties properties) {
        this.properties = properties;
    }

    @Override
    public void readObject(IVariantMap<String> map)
            throws LoaderException, ParseException {
        super.readObject(map);
    }

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        super.jsonIn(o, opts);

        op = o.readInto("op", op, new User());
        subject = o.getString("subject", subject);
        text = o.getString("text", text);

        subject = o.getString("subject", subject);
        // form = o.readInto("form", form, new UseForm());
        // parameters;
        // clickInfo;

        sentTime = o.getDate("sentTime", sentTime);
        receivedTime = o.getDate("receivedTime", receivedTime);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(80);
        sb.append(id());
        sb.append(" - ");
        sb.append(subject);
        if (op != null) {
            sb.append("@");
            sb.append(op.getLabel());
        }
        return sb.toString();
    }

}
