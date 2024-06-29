package net.bodz.bas.c.javax.servlet;

import javax.servlet.ServletSecurityElement;

import net.bodz.bas.t.transform.TransformedCollection;

public class JaServletSecurityElement {

    public static ServletSecurityElement ja2x(jakarta.servlet.ServletSecurityElement ja) {
        return new ServletSecurityElement(//
                JaHttpConstraintElement.ja2x(ja), //
                TransformedCollection.transform(//
                        ja.getHttpMethodConstraints(), //
                        (jakarta.servlet.HttpMethodConstraintElement el) -> JaHttpMethodConstraintElement.ja2x(el)//
                )//
        );
    }

}
