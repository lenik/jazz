package net.bodz.bas.i18n.dom;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import net.bodz.bas.c.string.StringHtml;
import net.bodz.bas.i18n.LocaleColo;

public class TreeMapDomainString
        extends TreeMap<String, String>
        implements DomainString {

    private static final long serialVersionUID = 1L;

    public TreeMapDomainString() {
        super();
    }

    public TreeMapDomainString(Map<? extends String, ? extends String> m) {
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
        return ParaLangUtil.formatParaLangString(this);
    }

    @Override
    public String toParaLangString(String separator) {
        return ParaLangUtil.formatParaLangString(this, separator);
    }

    @Override
    public String toMultiLangString() {
        return null;
    }

    @Override
    public String toMultiLangString(String langSeparator, String lineSeparator) {
        return null;
    }

    @Override
    public DomainString append(DomainString other) {
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
    public DomainString concat(DomainString other) {
        TreeMapDomainString copy = clone();
        return copy.append(other);
    }

    @Override
    public DomainString join(DomainString other) {
        return null;
    }

    @Override
    public String dumpContent() {
        return null;
    }

    @Override
    public TreeMapDomainString clone() {
        return new TreeMapDomainString(this);
    }

}
