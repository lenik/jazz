package net.bodz.lily.concrete;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.content.IReset;
import net.bodz.bas.db.ibatis.IncludeMapperXml;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.fmt.json.obj.BeanJsonLoader;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.meta.bean.DetailLevel;
import net.bodz.bas.meta.bean.Internal;
import net.bodz.bas.meta.cache.Derived;
import net.bodz.bas.meta.decl.Priority;
import net.bodz.bas.repr.content.IContent;
import net.bodz.bas.repr.form.NullConvertion;
import net.bodz.bas.repr.form.meta.FormInput;
import net.bodz.bas.repr.form.meta.NumericInput;
import net.bodz.bas.repr.form.meta.OfGroup;
import net.bodz.bas.repr.form.meta.StdGroup;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.meta.Face;
import net.bodz.bas.repr.state.IStated;
import net.bodz.bas.repr.state.State;
import net.bodz.bas.repr.state.StateJsonFn;
import net.bodz.bas.repr.state.StdStates;
import net.bodz.bas.t.variant.IVarMapForm;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.entity.ws.DefaultWebSupport;
import net.bodz.lily.entity.ws.IWebSupport;
import net.bodz.lily.meta.FieldGroupVue;
import net.bodz.lily.security.IAccessControlled;
import net.bodz.lily.security.IAccessMode;
import net.bodz.lily.security.IGroup;
import net.bodz.lily.security.IUser;
import net.bodz.lily.security.Users;

/**
 * Co/Con: Concrete, also Content, Controlled
 */
@FieldGroupVue
@IncludeMapperXml
public abstract class CoObject
        extends StructRow
        implements
            IReset,
            IAccessControlled,
            IStated {

    private static final long serialVersionUID = 1L;

    public static final String FIELD_LABEL = "label";
    public static final String FIELD_DESCRIPTION = "description";
    public static final String FIELD_ICON = "icon";

    public static final String FIELD_FLAGS = "flags";
    public static final String FIELD_PRIORITY = "priority";
    public static final String FIELD_STATE = "state";
    public static final String FIELD_OWNER_USER_ID = "uid";
    public static final String FIELD_OWNER_GROUP_ID = "gid";
    public static final String FIELD_ACL = "acl";
    public static final String FIELD_ACCESS_MODE = "mode";

    public static final int N_LABEL = 80;
    public static final int N_DESCRIPTION = 200;
    public static final int N_ICON = 30;

    // U, U1 = label, description
    private String label;
    private String description;
    private String icon;

    // F
    private int flags = 0;
    private int priority;
    private State state = StdStates.INIT;

    // A = uid, gid, mode, acl
    private IUser ownerUser;
    private IGroup ownerGroup;
    private int ownerUserId;
    private int ownerGroupId;

    private Integer acl;
    private int accessMode = IAccessMode.M_PUBLIC;

    public CoObject() {
        // Opt to reinit()?
    }

    @DetailLevel(DetailLevel.HIDDEN)
    public abstract Object id();

    public abstract void id_(Serializable id);

    /**
     * <p lang="en">
     * Icon name.
     *
     * <p lang="zh">
     * 图标。用skeljs-Icon的格式指定的图标符号。
     *
     * @label Icon
     * @label.zh 图标
     * @placeholder 输入skeljs兼容的图标符号
     */
    @Column(name = FIELD_ICON, precision = N_ICON)
    @FormInput(nullconv = NullConvertion.NONE)
    @Priority(20)
    @TextInput(maxLength = N_ICON)
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * <p lang="en">
     * A short descriptive label.
     *
     * <p lang="zh">
     * 简短的描述性标签。这个标签将出现在打印的列表中，用于标题、抬头等用途。
     *
     * @label Label
     * @label.zh 名称
     * @placeholder 输入简短的描述性名称…
     */
    @Column(name = FIELD_LABEL, precision = N_LABEL)
    @FormInput(nullconv = NullConvertion.NONE)
    @Priority(20)
    @TextInput(maxLength = N_LABEL)
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * <p lang="en">
     * A summary descriptive message.
     *
     * <p lang="zh">
     * 概括性的描述文本，以及必要的附加信息。
     *
     * @label Description
     * @label.zh 描述
     * @placeholder 输入概括性的描述信息…
     */
    @Column(name = FIELD_DESCRIPTION, precision = N_DESCRIPTION)
    @Priority(21)
    @TextInput(maxLength = N_DESCRIPTION)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        Object id = id();
        if (id == null)
            return "new";
        String sb = id + " - " + getLabel();
        return sb;
    }

    /** ⇱ Implementation Of {@link IContent}. */
    /* _____________________________ */static section.iface __CONTENT__;

    /**
     * <p lang="en">
     * Describe the priority order. Important objects should have a higher priority.
     *
     * <p lang="zh">
     * 用于指定优先次序，通常重要的信息具有较高的优先级。
     *
     * @label Priority
     * @label.zh 优先级
     */
    @Column(name = FIELD_PRIORITY, nullable = false)
    @Face("Priority")
    @FormInput(textWidth = 4)
    @NumericInput(min = -1000, max = 1000)
    @OfGroup(StdGroup.Schedule.class)
    @Override
    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * @label Flags
     * @label.zh 标志位
     */
    @Column(name = FIELD_FLAGS, nullable = false)
    @DetailLevel(DetailLevel.DETAIL2)
    @OfGroup(StdGroup.Settings.class)
    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

//    /**
//     * 自定义属性
//     */
//    @DetailLevel(DetailLevel.EXPERT2)
//    public JsonVariant getProperties() {
//        return null;
//    }
//
//    public void setProperties(JsonVariant properties) {
//        // throw new ReadOnlyException();
//    }

    /** ⇱ Implementation Of {@link IStated}. */
    /* _____________________________ */static section.iface __STATE__;

    /**
     * @label State
     * @label.zh 状态
     */
    @Column(name = FIELD_STATE, nullable = false)
    @DetailLevel(DetailLevel.DETAIL)
    @OfGroup(StdGroup.Status.class)
    @Override
    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Internal
    @Derived
    public boolean isNew() {
        return id() == null;
    }

    /** ⇱ Implementation Of {@link IAccessControlled}. */
    /* _____________________________ */static section.iface __ACL__;

    /**
     * <p lang="zh">
     * 描述信息的所有者（持有者）。
     *
     * @label Owner User
     * @label.zh 属主
     */
    @JoinColumn(name = FIELD_OWNER_USER_ID)
    @ManyToOne
    @OfGroup(StdGroup.Security.class)
    public IUser getOwnerUser() {
        return ownerUser;
    }

    public void setOwnerUser(IUser ownerUser) {
        this.ownerUser = ownerUser;
    }

    /**
     * <p lang="zh">
     * 描述信息所属的用户组。组内成员对该信息具有额外的控制权。
     *
     * @label Owner Group
     * @label.zh 属组
     */
    @JoinColumn(name = FIELD_OWNER_GROUP_ID)
    @ManyToOne
    @OfGroup(StdGroup.Security.class)
    public IGroup getOwnerGroup() {
        return ownerGroup;
    }

    public void setOwnerGroup(IGroup ownerGroup) {
        this.ownerGroup = ownerGroup;
    }

    @Column(name = FIELD_OWNER_USER_ID, nullable = false)
    @Derived
    @DetailLevel(DetailLevel.HIDDEN)
    @OfGroup(StdGroup.Security.class)
    @Override
    public synchronized Integer getOwnerUserId() {
        return ownerUser != null ? ownerUser.id() : Integer.valueOf(ownerUserId);
    }

    @Column(name = FIELD_OWNER_GROUP_ID, nullable = false)
    @Derived
    @DetailLevel(DetailLevel.HIDDEN)
    @OfGroup(StdGroup.Security.class)
    @Override
    public synchronized Integer getOwnerGroupId() {
        return ownerGroup != null ? ownerGroup.id() : Integer.valueOf(ownerGroupId);
    }

    public synchronized void setOwnerUserId(int id) {
        ownerUser = null;
        ownerUserId = id;
    }

    public synchronized void setOwnerGroupId(int id) {
        ownerGroup = null;
        ownerGroupId = id;
    }

    /**
     * <p lang="zh">
     * 指定属主、属组和其它人对该信息的访问权限。
     *<br>
     * 已定义的访问权限有：
     * <dl>
     * <dt>公开
     * <dd>所有人均可更改。
     * <dt>协同发布
     * <dd>属主和组内成员可以更改；其它人只能查看。
     * <dt>发布
     * <dd>属主可以更改；其它人只能查看。
     * <dt>协同管理
     * <dd>属主和组内成员可以更改；其它人不可见。
     * <dt>共享（默认）
     * <dd>属主可以更改；组内成员只能查看；其它人不可见。
     * <dt>私有
     * <dd>属主可以更改；其它人不可见。
     * <dt>发布/锁定
     * <dd>所有人只能查看。
     * <dt>共享/锁定
     * <dd>只有属主和组内成员可以查看。
     * <dt>私有/锁定
     * <dd>只有属主可以查看。
     * </dl>
     *
     * @label Access Mode
     * @label.zh 访问模式
     */
    @Column(name = FIELD_ACCESS_MODE, nullable = false)
    @Face("AccessMode")
    @OfGroup(StdGroup.Security.class)
    @Override
    public int getAccessMode() {
        return accessMode;
    }

    public void setAccessMode(int accessMode) {
        this.accessMode = accessMode;
    }

    /**
     * <p lang="en> Access Control List (ACL) applied on this object.
     *
     * <p lang="zh">
     * 在该对象上应用的访问控制列表。
     *
     * @label ACL
     * @label.zh ACL
     */
    @Column(name = FIELD_ACL)
    @FormInput(nullconv = NullConvertion.ZERO)
    @OfGroup(StdGroup.Security.class)
    @Override
    public Integer getAcl() {
        return acl;
    }

    public void setAcl(Integer acl) {
        this.acl = acl;
    }

    /** ⇱ Implementation Of {@link IVarMapForm}. */
    /* _____________________________ */static section.iface __VAR_MAP__;

    @Override
    public void readObject(IVariantMap<String> map)
            throws ParseException {
        super.readObject(map);

//        JsonVarMap propsMap = (JsonVarMap) map.get("properties");
//        if (propsMap != null) {
//            JsonObject wrapped = propsMap.getWrapped();
//            setProperties(JsonVariant.of(wrapped));
//        }
    }

    @Override
    public void writeObject(Map<String, Object> map) {
        super.writeObject(map);
    }

    /** ⇱ Implementation Of {@link IJsonForm}. */
    /* _____________________________ */static section.iface __JSON__;

    @Override
    protected boolean isJsonInOverrided(Class<?> clazz) {
        if (clazz == CoObject.class)
            return false;
        return super.isJsonInOverrided(clazz);
    }

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        super.jsonIn(o, opts);
        if (o == null)
            throw new NullPointerException("o");
        if (isJsonInByLoader()) {
            try {
                new BeanJsonLoader().load(this, JsonVariant.of(o), false);
            } catch (Exception e) {
                throw new ParseException("Failed to load: " + e.getMessage(), e);
            }
        } else {
            label = o.getString("label", label);
            description = o.getString("description", description);

            flags = o.getInt("flags", flags);
            priority = o.getInt("priority", priority);
            state = StateJsonFn.getFrom(o, "state", state);

            ownerUser = o.readInto("ownerUser", ownerUser, Users::newUser);
            ownerGroup = o.readInto("ownerGroup", ownerGroup, Users::newGroup);
            acl = o.getInt("acl", acl);
            accessMode = o.getInt("accessMode", accessMode);

//            JsonObject props = o.getJsonObject("properties");
//            setProperties(JsonVariant.of(props));
        }
    }

    @Internal
    @DetailLevel(DetailLevel.HIDDEN)
    public IWebSupport getWebSupport() {
        return new DefaultWebSupport(this);
    }

    public boolean partialEquals(CoObject o) {
        if (! Nullables.equals(label, o.label))
            return false;
        if (! Nullables.equals(description, o.description))
            return false;

        if (flags != o.flags || priority != o.priority || state != o.state)
            return false;

        // user, group, acl, mode
        return true;
    }

    public void assign(CoObject o) {
        super.assign(o);
        label = o.label;
        description = o.description;
        flags = o.flags;
        priority = o.priority;
        state = o.state;
        ownerUser = o.ownerUser;
        ownerGroup = o.ownerGroup;
        ownerUserId = o.ownerUserId;
        ownerGroupId = o.ownerGroupId;
        acl = o.acl;
        accessMode = o.accessMode;
    }

}
