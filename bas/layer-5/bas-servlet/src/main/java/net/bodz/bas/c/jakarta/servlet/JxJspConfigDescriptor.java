package net.bodz.bas.c.jakarta.servlet;

import java.util.Collection;

import jakarta.servlet.descriptor.JspConfigDescriptor;
import jakarta.servlet.descriptor.JspPropertyGroupDescriptor;
import jakarta.servlet.descriptor.TaglibDescriptor;

import net.bodz.bas.c.util.MapGlobal;
import net.bodz.bas.t.transform.TransformedCollection;

public class JxJspConfigDescriptor
        implements
            JspConfigDescriptor {

    javax.servlet.descriptor.JspConfigDescriptor jx;

    public JxJspConfigDescriptor(javax.servlet.descriptor.JspConfigDescriptor jx) {
        if (jx == null)
            throw new NullPointerException("jx");
        this.jx = jx;
    }

    @Override
    public Collection<TaglibDescriptor> getTaglibs() {
        return TransformedCollection.transform(jx.getTaglibs(), //
                (s) -> JxTaglibDescriptor.jx2a.cachedMap(s));
    }

    @Override
    public Collection<JspPropertyGroupDescriptor> getJspPropertyGroups() {
        return TransformedCollection.transform(jx.getJspPropertyGroups(), //
                (s) -> JxJspPropertyGroupDescriptor.jx2a.cachedMap(s));
    }

    public static final MapGlobal<javax.servlet.descriptor.JspConfigDescriptor, JspConfigDescriptor> jx2a//
            = MapGlobal.fn(JspConfigDescriptor.class, (s) -> new JxJspConfigDescriptor(s));

}
