package net.bodz.bas.gui.css3;

import java.util.regex.Pattern;

import net.bodz.bas.c.string.StringEscape;
import net.bodz.bas.err.ParseException;

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

    protected Character getCharProperty(String key, Character inherited, boolean inheritByDefault) {
        String str = getProperty(key);
        if (str == null)
            return inheritByDefault ? inherited : null;
        if ("inherited".equals(str))
            return inherited;
        if (str.startsWith("\'") && str.endsWith("\'")) {
            str = str.substring(1, str.length() - 1);
            try {
                str = StringEscape.unescapeJava(str);
            } catch (ParseException e) {
                throw new IllegalArgumentException("Failed to parse char literal: " + str, e);
            }
            if (str.length() == 0)
                return null;
            else
                return str.charAt(0);
        } else {
            throw new IllegalArgumentException("Bad char literal: " + str);
        }
    }

    static Pattern cCommentPattern;
    static {
        cCommentPattern = Pattern.compile(//
                "/\\* .*? \\*/", Pattern.COMMENTS | Pattern.DOTALL);
    }

    /**
     * Parse CSS-like style script.
     * <p>
     * 
     * Example:
     * 
     * <pre>
     * color: red; 
     * font-size: large;
     * </pre>.
     */
    public void parse(String script)
            throws ParseException {
        if (script == null)
            throw new NullPointerException("script");
        script = cCommentPattern.matcher(script).replaceAll("");

        for (String statement : script.split(";")) {

            statement = statement.trim();
            if (statement.isEmpty())
                continue;

            int colon = statement.indexOf(':');
            if (colon == -1)
                throw new ParseException("No colon in the statement: " + statement);

            String key = statement.substring(0, colon).trim();
            String value = statement.substring(colon + 1).trim();

            setProperty(key, value);
        }
    }

}
