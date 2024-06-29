package net.bodz.bas.c.jakarta.servlet;

import java.util.Collection;

import jakarta.servlet.descriptor.JspPropertyGroupDescriptor;

import net.bodz.bas.c.util.MapGlobal;

public class JxJspPropertyGroupDescriptor
        implements
            JspPropertyGroupDescriptor {

    javax.servlet.descriptor.JspPropertyGroupDescriptor jx;

    public JxJspPropertyGroupDescriptor(javax.servlet.descriptor.JspPropertyGroupDescriptor jx) {
        if (jx == null)
            throw new NullPointerException("jx");
        this.jx = jx;
    }

    @Override
    public Collection<String> getUrlPatterns() {
        return jx.getUrlPatterns();
    }

    @Override
    public String getElIgnored() {
        return jx.getElIgnored();
    }

    @Override
    public String getPageEncoding() {
        return jx.getPageEncoding();
    }

    @Override
    public String getScriptingInvalid() {
        return jx.getScriptingInvalid();
    }

    @Override
    public String getIsXml() {
        return jx.getIsXml();
    }

    @Override
    public Collection<String> getIncludePreludes() {
        return jx.getIncludePreludes();
    }

    @Override
    public Collection<String> getIncludeCodas() {
        return jx.getIncludeCodas();
    }

    @Override
    public String getDeferredSyntaxAllowedAsLiteral() {
        return jx.getDeferredSyntaxAllowedAsLiteral();
    }

    @Override
    public String getTrimDirectiveWhitespaces() {
        return jx.getTrimDirectiveWhitespaces();
    }

    @Override
    public String getDefaultContentType() {
        return jx.getDefaultContentType();
    }

    @Override
    public String getBuffer() {
        return jx.getBuffer();
    }

    @Override
    public String getErrorOnUndeclaredNamespace() {
        return jx.getErrorOnUndeclaredNamespace();
    }

    public static final MapGlobal<javax.servlet.descriptor.JspPropertyGroupDescriptor, JspPropertyGroupDescriptor> jx2a //
            = MapGlobal.fn(JspPropertyGroupDescriptor.class, (s) -> new JxJspPropertyGroupDescriptor(s));

}
