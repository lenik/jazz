package net.bodz.bas.ant;

import net.bodz.bas.jdk6compat.jdk7emul.ClassNotFoundException;
import net.bodz.bas.jdk6compat.jdk7emul.Jdk7Reflect;
import net.bodz.bas.traits.ParserUtil;
import net.bodz.bas.util.exception.ParseException;

/**
 * Utility class to help implement a custom tag in ant-xml.
 * <p>
 * Example:
 * 
 * <pre>
 * &lt;yourtask>
 *     &lt;customtag typename='TYPE'>
 *         VALUE-STRING
 *     &lt;/customtag>
 * &lt;/yourtask>
 * 
 * class YourTask {
 *     
 *     void addConfiguredCustomtag(Parameter parameter) {
 *         String customvalue = parameter.getValue(); 
 *         // do with custom value...
 *     }
 * }
 * </pre>
 */
public class Parameter {

    private Class<?> type = String.class;
    private String text;

    public void setType(Class<?> type) {
        if (type == null)
            throw new NullPointerException("type");
        this.type = type;
    }

    public void setTypeName(String typeName)
            throws ClassNotFoundException {
        if (typeName == null)
            throw new NullPointerException("typeName");
        this.type = Jdk7Reflect.forName(typeName);
    }

    public void setValue(String value) {
        this.text = value;
    }

    public Class<?> getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    public void addText(String text) {
        if (this.text == null)
            this.text = text;
        else
            this.text += text;
    }

    /**
     * @return <code>null</code> if text is <code>null</code>.
     */
    public Object parseValue()
            throws ParseException {
        if (text == null)
            return null;
        return ParserUtil.parse(type, text);
    }

    @Override
    public String toString() {
        return type.getName() + '(' + text + ')';
    }

}
