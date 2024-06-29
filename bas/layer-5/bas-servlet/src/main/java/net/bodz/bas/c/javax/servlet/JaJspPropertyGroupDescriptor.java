package net.bodz.bas.c.javax.servlet;

import java.util.Collection;

import javax.servlet.descriptor.JspPropertyGroupDescriptor;

import net.bodz.bas.c.util.MapGlobal;

public class JaJspPropertyGroupDescriptor
        implements
            JspPropertyGroupDescriptor {

    jakarta.servlet.descriptor.JspPropertyGroupDescriptor ja;

    public JaJspPropertyGroupDescriptor(jakarta.servlet.descriptor.JspPropertyGroupDescriptor ja) {
        if (ja == null)
            throw new NullPointerException("ja");
        this.ja = ja;
    }

    @Override
    public Collection<String> getUrlPatterns() {
        return ja.getUrlPatterns();
    }

    @Override
    public String getElIgnored() {
        return ja.getElIgnored();
    }

    @Override
    public String getPageEncoding() {
        return ja.getPageEncoding();
    }

    @Override
    public String getScriptingInvalid() {
        return ja.getScriptingInvalid();
    }

    @Override
    public String getIsXml() {
        return ja.getIsXml();
    }

    @Override
    public Collection<String> getIncludePreludes() {
        return ja.getIncludePreludes();
    }

    @Override
    public Collection<String> getIncludeCodas() {
        return ja.getIncludeCodas();
    }

    @Override
    public String getDeferredSyntaxAllowedAsLiteral() {
        return ja.getDeferredSyntaxAllowedAsLiteral();
    }

    @Override
    public String getTrimDirectiveWhitespaces() {
        return ja.getTrimDirectiveWhitespaces();
    }

    @Override
    public String getDefaultContentType() {
        return ja.getDefaultContentType();
    }

    @Override
    public String getBuffer() {
        return ja.getBuffer();
    }

    @Override
    public String getErrorOnUndeclaredNamespace() {
        return ja.getErrorOnUndeclaredNamespace();
    }

    public static final MapGlobal<jakarta.servlet.descriptor.JspPropertyGroupDescriptor, JspPropertyGroupDescriptor> ja2x //
            = MapGlobal.fn(JspPropertyGroupDescriptor.class, (s) -> new JaJspPropertyGroupDescriptor(s));

}
