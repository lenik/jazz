/**
 * JacobGen generated file --- do not edit
 *
 * (http://www.sourceforge.net/projects/jacob-project */
package com.jacob.ms.wbem;

import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class ISWbemRefresher extends Dispatch {

    public static final String componentName = "WbemScripting.ISWbemRefresher"; //$NON-NLS-1$

    public ISWbemRefresher() {
        super(componentName);
    }

    /**
     * This constructor is used instead of a case operation to turn a Dispatch
     * object into a wider object - it must exist in every wrapper class whose
     * instances may be returned from method calls wrapped in VT_DISPATCH
     * Variants.
     */
    public ISWbemRefresher(Dispatch d) {
        // take over the IDispatch pointer
        m_pDispatch = d.m_pDispatch;
        // null out the input's pointer
        d.m_pDispatch = 0;
    }

    public ISWbemRefresher(String compName) {
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
     * @param iIndex
     *            an input-parameter of type int
     * @return the result is of type ISWbemRefreshableItem
     */
    public ISWbemRefreshableItem item(int iIndex) {
        return new ISWbemRefreshableItem(Dispatch.call(this, "Item", new Variant(iIndex)) //$NON-NLS-1$
                .toDispatch());
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
     * @param objWbemServices
     *            an input-parameter of type ISWbemServicesEx
     * @param bsInstancePath
     *            an input-parameter of type String
     * @param iFlags
     *            an input-parameter of type int
     * @param objWbemNamedValueSet
     *            an input-parameter of type Object
     * @return the result is of type ISWbemRefreshableItem
     */
    public ISWbemRefreshableItem add(ISWbemServicesEx objWbemServices, String bsInstancePath,
            int iFlags, Object objWbemNamedValueSet) {
        return new ISWbemRefreshableItem(Dispatch.call(this, "Add", objWbemServices, //$NON-NLS-1$
                bsInstancePath, new Variant(iFlags), objWbemNamedValueSet).toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemServices
     *            an input-parameter of type ISWbemServicesEx
     * @param bsInstancePath
     *            an input-parameter of type String
     * @param iFlags
     *            an input-parameter of type int
     * @return the result is of type ISWbemRefreshableItem
     */
    public ISWbemRefreshableItem add(ISWbemServicesEx objWbemServices, String bsInstancePath,
            int iFlags) {
        return new ISWbemRefreshableItem(Dispatch.call(this, "Add", objWbemServices, //$NON-NLS-1$
                bsInstancePath, new Variant(iFlags)).toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemServices
     *            an input-parameter of type ISWbemServicesEx
     * @param bsInstancePath
     *            an input-parameter of type String
     * @return the result is of type ISWbemRefreshableItem
     */
    public ISWbemRefreshableItem add(ISWbemServicesEx objWbemServices, String bsInstancePath) {
        return new ISWbemRefreshableItem(Dispatch
                .call(this, "Add", objWbemServices, bsInstancePath).toDispatch()); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemServices
     *            an input-parameter of type ISWbemServicesEx
     * @param bsClassName
     *            an input-parameter of type String
     * @param iFlags
     *            an input-parameter of type int
     * @param objWbemNamedValueSet
     *            an input-parameter of type Object
     * @return the result is of type ISWbemRefreshableItem
     */
    public ISWbemRefreshableItem addEnum(ISWbemServicesEx objWbemServices, String bsClassName,
            int iFlags, Object objWbemNamedValueSet) {
        return new ISWbemRefreshableItem(Dispatch.call(this, "AddEnum", objWbemServices, //$NON-NLS-1$
                bsClassName, new Variant(iFlags), objWbemNamedValueSet).toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemServices
     *            an input-parameter of type ISWbemServicesEx
     * @param bsClassName
     *            an input-parameter of type String
     * @param iFlags
     *            an input-parameter of type int
     * @return the result is of type ISWbemRefreshableItem
     */
    public ISWbemRefreshableItem addEnum(ISWbemServicesEx objWbemServices, String bsClassName,
            int iFlags) {
        return new ISWbemRefreshableItem(Dispatch.call(this, "AddEnum", objWbemServices, //$NON-NLS-1$
                bsClassName, new Variant(iFlags)).toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemServices
     *            an input-parameter of type ISWbemServicesEx
     * @param bsClassName
     *            an input-parameter of type String
     * @return the result is of type ISWbemRefreshableItem
     */
    public ISWbemRefreshableItem addEnum(ISWbemServicesEx objWbemServices, String bsClassName) {
        return new ISWbemRefreshableItem(Dispatch.call(this, "AddEnum", objWbemServices, //$NON-NLS-1$
                bsClassName).toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param iIndex
     *            an input-parameter of type int
     * @param iFlags
     *            an input-parameter of type int
     */
    public void remove(int iIndex, int iFlags) {
        Dispatch.call(this, "Remove", new Variant(iIndex), new Variant(iFlags)); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param iIndex
     *            an input-parameter of type int
     */
    public void remove(int iIndex) {
        Dispatch.call(this, "Remove", new Variant(iIndex)); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param iFlags
     *            an input-parameter of type int
     */
    public void refresh(int iFlags) {
        Dispatch.call(this, "Refresh", new Variant(iFlags)); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     */
    public void refresh() {
        Dispatch.call(this, "Refresh"); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type boolean
     */
    public boolean getAutoReconnect() {
        return Dispatch.get(this, "AutoReconnect").changeType(Variant.VariantBoolean).getBoolean(); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param autoReconnect
     *            an input-parameter of type boolean
     */
    public void setAutoReconnect(boolean autoReconnect) {
        Dispatch.put(this, "AutoReconnect", new Variant(autoReconnect)); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     */
    public void deleteAll() {
        Dispatch.call(this, "DeleteAll"); //$NON-NLS-1$
    }

}
