package net.bodz.bas.c.javax.servlet;

import javax.servlet.descriptor.TaglibDescriptor;

import net.bodz.bas.c.util.MapGlobal;

public class JaTaglibDescriptor
        implements
            TaglibDescriptor {

    jakarta.servlet.descriptor.TaglibDescriptor ja;

    public JaTaglibDescriptor(jakarta.servlet.descriptor.TaglibDescriptor ja) {
        if (ja == null)
            throw new NullPointerException("ja");
        this.ja = ja;
    }

    @Override
    public String getTaglibURI() {
        return ja.getTaglibURI();
    }

    @Override
    public String getTaglibLocation() {
        return ja.getTaglibLocation();
    }

    public static final MapGlobal<jakarta.servlet.descriptor.TaglibDescriptor, TaglibDescriptor> ja2x //
            = MapGlobal.fn(TaglibDescriptor.class, (s) -> new JaTaglibDescriptor(s));

}
