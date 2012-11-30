package net.bodz.bas.gui.css3;

public abstract class AbstractPropertiesMapper {

    /**
     * @return Trimmed property value.
     */
    public abstract String getProperty(String key);

    public abstract void setProperty(String key, Object value);

    protected Boolean getBooleanProperty(String key, Boolean inherited, boolean inheritByDefault) {
        String str = getProperty(key);
        if (str == null)
            return inheritByDefault ? inherited : null;
        if ("inherited".equals(str))
            return inherited;
        switch (str) {
        case "true":
        case "yes":
        case "1":
            return true;
        case "false":
        case "no":
        case "0":
            return false;
        default:
            throw new IllegalArgumentException("Illegal boolean value: " + str);
        }
    }

    protected Integer getIntegerProperty(String key, Integer inherited, boolean inheritByDefault) {
        String str = getProperty(key);
        if (str == null)
            return inheritByDefault ? inherited : null;
        if ("inherited".equals(str))
            return inherited;
        return Integer.parseInt(str);
    }

    protected Float getFloatProperty(String key, Float inherited, boolean inheritByDefault) {
        String str = getProperty(key);
        if (str == null)
            return inheritByDefault ? inherited : null;
        if ("inherited".equals(str))
            return inherited;
        return Float.parseFloat(str);
    }

    protected <E extends Enum<E>> E getEnumProperty(String key, Class<E> enumClass, E inherited,
            boolean inheritByDefault) {
        String str = getProperty(key);
        if (str == null)
            return inheritByDefault ? inherited : null;
        if ("inherited".equals(str))
            return inherited;
        E val = Enum.valueOf(enumClass, str);
        return val;
    }

}
