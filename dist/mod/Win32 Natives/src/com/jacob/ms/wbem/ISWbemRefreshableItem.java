/**
 * JacobGen generated file --- do not edit
 *
 * (http://www.sourceforge.net/projects/jacob-project */
package com.jacob.ms.wbem;

import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class ISWbemRefreshableItem extends Dispatch {

    public static final String componentName = "WbemScripting.ISWbemRefreshableItem";

    public ISWbemRefreshableItem() {
        super(componentName);
    }

    /**
     * This constructor is used instead of a case operation to turn a Dispatch
     * object into a wider object - it must exist in every wrapper class whose
     * instances may be returned from method calls wrapped in VT_DISPATCH
     * Variants.
     */
    public ISWbemRefreshableItem(Dispatch d) {
        // take over the IDispatch pointer
        m_pDispatch = d.m_pDispatch;
        // null out the input's pointer
        d.m_pDispatch = 0;
    }

    public ISWbemRefreshableItem(String compName) {
        super(compName);
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type int
     */
    public int getIndex() {
        return Dispatch.get(this, "Index").changeType(Variant.VariantInt).getInt();
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type ISWbemRefresher
     */
    public ISWbemRefresher getRefresher() {
        return new ISWbemRefresher(Dispatch.get(this, "Refresher").toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type boolean
     */
    public boolean getIsSet() {
        return Dispatch.get(this, "IsSet").changeType(Variant.VariantBoolean).getBoolean();
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type ISWbemObjectEx
     */
    public ISWbemObjectEx getObject() {
        return new ISWbemObjectEx(Dispatch.get(this, "Object").toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type ISWbemObjectSet
     */
    public ISWbemObjectSet getObjectSet() {
        return new ISWbemObjectSet(Dispatch.get(this, "ObjectSet").toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param iFlags
     *            an input-parameter of type int
     */
    public void remove(int iFlags) {
        Dispatch.call(this, "Remove", new Variant(iFlags));
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     */
    public void remove() {
        Dispatch.call(this, "Remove");
    }

}
