package net.bodz.bas.c.jakarta.servlet;

import jakarta.servlet.descriptor.TaglibDescriptor;

import net.bodz.bas.c.util.MapGlobal;

public class JxTaglibDescriptor
        implements
            TaglibDescriptor {

    javax.servlet.descriptor.TaglibDescriptor jx;

    public JxTaglibDescriptor(javax.servlet.descriptor.TaglibDescriptor jx) {
        if (jx == null)
            throw new NullPointerException("jx");
        this.jx = jx;
    }

    @Override
    public String getTaglibURI() {
        return jx.getTaglibURI();
    }

    @Override
    public String getTaglibLocation() {
        return jx.getTaglibLocation();
    }

    public static final MapGlobal<javax.servlet.descriptor.TaglibDescriptor, TaglibDescriptor> jx2a //
            = MapGlobal.fn(TaglibDescriptor.class, (s) -> new JxTaglibDescriptor(s));

}
