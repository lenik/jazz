/**
 * JacobGen generated file --- do not edit
 *
 * (http://www.sourceforge.net/projects/jacob-project */
package com.jacob.ms.wbem;

import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class ISWbemLocator extends Dispatch {

    public static final String componentName = "WbemScripting.ISWbemLocator"; //$NON-NLS-1$

    public ISWbemLocator() {
        super(componentName);
    }

    /**
     * This constructor is used instead of a case operation to turn a Dispatch
     * object into a wider object - it must exist in every wrapper class whose
     * instances may be returned from method calls wrapped in VT_DISPATCH
     * Variants.
     */
    public ISWbemLocator(Dispatch d) {
        // take over the IDispatch pointer
        m_pDispatch = d.m_pDispatch;
        // null out the input's pointer
        d.m_pDispatch = 0;
    }

    public ISWbemLocator(String compName) {
        super(compName);
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strServer
     *            an input-parameter of type String
     * @param strNamespace
     *            an input-parameter of type String
     * @param strUser
     *            an input-parameter of type String
     * @param strPassword
     *            an input-parameter of type String
     * @param strLocale
     *            an input-parameter of type String
     * @param strAuthority
     *            an input-parameter of type String
     * @param iSecurityFlags
     *            an input-parameter of type int
     * @param objWbemNamedValueSet
     *            an input-parameter of type Object
     * @return the result is of type ISWbemServices
     */
    public ISWbemServices connectServer(String strServer, String strNamespace, String strUser,
            String strPassword, String strLocale, String strAuthority, int iSecurityFlags,
            Object objWbemNamedValueSet) {
        return new ISWbemServices(Dispatch.call(this, "ConnectServer", strServer, strNamespace, //$NON-NLS-1$
                strUser, strPassword, strLocale, strAuthority, new Variant(iSecurityFlags),
                objWbemNamedValueSet).toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strServer
     *            an input-parameter of type String
     * @param strNamespace
     *            an input-parameter of type String
     * @param strUser
     *            an input-parameter of type String
     * @param strPassword
     *            an input-parameter of type String
     * @param strLocale
     *            an input-parameter of type String
     * @param strAuthority
     *            an input-parameter of type String
     * @param iSecurityFlags
     *            an input-parameter of type int
     * @return the result is of type ISWbemServices
     */
    public ISWbemServices connectServer(String strServer, String strNamespace, String strUser,
            String strPassword, String strLocale, String strAuthority, int iSecurityFlags) {
        return new ISWbemServices(Dispatch.call(this, "ConnectServer", strServer, strNamespace, //$NON-NLS-1$
                strUser, strPassword, strLocale, strAuthority, new Variant(iSecurityFlags))
                .toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strServer
     *            an input-parameter of type String
     * @param strNamespace
     *            an input-parameter of type String
     * @param strUser
     *            an input-parameter of type String
     * @param strPassword
     *            an input-parameter of type String
     * @param strLocale
     *            an input-parameter of type String
     * @param strAuthority
     *            an input-parameter of type String
     * @return the result is of type ISWbemServices
     */
    public ISWbemServices connectServer(String strServer, String strNamespace, String strUser,
            String strPassword, String strLocale, String strAuthority) {
        return new ISWbemServices(Dispatch.call(this, "ConnectServer", strServer, strNamespace, //$NON-NLS-1$
                strUser, strPassword, strLocale, strAuthority).toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strServer
     *            an input-parameter of type String
     * @param strNamespace
     *            an input-parameter of type String
     * @param strUser
     *            an input-parameter of type String
     * @param strPassword
     *            an input-parameter of type String
     * @param strLocale
     *            an input-parameter of type String
     * @return the result is of type ISWbemServices
     */
    public ISWbemServices connectServer(String strServer, String strNamespace, String strUser,
            String strPassword, String strLocale) {
        return new ISWbemServices(Dispatch.call(this, "ConnectServer", strServer, strNamespace, //$NON-NLS-1$
                strUser, strPassword, strLocale).toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strServer
     *            an input-parameter of type String
     * @param strNamespace
     *            an input-parameter of type String
     * @param strUser
     *            an input-parameter of type String
     * @param strPassword
     *            an input-parameter of type String
     * @return the result is of type ISWbemServices
     */
    public ISWbemServices connectServer(String strServer, String strNamespace, String strUser,
            String strPassword) {
        return new ISWbemServices(Dispatch.call(this, "ConnectServer", strServer, strNamespace, //$NON-NLS-1$
                strUser, strPassword).toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strServer
     *            an input-parameter of type String
     * @param strNamespace
     *            an input-parameter of type String
     * @param strUser
     *            an input-parameter of type String
     * @return the result is of type ISWbemServices
     */
    public ISWbemServices connectServer(String strServer, String strNamespace, String strUser) {
        return new ISWbemServices(Dispatch.call(this, "ConnectServer", strServer, strNamespace, //$NON-NLS-1$
                strUser).toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strServer
     *            an input-parameter of type String
     * @param strNamespace
     *            an input-parameter of type String
     * @return the result is of type ISWbemServices
     */
    public ISWbemServices connectServer(String strServer, String strNamespace) {
        return new ISWbemServices(Dispatch.call(this, "ConnectServer", strServer, strNamespace) //$NON-NLS-1$
                .toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strServer
     *            an input-parameter of type String
     * @return the result is of type ISWbemServices
     */
    public ISWbemServices connectServer(String strServer) {
        return new ISWbemServices(Dispatch.call(this, "ConnectServer", strServer).toDispatch()); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type ISWbemServices
     */
    public ISWbemServices connectServer() {
        return new ISWbemServices(Dispatch.call(this, "ConnectServer").toDispatch()); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type ISWbemSecurity
     */
    public ISWbemSecurity getSecurity_() {
        return new ISWbemSecurity(Dispatch.get(this, "Security_").toDispatch()); //$NON-NLS-1$
    }

}
