package net.bodz.mda.xjdoc.model;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.flatf.IFlatfForm;
import net.bodz.bas.fmt.flatf.IFlatfOutput;
import net.bodz.bas.fmt.flatf.ISectionHandler;
import net.bodz.bas.i18n.dom.MultiLangStrings;
import net.bodz.bas.i18n.dom.StrFn;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.sugar.Tooling;
import net.bodz.mda.xjdoc.taglib.AbstractTagLibrary;
import net.bodz.mda.xjdoc.taglib.ITagLibrary;

public class MutableElementDoc
        implements
            IMutableElementDoc,
            IFlatfForm {

    ITagLibrary tagLibrary;
    iString text = iString.NULL;
    Map<String, IDocTag<?>> tagMap = new LinkedHashMap<>();

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

    @Override
    public Map<String, IDocTag<?>> getTagMap() {
        return tagMap;
    }

    @Override
    public boolean isTagPresent(String name) {
        Object value = tagMap.get(name);
        return value != null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends IDocTag<?>> T getTag(String tagName) {
        return (T) tagMap.get(tagName);
    }

    @Override
    public void setTag(String tagName, IDocTag<?> value) {
        tagMap.put(tagName, value);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends IDocTag<?>> T makeTag(String tagName) {
        T tag = (T) tagMap.get(tagName);
        if (tag == null) {
            tag = (T) tagLibrary.createTag(tagName);
            if (tag == null)
                throw new IllegalUsageException("tag isn't defined: " + tagName);
            tagMap.put(tagName, tag);
        }
        return tag;
    }

    @Override
    public IDocTag<?> removeTag(String tagName) {
        return tagMap.remove(tagName);
    }

    @Override
    public String getString(String tagName, String defaultValue) {
        IDocTag<?> tag = getTag(tagName);
        if (tag == null)
            return defaultValue;
        String s = tag.getString();
        if (s == null)
            return defaultValue;
        return s;
    }

    @Override
    public iString getText(String tagName, iString defaultValue) {
        IDocTag<?> tag = getTag(tagName);
        if (tag == null)
            return defaultValue;

        iString str = tag.getText();
        if (str == null)
            return defaultValue;

        return str;
    }

    /** ⇱ Implementaton Of {@link net.bodz.bas.fmt.flatf.IFlatfForm}. */
    /* _____________________________ */static section.iface __FLATF__;

    @Override
    public void writeObject(IFlatfOutput out, IOptions options)
            throws IOException, FormatException {
        ITagLibrary taglib = null;
        if (options != null)
            taglib = options.get(ITagLibrary.class);
        if (taglib == null)
            throw new IllegalUsageException("Book is not provided in the options.");

        if (text != null && text != iString.NULL)
            out.attribute(".", text);
        for (Entry<String, IDocTag<?>> entry : tagMap.entrySet()) {
            String tagName = entry.getKey();
            IDocTag<?> tag = entry.getValue();

            if (tag == null) {
                tag = taglib.createTag(tagName);
                if (tag == null)
                    continue; // not supported
            }

            try {
                tag.writeObject(out, tagName, options);
            } catch (FormatException e) {
                throw new FormatException("Failed to write ff-entry for tag " + tagName + " := " + tag, e);
            }
        }
    }

    @Override
    public ISectionHandler getSectionHandler(String sectionName, IOptions options) {
        ITagLibrary taglib = AbstractTagLibrary.fromOptions(options);
        return new SectionHandler(taglib, options);
    }

    protected class SectionHandler
            implements
                ISectionHandler {

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

            IDocTag<?> docTag = getTag(tagName);
            if (docTag == null) {
                docTag = taglib.createTag(tagName);
                if (docTag == null) {
                    return; // skip if not supported
                }
                tagMap.put(tagName, docTag);
            }

            try {
                docTag.parseAttribute(tagName, suffix, string, options);
            } catch (ParseException e) {
                throw new RuntimeException(String.format(//
                        "Failed to parse ff-entry: %s (tt=%s), error %s", //
                        string, docTag.getClass().getSimpleName(), e.getMessage()), e);
            }
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

        iString label = getText(LABEL);
        if (! StrFn.isEmpty(label)) {
            sb.append(label);
            sb.append(": ");
        }

        iString description = getText(DESCRIPTION);
        if (! StrFn.isEmpty(description))
            sb.append(description);

        return sb.toString();
    }

}
