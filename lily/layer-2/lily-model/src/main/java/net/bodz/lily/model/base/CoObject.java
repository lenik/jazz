package net.bodz.lily.model.base;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.exceptions.PersistenceException;
import org.joda.time.DateTime;

import net.bodz.bas.db.ibatis.IMapperProvider;
import net.bodz.bas.db.ibatis.IMapperTemplate;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.meta.ViewCriteria;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.http.ctx.CurrentHttpService;
import net.bodz.bas.meta.bean.DetailLevel;
import net.bodz.bas.meta.cache.Derived;
import net.bodz.bas.meta.decl.Priority;
import net.bodz.bas.repr.content.IContent;
import net.bodz.bas.repr.form.NullConvertion;
import net.bodz.bas.repr.form.meta.FormInput;
import net.bodz.bas.repr.form.meta.NumericInput;
import net.bodz.bas.repr.form.meta.OfGroup;
import net.bodz.bas.repr.form.meta.StdGroup;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.req.IMethodOfRequest;
import net.bodz.bas.repr.req.MethodNames;
import net.bodz.bas.repr.state.State;
import net.bodz.bas.repr.state.StdStates;
import net.bodz.bas.std.rfc.http.CacheControlMode;
import net.bodz.bas.std.rfc.http.CacheRevalidationMode;
import net.bodz.lily.model.base.security.Group;
import net.bodz.lily.model.base.security.IAccessControlled;
import net.bodz.lily.model.base.security.LoginContext;
import net.bodz.lily.model.base.security.User;

/**
 * Co/Con: Concrete, also Content, Controlled
 */
public abstract class CoObject
        // extends AbstractTextParametric
        implements Serializable, IInstantiable, IContent, IAccessControlled, ILazyLoading {

    private static final long serialVersionUID = 1L;

    public static final int N_CODE_NAME = 20;
    public static final int N_LABEL = 80;
    public static final int N_DESCRIPTION = 200;
    public static final int N_COMMENT = 200;
    public static final int N_IMAGE = 100;

    /** 私密 */
    public static final int M_PRIVATE = 0600;
    /** 共享 */
    public static final int M_SHARED = 0640;
    /** 协作 */
    public static final int M_COOP = 0660;
    /** 公开 */
    public static final int M_PUBLIC = 0644;
    /** 协作公开 */
    public static final int M_PUBLIC_COOP = 0664;

    private String codeName;
    private String label;
    private String description;
    private String comment;
    private String image;

    private int priority;
    private DateTime creationDate = new DateTime();
    private DateTime lastModifiedDate = creationDate;
    private int flags = 0;
    private State state = StdStates.INIT;
    private int version;

    private User ownerUser;
    private Group ownerGroup;
    private int accessMode = M_PUBLIC;
    private int acl;

    public CoObject() {
    }

    @Override
    public void instantiate() {
        HttpSession session = CurrentHttpService.getSessionOpt();
        if (session != null) {
            LoginContext loginContext = (LoginContext) session.getAttribute(LoginContext.ATTRIBUTE_KEY);
            if (loginContext != null) {
                ownerUser = loginContext.user;
                ownerGroup = loginContext.user.getPrimaryGroup();
            }
        }
    }

    /**
     * @return Non-<code>null</code> id.
     */
    public abstract Object getId();

    /**
     * <p lang="en">
     * An optional unique code name, which can be specified and alternated by user.
     * 
     * <p lang="zh">
     * 类似于 id，代码不可重复。和 id 不同的是，代码是可以自行指定的，可以包含数字、字母，且可以更改。
     * 
     * @label Code Name
     * @label.zh 代码
     * @placeholder 输入代码/别名…
     */
    @Priority(1)
    @TextInput(maxLength = N_CODE_NAME)
    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
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
    @Priority(21)
    @TextInput(maxLength = N_DESCRIPTION)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @label Comment
     * @label.zh 注释
     * @placeholder 输入注释…
     */
    @DetailLevel(DetailLevel.EXPERT)
    @Priority(800)
    @TextInput(maxLength = N_COMMENT)
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @label Image
     * @label.zh 图像
     * @placeholder 输入图像的名称…
     * @deprecated See #
     */
    @OfGroup(StdGroup.Visual.class)
    @TextInput(maxLength = N_IMAGE)
    public String getImage() {
        return image;
    }

    /**
     * @deprecated
     */
    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        Object id = getId();
        if (id == null)
            return "new";
        String codeName = getCodeName();
        StringBuilder sb = new StringBuilder(80);
        if (codeName != null)
            sb.append(codeName);
        else
            sb.append(id);
        sb.append(" - ");
        sb.append(label);
        return sb.toString();
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
    @ViewCriteria("Priority")
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
     * 信息的创建时间。
     * 
     * @label Creation Date
     * @label.zh 创建时间
     */
    @FormInput(readOnly = true)
    @OfGroup({ StdGroup.Content.class, StdGroup.Status.class })
    @Priority(-100 + 0)
    public DateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(DateTime creationDate) {
        if (creationDate == null)
            throw new NullPointerException("creationDate");
        this.creationDate = creationDate;
    }

    @Derived
    @DetailLevel(DetailLevel.HIDDEN)
    @OfGroup({ StdGroup.Content.class, StdGroup.Status.class })
    @Override
    public long getCreationTime() {
        return creationDate.getMillis();
    }

    public void touch() {
        lastModifiedDate = new DateTime();
        if (creationDate == null)
            creationDate = lastModifiedDate;
    }

    /**
     * 信息最近一次的修改时间。
     * 
     * @label Last Modified Date
     * @label.zh 修改时间
     */
    @FormInput(readOnly = true)
    @OfGroup({ StdGroup.Content.class, StdGroup.Status.class, StdGroup.Cache.class })
    @Priority(-100 + 1)
    public DateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(DateTime lastModified) {
        if (lastModified == null)
            throw new NullPointerException("lastModified");
        this.lastModifiedDate = lastModified;
    }

    @Derived
    @DetailLevel(DetailLevel.HIDDEN)
    @OfGroup({ StdGroup.Content.class, StdGroup.Status.class })
    @Override
    public long getLastModified() {
        return lastModifiedDate.getMillis();
    }

    /**
     * @label Flags
     * @label.zh 标志位
     */
    @DetailLevel(DetailLevel.EXPERT)
    @OfGroup(StdGroup.Settings.class)
    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    @DetailLevel(DetailLevel.HIDDEN)
    @OfGroup(StdGroup.Cache.class)
    @Override
    public CacheControlMode getCacheControlMode() {
        return CacheControlMode.NO_CACHE;
    }

    @DetailLevel(DetailLevel.HIDDEN)
    @OfGroup(StdGroup.Cache.class)
    @Override
    public CacheRevalidationMode getCacheRevalidationMode() {
        return CacheRevalidationMode.WANTED;
    }

    /**
     * One day by default.
     * 
     * @label Max Age
     * @label.zh 缓存寿命
     */
    @DetailLevel(DetailLevel.EXPERT2)
    @OfGroup(StdGroup.Cache.class)
    @Override
    public int getMaxAge() {
        return 86400;
    }

    /**
     * @label E-Tag
     * @label.zh 实体标签
     */
    @DetailLevel(DetailLevel.HIDDEN)
    @OfGroup(StdGroup.Cache.class)
    @Override
    public String getETag() {
        long time = getLastModified();
        return String.valueOf(time);
    }

    /**
     * @label Weak Validation
     * @label.zh 弱校验
     */
    @DetailLevel(DetailLevel.EXPERT2)
    @OfGroup(StdGroup.Cache.class)
    @Override
    public boolean isWeakValidation() {
        return true;
    }

    /** ⇱ Implementation Of {@link IStateful}. */
    /* _____________________________ */static section.iface __STATE__;

    /**
     * @label State
     * @label.zh 状态
     */
    @DetailLevel(DetailLevel.EXPERT)
    @OfGroup(StdGroup.Status.class)
    public State getState() {
        return state;
    }

    public void setState(State state) {
        if (state == null)
            throw new NullPointerException("state");
        this.state = state;
    }

    /**
     * @label Version
     * @label.zh 版本
     */
    @DetailLevel(DetailLevel.NORMAL)
    @OfGroup(StdGroup.Version.class)
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
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
    @OfGroup(StdGroup.Security.class)
    public User getOwnerUser() {
        return ownerUser;
    }

    public void setOwnerUser(User ownerUser) {
        this.ownerUser = ownerUser;
    }

    /**
     * <p lang="zh">
     * 描述信息所属的用户组。组内成员对该信息具有额外的控制权。
     * 
     * @label Owner Group
     * @label.zh 属组
     */
    @OfGroup(StdGroup.Security.class)
    public Group getOwnerGroup() {
        return ownerGroup;
    }

    public void setOwnerGroup(Group ownerGroup) {
        this.ownerGroup = ownerGroup;
    }

    @Derived
    @DetailLevel(DetailLevel.HIDDEN)
    @OfGroup(StdGroup.Security.class)
    @Override
    public int getUserId() {
        return ownerUser == null ? 0 : ownerUser.getId();
    }

    @Derived
    @DetailLevel(DetailLevel.HIDDEN)
    @OfGroup(StdGroup.Security.class)
    @Override
    public int getGroupId() {
        return ownerGroup == null ? 0 : ownerGroup.getId();
    }

    /**
     * <p lang="zh">
     * 指定属主、属组和其它人对该信息的访问权限。
     * 
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
    @ViewCriteria("AccessMode")
    @OfGroup(StdGroup.Security.class)
    @Override
    public int getAccessMode() {
        return accessMode;
    }

    public void setAccessMode(int accessMode) {
        this.accessMode = accessMode;
    }

    /**
     * <p lang="en>
     * Access Control List (ACL) applied on this object.
     * 
     * <p lang="zh">
     * 在该对象上应用的访问控制列表。
     * 
     * @label ACL
     * @label.zh ACL
     */
    @FormInput(nullconv = NullConvertion.ZERO)
    @OfGroup(StdGroup.Security.class)
    @Override
    public int getAcl() {
        return acl;
    }

    public void setAcl(int acl) {
        this.acl = acl;
    }

    public Object persist(IHtmlViewContext ctx, IHtmlTag out)
            throws PersistenceException, IOException {
        IMapperProvider provider = ctx.query(IMapperProvider.class);
        IMapperTemplate<CoObject, ?> mapper = provider.getMapperForObject(getClass());
        if (mapper == null)
            throw new PersistenceException("No mapper for " + getClass());

        String methodName = ctx.query(IMethodOfRequest.class).getMethodName();

        boolean creation = false;
        if (getId() == null)
            creation = true;
        else if (methodName != null)
            switch (methodName) {
            case MethodNames.CREATE:
                creation = true;
                break;
            case MethodNames.UPDATE:
                creation = false;
                break;
            }

        if (creation) {
            long longId = mapper.insert(this);

            IdType aIdType = getClass().getAnnotation(IdType.class);
            if (aIdType == null)
                throw new IllegalUsageException("Unknown id type of " + getClass());
            Class<?> idType = aIdType.value();

            if (idType == Long.class)
                return longId;
            else
                return (int) longId;
        } else {
            mapper.update(this);
            return getId();
        }
    }

}
