package net.bodz.lily.concrete;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.time.ZonedDateTime;
import java.util.Map;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.LoaderException;
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
import net.bodz.bas.meta.cache.Derived;
import net.bodz.bas.meta.decl.Priority;
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
import net.bodz.lily.meta.CriteriaClass;

@CriteriaClass(StructRowCriteriaBuilder.class)
public abstract class StructRow
        implements
            IContent,
            Serializable,
            IVarMapForm,
            IJsonForm,
            Cloneable {

    private static final long serialVersionUID = 1L;

    // V = creation, lastmod, version
    private ZonedDateTime creationDate = ZonedDateTime.now();
    private ZonedDateTime lastModifiedDate = creationDate;
    private int version;

    public StructRow() {
    }

    /** ⇱ Implementation Of {@link IContent}. */
    /* _____________________________ */static section.iface __CONTENT__;

    /**
     * 信息的创建时间。
     *
     * @label Creation Date
     * @label.zh 创建时间
     */
    @FormInput(readOnly = true)
    @OfGroup({ StdGroup.Content.class, StdGroup.Status.class })
    @Priority(-100 + 0)
    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        if (creationDate == null)
            throw new NullPointerException("creationDate");
        this.creationDate = creationDate;
    }

    @Derived
    @DetailLevel(DetailLevel.HIDDEN)
    @OfGroup({ StdGroup.Content.class, StdGroup.Status.class })
    @Override
    public long getCreationTime() {
        return creationDate.toInstant().toEpochMilli();
    }

    public void touch() {
        lastModifiedDate = ZonedDateTime.now();
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
    public ZonedDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(ZonedDateTime lastModified) {
        if (lastModified == null)
            throw new NullPointerException("lastModified");
        this.lastModifiedDate = lastModified;
    }

    @Derived
    @DetailLevel(DetailLevel.HIDDEN)
    @OfGroup({ StdGroup.Content.class, StdGroup.Status.class })
    @Override
    public long getLastModified() {
        return lastModifiedDate.toInstant().toEpochMilli();
    }

    /** ⇱ Implementation Of {@link ICacheControl}. */
    /* _____________________________ */static section.iface __CACHE__;

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
        return CacheRevalidationMode.MUST_REVALIDATE;
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

    /**
     * @label Version
     * @label.zh 版本
     */
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
            throws LoaderException, ParseException {
        VarMapLoader loader = new VarMapLoader();
        loader.load(getClass(), this, map);
    }

    @Override
    public void writeObject(Map<String, Object> map) {
        throw new NotImplementedException();
    }

    /** ⇱ Implementation Of {@link IJsonForm}. */
    /* _____________________________ */static section.iface __JSON__;

    protected Boolean overrided_jsonIn = false;
    protected Boolean overrided_jsonOut = false;

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
            creationDate = o.getZonedDateTime("creationDate", creationDate);
            lastModifiedDate = o.getZonedDateTime("lastModifiedDate", lastModifiedDate);
            version = o.getInt("version", version);
        }
    }

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
        BeanJsonDumper dumper = new BeanJsonDumper(out);
        dumper.dump(this);
    }

    public boolean partialEquals(StructRow o) {
        return true;
    }

    public void assign(StructRow o) {
        this.creationDate = o.creationDate;
        this.lastModifiedDate = o.lastModifiedDate;
        this.version = o.version;
    }

}
