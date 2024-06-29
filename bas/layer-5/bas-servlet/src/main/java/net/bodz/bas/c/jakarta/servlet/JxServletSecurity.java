package net.bodz.bas.c.jakarta.servlet;

import jakarta.servlet.annotation.ServletSecurity.EmptyRoleSemantic;
import jakarta.servlet.annotation.ServletSecurity.TransportGuarantee;

import net.bodz.bas.err.UnexpectedException;

public class JxServletSecurity {

    public static EmptyRoleSemantic jx2a(javax.servlet.annotation.ServletSecurity.EmptyRoleSemantic jx) {
        if (jx == null)
            return null;
        switch (jx) {
        case PERMIT:
            return EmptyRoleSemantic.PERMIT;
        case DENY:
            return EmptyRoleSemantic.DENY;
        default:
            throw new UnexpectedException();
        }
    }

    public static TransportGuarantee jx2a(javax.servlet.annotation.ServletSecurity.TransportGuarantee jx) {
        if (jx == null)
            return null;
        switch (jx) {
        case NONE:
            return TransportGuarantee.NONE;
        case CONFIDENTIAL:
            return TransportGuarantee.CONFIDENTIAL;
        default:
            throw new UnexpectedException();
        }
    }

}
