package net.bodz.bas.c.jakarta.servlet;

import jakarta.servlet.HttpConstraintElement;
import jakarta.servlet.HttpMethodConstraintElement;

public class JxHttpMethodConstraintElement {

    public static HttpMethodConstraintElement jx2a(javax.servlet.HttpMethodConstraintElement jx) {
        HttpConstraintElement ja = JxHttpConstraintElement.jx2a(jx);
        return new HttpMethodConstraintElement(//
                jx.getMethodName(), ja);
    }

}
