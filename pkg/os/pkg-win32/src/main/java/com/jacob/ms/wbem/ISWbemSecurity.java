/**
 * JacobGen generated file --- do not edit
 *
 * (http://www.sourceforge.net/projects/jacob-project */
package com.jacob.ms.wbem;

import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class ISWbemSecurity
        extends Dispatch {

    public static final String componentName = "WbemScripting.ISWbemSecurity";

    public ISWbemSecurity() {
        super(componentName);
    }

    /**
     * This constructor is used instead of a case operation to turn a Dispatch object into a wider
     * object - it must exist in every wrapper class whose instances may be returned from method
     * calls wrapped in VT_DISPATCH Variants.
     */
    public ISWbemSecurity(Dispatch d) {
        // take over the IDispatch pointer
        m_pDispatch = d.m_pDispatch;
        // null out the input's pointer
        d.m_pDispatch = 0;
    }

    public ISWbemSecurity(String compName) {
        super(compName);
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type int
     */
    public int getImpersonationLevel() {
        return Dispatch.get(this, "ImpersonationLevel").changeType(Variant.VariantInt).getInt();
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param impersonationLevel
     *            an input-parameter of type int
     */
    public void setImpersonationLevel(int impersonationLevel) {
        Dispatch.put(this, "ImpersonationLevel", new Variant(impersonationLevel));
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type int
     */
    public int getAuthenticationLevel() {
        return Dispatch.get(this, "AuthenticationLevel").changeType(Variant.VariantInt).getInt();
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param authenticationLevel
     *            an input-parameter of type int
     */
    public void setAuthenticationLevel(int authenticationLevel) {
        Dispatch.put(this, "AuthenticationLevel", new Variant(authenticationLevel));
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type ISWbemPrivilegeSet
     */
    public ISWbemPrivilegeSet getPrivileges() {
        return new ISWbemPrivilegeSet(Dispatch.get(this, "Privileges").toDispatch());
    }

}
