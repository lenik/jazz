/**
 * JacobGen generated file --- do not edit
 *
 * (http://www.sourceforge.net/projects/jacob-project */
package com.jacob.ms.wbem;

import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class ISWbemQualifier
        extends Dispatch {

    public static final String componentName = "WbemScripting.ISWbemQualifier";

    public ISWbemQualifier() {
        super(componentName);
    }

    /**
     * This constructor is used instead of a case operation to turn a Dispatch object into a wider
     * object - it must exist in every wrapper class whose instances may be returned from method
     * calls wrapped in VT_DISPATCH Variants.
     */
    public ISWbemQualifier(Dispatch d) {
        // take over the IDispatch pointer
        m_pDispatch = d.m_pDispatch;
        // null out the input's pointer
        d.m_pDispatch = 0;
    }

    public ISWbemQualifier(String compName) {
        super(compName);
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type Variant
     */
    public Variant getValue() {
        return Dispatch.get(this, "Value");
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param value
     *            an input-parameter of type Variant
     */
    public void setValue(Variant value) {
        Dispatch.put(this, "Value", value);
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
     * @return the result is of type boolean
     */
    public boolean getIsLocal() {
        return Dispatch.get(this, "IsLocal").changeType(Variant.VariantBoolean).getBoolean();
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type boolean
     */
    public boolean getPropagatesToSubclass() {
        return Dispatch.get(this, "PropagatesToSubclass").changeType(Variant.VariantBoolean).getBoolean();
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param propagatesToSubclass
     *            an input-parameter of type boolean
     */
    public void setPropagatesToSubclass(boolean propagatesToSubclass) {
        Dispatch.put(this, "PropagatesToSubclass", new Variant(propagatesToSubclass));
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type boolean
     */
    public boolean getPropagatesToInstance() {
        return Dispatch.get(this, "PropagatesToInstance").changeType(Variant.VariantBoolean).getBoolean();
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param propagatesToInstance
     *            an input-parameter of type boolean
     */
    public void setPropagatesToInstance(boolean propagatesToInstance) {
        Dispatch.put(this, "PropagatesToInstance", new Variant(propagatesToInstance));
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type boolean
     */
    public boolean getIsOverridable() {
        return Dispatch.get(this, "IsOverridable").changeType(Variant.VariantBoolean).getBoolean();
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param isOverridable
     *            an input-parameter of type boolean
     */
    public void setIsOverridable(boolean isOverridable) {
        Dispatch.put(this, "IsOverridable", new Variant(isOverridable));
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type boolean
     */
    public boolean getIsAmended() {
        return Dispatch.get(this, "IsAmended").changeType(Variant.VariantBoolean).getBoolean();
    }

}
