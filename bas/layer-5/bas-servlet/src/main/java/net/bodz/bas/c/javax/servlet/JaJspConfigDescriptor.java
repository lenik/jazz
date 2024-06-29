package net.bodz.bas.c.javax.servlet;

import java.util.Collection;

import javax.servlet.descriptor.JspConfigDescriptor;
import javax.servlet.descriptor.JspPropertyGroupDescriptor;
import javax.servlet.descriptor.TaglibDescriptor;

import net.bodz.bas.c.util.MapGlobal;
import net.bodz.bas.t.transform.TransformedCollection;

public class JaJspConfigDescriptor
        implements
            JspConfigDescriptor {

    jakarta.servlet.descriptor.JspConfigDescriptor ja;

    public JaJspConfigDescriptor(jakarta.servlet.descriptor.JspConfigDescriptor ja) {
        if (ja == null)
            throw new NullPointerException("ja");
        this.ja = ja;
    }

    @Override
    public Collection<TaglibDescriptor> getTaglibs() {
        return TransformedCollection.transform(ja.getTaglibs(), //
                (s) -> JaTaglibDescriptor.ja2x.cachedMap(s));
    }

    @Override
    public Collection<JspPropertyGroupDescriptor> getJspPropertyGroups() {
        return TransformedCollection.transform(ja.getJspPropertyGroups(), //
                (s) -> JaJspPropertyGroupDescriptor.ja2x.cachedMap(s));
    }

    public static final MapGlobal<jakarta.servlet.descriptor.JspConfigDescriptor, JspConfigDescriptor> ja2x //
            = MapGlobal.fn(JspConfigDescriptor.class, (s) -> new JaJspConfigDescriptor(s));

}
