/**
 * JacobGen generated file --- do not edit
 *
 * (http://www.sourceforge.net/projects/jacob-project */
package com.jacob.ms.wbem;

import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class ISWbemMethodSet extends Dispatch {

    public static final String componentName = "WbemScripting.ISWbemMethodSet";

    public ISWbemMethodSet() {
        super(componentName);
    }

    /**
     * This constructor is used instead of a case operation to turn a Dispatch
     * object into a wider object - it must exist in every wrapper class whose
     * instances may be returned from method calls wrapped in VT_DISPATCH
     * Variants.
     */
    public ISWbemMethodSet(Dispatch d) {
        // take over the IDispatch pointer
        m_pDispatch = d.m_pDispatch;
        // null out the input's pointer
        d.m_pDispatch = 0;
    }

    public ISWbemMethodSet(String compName) {
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
     * @param strName
     *            an input-parameter of type String
     * @param iFlags
     *            an input-parameter of type int
     * @return the result is of type ISWbemMethod
     */
    public ISWbemMethod item(String strName, int iFlags) {
        return new ISWbemMethod(Dispatch.call(this, "Item", strName, new Variant(iFlags))
                .toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strName
     *            an input-parameter of type String
     * @return the result is of type ISWbemMethod
     */
    public ISWbemMethod item(String strName) {
        return new ISWbemMethod(Dispatch.call(this, "Item", strName).toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type int
     */
    public int getCount() {
        return Dispatch.get(this, "Count").changeType(Variant.VariantInt).getInt();
    }

}
