package net.bodz.bas.c.javax.servlet;

import javax.servlet.HttpConstraintElement;
import javax.servlet.HttpMethodConstraintElement;

public class JaHttpMethodConstraintElement {

    public static HttpMethodConstraintElement ja2x(jakarta.servlet.HttpMethodConstraintElement ja) {
        HttpConstraintElement jx = JaHttpConstraintElement.ja2x(ja);
        return new HttpMethodConstraintElement(//
                ja.getMethodName(), jx);
    }

}
