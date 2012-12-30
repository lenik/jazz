package net.bodz.bas.i18n.dom;

import java.util.Map.Entry;

import net.bodz.bas.c.string.StringHtml;
import net.bodz.bas.c.string.StringPart;
import net.bodz.bas.i18n.LocaleColo;

public class XiString
        extends XDomainNode<XiString, String>
        implements iString, Cloneable {

    public XiString() {
        super(null, null);
    }

    public XiString(String value) {
        super(null, value);
    }

    public XiString(String domain, String value) {
        super(domain, value);
    }

    public XiString(String domain, String value, XiString... follows) {
        super(domain, value, follows);
    }

    protected XiString(XiString other) {
        super(other);
    }

    public static XiString of(String plainString) {
        return new XiString(plainString);
    }

    @Override
    protected XiString createNode(String domain, String value) {
        return new XiString(domain, value);
    }

    @Override
    public XiString clone() {
        return new XiString(this);
    }

    /**
     * Get locale string.
     */
    @Override
    public String toString() {
        String path = LocaleColo.getInstance().getPath();
        String leaf = getNearest(path);
        return leaf;
    }

    @Override
    public String toPlainText() {
        String str = toString();
        return StringHtml.toPlain(str);
    }

    /**
     * A para-lang string is formatted as:
     * 
     * <pre>
     * string for default-locale
     * 
     * &lt;p lang="LOCALE1"&gt; 
     *      string for locale1...
     * 
     * &lt;p lang="LOCALE2"&gt; 
     *      string for locale2...
     * </pre>
     * 
     * @see ParaLangString#parse(iString, String)
     */
    public static XiString parseParaLangString(String plText) {
        XiString ds = new XiString();
        ParaLangString.parse(ds, plText);
        return ds;
    }

    @Override
    public String toParaLangString() {
        return toParaLangString("\n");
    }

    @Override
    public String toParaLangString(String separator) {
        return ParaLangString.format(this, separator);
    }

    /**
     * A multi-lang string is formatted as:
     * 
     * <pre>
     * "default-locale"
     * LOCALE1 "string for locale1"
     *         "more..."
     * LOCALE2 "string for locale2"
     *         "more..."
     * </pre>
     * 
     * @param mlstr
     *            multi-lang string to be parsed.
     * @return <code>null</code> iif <code>mlstr</code> is <code>null</code>.
     */
    public static XiString parseMultiLangString(String mlstr) {
        if (mlstr == null)
            return null;
        MultiLangStringParser parser = new MultiLangStringParser();
        return parser.parse(mlstr);
    }

    @Override
    public String toMultiLangString() {
        return toMultiLangString("\n", null);
    }

    @Override
    public String toMultiLangString(String langSeparator, String lineSeparator) {
        MultiLangStringFormatter formatter = new MultiLangStringFormatter();
        formatter.setDomainSeparator(langSeparator);
        formatter.setLineSeparator(lineSeparator);
        return formatter.format(this);
    }

    @Override
    public iString append(iString other) {
        _join(other, false, this);
        return this;
    }

    @Override
    public XiString concat(iString other) {
        XiString out = clone();
        _join(other, false, out);
        return out;
    }

    @Override
    public XiString join(iString other) {
        XiString out = clone();
        _join(other, true, out);
        return out;
    }

    void _join(iString other, boolean bestFits, XiString output) {
        if (other == null)
            throw new NullPointerException("other");

        if (bestFits) { // find which domains are occurred in this only.
            for (Entry<String, XiString> entry : this) {
                String d1 = entry.getKey();
                XiString node1 = entry.getValue();
                if (node1.value == null)
                    continue;
                if (other.get(d1) != null)
                    continue;

                String fallback2 = other.getNearest(d1);
                if (fallback2 != null)
                    if (output == this)
                        node1.value += fallback2;
                    else
                        output.put(d1, node1.value + fallback2);
            }
        }

        for (Entry<String, String> entry : other.entrySet()) {
            String d2 = entry.getKey();
            String s2 = entry.getValue();
            if (s2 == null)
                continue;

            String fallback1 = null;
            if (bestFits)
                fallback1 = getNearest(d2);

            XiString outNode = output.create(d2, fallback1);
            if (outNode.value == null)
                outNode.value = s2;
            else
                outNode.value += s2;
        }
    }

    @Override
    public iString headPar() {
        return DomainStrings.headPar(this);
    }

    @Override
    public iString tailPar() {
        return DomainStrings.tailPar(this);
    }

    @Override
    public String getHeadPar() {
        String str = toString();
        return StringPart.getHeadPar(str);
    }

    @Override
    public String getTailPar() {
        String str = toString();
        return StringPart.getTailPar(str);
    }

}
