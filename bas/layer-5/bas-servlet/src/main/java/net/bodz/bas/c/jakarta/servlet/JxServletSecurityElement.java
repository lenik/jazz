package net.bodz.bas.c.jakarta.servlet;

import jakarta.servlet.ServletSecurityElement;

import net.bodz.bas.t.transform.TransformedCollection;

public class JxServletSecurityElement {

    public static ServletSecurityElement jx2a(javax.servlet.ServletSecurityElement jx) {
        return new ServletSecurityElement(//
                JxHttpConstraintElement.jx2a(jx), //
                TransformedCollection.transform(//
                        jx.getHttpMethodConstraints(), //
                        (javax.servlet.HttpMethodConstraintElement el) -> JxHttpMethodConstraintElement.jx2a(el)//
                )//
        );
    }

}
