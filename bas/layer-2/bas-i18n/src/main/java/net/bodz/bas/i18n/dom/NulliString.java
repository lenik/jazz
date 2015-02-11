package net.bodz.bas.i18n.dom;

import java.util.Collections;
import java.util.Map.Entry;
import java.util.Set;

public class NulliString
        implements iString {

    private NulliString() {
    }

    @Override
    public NulliString clone() {
        return this;
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public String get(String path) {
        return null;
    }

    @Override
    public String getNearest(String path) {
        return null;
    }

    @Override
    public String put(String path, String value) {
        return null;
    }

    @Override
    public String pull(String path) {
        return null;
    }

    @Override
    public String remove(String path) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set<String> keySet() {
        return Collections.emptySet();
    }

    @Override
    public Set<Entry<String, String>> entrySet() {
        return Collections.emptySet();
    }

    @Override
    public String toPlainText() {
        return null;
    }

    @Override
    public String toParaLangString() {
        return null;
    }

    @Override
    public String toParaLangString(String separator) {
        return null;
    }

    @Override
    public String toMultiLangString() {
        return null;
    }

    @Override
    public String toMultiLangString(String langSeparator, String lineSeparator) {
        MultiLangStringFormatter formatter = new MultiLangStringFormatter();
        formatter.setDomainSeparator(langSeparator);
        formatter.setLineSeparator(lineSeparator);
        return formatter.format(this);
    }

    @Override
    public String dumpContent() {
        return null;
    }

    @Override
    public iString append(iString other) {
        return other;
    }

    @Override
    public iString concat(iString other) {
        return other;
    }

    @Override
    public iString join(iString other) {
        return other;
    }

    @Override
    public iString headPar() {
        return null;
    }

    @Override
    public iString tailPar() {
        return null;
    }

    @Override
    public String getHeadPar() {
        return null;
    }

    @Override
    public String getTailPar() {
        return null;
    }

    public static final NulliString INSTANCE = new NulliString();

}
