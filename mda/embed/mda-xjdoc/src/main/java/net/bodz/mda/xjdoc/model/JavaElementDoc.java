package net.bodz.mda.xjdoc.model;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.i18n.dom.XiString;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.sugar.Tooling;
import net.bodz.bas.text.flatf.IFlatfOutput;
import net.bodz.bas.text.flatf.IFlatfSerializable;
import net.bodz.bas.text.flatf.ISectionHandler;
import net.bodz.mda.xjdoc.taglib.AbstractTagLibrary;
import net.bodz.mda.xjdoc.taglib.ITagLibrary;
import net.bodz.mda.xjdoc.tagtype.DefaultTagType;
import net.bodz.mda.xjdoc.tagtype.ITagType;

public class JavaElementDoc
        implements IJavaElementDoc, IFlatfSerializable {

    String name;
    iString text;
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
    public iString getLabel() {
        iString label = (iString) getTag("name");
        return label;
    }

    @Override
    public void setLabel(iString label) {
        setTag("name", label);
    }

    @Override
    public iString getText() {
        return text;
    }

    @Override
    public void setText(iString text) {
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
    public void writeObject(IFlatfOutput out, IOptions options)
            throws IOException {
        ITagLibrary taglib = null;
        if (options != null)
            taglib = options.get(ITagLibrary.class);
        if (taglib == null)
            throw new IllegalUsageException("Book is not provided in the options.");

        if (text != null)
            out.attribute(".", text);
        for (Entry<String, Object> entry : tagMap.entrySet()) {
            String tagName = entry.getKey();
            Object tagValue = entry.getValue();

            ITagType tagType = taglib.getTagType(tagName);
            if (tagType == null) {
                // throw new IllegalUsageException("Undefined tag: " + tagName);
                tagType = DefaultTagType.getInstance();
            }

            try {
                tagType.writeEntries(out, tagName, tagValue, options);
            } catch (RuntimeException e) {
                throw new RuntimeException("Failed to write ff-entry for tag " + tagName + " := " + tagValue, e);
            }
        }
    }

    @Override
    public ISectionHandler getSectionHandler(String sectionName, IOptions options) {
        ITagLibrary taglib = AbstractTagLibrary.fromOptions(options);
        return new FlatfHandler(taglib, options);
    }

    protected class FlatfHandler
            implements ISectionHandler {

        final ITagLibrary taglib;
        final IOptions options;

        public FlatfHandler(ITagLibrary taglib, IOptions options) {
            if (taglib == null)
                throw new NullPointerException("taglib");
            this.taglib = taglib;
            this.options = options;
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
                iString text = XiString.parseMultiLangString(string);
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
            if (tagType == null)
                tagType = DefaultTagType.getInstance();

            Object cont = getTag(tagName);
            Object tagValue;

            try {
                tagValue = tagType.parseEntry(cont, suffix, string, options);
            } catch (RuntimeException e) {
                throw new RuntimeException("Failed to parse ff-entry: " + string);
            }

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
