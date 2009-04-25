/**
 * JacobGen generated file --- do not edit
 *
 * (http://www.sourceforge.net/projects/jacob-project */
package com.jacob.ms.wbem;

import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class ISWbemObjectPath extends Dispatch {

    public static final String componentName = "WbemScripting.ISWbemObjectPath";

    public ISWbemObjectPath() {
        super(componentName);
    }

    /**
     * This constructor is used instead of a case operation to turn a Dispatch
     * object into a wider object - it must exist in every wrapper class whose
     * instances may be returned from method calls wrapped in VT_DISPATCH
     * Variants.
     */
    public ISWbemObjectPath(Dispatch d) {
        // take over the IDispatch pointer
        m_pDispatch = d.m_pDispatch;
        // null out the input's pointer
        d.m_pDispatch = 0;
    }

    public ISWbemObjectPath(String compName) {
        super(compName);
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type String
     */
    public String getPath() {
        return Dispatch.get(this, "Path").toString();
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param path
     *            an input-parameter of type String
     */
    public void setPath(String path) {
        Dispatch.put(this, "Path", path);
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type String
     */
    public String getRelPath() {
        return Dispatch.get(this, "RelPath").toString();
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param relPath
     *            an input-parameter of type String
     */
    public void setRelPath(String relPath) {
        Dispatch.put(this, "RelPath", relPath);
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type String
     */
    public String getServer() {
        return Dispatch.get(this, "Server").toString();
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param server
     *            an input-parameter of type String
     */
    public void setServer(String server) {
        Dispatch.put(this, "Server", server);
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type String
     */
    public String getNamespace() {
        return Dispatch.get(this, "Namespace").toString();
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param namespace
     *            an input-parameter of type String
     */
    public void setNamespace(String namespace) {
        Dispatch.put(this, "Namespace", namespace);
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type String
     */
    public String getParentNamespace() {
        return Dispatch.get(this, "ParentNamespace").toString();
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type String
     */
    public String getDisplayName() {
        return Dispatch.get(this, "DisplayName").toString();
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param displayName
     *            an input-parameter of type String
     */
    public void setDisplayName(String displayName) {
        Dispatch.put(this, "DisplayName", displayName);
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type String
     */
    public String getClass1() {
        return Dispatch.get(this, "Class").toString();
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param class an input-parameter of type String
     */
    public void setClass(String _class) {
        Dispatch.put(this, "Class", _class);
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type boolean
     */
    public boolean getIsClass() {
        return Dispatch.get(this, "IsClass").changeType(Variant.VariantBoolean).getBoolean();
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     */
    public void setAsClass() {
        Dispatch.call(this, "SetAsClass");
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type boolean
     */
    public boolean getIsSingleton() {
        return Dispatch.get(this, "IsSingleton").changeType(Variant.VariantBoolean).getBoolean();
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     */
    public void setAsSingleton() {
        Dispatch.call(this, "SetAsSingleton");
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type ISWbemNamedValueSet
     */
    public ISWbemNamedValueSet getKeys() {
        return new ISWbemNamedValueSet(Dispatch.get(this, "Keys").toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type ISWbemSecurity
     */
    public ISWbemSecurity getSecurity_() {
        return new ISWbemSecurity(Dispatch.get(this, "Security_").toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type String
     */
    public String getLocale() {
        return Dispatch.get(this, "Locale").toString();
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param locale
     *            an input-parameter of type String
     */
    public void setLocale(String locale) {
        Dispatch.put(this, "Locale", locale);
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type String
     */
    public String getAuthority() {
        return Dispatch.get(this, "Authority").toString();
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param authority
     *            an input-parameter of type String
     */
    public void setAuthority(String authority) {
        Dispatch.put(this, "Authority", authority);
    }

}
