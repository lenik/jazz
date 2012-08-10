/**
 * JacobGen generated file --- do not edit
 *
 * (http://www.sourceforge.net/projects/jacob-project */
package com.jacob.ms.wbem;

import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class ISWbemQualifierSet
        extends Dispatch {

    public static final String componentName = "WbemScripting.ISWbemQualifierSet";

    public ISWbemQualifierSet() {
        super(componentName);
    }

    /**
     * This constructor is used instead of a case operation to turn a Dispatch object into a wider
     * object - it must exist in every wrapper class whose instances may be returned from method
     * calls wrapped in VT_DISPATCH Variants.
     */
    public ISWbemQualifierSet(Dispatch d) {
        // take over the IDispatch pointer
        m_pDispatch = d.m_pDispatch;
        // null out the input's pointer
        d.m_pDispatch = 0;
    }

    public ISWbemQualifierSet(String compName) {
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
     * @param name
     *            an input-parameter of type String
     * @param iFlags
     *            an input-parameter of type int
     * @return the result is of type ISWbemQualifier
     */
    public ISWbemQualifier item(String name, int iFlags) {
        return new ISWbemQualifier(Dispatch.call(this, "Item", name, new Variant(iFlags)).toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param name
     *            an input-parameter of type String
     * @return the result is of type ISWbemQualifier
     */
    public ISWbemQualifier item(String name) {
        return new ISWbemQualifier(Dispatch.call(this, "Item", name).toDispatch());
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
     * @param strName
     *            an input-parameter of type String
     * @param varVal
     *            an input-parameter of type Variant
     * @param bPropagatesToSubclass
     *            an input-parameter of type boolean
     * @param bPropagatesToInstance
     *            an input-parameter of type boolean
     * @param bIsOverridable
     *            an input-parameter of type boolean
     * @param iFlags
     *            an input-parameter of type int
     * @return the result is of type ISWbemQualifier
     */
    public ISWbemQualifier add(String strName, Variant varVal, boolean bPropagatesToSubclass,
            boolean bPropagatesToInstance, boolean bIsOverridable, int iFlags) {
        return new ISWbemQualifier(Dispatch.call(this, "Add", strName, varVal, new Variant(bPropagatesToSubclass),
                new Variant(bPropagatesToInstance), new Variant(bIsOverridable), new Variant(iFlags)).toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strName
     *            an input-parameter of type String
     * @param varVal
     *            an input-parameter of type Variant
     * @param bPropagatesToSubclass
     *            an input-parameter of type boolean
     * @param bPropagatesToInstance
     *            an input-parameter of type boolean
     * @param bIsOverridable
     *            an input-parameter of type boolean
     * @return the result is of type ISWbemQualifier
     */
    public ISWbemQualifier add(String strName, Variant varVal, boolean bPropagatesToSubclass,
            boolean bPropagatesToInstance, boolean bIsOverridable) {
        return new ISWbemQualifier(Dispatch.call(this, "Add", strName, varVal, new Variant(bPropagatesToSubclass),
                new Variant(bPropagatesToInstance), new Variant(bIsOverridable)).toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strName
     *            an input-parameter of type String
     * @param varVal
     *            an input-parameter of type Variant
     * @param bPropagatesToSubclass
     *            an input-parameter of type boolean
     * @param bPropagatesToInstance
     *            an input-parameter of type boolean
     * @return the result is of type ISWbemQualifier
     */
    public ISWbemQualifier add(String strName, Variant varVal, boolean bPropagatesToSubclass,
            boolean bPropagatesToInstance) {
        return new ISWbemQualifier(Dispatch.call(this, "Add", strName, varVal, new Variant(bPropagatesToSubclass),
                new Variant(bPropagatesToInstance)).toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strName
     *            an input-parameter of type String
     * @param varVal
     *            an input-parameter of type Variant
     * @param bPropagatesToSubclass
     *            an input-parameter of type boolean
     * @return the result is of type ISWbemQualifier
     */
    public ISWbemQualifier add(String strName, Variant varVal, boolean bPropagatesToSubclass) {
        return new ISWbemQualifier(Dispatch.call(this, "Add", strName, varVal, new Variant(bPropagatesToSubclass))
                .toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strName
     *            an input-parameter of type String
     * @param varVal
     *            an input-parameter of type Variant
     * @return the result is of type ISWbemQualifier
     */
    public ISWbemQualifier add(String strName, Variant varVal) {
        return new ISWbemQualifier(Dispatch.call(this, "Add", strName, varVal).toDispatch());
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
        Dispatch.call(this, "Remove", strName, new Variant(iFlags));
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strName
     *            an input-parameter of type String
     */
    public void remove(String strName) {
        Dispatch.call(this, "Remove", strName);
    }

}
