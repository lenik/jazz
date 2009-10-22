/**
 * JacobGen generated file --- do not edit
 *
 * (http://www.sourceforge.net/projects/jacob-project */
package com.jacob.ms.wbem;

import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class ISWbemProperty extends Dispatch {

    public static final String componentName = "WbemScripting.ISWbemProperty"; //$NON-NLS-1$

    public ISWbemProperty() {
        super(componentName);
    }

    /**
     * This constructor is used instead of a case operation to turn a Dispatch
     * object into a wider object - it must exist in every wrapper class whose
     * instances may be returned from method calls wrapped in VT_DISPATCH
     * Variants.
     */
    public ISWbemProperty(Dispatch d) {
        // take over the IDispatch pointer
        m_pDispatch = d.m_pDispatch;
        // null out the input's pointer
        d.m_pDispatch = 0;
    }

    public ISWbemProperty(String compName) {
        super(compName);
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type Variant
     */
    public Variant getValue() {
        return Dispatch.get(this, "Value"); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param value
     *            an input-parameter of type Variant
     */
    public void setValue(Variant value) {
        Dispatch.put(this, "Value", value); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type String
     */
    public String getName() {
        return Dispatch.get(this, "Name").toString(); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type boolean
     */
    public boolean getIsLocal() {
        return Dispatch.get(this, "IsLocal").changeType(Variant.VariantBoolean).getBoolean(); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type String
     */
    public String getOrigin() {
        return Dispatch.get(this, "Origin").toString(); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type int
     */
    public int getCIMType() {
        return Dispatch.get(this, "CIMType").changeType(Variant.VariantInt).getInt(); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type ISWbemQualifierSet
     */
    public ISWbemQualifierSet getQualifiers_() {
        return new ISWbemQualifierSet(Dispatch.get(this, "Qualifiers_").toDispatch()); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type boolean
     */
    public boolean getIsArray() {
        return Dispatch.get(this, "IsArray").changeType(Variant.VariantBoolean).getBoolean(); //$NON-NLS-1$
    }

}
