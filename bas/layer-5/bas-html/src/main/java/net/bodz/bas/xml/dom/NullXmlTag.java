package net.bodz.bas.xml.dom;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class NullXmlTag
        implements IXmlTag {

    @Override
    public XmlNodeType getType() {
        return XmlNodeType.ELEMENT;
    }

    @Override
    public IXmlTag getParent() {
        return null;
    }

    @Override
    public IXmlNode attach(IXmlTag parent) {
        return this;
    }

    @Override
    public IXmlNode detach() {
        return this;
    }

    @Override
    public Collection<? extends IXmlNode> getChildren() {
        return Collections.emptyList();
    }

    @Override
    public int getPosition() {
        return 0;
    }

    @Override
    public IXmlTag getRoot() {
        return this;
    }

    @Override
    public String getTagName() {
        return null;
    }

    @Override
    public Map<String, String> getAttributeMap() {
        return Collections.emptyMap();
    }

    @Override
    public boolean isVoid() {
        return false;
    }

    @Override
    public IXmlTag _name(String tagName) {
        return this;
    }

    @Override
    public IXmlTag attr(String name, String value) {
        return this;
    }

    @Override
    public IXmlTag attr(String name, Object value) {
        return this;
    }

    @Override
    public void at(int position) {
    }

    @Override
    public boolean remove(IXmlNode child) {
        return false;
    }

    @Override
    public IXmlTag insert(IXmlNode child) {
        return this;
    }

    @Override
    public IXmlTag insert(String tagName) {
        return this;
    }

    @Override
    public XmlPI pi(String target) {
        return new XmlPI(this, target);
    }

    @Override
    public IXmlTag text(String content, String nullVerbatim) {
        return this;
    }

    @Override
    public IXmlTag text(String content) {
        return this;
    }

    @Override
    public IXmlTag text(Object content) {
        return this;
    }

    @Override
    public IXmlTag text(String format, Object... args) {
        return this;
    }

    @Override
    public void print(String s) {
    }

    @Override
    public void print(Object s) {
    }

    @Override
    public void println(String s) {
    }

    @Override
    public void println(Object s) {
    }

    @Override
    public IXmlTag comment(String content) {
        return this;
    }

    @Override
    public IXmlTag cdata(String content) {
        return this;
    }

    @Override
    public IXmlTag verbatim(String content) {
        return this;
    }

}
