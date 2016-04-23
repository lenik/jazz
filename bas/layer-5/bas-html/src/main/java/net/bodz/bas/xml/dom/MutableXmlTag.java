package net.bodz.bas.xml.dom;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.bodz.bas.meta.decl.ThreadUnsafe;

@SuppressWarnings("unchecked")
@ThreadUnsafe
public class MutableXmlTag<self_t extends IXmlTag>
        extends AbstractXmlNode<self_t>
        implements IXmlTag {

    private String tagName;
    private Map<String, String> attributes;
    private List<IXmlNode> children;
    private int insertPoint;

    protected MutableXmlTag(IXmlTag parent) {
        super(parent);
        attributes = new LinkedHashMap<String, String>();
        children = new ArrayList<IXmlNode>();
    }

    public MutableXmlTag(IXmlTag parent, String tagName) {
        this(parent);
        this.tagName = tagName;
    }

    @Override
    public XmlNodeType getType() {
        return XmlNodeType.ELEMENT;
    }

    @Override
    public IXmlTag getRoot() {
        IXmlTag parent = getParent();
        if (parent == null)
            return this;
        else
            return parent.getRoot();
    }

    @Override
    public String getTagName() {
        return tagName;
    }

    @Override
    public Map<String, String> getAttributeMap() {
        return attributes;
    }

    @Override
    public boolean isTerm() {
        return false;
    }

    @Override
    public Collection<? extends IXmlNode> getChildren() {
        return children;
    }

    public self_t _name(String tagName) {
        this.tagName = tagName;
        return (self_t) this;
    }

    @Override
    public self_t attr(String name, String value) {
        attributes.put(name, value);
        return (self_t) this;
    }

    @Override
    public final self_t attr(String name, Object value) {
        String str = value == null ? null : value.toString();
        return attr(name, str);
    }

    @Override
    public void at(int position) {
        if (position < 0)
            throw new IllegalArgumentException("Position is negative: " + position);
        if (position > children.size())
            position = children.size();
        insertPoint = position;
    }

    @Override
    public boolean remove(IXmlNode child) {
        int index = children.indexOf(child);
        if (index == -1)
            return false;
        children.remove(index);
        insertPoint = index;
        return true;
    }

    @Override
    public self_t insert(IXmlNode child) {
        if (insertPoint > children.size())
            insertPoint = children.size();
        children.add(insertPoint, child);
        insertPoint++;
        return (self_t) this;
    }

    @Override
    public IXmlTag insert(String tagName) {
        return new XmlTag(this, tagName);
    }

    @Override
    public XmlPI pi(String target) {
        return new XmlPI(this, target);
    }

    @Override
    public self_t text(String text, String nullVerbatim) {
        XmlText node = new XmlText(this, text);
        node.setNullVerbatim(nullVerbatim);
        return (self_t) this;
    }

    @Override
    public self_t text(String text) {
        new XmlText(this, text);
        return (self_t) this;
    }

    @Override
    public final self_t text(Object content) {
        String text = content == null ? null : content.toString();
        return text(text);
    }

    @Override
    public final self_t textf(String format, Object... args) {
        String text = String.format(format, args);
        return text(text);
    }

    @Override
    public final void print(String s) {
        text(s);
    }

    @Override
    public final void print(Object s) {
        print(String.valueOf(s));
    }

    @Override
    public final void println(String s) {
        text(s + "\n");
    }

    @Override
    public final void println(Object s) {
        println(String.valueOf(s));
    }

    @Override
    public self_t comment(String content) {
        new XmlComment(this, content);
        return (self_t) this;
    }

    @Override
    public self_t cdata(String content) {
        new XmlCData(this, content);
        return (self_t) this;
    }

    @Override
    public self_t verbatim(String content) {
        new XmlVerbatim(this, content);
        return (self_t) this;
    }

}
