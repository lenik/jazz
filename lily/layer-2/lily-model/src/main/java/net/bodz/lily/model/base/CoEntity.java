package net.bodz.lily.model.base;

import java.io.Serializable;

import net.bodz.bas.c.type.TypeExtras;
import net.bodz.bas.err.IllegalUsageError;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.bean.DetailLevel;
import net.bodz.bas.potato.PotatoTypes;
import net.bodz.bas.potato.element.IPropertyAccessor;
import net.bodz.bas.potato.element.IType;
import net.bodz.bas.repr.content.IContent;
import net.bodz.bas.repr.form.FormStructBuilder;
import net.bodz.bas.repr.form.IFormField;
import net.bodz.bas.repr.form.IFormStruct;
import net.bodz.bas.repr.form.IFormView;
import net.bodz.bas.repr.form.meta.OfGroup;
import net.bodz.bas.std.rfc.http.CacheControlMode;
import net.bodz.bas.std.rfc.http.CacheRevalidationMode;
import net.bodz.bas.std.rfc.http.ICacheControl;

import net.bodz.lily.model.base.security.Group;
import net.bodz.lily.model.base.security.IAccessControlled;
import net.bodz.lily.model.base.security.User;

/**
 * Co/Con: Concrete, also Content, Controlled
 */
public abstract class CoEntity
        implements Serializable, IContent, IStateful, IAccessControlled, IFormView {

    private static final long serialVersionUID = 1L;

    public static final int N_CODE_NAME = 20;
    public static final int N_LABEL = 80;
    public static final int N_DESCRIPTION = 200;

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
    private long creationTime; // = System.currentTimeMillis();
    private long lastModified; // = creationDate;
    private int flags = 0;
    private int state = S_INIT;
    private int version;

    private User owner;
    private Group ownerGroup;
    private int accessMode = M_COOP;
    private int acl;

    /**
     * @return Non-<code>null</code> id.
     */
    public abstract Object getId();

    /**
     * @label Code Name
     * @label.zh.cn 代码
     */
    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    /**
     * @label Label
     * @label.zh.cn 名称
     */
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @label Description
     * @label.zh.cn 描述
     */
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return getCodeName() + " - " + getLabel();
    }

    /** ⇱ Implementation Of {@link IContent}. */
    /* _____________________________ */static section.iface __CONTENT__;

    /**
     * @label Priority
     * @label.zh.cn 优先级
     */
    @OfGroup(IContent.class)
    @Override
    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * @label Creation Date
     * @label.zh.cn 创建时间
     */
    @OfGroup(IContent.class)
    @Override
    public long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }

    public void touch() {
        lastModified = System.currentTimeMillis();
        if (creationTime == 0L)
            creationTime = lastModified;
    }

    /**
     * @label Last Modified Date
     * @label.zh.cn 修改时间
     */
    @OfGroup({ IContent.class, ICacheControl.class })
    @Override
    public long getLastModified() {
        return lastModified;
    }

    public void setLastModified(long lastModified) {
        this.lastModified = lastModified;
    }

    /**
     * @label Flags
     * @label.zh.cn 标志位
     */
    @DetailLevel(DetailLevel.EXPERT)
    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    @DetailLevel(DetailLevel.HIDDEN)
    @OfGroup(ICacheControl.class)
    @Override
    public CacheControlMode getCacheControlMode() {
        return CacheControlMode.AUTO;
    }

    @DetailLevel(DetailLevel.HIDDEN)
    @OfGroup(ICacheControl.class)
    @Override
    public CacheRevalidationMode getCacheRevalidationMode() {
        return CacheRevalidationMode.WANTED;
    }

    /**
     * One day by default.
     * 
     * @label Max Age
     * @label.zh.cn 缓存寿命
     */
    @DetailLevel(DetailLevel.EXPERT2)
    @OfGroup(ICacheControl.class)
    @Override
    public int getMaxAge() {
        return 86400;
    }

    /**
     * @label E-Tag
     * @label.zh.cn 实体标签
     */
    @DetailLevel(DetailLevel.HIDDEN)
    @OfGroup(ICacheControl.class)
    @Override
    public String getETag() {
        long time = getLastModified();
        return String.valueOf(time);
    }

    /**
     * @label Weak Validation
     * @labal.zh.cn 弱校验
     */
    @DetailLevel(DetailLevel.EXPERT2)
    @OfGroup(ICacheControl.class)
    @Override
    public boolean isWeakValidation() {
        return true;
    }

    /** ⇱ Implementation Of {@link IStateful}. */
    /* _____________________________ */static section.iface __STATE__;

    /**
     * @label State
     * @label.zh.cn 状态
     */
    @DetailLevel(DetailLevel.EXPERT)
    @Override
    public int getState() {
        return state;
    }

    @Override
    public void setState(int state) {
        this.state = state;
    }

    /**
     * @label Version
     * @label.zh.cn 版本
     */
    @DetailLevel(DetailLevel.EXPERT)
    @OfGroup(IContent.class)
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
     * @label.zh.cn 属主
     */
    @OfGroup(IAccessControlled.class)
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    /**
     * @label Group
     * @label.zh.cn 属组
     */
    @OfGroup(IAccessControlled.class)
    public Group getOwnerGroup() {
        return ownerGroup;
    }

    public void setOwnerGroup(Group ownerGroup) {
        this.ownerGroup = ownerGroup;
    }

    @DetailLevel(DetailLevel.HIDDEN)
    @OfGroup(IAccessControlled.class)
    @Override
    public int getUserId() {
        return owner == null ? 0 : owner.getId();
    }

    @DetailLevel(DetailLevel.HIDDEN)
    @Override
    public int getGroupId() {
        return ownerGroup == null ? 0 : ownerGroup.getId();
    }

    /**
     * @label Access Mode
     * @label.zh.cn 访问模式
     */
    @OfGroup(IAccessControlled.class)
    @Override
    public int getAccessMode() {
        return accessMode;
    }

    public void setAccessMode(int accessMode) {
        this.accessMode = accessMode;
    }

    /**
     * @label ACL
     * @label.zh.cn ACL
     */
    @OfGroup(IAccessControlled.class)
    @Override
    public int getAcl() {
        return acl;
    }

    public void setAcl(int acl) {
        this.acl = acl;
    }

    /** ⇱ Implementation Of {@link IFormView}. */
    /* _____________________________ */static section.iface __DATA__;

    @DetailLevel(DetailLevel.HIDDEN)
    @Override
    public IFormStruct getFormStruct() {
        IType type = PotatoTypes.getInstance().forClass(getClass());
        TypeExtras extras = TypeExtras.of(getClass());
        IFormStruct struct = extras.getFeature(IFormStruct.class);
        if (struct == null) {
            struct = buildFormStruct(type);
            extras.setFeature(IFormStruct.class, struct);
        }
        return struct;
    }

    protected IFormStruct buildFormStruct(IType type) {
        FormStructBuilder formStructBuilder = new FormStructBuilder();
        try {
            return formStructBuilder.buildFromType(type);
        } catch (ParseException e) {
            throw new IllegalUsageError(e.getMessage(), e);
        }
    }

    public Object getFormFieldValue(String name) {
        if (name == null)
            throw new NullPointerException("name");
        IFormStruct metadata = getFormStruct();
        IFormField field = metadata.getField(name);
        if (field == null)
            throw new IllegalArgumentException("bad field: " + name);

        IPropertyAccessor accessor = field.getAccessor();
        try {
            return accessor.getValue(this);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public void setFormFieldValue(String name, Object value) {
        if (name == null)
            throw new NullPointerException("name");
        IFormStruct metadata = getFormStruct();
        IFormField field = metadata.getField(name);
        if (field == null)
            throw new IllegalArgumentException("bad field: " + name);

        IPropertyAccessor accessor = field.getAccessor();
        try {
            accessor.setValue(this, value);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
