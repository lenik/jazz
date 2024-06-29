package net.bodz.bas.c.javax.servlet;

import javax.servlet.annotation.ServletSecurity.EmptyRoleSemantic;
import javax.servlet.annotation.ServletSecurity.TransportGuarantee;

import net.bodz.bas.err.UnexpectedException;

public class JaServletSecurity {

    public static EmptyRoleSemantic ja2x(jakarta.servlet.annotation.ServletSecurity.EmptyRoleSemantic jx) {
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

    public static TransportGuarantee ja2x(jakarta.servlet.annotation.ServletSecurity.TransportGuarantee jx) {
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
