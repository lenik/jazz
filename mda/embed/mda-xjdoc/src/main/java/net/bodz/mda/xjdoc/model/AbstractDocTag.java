package net.bodz.mda.xjdoc.model;

import net.bodz.bas.c.type.TypeParam;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.i18n.dom.XiString;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.t.coll.IContainer;
import net.bodz.bas.t.coll.SingleContainer;
import net.bodz.bas.t.variant.MutableVariant;

public abstract class AbstractDocTag<T>
        implements
            IDocTag<T> {

    private String tagName;

    private final Class<T> dataType;
    protected T data;

    public AbstractDocTag() {
        dataType = TypeParam.infer1(getClass(), AbstractDocTag.class, 0);
    }

    public AbstractDocTag(T data) {
        this();
        this.data = data;
    }

    public AbstractDocTag(Class<T> dataType) {
        this.dataType = dataType;
    }

    public AbstractDocTag(Class<T> dataType, T data) {
        this.dataType = dataType;
        this.data = data;
    }

    @Override
    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public Class<? extends T> getDataType() {
        return dataType;
    }

    @Override
    public Class<?> getElementType() {
        return dataType;
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public void setData(T data) {
        this.data = data;
    }

    @Override
    public IContainer<?> getContainer() {
        return SingleContainer.of(data);
    }

    @Override
    public boolean isEmpty() {
        return data == null;
    }

    @Override
    public Object toScalar() {
        return data;
    }

    @Override
    public iString getText() {
        Object obj = toScalar();
        if (obj == null)
            return null;
        if (obj instanceof iString)
            return (iString) obj;
        String str = obj.toString();
        return new XiString(str);
    }

    @Override
    public iString getText(iString fallback) {
        Object obj = toScalar();
        if (obj == null)
            return fallback;
        return (iString) obj;
    }

    @Override
    public String getString() {
        Object obj = toScalar();
        if (obj == null)
            return null;
        return obj.toString();
    }

    @Override
    public String getString(String fallback) {
        Object obj = toScalar();
        if (obj == null)
            return fallback;
        return obj.toString();
    }

    @Override
    public Integer getInt()
            throws ParseException {
        Object obj = toScalar();
        if (obj == null)
            return null;
        return new MutableVariant(obj).getInt();
    }

    @Override
    public int getInt(int fallback) {
        Object obj = toScalar();
        if (obj == null)
            return fallback;
        return new MutableVariant(obj).getInt(fallback);
    }

    @Override
    public Long getLong()
            throws ParseException {
        Object obj = toScalar();
        if (obj == null)
            return null;
        return new MutableVariant(obj).getLong();
    }

    @Override
    public long getLong(long fallback) {
        Object obj = toScalar();
        if (obj == null)
            return fallback;
        return new MutableVariant(obj).getLong(fallback);
    }

    @Override
    public Boolean getBool()
            throws ParseException {
        Object obj = toScalar();
        if (obj == null)
            return null;
        return new MutableVariant(obj).getBoolean();
    }

    @Override
    public boolean getBool(boolean fallback) {
        Object obj = toScalar();
        if (obj == null)
            return fallback;
        return new MutableVariant(obj).getBoolean(fallback);
    }

    @Override
    public <E extends Enum<E>> E getEnum(Class<E> enumType) {
        Object obj = toScalar();
        if (obj == null)
            return null;
        return new MutableVariant(obj).getEnum(enumType);
    }

    @Override
    public <E extends Enum<E>> E getEnum(Class<E> enumType, E fallback) {
        Object obj = toScalar();
        if (obj == null)
            return fallback;
        return new MutableVariant(obj).getEnum(enumType, fallback);
    }

    //

    boolean checkIndexValid(int index) {
        if (index < 0 || index >= 1)
            return false;
        if (data == null)
            if (index == 0)
                return false;
        return true;
    }

    void ensureIndexValid(int index) {
        if (index < 0 || index >= 1)
            throw new IndexOutOfBoundsException();
        if (data == null)
            if (index == 0)
                throw new IndexOutOfBoundsException();
    }

    @Override
    public Object at(int index) {
        ensureIndexValid(index);
        return data;
    }

    @Override
    public Object at(int index, Object fallback) {
        if (! checkIndexValid(index))
            return fallback;
        return data;
    }

    @Override
    public iString getText(int index, iString fallback) {
        ensureIndexValid(index);
        return getText();
    }

    @Override
    public String getString(int index, String fallback) {
        if (! checkIndexValid(index))
            return fallback;
        return getString(fallback);
    }

    @Override
    public Integer getIntAt(int index)
            throws ParseException {
        ensureIndexValid(index);
        return getInt();
    }

    @Override
    public int getInt(int index, int fallback) {
        if (! checkIndexValid(index))
            return fallback;
        return getInt(fallback);
    }

    @Override
    public Long getLongAt(int index)
            throws ParseException {
        ensureIndexValid(index);
        return getLong();
    }

    @Override
    public long getLong(int index, long fallback) {
        if (! checkIndexValid(index))
            return fallback;
        return getLong(fallback);
    }

    @Override
    public Boolean getBool(int index)
            throws ParseException {
        ensureIndexValid(index);
        return getBool();
    }

    @Override
    public boolean getBool(int index, boolean fallback) {
        if (! checkIndexValid(index))
            return fallback;
        return getBool(fallback);
    }

    @Override
    public <E extends Enum<E>> E getEnum(Class<E> enumType, int index)
            throws ParseException {
        ensureIndexValid(index);
        return getEnum(enumType);
    }

    @Override
    public <E extends Enum<E>> E getEnum(Class<E> enumType, int index, E fallback) {
        if (! checkIndexValid(index))
            return fallback;
        return getEnum(enumType, fallback);
    }

    //
    @Override
    public String toString() {
        if (data == null)
            return "(null)";
        else
            return data.toString();
    }

}
