package net.bodz.bas.c.javax.servlet;

import javax.servlet.MultipartConfigElement;

public class JaMultipartConfigElement {

    public static MultipartConfigElement ja2x(jakarta.servlet.MultipartConfigElement ja) {
        return new MultipartConfigElement(//
                ja.getLocation(), //
                ja.getMaxFileSize(), //
                ja.getMaxRequestSize(), //
                ja.getFileSizeThreshold());
    }

}
