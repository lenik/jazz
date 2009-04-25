/**
 * JacobGen generated file --- do not edit
 *
 * (http://www.sourceforge.net/projects/jacob-project */
package com.jacob.ms.wbem;

import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class ISWbemPrivilegeSet extends Dispatch {

    public static final String componentName = "WbemScripting.ISWbemPrivilegeSet";

    public ISWbemPrivilegeSet() {
        super(componentName);
    }

    /**
     * This constructor is used instead of a case operation to turn a Dispatch
     * object into a wider object - it must exist in every wrapper class whose
     * instances may be returned from method calls wrapped in VT_DISPATCH
     * Variants.
     */
    public ISWbemPrivilegeSet(Dispatch d) {
        // take over the IDispatch pointer
        m_pDispatch = d.m_pDispatch;
        // null out the input's pointer
        d.m_pDispatch = 0;
    }

    public ISWbemPrivilegeSet(String compName) {
        super(compName);
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type Variant
     */
    public Variant get_NewEnum() {
        return Dispatch.get(this, "_NewEnum");
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param iPrivilege
     *            an input-parameter of type int
     * @return the result is of type ISWbemPrivilege
     */
    public ISWbemPrivilege item(int iPrivilege) {
        return new ISWbemPrivilege(Dispatch.call(this, "Item", new Variant(iPrivilege))
                .toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type int
     */
    public int getCount() {
        return Dispatch.get(this, "Count").changeType(Variant.VariantInt).getInt();
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param iPrivilege
     *            an input-parameter of type int
     * @param bIsEnabled
     *            an input-parameter of type boolean
     * @return the result is of type ISWbemPrivilege
     */
    public ISWbemPrivilege add(int iPrivilege, boolean bIsEnabled) {
        return new ISWbemPrivilege(Dispatch.call(this, "Add", new Variant(iPrivilege),
                new Variant(bIsEnabled)).toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param iPrivilege
     *            an input-parameter of type int
     * @return the result is of type ISWbemPrivilege
     */
    public ISWbemPrivilege add(int iPrivilege) {
        return new ISWbemPrivilege(Dispatch.call(this, "Add", new Variant(iPrivilege)).toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param iPrivilege
     *            an input-parameter of type int
     */
    public void remove(int iPrivilege) {
        Dispatch.call(this, "Remove", new Variant(iPrivilege));
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     */
    public void deleteAll() {
        Dispatch.call(this, "DeleteAll");
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strPrivilege
     *            an input-parameter of type String
     * @param bIsEnabled
     *            an input-parameter of type boolean
     * @return the result is of type ISWbemPrivilege
     */
    public ISWbemPrivilege addAsString(String strPrivilege, boolean bIsEnabled) {
        return new ISWbemPrivilege(Dispatch.call(this, "AddAsString", strPrivilege,
                new Variant(bIsEnabled)).toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strPrivilege
     *            an input-parameter of type String
     * @return the result is of type ISWbemPrivilege
     */
    public ISWbemPrivilege addAsString(String strPrivilege) {
        return new ISWbemPrivilege(Dispatch.call(this, "AddAsString", strPrivilege).toDispatch());
    }

}
