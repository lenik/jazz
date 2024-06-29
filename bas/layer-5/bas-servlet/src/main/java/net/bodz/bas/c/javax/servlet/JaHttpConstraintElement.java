package net.bodz.bas.c.javax.servlet;

import javax.servlet.HttpConstraintElement;

public class JaHttpConstraintElement {

    public static HttpConstraintElement ja2x(jakarta.servlet.HttpConstraintElement ja) {
        return new HttpConstraintElement( //
                JaServletSecurity.ja2x(ja.getEmptyRoleSemantic()), //
                JaServletSecurity.ja2x(ja.getTransportGuarantee()), //
                ja.getRolesAllowed());
    };

}
