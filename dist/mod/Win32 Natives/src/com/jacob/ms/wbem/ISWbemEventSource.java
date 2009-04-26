/**
 * JacobGen generated file --- do not edit
 *
 * (http://www.sourceforge.net/projects/jacob-project */
package com.jacob.ms.wbem;

import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class ISWbemEventSource extends Dispatch {

    public static final String componentName = "WbemScripting.ISWbemEventSource"; //$NON-NLS-1$

    public ISWbemEventSource() {
        super(componentName);
    }

    /**
     * This constructor is used instead of a case operation to turn a Dispatch
     * object into a wider object - it must exist in every wrapper class whose
     * instances may be returned from method calls wrapped in VT_DISPATCH
     * Variants.
     */
    public ISWbemEventSource(Dispatch d) {
        // take over the IDispatch pointer
        m_pDispatch = d.m_pDispatch;
        // null out the input's pointer
        d.m_pDispatch = 0;
    }

    public ISWbemEventSource(String compName) {
        super(compName);
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param iTimeoutMs
     *            an input-parameter of type int
     * @return the result is of type ISWbemObject
     */
    public ISWbemObject nextEvent(int iTimeoutMs) {
        return new ISWbemObject(Dispatch.call(this, "NextEvent", new Variant(iTimeoutMs)) //$NON-NLS-1$
                .toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type ISWbemObject
     */
    public ISWbemObject nextEvent() {
        return new ISWbemObject(Dispatch.call(this, "NextEvent").toDispatch()); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type ISWbemSecurity
     */
    public ISWbemSecurity getSecurity_() {
        return new ISWbemSecurity(Dispatch.get(this, "Security_").toDispatch()); //$NON-NLS-1$
    }

}
