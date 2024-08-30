package net.bodz.mda.xjdoc.model;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.c.type.TypeParam;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.t.coll.IContainer;
import net.bodz.bas.t.variant.MutableVariant;

public abstract class AbstractMultiDocTag<E>
        extends AbstractDocTag<IContainer<E>> {

    protected final Class<E> elementType;

    public AbstractMultiDocTag() {
        this(null);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public AbstractMultiDocTag(IContainer<E> data) {
        super((Class) IContainer.class, data);
        this.elementType = TypeParam.infer1(getClass(), AbstractMultiDocTag.class, 0);
    }

    public boolean isIndexValid(int index) {
        return getContainer().isIndexValid(index);
    }

    @Override
    public IContainer<E> getContainer() {
        return data;
    }

    @Override
    public Class<?> getElementType() {
        return elementType;
    }

    @Override
    public Object toScalar() {
        if (data == null)
            return null;
        if (data.isEmpty())
            return null;
        return data.getFirst();
    }

    @Override
    public String[] getStringArray() {
        IContainer<?> a = getContainer();
        int n = a.size();
        String[] sv = new String[n];
        for (int i = 0; i < n; i++)
            sv[i] = Nullables.toString(a.get(i));
        return sv;
    }

    @Override
    public Integer[] getIntArray() {
        IContainer<?> a = getContainer();
        int n = a.size();
        Integer[] b = new Integer[n];
        for (int i = 0; i < n; i++) {
            Object obj = a.get(i);
            if (obj != null) {
                int item = new MutableVariant(obj).getInt();
                b[i] = item;
            }
        }
        return b;
    }

    @Override
    public Long[] getLongArray() {
        IContainer<?> a = getContainer();
        int n = a.size();
        Long[] b = new Long[n];
        for (int i = 0; i < n; i++) {
            Object obj = a.get(i);
            if (obj != null) {
                long item = new MutableVariant(obj).getLong();
                b[i] = item;
            }
        }
        return b;
    }

    @Override
    public Boolean[] getBoolArray() {
        IContainer<?> a = getContainer();
        int n = a.size();
        Boolean[] b = new Boolean[n];
        for (int i = 0; i < n; i++) {
            Object obj = a.get(i);
            if (obj != null) {
                boolean item = new MutableVariant(obj).getBoolean();
                b[i] = item;
            }
        }
        return b;
    }

    @Override
    public String[] getStringArray(String fallback) {
        IContainer<?> a = getContainer();
        int n = a.size();
        String[] sv = new String[n];
        for (int i = 0; i < n; i++) {
            Object obj = a.get(i);
            if (obj != null) {
                sv[i] = Nullables.toString(obj);
            } else {
                sv[i] = fallback;
            }
        }
        return sv;
    }

    @Override
    public int[] getIntArray(int fallback) {
        IContainer<?> a = getContainer();
        int n = a.size();
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            Object obj = a.get(i);
            if (obj != null) {
                int item = new MutableVariant(obj).getInt();
                b[i] = item;
            } else {
                b[i] = fallback;
            }
        }
        return b;
    }

    @Override
    public long[] getLongArray(long fallback) {
        IContainer<?> a = getContainer();
        int n = a.size();
        long[] b = new long[n];
        for (int i = 0; i < n; i++) {
            Object obj = a.get(i);
            if (obj != null) {
                long item = new MutableVariant(a.get(i)).getLong();
                b[i] = item;
            } else {
                b[i] = fallback;
            }
        }
        return b;
    }

    @Override
    public boolean[] getBoolArray(boolean fallback) {
        IContainer<?> a = getContainer();
        int n = a.size();
        boolean[] b = new boolean[n];
        for (int i = 0; i < n; i++) {
            Object obj = a.get(i);
            if (obj != null) {
                boolean item = new MutableVariant(a.get(i)).getBoolean();
                b[i] = item;
            } else {
                b[i] = fallback;
            }
        }
        return b;
    }

    //

    @Override
    public iString getText(int index, iString fallback) {
        if (! isIndexValid(index))
            return fallback;
        Object obj = at(index);
        return (iString) obj;
    }

    @Override
    public String getString(int index, String fallback) {
        if (! isIndexValid(index))
            return fallback;
        Object obj = at(index);
        if (obj == null)
            return null;
        else
            return obj.toString();
    }

    @Override
    public Integer getIntAt(int index)
            throws ParseException {
        if (! isIndexValid(index))
            return null;
        Object obj = at(index);
        if (obj == null)
            return null;
        return new MutableVariant(obj).getInt();
    }

    @Override
    public int getInt(int index, int fallback) {
        if (! isIndexValid(index))
            return fallback;
        Object obj = at(index);
        if (obj == null)
            return fallback;
        return new MutableVariant(obj).getInt(fallback);
    }

    @Override
    public Long getLongAt(int index)
            throws ParseException {
        if (! isIndexValid(index))
            return null;
        Object obj = at(index);
        if (obj == null)
            return null;
        return new MutableVariant(obj).getLong();
    }

    @Override
    public long getLong(int index, long fallback) {
        if (! isIndexValid(index))
            return fallback;
        Object obj = at(index);
        if (obj == null)
            return fallback;
        return new MutableVariant(obj).getLong(fallback);
    }

    @Override
    public Boolean getBool(int index)
            throws ParseException {
        if (! isIndexValid(index))
            return null;
        Object obj = at(index);
        if (obj == null)
            return null;
        return new MutableVariant(obj).getBoolean();
    }

    @Override
    public boolean getBool(int index, boolean fallback) {
        if (! isIndexValid(index))
            return fallback;
        Object obj = at(index);
        if (obj == null)
            return fallback;
        return new MutableVariant(obj).getBoolean(fallback);
    }

    @Override
    public <T extends Enum<T>> T getEnum(Class<T> enumType, int index)
            throws ParseException {
        if (! isIndexValid(index))
            return null;
        Object obj = at(index);
        if (obj == null)
            return null;
        return new MutableVariant(obj).getEnum(enumType);
    }

    @Override
    public <T extends Enum<T>> T getEnum(Class<T> enumType, int index, T fallback) {
        if (! isIndexValid(index))
            return fallback;
        Object obj = at(index);
        if (obj == null)
            return fallback;
        return new MutableVariant(obj).getEnum(enumType, fallback);
    }

}
