/**
 * JacobGen generated file --- do not edit
 *
 * (http://www.sourceforge.net/projects/jacob-project */
package com.jacob.ms.wbem;

import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class ISWbemPrivilege extends Dispatch {

    public static final String componentName = "WbemScripting.ISWbemPrivilege";

    public ISWbemPrivilege() {
        super(componentName);
    }

    /**
     * This constructor is used instead of a case operation to turn a Dispatch
     * object into a wider object - it must exist in every wrapper class whose
     * instances may be returned from method calls wrapped in VT_DISPATCH
     * Variants.
     */
    public ISWbemPrivilege(Dispatch d) {
        // take over the IDispatch pointer
        m_pDispatch = d.m_pDispatch;
        // null out the input's pointer
        d.m_pDispatch = 0;
    }

    public ISWbemPrivilege(String compName) {
        super(compName);
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type boolean
     */
    public boolean getIsEnabled() {
        return Dispatch.get(this, "IsEnabled").changeType(Variant.VariantBoolean).getBoolean();
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param isEnabled
     *            an input-parameter of type boolean
     */
    public void setIsEnabled(boolean isEnabled) {
        Dispatch.put(this, "IsEnabled", new Variant(isEnabled));
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type String
     */
    public String getName() {
        return Dispatch.get(this, "Name").toString();
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
     * @return the result is of type int
     */
    public int getIdentifier() {
        return Dispatch.get(this, "Identifier").changeType(Variant.VariantInt).getInt();
    }

}
