/**
 * JacobGen generated file --- do not edit
 *
 * (http://www.sourceforge.net/projects/jacob-project */
package com.jacob.ms.wbem;

import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class ISWbemNamedValueSet extends Dispatch {

    public static final String componentName = "WbemScripting.ISWbemNamedValueSet"; //$NON-NLS-1$

    public ISWbemNamedValueSet() {
        super(componentName);
    }

    /**
     * This constructor is used instead of a case operation to turn a Dispatch
     * object into a wider object - it must exist in every wrapper class whose
     * instances may be returned from method calls wrapped in VT_DISPATCH
     * Variants.
     */
    public ISWbemNamedValueSet(Dispatch d) {
        // take over the IDispatch pointer
        m_pDispatch = d.m_pDispatch;
        // null out the input's pointer
        d.m_pDispatch = 0;
    }

    public ISWbemNamedValueSet(String compName) {
        super(compName);
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type Variant
     */
    public Variant get_NewEnum() {
        return Dispatch.get(this, "_NewEnum"); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strName
     *            an input-parameter of type String
     * @param iFlags
     *            an input-parameter of type int
     * @return the result is of type ISWbemNamedValue
     */
    public ISWbemNamedValue item(String strName, int iFlags) {
        return new ISWbemNamedValue(Dispatch.call(this, "Item", strName, new Variant(iFlags)) //$NON-NLS-1$
                .toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strName
     *            an input-parameter of type String
     * @return the result is of type ISWbemNamedValue
     */
    public ISWbemNamedValue item(String strName) {
        return new ISWbemNamedValue(Dispatch.call(this, "Item", strName).toDispatch()); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type int
     */
    public int getCount() {
        return Dispatch.get(this, "Count").changeType(Variant.VariantInt).getInt(); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strName
     *            an input-parameter of type String
     * @param varValue
     *            an input-parameter of type Variant
     * @param iFlags
     *            an input-parameter of type int
     * @return the result is of type ISWbemNamedValue
     */
    public ISWbemNamedValue add(String strName, Variant varValue, int iFlags) {
        return new ISWbemNamedValue(Dispatch.call(this, "Add", strName, varValue, //$NON-NLS-1$
                new Variant(iFlags)).toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strName
     *            an input-parameter of type String
     * @param varValue
     *            an input-parameter of type Variant
     * @return the result is of type ISWbemNamedValue
     */
    public ISWbemNamedValue add(String strName, Variant varValue) {
        return new ISWbemNamedValue(Dispatch.call(this, "Add", strName, varValue).toDispatch()); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strName
     *            an input-parameter of type String
     * @param iFlags
     *            an input-parameter of type int
     */
    public void remove(String strName, int iFlags) {
        Dispatch.call(this, "Remove", strName, new Variant(iFlags)); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strName
     *            an input-parameter of type String
     */
    public void remove(String strName) {
        Dispatch.call(this, "Remove", strName); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type ISWbemNamedValueSet
     */
    public ISWbemNamedValueSet clone1() {
        return new ISWbemNamedValueSet(Dispatch.call(this, "Clone").toDispatch()); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     */
    public void deleteAll() {
        Dispatch.call(this, "DeleteAll"); //$NON-NLS-1$
    }

}
