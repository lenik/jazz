package net.bodz.lily.t.base;

import java.util.Date;

import javax.persistence.Column;

import net.bodz.bas.db.ibatis.IncludeMapperXml;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.meta.bean.DetailLevel;
import net.bodz.bas.meta.cache.Derived;
import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.NullConvertion;
import net.bodz.bas.repr.form.meta.FormInput;
import net.bodz.bas.repr.form.meta.OfGroup;
import net.bodz.bas.repr.form.meta.StdGroup;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.bas.site.file.UploadHint;
import net.bodz.bas.t.order.IPriority;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.entity.IId;
import net.bodz.lily.meta.CriteriaClass;
import net.bodz.lily.meta.TypeParamType;
import net.bodz.lily.meta.TypeParameters;
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
@CriteriaClass(CoMessageMask.class)
@IncludeMapperXml
@TypeParameters({ TypeParamType.ID_TYPE })
@UploadHint
public abstract class CoMessage<Id>
        extends CoMomentInterval<Id>
        implements
            IPriority,
            IId<Id> {

    private static final long serialVersionUID = 1L;

    public static final int N_SUBJECT = 200;

    private User op;
    private String subject;
    private String rawText;
    private UseForm form;
    private ParameterMap parameters;
    private UserClickInfo clickInfo;

    private Date sentTime;
    private Date receivedTime;

    private RichProperties properties = createProperties();

    public CoMessage() {
    }

    @Override
    public void setOwnerUser(User ownerUser) {
        super.setOwnerUser(ownerUser);
        if (op == null)
            op = getOwnerUser();
    }

    @Override
    public Id getId() {
        return id();
    }

    @Override
    public void setId(Id id) {
        id(id);
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

    int opId;

    static final int _ord_OP_ID = 1;

    @Ordinal(_ord_OP_ID)
    @Precision(value = 10)
    @Column(name = "op", nullable = false, precision = 10)
    public synchronized int getOpId() {
        if (op != null) {
            Integer opId = op.getId();
            if (opId != null)
                return opId.intValue();
        }
        return opId;
    }

    public synchronized void setOpId(int value) {
//        this.op = null;
        this.opId = value;
    }

    Integer formId;

    static final int _ord_FORM_ID = 1;

    @Ordinal(_ord_FORM_ID)
    @Precision(value = 10)
    @Column(name = "op", nullable = false, precision = 10)
    public synchronized Integer getFormId() {
        if (form != null)
            return form.getDefId();
        return formId;
    }

    public synchronized void setFormId(Integer formId) {
        this.formId = formId;
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
    public String getRawText() {
        return rawText;
    }

    public void setRawText(String rawText) {
        this.rawText = rawText;
    }

    @Derived
    public TextObject getText() {
        return new TextObject(rawText);
    }

    @Derived
    public HtmlTextObject getHtml() {
        return new HtmlTextObject(rawText);
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
        rawText = o.getString("text", rawText);

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
