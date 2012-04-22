/**
 * JacobGen generated file --- do not edit
 *
 * (http://www.sourceforge.net/projects/jacob-project */
package com.jacob.ms.wbem;

import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class ISWbemPropertySet extends Dispatch {

    public static final String componentName = "WbemScripting.ISWbemPropertySet"; //$NON-NLS-1$

    public ISWbemPropertySet() {
        super(componentName);
    }

    /**
     * This constructor is used instead of a case operation to turn a Dispatch
     * object into a wider object - it must exist in every wrapper class whose
     * instances may be returned from method calls wrapped in VT_DISPATCH
     * Variants.
     */
    public ISWbemPropertySet(Dispatch d) {
        // take over the IDispatch pointer
        m_pDispatch = d.m_pDispatch;
        // null out the input's pointer
        d.m_pDispatch = 0;
    }

    public ISWbemPropertySet(String compName) {
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
     * @return the result is of type ISWbemProperty
     */
    public ISWbemProperty item(String strName, int iFlags) {
        return new ISWbemProperty(Dispatch.call(this, "Item", strName, new Variant(iFlags)) //$NON-NLS-1$
                .toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strName
     *            an input-parameter of type String
     * @return the result is of type ISWbemProperty
     */
    public ISWbemProperty item(String strName) {
        return new ISWbemProperty(Dispatch.call(this, "Item", strName).toDispatch()); //$NON-NLS-1$
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
     * @param iCimType
     *            an input-parameter of type int
     * @param bIsArray
     *            an input-parameter of type boolean
     * @param iFlags
     *            an input-parameter of type int
     * @return the result is of type ISWbemProperty
     */
    public ISWbemProperty add(String strName, int iCimType, boolean bIsArray, int iFlags) {
        return new ISWbemProperty(Dispatch.call(this, "Add", strName, new Variant(iCimType), //$NON-NLS-1$
                new Variant(bIsArray), new Variant(iFlags)).toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strName
     *            an input-parameter of type String
     * @param iCimType
     *            an input-parameter of type int
     * @param bIsArray
     *            an input-parameter of type boolean
     * @return the result is of type ISWbemProperty
     */
    public ISWbemProperty add(String strName, int iCimType, boolean bIsArray) {
        return new ISWbemProperty(Dispatch.call(this, "Add", strName, new Variant(iCimType), //$NON-NLS-1$
                new Variant(bIsArray)).toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strName
     *            an input-parameter of type String
     * @param iCimType
     *            an input-parameter of type int
     * @return the result is of type ISWbemProperty
     */
    public ISWbemProperty add(String strName, int iCimType) {
        return new ISWbemProperty(Dispatch.call(this, "Add", strName, new Variant(iCimType)) //$NON-NLS-1$
                .toDispatch());
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

}
