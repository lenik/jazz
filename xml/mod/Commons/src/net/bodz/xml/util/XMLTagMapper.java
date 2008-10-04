package net.bodz.xml.util;

import java.util.Map.Entry;

import org.jibx.runtime.IAliasable;
import org.jibx.runtime.IMarshaller;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshaller;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;
import org.jibx.runtime.impl.MarshallingContext;
import org.jibx.runtime.impl.UnmarshallingContext;

public class XMLTagMapper implements IMarshaller, IUnmarshaller, IAliasable {

    protected String ns;   // expected ns-uri of the containing tag
    protected int    index;
    protected String name; // expected name of the containing tag

    public XMLTagMapper() {
        ns = null;
        index = 0;
        name = "tag";
    }

    public XMLTagMapper(String uri, int index, String name) {
        this.ns = uri;
        this.index = index;
        this.name = name;
    }

    // --o IMarshaller

    public boolean isExtension(int index) {
        return false;
    }

    protected void marshalTag(XMLTag tag, MarshallingContext ctx)
            throws JiBXException {

        String name = tag.getName();

        // attributes
        ctx.startTagAttributes(index, name);
        for (Entry<String, Object> entry : tag.getAttributes().entrySet()) {
            String name1 = entry.getKey();
            Object value = entry.getValue();
            String value1 = value == null ? null : value.toString();
            ctx.attribute(index, name1, value1);
        }
        ctx.closeStartContent();

        // children
        for (Node<?> child : tag.children()) {
            if (child instanceof XMLTag) {
                XMLTag childTag = (XMLTag) child;
                marshalTag(childTag, ctx);
            } else if (child instanceof XMLText) {
                XMLText childText = (XMLText) child;
                ctx.content(childText.value2());
            } else {
                // ignore
            }
        }

        // finish with end tag for container element
        ctx.endTag(index, name);
    }

    public void marshal(Object obj, IMarshallingContext ictx)
            throws JiBXException {

        // make sure the parameters are as expected
        if (!(obj instanceof XMLTag))
            throw new JiBXException("Invalid object type for marshaller");
        else if (!(ictx instanceof MarshallingContext))
            throw new JiBXException("Invalid object type for marshaller");

        // start by generating start tag for container
        MarshallingContext ctx = (MarshallingContext) ictx;
        XMLTag tag = (XMLTag) obj;

        marshalTag(tag, ctx);
    }

    // --o IUnmarshaller

    public boolean isPresent(IUnmarshallingContext ctx) throws JiBXException {
        return ctx.isAt(ns, name);
    }

    protected XMLTag unmarshalTag(UnmarshallingContext ctx)
            throws JiBXException {
        ctx.toStart();
        String name = ctx.getElementName();
        String prefix = ctx.getPrefix();
        String ns = ctx.getNamespace();

        // tag self
        XMLTag tag = new XMLTag(name);
        if (ns != null)
            if (prefix == null)
                tag.set("xmlns", ns);
            else
                tag.set("xmlns:" + prefix, ns);

        // attributes
        int attrs = ctx.getAttributeCount();
        for (int index = 0; index < attrs; index++) {
            String name1 = ctx.getAttributeName(index);
            String value1 = ctx.getAttributeValue(index);
            String prefix1 = ctx.getAttributePrefix(index);
            String ns1 = ctx.getAttributeNamespace(index);
            if (ns1 != null) {
                // XXX - conflicts with element's namespace?
                if (prefix1 == null)
                    tag.set("xmlns", ns1);
                else
                    tag.set("xmlns:" + prefix1, ns1);
            }
            if (prefix1 == null)
                tag.set(name1, value1);
            else
                tag.set(prefix1 + ":" + name1, value1);
        }
        ctx.parsePastStartTag(ns, name);

        // text (mixed...?)
        String text = ctx.parseContentText();
        tag.setInner(text);

        // children
        while (!ctx.isEnd()) {
            XMLTag child = unmarshalTag(ctx);
            tag.add(child);
        }

        ctx.parsePastEndTag(ns, name);
        return tag;
    }

    public Object unmarshal(Object obj, IUnmarshallingContext ictx)
            throws JiBXException {
        UnmarshallingContext ctx = (UnmarshallingContext) ictx;

        // make sure we're at the appropriate start tag
        if (!ctx.isAt(ns, name))
            ctx.throwStartTagNameError(ns, name);

        XMLTag tag = unmarshalTag(ctx);
        return tag;
    }
}
