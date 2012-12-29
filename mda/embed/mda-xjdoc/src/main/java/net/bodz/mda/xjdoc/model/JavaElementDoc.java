package net.bodz.mda.xjdoc.model;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.i18n.dom.DomainString;
import net.bodz.bas.i18n.dom.XDomainString;
import net.bodz.bas.rtx.INegotiation;
import net.bodz.bas.sugar.Tooling;
import net.bodz.bas.text.flatf.IFlatfOutput;
import net.bodz.bas.text.flatf.IFlatfSerializable;
import net.bodz.bas.text.flatf.ISectionHandler;
import net.bodz.mda.xjdoc.tags.ITagLibrary;
import net.bodz.mda.xjdoc.tags.ITagType;
import net.bodz.mda.xjdoc.tags.TagLibrary;

public class JavaElementDoc
        implements IJavaElementDoc, IFlatfSerializable {

    String name;
    DomainString text;
    Map<String, Object> tagMap = new LinkedHashMap<String, Object>();

    public JavaElementDoc() {
    }

    public JavaElementDoc(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        if (name == null)
            throw new NullPointerException("name");
        this.name = name;
    }

    @Override
    public DomainString getDisplayName() {
        DomainString displayName = (DomainString) getTag("name");
        return displayName;
    }

    @Override
    public void setDisplayName(DomainString displayName) {
        setTag("name", displayName);
    }

    @Override
    public DomainString getText() {
        return text;
    }

    @Override
    public void setText(DomainString text) {
        this.text = text;
    }

    @Override
    public Object getTag(String tagName) {
        return tagMap.get(tagName);
    }

    @Override
    public <T> T getTag(String tagName, Class<T> tagValueType) {
        Object _value = tagMap.get(tagName);
        if (_value == null)
            return null;
        T value = tagValueType.cast(_value);
        return value;
    }

    @Override
    public void setTag(String tagName, Object tagValue) {
        tagMap.put(tagName, tagValue);
    }

    @Override
    public Object removeTag(String tagName) {
        return tagMap.remove(tagName);
    }

    @Override
    public Map<String, Object> getTagMap() {
        return tagMap;
    }

    // --o IFlatfSerializable

    @Override
    public void writeObject(IFlatfOutput out, INegotiation negotiation)
            throws IOException {
        ITagLibrary taglib = null;
        if (negotiation != null)
            taglib = negotiation.get(ITagLibrary.class);
        if (taglib == null)
            throw new IllegalUsageException("Book is not provided in the negotiation.");

        if (text != null)
            out.attribute(".", text);
        for (Entry<String, Object> entry : tagMap.entrySet()) {
            String tagName = entry.getKey();
            Object tagValue = entry.getValue();
            ITagType tagType = taglib.getTagType(tagName);
            tagType.writeEntries(out, tagName, tagValue, negotiation);
        }
    }

    @Override
    public ISectionHandler getSectionHandler(String sectionName, INegotiation negotiation) {
        ITagLibrary taglib = TagLibrary.getInstance(negotiation);
        return new FlatfHandler(taglib, negotiation);
    }

    protected class FlatfHandler
            implements ISectionHandler {

        final ITagLibrary taglib;
        final INegotiation negotiation;

        public FlatfHandler(ITagLibrary taglib, INegotiation negotiation) {
            if (taglib == null)
                throw new NullPointerException("taglib");
            this.taglib = taglib;
            this.negotiation = negotiation;
        }

        @Override
        public boolean pi(String command, String data)
                throws ParseException {
            return processInstruction(command, data);
        }

        @Override
        public void sectionBegin(String name) {
        }

        @Override
        public void sectionEnd(String name) {
        }

        @Override
        public void attribute(String name, String string)
                throws ParseException {
            if (".".equals(name)) {
                DomainString text = XDomainString.parseParaLang(string);
                setText(text);
                return;
            }

            String tagName, suffix;
            int dot = name.indexOf('.');
            if (dot == -1) {
                tagName = name;
                suffix = null;
            } else {
                tagName = name.substring(0, dot);
                suffix = name.substring(dot + 1);
            }
            ITagType tagType = taglib.getTagType(tagName);
            Object cont = getTag(tagName);
            Object tagValue = tagType.parseEntry(cont, suffix, string, negotiation);
            setTag(tagName, tagValue);
        }

    }

    protected boolean processInstruction(String command, String data)
            throws ParseException {
        return false;
    }

    @Override
    public <T> T as(Class<T> decoratedType) {
        return new Tooling(this).getWrapper(decoratedType);
    }

}
