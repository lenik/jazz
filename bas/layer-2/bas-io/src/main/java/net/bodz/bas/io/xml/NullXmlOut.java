package net.bodz.bas.io.xml;

import java.util.Map;

public class NullXmlOut
        implements IXmlOut {

    @Override
    public String getTagName() {
        return null;
    }

    @Override
    public IXmlOut getParent() {
        return null;
    }

    @Override
    public IXmlOut begin(String name) {
        return this;
    }

    @Override
    public IXmlOut end() {
        return this;
    }

    @Override
    public IXmlOut attrs(Map<String, ?> attributes) {
        return this;
    }

    @Override
    public IXmlOut attr(String name, String value) {
        return this;
    }

    @Override
    public IXmlOut attr(String name, Object value) {
        return this;
    }

    @Override
    public IXmlOut id(String id) {
        return this;
    }

    @Override
    public IXmlOut text(String str) {
        return this;
    }

    @Override
    public IXmlOut text(Object str) {
        return null;
    }

    @Override
    public IXmlOut textln(String str) {
        return this;
    }

    @Override
    public IXmlOut textln(Object str) {
        return null;
    }

    @Override
    public IXmlOut cdata(String cdata) {
        return this;
    }

    @Override
    public IXmlOut pi(String target, String data) {
        return this;
    }

    @Override
    public IXmlOut comment(String str) {
        return this;
    }

    @Override
    public void verbatim(String str) {
    }

    @Override
    public void indent(int level) {
    }

    @Override
    public void flush(boolean sync) {
    }

    @Override
    public void flush() {
    }

    @Override
    public void close() {
    }

    @Override
    public boolean isClosed() {
        return false;
    }

}
