package net.bodz.lily.model.base;

import java.io.Serializable;
import java.util.Date;

import javax.servlet.http.HttpSession;

import net.bodz.bas.html.meta.HtmlViewBuilder;
import net.bodz.bas.http.ctx.CurrentHttpService;
import net.bodz.bas.meta.bean.DetailLevel;
import net.bodz.bas.meta.cache.Derived;
import net.bodz.bas.repr.content.IContent;
import net.bodz.bas.repr.form.meta.FormInput;
import net.bodz.bas.repr.form.meta.NumericInput;
import net.bodz.bas.repr.form.meta.OfGroup;
import net.bodz.bas.repr.form.meta.StdGroup;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.state.State;
import net.bodz.bas.repr.state.StdStates;
import net.bodz.bas.std.rfc.http.CacheControlMode;
import net.bodz.bas.std.rfc.http.CacheRevalidationMode;

import net.bodz.lily.model.base.impl.Priority_htm;
import net.bodz.lily.model.base.security.Group;
import net.bodz.lily.model.base.security.IAccessControlled;
import net.bodz.lily.model.base.security.LoginContext;
import net.bodz.lily.model.base.security.User;
import net.bodz.lily.model.base.security.impl.AccessMode_htm;

/**
 * Co/Con: Concrete, also Content, Controlled
 */
public abstract class CoEntity
        implements Serializable, IContent, IAccessControlled {

    private static final long serialVersionUID = 1L;

    public static final int N_CODE_NAME = 20;
    public static final int N_LABEL = 80;
    public static final int N_DESCRIPTION = 200;
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
    private String image;

    private int priority;
    private Date creationDate = new Date();
    private Date lastModifiedDate = creationDate;
    private int flags = 0;
    private State state = StdStates.START;
    private int version;

    private User owner;
    private Group ownerGroup;
    private int accessMode = M_COOP;
    private int acl;

    public CoEntity() {
        HttpSession session = CurrentHttpService.getSessionOpt();
        if (session != null) {
            LoginContext loginContext = (LoginContext) session.getAttribute(LoginContext.ATTRIBUTE_KEY);
            if (loginContext != null) {
                owner = loginContext.user;
                ownerGroup = loginContext.user.getPrimaryGroup();
            }
        }
    }

    /**
     * @return Non-<code>null</code> id.
     */
    public abstract Object getId();

    /**
     * @label Code Name
     * @label.zh 代码
     * @placeholder 输入代码/别名…
     */
    @TextInput(maxLength = N_CODE_NAME)
    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    /**
     * @label Label
     * @label.zh 名称
     * @placeholder 输入简短的描述性名称…
     */
    @TextInput(maxLength = N_LABEL)
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @label Description
     * @label.zh 描述
     * @placeholder 输入概括性的描述信息…
     */
    @TextInput(maxLength = N_DESCRIPTION)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @label Image
     * @label.zh 图像
     * @placeholder 输入图像的名称…
     */
    @TextInput(maxLength = N_IMAGE)
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(80);
        String codeName = getCodeName();
        if (codeName != null)
            sb.append(codeName);
        else
            sb.append(getId());
        sb.append(" - ");
        sb.append(getLabel());
        return sb.toString();
    }

    /** ⇱ Implementation Of {@link IContent}. */
    /* _____________________________ */static section.iface __CONTENT__;

    /**
     * @label Priority
     * @label.zh 优先级
     */
    @HtmlViewBuilder(Priority_htm.class)
    @OfGroup(StdGroup.Schedule.class)
    @FormInput(textWidth = 4)
    @NumericInput(min = -1000, max = 1000)
    @Override
    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * @label Creation Date
     * @label.zh 创建时间
     */
    @OfGroup({ StdGroup.Content.class, StdGroup.Status.class })
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        if (creationDate == null)
            throw new NullPointerException("creationDate");
        this.creationDate = creationDate;
    }

    @OfGroup({ StdGroup.Content.class, StdGroup.Status.class })
    @Derived
    @Override
    public long getCreationTime() {
        return creationDate.getTime();
    }

    public void touch() {
        lastModifiedDate = new Date();
        if (creationDate == null)
            creationDate = lastModifiedDate;
    }

    /**
     * @label Last Modified Date
     * @label.zh 修改时间
     */
    @OfGroup({ StdGroup.Content.class, StdGroup.Status.class, StdGroup.Cache.class })
    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModified) {
        if (lastModified == null)
            throw new NullPointerException("lastModified");
        this.lastModifiedDate = lastModified;
    }

    @OfGroup({ StdGroup.Content.class, StdGroup.Status.class })
    @Derived
    @Override
    public long getLastModified() {
        return lastModifiedDate.getTime();
    }

    /**
     * @label Flags
     * @label.zh 标志位
     */
    @OfGroup(StdGroup.Settings.class)
    @DetailLevel(DetailLevel.EXPERT)
    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    @OfGroup(StdGroup.Cache.class)
    @DetailLevel(DetailLevel.HIDDEN)
    @Override
    public CacheControlMode getCacheControlMode() {
        return CacheControlMode.AUTO;
    }

    @OfGroup(StdGroup.Cache.class)
    @DetailLevel(DetailLevel.HIDDEN)
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
     * @labal.zh 弱校验
     */
    @OfGroup(StdGroup.Cache.class)
    @DetailLevel(DetailLevel.EXPERT2)
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
    @OfGroup(StdGroup.Status.class)
    @DetailLevel(DetailLevel.EXPERT)
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
    @OfGroup(StdGroup.Version.class)
    @DetailLevel(DetailLevel.EXPERT)
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    /** ⇱ Implementation Of {@link IAccessControlled}. */
    /* _____________________________ */static section.iface __ACL__;

    /**
     * @label Owner
     * @label.zh 属主
     */
    @OfGroup(StdGroup.Security.class)
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    /**
     * @label Group
     * @label.zh 属组
     */
    @OfGroup(StdGroup.Security.class)
    public Group getOwnerGroup() {
        return ownerGroup;
    }

    public void setOwnerGroup(Group ownerGroup) {
        this.ownerGroup = ownerGroup;
    }

    @OfGroup(StdGroup.Security.class)
    @DetailLevel(DetailLevel.HIDDEN)
    @Derived
    @Override
    public int getUserId() {
        return owner == null ? 0 : owner.getId();
    }

    @OfGroup(StdGroup.Security.class)
    @DetailLevel(DetailLevel.HIDDEN)
    @Derived
    @Override
    public int getGroupId() {
        return ownerGroup == null ? 0 : ownerGroup.getId();
    }

    /**
     * @label Access Mode
     * @label.zh 访问模式
     */
    @HtmlViewBuilder(AccessMode_htm.class)
    @OfGroup(StdGroup.Security.class)
    @Override
    public int getAccessMode() {
        return accessMode;
    }

    public void setAccessMode(int accessMode) {
        this.accessMode = accessMode;
    }

    /**
     * @label ACL
     * @label.zh ACL
     */
    @OfGroup(StdGroup.Security.class)
    @Override
    public int getAcl() {
        return acl;
    }

    public void setAcl(int acl) {
        this.acl = acl;
    }

}
