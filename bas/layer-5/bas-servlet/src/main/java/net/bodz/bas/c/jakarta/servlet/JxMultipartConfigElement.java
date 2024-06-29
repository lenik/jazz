package net.bodz.bas.c.jakarta.servlet;

import jakarta.servlet.MultipartConfigElement;

public class JxMultipartConfigElement {

    public static MultipartConfigElement jx2a(javax.servlet.MultipartConfigElement jx) {
        return new MultipartConfigElement(//
                jx.getLocation(), //
                jx.getMaxFileSize(), //
                jx.getMaxRequestSize(), //
                jx.getFileSizeThreshold());
    }

}
