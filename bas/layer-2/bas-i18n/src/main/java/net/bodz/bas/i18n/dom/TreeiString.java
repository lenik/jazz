package net.bodz.bas.i18n.dom;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import net.bodz.bas.c.string.StringHtml;
import net.bodz.bas.c.string.StringPart;
import net.bodz.bas.i18n.LocaleColo;

public class TreeiString
        extends TreeMap<String, String>
        implements iString {

    private static final long serialVersionUID = 1L;

    public TreeiString() {
        super();
    }

    public TreeiString(Map<? extends String, ? extends String> m) {
        super(m);
    }

    @Override
    public String get(String path) {
        return null;
    }

    @Override
    public String getNearest(String path) {
        Entry<String, String> floor = floorEntry(path);
        return null;
    }

    @Override
    public String pull(String path) {
        return getNearest(path);
    }

    @Override
    public String remove(String path) {
        return super.remove(path);
    }

    /**
     * Get locale string.
     */
    @Override
    public String toString() {
        String path = LocaleColo.getInstance().getPath();
        String leaf = get(path);
        return leaf;
    }

    @Override
    public String toPlainText() {
        String str = toString();
        return StringHtml.toPlain(str);
    }

    @Override
    public String toParaLangString() {
        return toParaLangString("\n");
    }

    @Override
    public String toParaLangString(String separator) {
        return iString.fn.toParaLangString(this, separator);
    }

    @Override
    public String toMultiLangString() {
        return toMultiLangString("\n", null);
    }

    @Override
    public String toMultiLangString(String langSeparator, String lineSeparator) {
        return iString.fn.toMultiLangString(this, langSeparator, lineSeparator);
    }

    @Override
    public iString append(iString other) {
        for (Entry<String, String> entry : other.entrySet()) {
            String domain = entry.getKey();
            String value = entry.getValue();
            String cat = get(domain);
            if (cat == null)
                cat = value;
            else
                cat += value;
            put(domain, cat);
        }
        return this;
    }

    @Override
    public String dumpContent() {
        StringBuilder sb = new StringBuilder();
        for (Entry<String, String> entry : entrySet()) {
            String domainPath = entry.getKey();
            String value = entry.getValue();
            if (value == null) {
                // sb.append(domainPath + ": (intermediate)\n");
                continue;
            }
            sb.append(domainPath + ": " + value);
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public iString concat(iString other) {
        TreeiString copy = clone();
        return copy.append(other);
    }

    @Override
    public iString join(iString other) {
        return DomainStrings.join(this, other);
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

    @Override
    public TreeiString clone() {
        return new TreeiString(this);
    }

}
