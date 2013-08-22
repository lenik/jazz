package net.bodz.bas.rtx;

import net.bodz.bas.err.IllegalUsageException;

public abstract class AbstractOptions
        implements IOptions {

    @Override
    public IOption getOption(Class<?> clazz) {
        String id = clazz.getCanonicalName();
        return getOption(id);
    }

    @Override
    public final <T> T get(Class<T> clazz) {
        String id = clazz.getCanonicalName();
        return get(id, null);
    }

    @Override
    public final <T> T get(Class<T> clazz, T defaultValue) {
        String id = clazz.getCanonicalName();
        return get(id, defaultValue);
    }

    @Override
    public final <T> T get(String id) {
        return get(id, null);
    }

    @Override
    public <T> T get(String id, T defaultValue) {
        IOption parameter = getOption(id);
        if (parameter == null)
            return defaultValue;
        else
            return parameter.getValue();
    }

    @Override
    public int getInt(String id) {
        return getInt(id, 0);
    }

    @Override
    public int getInt(String id, int defaultValue) {
        Object value = get(id);

        if (value instanceof Number)
            return ((Number) value).intValue();

        if (value instanceof String)
            return Integer.parseInt((String) value);

        throw new IllegalUsageException("Not a numeric value: " + value);
    }

    @Override
    public long getLong(String id) {
        return getLong(id, 0L);
    }

    @Override
    public long getLong(String id, long defaultValue) {
        Object value = get(id);

        if (value instanceof Number)
            return ((Number) value).longValue();

        if (value instanceof String)
            return Long.parseLong((String) value);

        throw new IllegalUsageException("Not a numeric value: " + value);
    }

    @Override
    public float getFloat(String id) {
        return getFloat(id, 0.0f);
    }

    @Override
    public float getFloat(String id, float defaultValue) {
        Object value = get(id);

        if (value instanceof Number)
            return ((Number) value).floatValue();

        if (value instanceof String)
            return Float.parseFloat((String) value);

        throw new IllegalUsageException("Not a numeric value: " + value);
    }

    @Override
    public double getDouble(String id) {
        return getDouble(id, 0.0f);
    }

    @Override
    public double getDouble(String id, double defaultValue) {
        Object value = get(id);

        if (value instanceof Number)
            return ((Number) value).doubleValue();

        if (value instanceof String)
            return Double.parseDouble((String) value);

        throw new IllegalUsageException("Not a numeric value: " + value);
    }

    @Override
    public boolean getBoolean(String id) {
        return getBoolean(id, false);
    }

    @Override
    public boolean getBoolean(String id, boolean defaultValue) {
        Object value = get(id);

        if (value instanceof Boolean)
            return ((Boolean) value).booleanValue();

        if (value instanceof String)
            return Boolean.parseBoolean((String) value);

        throw new IllegalUsageException("Not a boolean value: " + value);
    }

    @Override
    public String toString() {
        StringBuilder buf = null;
        for (IOption param : this) {
            if (buf == null)
                buf = new StringBuilder(this.size() * 100);
            else
                buf.append('\n');
            buf.append(param);
        }
        return buf.toString();
    }

    protected abstract AbstractOptions addOption(IOption option);

    public AbstractOptions addOption(String id, Object value) {
        option(id, value);
        return this;
    }

    public <T> AbstractOptions addOption(Class<T> type, T value) {
        option(type, value);
        return this;
    }

    public AbstractOptions addOption(Object typedValue) {
        option(typedValue);
        return this;
    }

    protected void option(String id, Object value) {
        if (id == null)
            throw new NullPointerException("id");
        Option option = new Option(id, value);
        addOption(option);
    }

    protected <T> void option(Class<T> type, T value) {
        if (type == null)
            throw new NullPointerException("type");
        Option option = new Option(type, value);
        addOption(option);
    }

    protected void option(Object typedValue) {
        if (typedValue == null)
            throw new NullPointerException("typedValue");
        Option option = new Option(typedValue);
        addOption(option);
    }

}
