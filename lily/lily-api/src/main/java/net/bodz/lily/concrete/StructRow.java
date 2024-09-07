package net.bodz.lily.concrete;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.time.OffsetDateTime;
import java.util.Map;

import javax.persistence.Column;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.fmt.json.obj.BeanJsonDumper;
import net.bodz.bas.fmt.json.obj.BeanJsonLoader;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.meta.bean.DetailLevel;
import net.bodz.bas.meta.bean.IgnoreChild;
import net.bodz.bas.meta.bean.Internal;
import net.bodz.bas.meta.decl.Priority;
import net.bodz.bas.meta.decl.TsTyped;
import net.bodz.bas.repr.content.IContent;
import net.bodz.bas.repr.form.meta.FormInput;
import net.bodz.bas.repr.form.meta.OfGroup;
import net.bodz.bas.repr.form.meta.StdGroup;
import net.bodz.bas.std.rfc.http.CacheControlMode;
import net.bodz.bas.std.rfc.http.CacheRevalidationMode;
import net.bodz.bas.std.rfc.http.ICacheControl;
import net.bodz.bas.t.variant.IVarMapForm;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.bas.t.variant.VarMapLoader;
import net.bodz.lily.meta.FieldGroupVue;
import net.bodz.lily.meta.ReadOnly;

@FieldGroupVue
@TsTyped
public abstract class StructRow
        implements
            IContent,
            Serializable,
            IVarMapForm,
            IJsonForm,
            Cloneable {

    private static final long serialVersionUID = 1L;

    public static final String FIELD_CREATION_DATE = "creation";
    public static final String FIELD_LAST_MODIFIED = "lastmod";
    public static final String FIELD_VERSION = "version";

    // V = creation, lastmod, version
    private OffsetDateTime creationDate = OffsetDateTime.now();
    private OffsetDateTime lastModified = creationDate;
    private int version;

    public StructRow() {
    }

    /** ⇱ Implementation Of {@link IContent}. */
    /* _____________________________ */static section.iface __CONTENT__;

    @Internal
    @Override
    public int getPriority() {
        return 0;
    }

    /**
     * 信息的创建时间。
     *
     * @label Creation Date
     * @label.zh 创建时间
     */
    @ReadOnly
    @Column(name = FIELD_CREATION_DATE, nullable = false)
    @FormInput(readOnly = true)
    @OfGroup({ StdGroup.Content.class, StdGroup.Status.class })
    @Override
    @Priority(-100 + 0)
    public OffsetDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(OffsetDateTime creationDate) {
        if (creationDate == null)
            throw new NullPointerException("creationDate");
        this.creationDate = creationDate;
    }

    public void touch() {
        lastModified = OffsetDateTime.now();
        if (creationDate == null)
            creationDate = lastModified;
    }

    /**
     * 信息最近一次的修改时间。
     *
     * @label Last Modified Date
     * @label.zh 修改时间
     */
    @ReadOnly
    @Column(name = FIELD_LAST_MODIFIED, nullable = false)
    @FormInput(readOnly = true)
    @OfGroup({ StdGroup.Content.class, StdGroup.Status.class, StdGroup.Cache.class })
    @Override
    @Priority(-100 + 1)
    public OffsetDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(OffsetDateTime lastModified) {
        if (lastModified == null)
            throw new NullPointerException("lastModified");
        this.lastModified = lastModified;
    }

    /** ⇱ Implementation Of {@link ICacheControl}. */
    /* _____________________________ */static section.iface __CACHE__;

    @Internal
    @DetailLevel(DetailLevel.HIDDEN)
    @OfGroup(StdGroup.Cache.class)
    @Override
    public CacheControlMode getCacheControlMode() {
        return CacheControlMode.NO_CACHE;
    }

    @Internal
    @DetailLevel(DetailLevel.HIDDEN)
    @OfGroup(StdGroup.Cache.class)
    @Override
    public CacheRevalidationMode getCacheRevalidationMode() {
        return CacheRevalidationMode.MUST_REVALIDATE;
    }

    /**
     * One day by default.
     *
     * @label Max Age
     * @label.zh 缓存寿命
     */
    @Internal
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
    @IgnoreChild
    @DetailLevel(DetailLevel.HIDDEN)
    @OfGroup(StdGroup.Cache.class)
    @Override
    public String getETag() {
        return IContent.super.getETag();
    }

    /**
     * @label Weak Validation
     * @label.zh 弱校验
     */
    @Internal
    @DetailLevel(DetailLevel.EXPERT2)
    @OfGroup(StdGroup.Cache.class)
    @Override
    public boolean isWeakValidation() {
        return true;
    }

    /**
     * @label Version
     * @label.zh 版本
     */
    @Column(name = FIELD_VERSION, nullable = false)
    @DetailLevel(DetailLevel.DETAIL)
    @OfGroup(StdGroup.Version.class)
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new UnsupportedOperationException(e.getMessage(), e);
        }
    }

    /** ⇱ Implementation Of {@link IVarMapForm}. */
    /* _____________________________ */static section.iface __VAR_MAP__;

    @Override
    public void readObject(IVariantMap<String> map)
            throws ParseException {
        VarMapLoader.defaultParse(this, map);
    }

    @Override
    public void writeObject(Map<String, Object> map) {
        throw new NotImplementedException();
    }

    /** ⇱ Implementation Of {@link IJsonForm}. */
    /* _____________________________ */static section.iface __JSON__;

    protected Boolean overrided_jsonIn = false;
    protected Boolean overrided_jsonOut = false;

    @Internal
    @Override
    public boolean isJsonInByLoader() {
        if (overrided_jsonIn == null)
            synchronized (this) {
                if (overrided_jsonIn == null)
                    try {
                        Method mtd = getClass().getMethod("jsonIn", JsonObject.class, JsonFormOptions.class);
                        Class<?> declaringClass = mtd.getDeclaringClass();
                        overrided_jsonIn = isJsonInOverrided(declaringClass);
                        overrided_jsonIn = true;
                    } catch (NoSuchMethodException e) {
                        overrided_jsonIn = false;
                    }
            }
        return ! overrided_jsonIn;
    }

    protected boolean isJsonInOverrided(Class<?> clazz) {
        if (clazz == StructRow.class)
            return false;
        return true;
    }

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        if (o == null)
            throw new NullPointerException("o");
        if (isJsonInByLoader()) {
            try {
                new BeanJsonLoader().load(this, JsonVariant.of(o), false);
            } catch (Exception e) {
                throw new ParseException("Failed to load: " + e.getMessage(), e);
            }
        } else {
            creationDate = o.getOffsetDateTime("creationDate", creationDate);
            lastModified = o.getOffsetDateTime("lastModifiedDate", lastModified);
            version = o.getInt("version", version);
        }
    }

    @Internal
    @Override
    public boolean isJsonOutByDumper() {
        if (overrided_jsonOut == null)
            synchronized (this) {
                if (overrided_jsonOut == null)
                    try {
                        Method mtd = getClass().getMethod("jsonOut", JsonObject.class, JsonFormOptions.class);
                        Class<?> declaringClass = mtd.getDeclaringClass();
                        overrided_jsonOut = isJsonOutOverrided(declaringClass);
                        overrided_jsonOut = true;
                    } catch (NoSuchMethodException e) {
                        overrided_jsonOut = false;
                    }
            }
        return ! overrided_jsonOut;
    }

    protected boolean isJsonOutOverrided(Class<?> clazz) {
        if (clazz == StructRow.class)
            return false;
        return true;
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        BeanJsonDumper dumper = new BeanJsonDumper(out, true);
        dumper.dumpObject(this.getClass(), this, true);
    }

    public boolean partialEquals(StructRow o) {
        return true;
    }

    public void assign(StructRow o) {
        this.creationDate = o.creationDate;
        this.lastModified = o.lastModified;
        this.version = o.version;
    }

}
