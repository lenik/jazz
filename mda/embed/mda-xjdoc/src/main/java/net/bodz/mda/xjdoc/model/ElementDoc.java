package net.bodz.mda.xjdoc.model;

import java.beans.Transient;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.i18n.dom.DomainString;
import net.bodz.bas.i18n.dom.XDomainString;
import net.bodz.bas.lang.negotiation.INegotiation;
import net.bodz.bas.lang.negotiation.NegotiationException;
import net.bodz.bas.text.flatf.IFlatfOutput;
import net.bodz.bas.text.flatf.IFlatfSerializable;
import net.bodz.bas.text.flatf.ISectionHandler;
import net.bodz.mda.xjdoc.tags.ITagBook;
import net.bodz.mda.xjdoc.tags.ITagType;
import net.bodz.mda.xjdoc.tags.TagBook;

public class ElementDoc
        implements IElementDoc, IFlatfSerializable {

    String name;
    DomainString text;
    Map<String, Object> tagMap = new LinkedHashMap<String, Object>();

    public ElementDoc() {
    }

    public ElementDoc(String name) {
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
    public DomainString getText() {
        return text;
    }

    @Override
    public void setText(DomainString text) {
        this.text = text;
    }

    @Transient
    public DomainString getTextHeader() {
        DomainString header = new XDomainString();
        for (Entry<String, String> entry : text.entrySet()) {
            String domain = entry.getKey();
            String _text = entry.getValue();
            String _header;

            int parbreak = indexOfParbreak(_text);
            if (parbreak == -1)
                _header = _text;
            else
                _header = _text.substring(0, parbreak);

            header.put(domain, _header.trim());
        }
        return header;
    }

    public DomainString getTextBody() {
        DomainString body = new XDomainString();
        for (Entry<String, String> entry : text.entrySet()) {
            String domain = entry.getKey();
            String _text = entry.getValue();
            String _body;

            int parbreak = indexOfParbreak(_text);
            if (parbreak == -1)
                continue;
            else
                _body = _text.substring(parbreak + 1);

            body.put(domain, _body.trim());
        }
        return body;
    }

    static int indexOfParbreak(String s) {
        int off = 0;
        while ((off = s.indexOf('\n', off)) != -1) {
            int ahead = off + 1;
            if (ahead < s.length()) {
                char ch = s.charAt(ahead);
                if (ch == '\n') // \n\n
                    return off;
                String after = s.substring(off + 1).trim();
                if (!after.isEmpty()) {
                    ch = after.charAt(0);
                    if (ch == '-') // \n- Here '-' is a special char for line-continue.
                        continue;
                    if (!Character.isAlphabetic(ch)) // \n<punct>
                        return off;
                }
            }
        }
        return -1;
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
            throws IOException, NegotiationException {
        ITagBook book = null;
        if (negotiation != null)
            book = negotiation.get(ITagBook.class);
        if (book == null)
            throw new IllegalUsageException("Book is not provided in the negotiation.");

        if (text != null)
            out.attribute(".", text);
        for (Entry<String, Object> entry : tagMap.entrySet()) {
            String tagName = entry.getKey();
            Object tagValue = entry.getValue();
            ITagType tagType = book.getTagType(tagName);
            tagType.writeEntries(out, tagName, tagValue, negotiation);
        }
    }

    @Override
    public ISectionHandler getSectionHandler(String sectionName, INegotiation negotiation)
            throws NegotiationException {
        ITagBook book = TagBook.getInstance(negotiation);
        return new FlatfHandler(book, negotiation);
    }

    protected class FlatfHandler
            implements ISectionHandler {

        final ITagBook book;
        final INegotiation negotiation;

        public FlatfHandler(ITagBook book, INegotiation negotiation) {
            if (book == null)
                throw new NullPointerException("book");
            this.book = book;
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
            String tagName, suffix;
            int dot = name.indexOf('.');
            if (dot == -1) {
                tagName = name;
                suffix = null;
            } else {
                tagName = name.substring(0, dot);
                suffix = name.substring(dot + 1);
            }
            ITagType tagType = book.getTagType(tagName);
            Object cont = getTag(tagName);
            Object tagValue = tagType.parseEntry(cont, suffix, string, negotiation);
            setTag(tagName, tagValue);
        }

    }

    protected boolean processInstruction(String command, String data)
            throws ParseException {
        return false;
    }

}
