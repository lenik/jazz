package net.bodz.bas.ui.css3;

import java.util.Map;
import java.util.regex.Pattern;

import net.bodz.bas.c.string.StringEscape;
import net.bodz.bas.c.string.StringPart;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.map.AbstractParsedMap;
import net.bodz.bas.ui.style.IColor;

public abstract class AbstractCss3Properties
        extends AbstractParsedMap<String, String>
        implements ICss3Properties {

    private static final long serialVersionUID = 1L;

    public AbstractCss3Properties(Map<String, String> _orig) {
        super(_orig);
    }

    public static <E extends Enum<E>> E parseEnum(String str, Class<E> enumClass) {
        if (str == null)
            return null;
        return Enum.valueOf(enumClass, str);
    }

    public static Boolean parseBoolean(String str) {
        if (str == null)
            return null;
        return Boolean.valueOf(str);
    }

    /**
     * @throws NumberFormatException
     */
    public static Integer parseInteger(String str) {
        if (str == null)
            return null;
        return Integer.valueOf(str);
    }

    /**
     * @throws NumberFormatException
     */
    public static Float parseFloat(String str) {
        if (str == null)
            return null;
        return Float.valueOf(str);
    }

    public static Character parseChar(String str) {
        if (str == null)
            return null;
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

    public static ICss3Length parseCssLength(String str) {
        if (str == null)
            return null;
        switch (str) {
        case "auto":
        case "none":
            return Css3Length.NaN;
        }

        if (str.endsWith("%"))
            str = StringPart.chop(str).trim();
        return Css3Length.parseOrNaN(str);
    }

    public static ICss3Int parseCssInt(String str) {
        if (str == null)
            return null;
        try {
            return Css3Int.parse(str);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    public static IColor parseColor(String str) {
        if (str == null)
            return null;
        try {
            return WebColor.parse(str);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    @Override
    public void setMargin(ICss3Length marginTop, ICss3Length marginRight, ICss3Length marginBottom,
            ICss3Length marginLeft) {
        if (marginRight == null)
            marginRight = marginTop;
        if (marginBottom == null)
            marginBottom = marginTop;
        if (marginLeft == null)
            marginLeft = marginRight;
        setMarginTop(marginTop);
        setMarginRight(marginRight);
        setMarginBottom(marginBottom);
        setMarginLeft(marginLeft);
    }

    @Override
    public void setPadding(ICss3Length paddingTop, ICss3Length paddingRight, ICss3Length paddingBottom,
            ICss3Length paddingLeft) {
        if (paddingRight == null)
            paddingRight = paddingTop;
        if (paddingBottom == null)
            paddingBottom = paddingTop;
        if (paddingLeft == null)
            paddingLeft = paddingRight;
        setPaddingTop(paddingTop);
        setPaddingRight(paddingRight);
        setPaddingBottom(paddingBottom);
        setPaddingLeft(paddingLeft);
    }

    @Override
    public void setBorder(Border borderTop, Border borderRight, Border borderBottom, Border borderLeft) {
        if (borderRight == null)
            borderRight = borderTop;
        if (borderBottom == null)
            borderBottom = borderTop;
        if (borderLeft == null)
            borderLeft = borderRight;
        setBorderTop(borderTop);
        setBorderRight(borderRight);
        setBorderBottom(borderBottom);
        setBorderLeft(borderLeft);
    }

    @Override
    public void setBorderRadius(ICss3Length borderTopLeftRadius, ICss3Length borderTopRightRadius,
            ICss3Length borderBottomRightRadius, ICss3Length borderBottomLeftRadius) {
        if (borderTopRightRadius == null)
            borderTopRightRadius = borderTopLeftRadius;
        if (borderBottomRightRadius == null)
            borderBottomRightRadius = borderTopLeftRadius;
        if (borderBottomLeftRadius == null)
            borderBottomLeftRadius = borderTopRightRadius;
        setBorderTopLeftRadius(borderTopLeftRadius);
        setBorderTopRightRadius(borderTopRightRadius);
        setBorderBottomRightRadius(borderBottomRightRadius);
        setBorderBottomLeftRadius(borderBottomLeftRadius);
    }

    private static final Pattern commentPattern;
    static {
        commentPattern = Pattern.compile(//
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
    public void parseCss(String script)
            throws ParseException {
        if (script == null)
            throw new NullPointerException("script");
        script = commentPattern.matcher(script).replaceAll("");

        for (String statement : script.split(";")) {

            statement = statement.trim();
            if (statement.isEmpty())
                continue;

            int colon = statement.indexOf(':');
            if (colon == -1)
                throw new ParseException("No colon in the statement: " + statement);

            String key = statement.substring(0, colon).trim();
            String value = statement.substring(colon + 1).trim();

            put(key, value);
        }
    }

}