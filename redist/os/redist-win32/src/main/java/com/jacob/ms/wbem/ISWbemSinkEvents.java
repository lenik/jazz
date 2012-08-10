/**
 * JacobGen generated file --- do not edit
 *
 * (http://www.sourceforge.net/projects/jacob-project */
package com.jacob.ms.wbem;

import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class ISWbemSinkEvents
        extends Dispatch {

    public static final String componentName = "WbemScripting.ISWbemSinkEvents";

    public ISWbemSinkEvents() {
        super(componentName);
    }

    /**
     * This constructor is used instead of a case operation to turn a Dispatch object into a wider
     * object - it must exist in every wrapper class whose instances may be returned from method
     * calls wrapped in VT_DISPATCH Variants.
     */
    public ISWbemSinkEvents(Dispatch d) {
        // take over the IDispatch pointer
        m_pDispatch = d.m_pDispatch;
        // null out the input's pointer
        d.m_pDispatch = 0;
    }

    public ISWbemSinkEvents(String compName) {
        super(compName);
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemObject
     *            an input-parameter of type ISWbemObject
     * @param objWbemAsyncContext
     *            an input-parameter of type ISWbemNamedValueSet
     */
    public void onObjectReady(ISWbemObject objWbemObject, ISWbemNamedValueSet objWbemAsyncContext) {
        Dispatch.call(this, "OnObjectReady", objWbemObject, objWbemAsyncContext);
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param iHResult
     *            an input-parameter of type int
     * @param objWbemErrorObject
     *            an input-parameter of type ISWbemObject
     * @param objWbemAsyncContext
     *            an input-parameter of type ISWbemNamedValueSet
     */
    public void onCompleted(int iHResult, ISWbemObject objWbemErrorObject, ISWbemNamedValueSet objWbemAsyncContext) {
        Dispatch.call(this, "OnCompleted", new Variant(iHResult), objWbemErrorObject, objWbemAsyncContext);
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param iUpperBound
     *            an input-parameter of type int
     * @param iCurrent
     *            an input-parameter of type int
     * @param strMessage
     *            an input-parameter of type String
     * @param objWbemAsyncContext
     *            an input-parameter of type ISWbemNamedValueSet
     */
    public void onProgress(int iUpperBound, int iCurrent, String strMessage, ISWbemNamedValueSet objWbemAsyncContext) {
        Dispatch.call(this, "OnProgress", new Variant(iUpperBound), new Variant(iCurrent), strMessage,
                objWbemAsyncContext);
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemObjectPath
     *            an input-parameter of type ISWbemObjectPath
     * @param objWbemAsyncContext
     *            an input-parameter of type ISWbemNamedValueSet
     */
    public void onObjectPut(ISWbemObjectPath objWbemObjectPath, ISWbemNamedValueSet objWbemAsyncContext) {
        Dispatch.call(this, "OnObjectPut", objWbemObjectPath, objWbemAsyncContext);
    }

}
