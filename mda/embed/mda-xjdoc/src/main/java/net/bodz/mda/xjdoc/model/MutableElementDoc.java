package net.bodz.mda.xjdoc.model;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;
import java.util.Map.Entry;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.flatf.IFlatfOutput;
import net.bodz.bas.fmt.flatf.IFlatfSerializable;
import net.bodz.bas.fmt.flatf.ISectionHandler;
import net.bodz.bas.i18n.dom.MultiLangStrings;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.sugar.Tooling;
import net.bodz.mda.xjdoc.taglib.AbstractTagLibrary;
import net.bodz.mda.xjdoc.taglib.ITagLibrary;
import net.bodz.mda.xjdoc.tagtype.ITagType;

public class MutableElementDoc
        implements IElementDoc, IFlatfSerializable {

    ITagLibrary tagLibrary;
    iString text = iString.NULL;
    Map<String, Object> tagMap = new LinkedHashMap<String, Object>();

    public MutableElementDoc(ITagLibrary tagLibrary) {
        this.tagLibrary = tagLibrary;
    }

    @Override
    public ITagLibrary getTagLibrary() {
        return tagLibrary;
    }

    public void setTagLibrary(ITagLibrary tagLibrary) {
        this.tagLibrary = tagLibrary;
    }

    @Override
    public iString getText() {
        return text;
    }

    @Override
    public void setText(iString text) {
        if (text == null)
            throw new NullPointerException("text");
        this.text = text;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getTag(String tagName) {
        return (T) tagMap.get(tagName);
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

    @Override
    public Object getFirstTag(String tagName) {
        Object value = getTag(tagName);
        if (value == null)
            return null;

        ITagType tagType = tagLibrary.getTagType(tagName);
        if (tagType == null)
            tagType = tagLibrary.getDefaultTagType();

        switch (tagType.getAggregationEnum()) {
        case ARRAY:
            int length = Array.getLength(value);
            if (length == 0)
                return null;
            else
                return Array.get(value, 0);

        case LIST:
            List<?> list = (List<?>) value;
            if (list.isEmpty())
                return null;
            else
                return list.get(0);

        case COLLECTION:
        case SET:
            Collection<?> collection = (Collection<?>) value;
            Iterator<?> iterator = collection.iterator();
            return iterator.hasNext() ? iterator.next() : null;

        case MAP:
        case SCALAR:
        default:
            return value;
        }
    }

    @Override
    public Collection<?> getAllTag(String tagName) {
        Object value = getTag(tagName);
        if (value == null)
            return Collections.emptyList();

        ITagType tagType = tagLibrary.getTagType(tagName);
        if (tagType == null)
            tagType = tagLibrary.getDefaultTagType();

        switch (tagType.getAggregationEnum()) {
        case ARRAY:
            Object[] array = (Object[]) value;
            List<?> list = Arrays.asList(array);
            return list;

        case COLLECTION:
        case LIST:
            return (Collection<?>) value;

        case MAP:
        case SCALAR:
        default:
            return Arrays.asList(value);
        }
    }

    @Override
    public iString getTextTag(String tagName) {
        return getTag(tagName, iString.class);
    }

    /** ⇱ Implementaton Of {@link net.bodz.bas.fmt.flatf.IFlatfSerializable}. */
    /* _____________________________ */static section.iface __FLATF__;

    @Override
    public void writeObject(IFlatfOutput out, IOptions options)
            throws IOException {
        ITagLibrary taglib = null;
        if (options != null)
            taglib = options.get(ITagLibrary.class);
        if (taglib == null)
            throw new IllegalUsageException("Book is not provided in the options.");

        if (text != null && text != iString.NULL)
            out.attribute(".", text);
        for (Entry<String, Object> entry : tagMap.entrySet()) {
            String tagName = entry.getKey();
            Object tagValue = entry.getValue();

            ITagType tagType = taglib.getTagType(tagName);
            if (tagType == null) {
                // throw new IllegalUsageException("Undefined tag: " + tagName);
                tagType = taglib.getDefaultTagType();
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
        return new SectionHandler(taglib, options);
    }

    protected class SectionHandler
            implements ISectionHandler {

        final ITagLibrary taglib;
        final IOptions options;

        public SectionHandler(ITagLibrary taglib, IOptions options) {
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
                iString text = MultiLangStrings.parse(string);
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
                tagType = taglib.getDefaultTagType();

            Object cont = getTag(tagName);
            Object tagValue;

            try {
                tagValue = tagType.parseEntry(cont, suffix, string, options);
            } catch (RuntimeException e) {
                throw new RuntimeException(String.format(//
                        "Failed to parse ff-entry: %s (tt=%s)", string, tagType));
            }

            setTag(tagName, tagValue);
        }

    }

    protected boolean processInstruction(String command, String data)
            throws ParseException {
        return false;
    }

    @Override
    public <T> T to(Class<T> clazz) {
        return new Tooling(this).getWrapper(clazz);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        iString label = getTextTag(LABEL);
        if (!iString.fn.isEmpty(label)) {
            sb.append(label);
            sb.append(": ");
        }

        iString description = getTextTag(DESCRIPTION);
        if (!iString.fn.isEmpty(description))
            sb.append(description);

        return sb.toString();
    }

}
