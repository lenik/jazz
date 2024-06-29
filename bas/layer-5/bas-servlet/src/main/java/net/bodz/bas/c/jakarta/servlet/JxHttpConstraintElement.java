package net.bodz.bas.c.jakarta.servlet;

import jakarta.servlet.HttpConstraintElement;

public class JxHttpConstraintElement {

    public static HttpConstraintElement jx2a(javax.servlet.HttpConstraintElement ja) {
        return new HttpConstraintElement( //
                JxServletSecurity.jx2a(ja.getEmptyRoleSemantic()), //
                JxServletSecurity.jx2a(ja.getTransportGuarantee()), //
                ja.getRolesAllowed());
    };

}
