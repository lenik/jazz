/**
 * JacobGen generated file --- do not edit
 *
 * (http://www.sourceforge.net/projects/jacob-project */
package com.jacob.ms.wbem;

import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class ISWbemObject extends Dispatch {

    public static final String componentName = "WbemScripting.ISWbemObject"; //$NON-NLS-1$

    public ISWbemObject() {
        super(componentName);
    }

    /**
     * This constructor is used instead of a case operation to turn a Dispatch
     * object into a wider object - it must exist in every wrapper class whose
     * instances may be returned from method calls wrapped in VT_DISPATCH
     * Variants.
     */
    public ISWbemObject(Dispatch d) {
        // take over the IDispatch pointer
        m_pDispatch = d.m_pDispatch;
        // null out the input's pointer
        d.m_pDispatch = 0;
    }

    public ISWbemObject(String compName) {
        super(compName);
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param iFlags
     *            an input-parameter of type int
     * @param objWbemNamedValueSet
     *            an input-parameter of type Object
     * @return the result is of type ISWbemObjectPath
     */
    public ISWbemObjectPath put_(int iFlags, Object objWbemNamedValueSet) {
        return new ISWbemObjectPath(Dispatch.call(this, "Put_", new Variant(iFlags), //$NON-NLS-1$
                objWbemNamedValueSet).toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param iFlags
     *            an input-parameter of type int
     * @return the result is of type ISWbemObjectPath
     */
    public ISWbemObjectPath put_(int iFlags) {
        return new ISWbemObjectPath(Dispatch.call(this, "Put_", new Variant(iFlags)).toDispatch()); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type ISWbemObjectPath
     */
    public ISWbemObjectPath put_() {
        return new ISWbemObjectPath(Dispatch.call(this, "Put_").toDispatch()); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param iFlags
     *            an input-parameter of type int
     * @param objWbemNamedValueSet
     *            an input-parameter of type Object
     * @param objWbemAsyncContext
     *            an input-parameter of type Object
     */
    public void putAsync_(Object objWbemSink, int iFlags, Object objWbemNamedValueSet,
            Object objWbemAsyncContext) {
        Dispatch.call(this, "PutAsync_", objWbemSink, new Variant(iFlags), objWbemNamedValueSet, //$NON-NLS-1$
                objWbemAsyncContext);
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param iFlags
     *            an input-parameter of type int
     * @param objWbemNamedValueSet
     *            an input-parameter of type Object
     */
    public void putAsync_(Object objWbemSink, int iFlags, Object objWbemNamedValueSet) {
        Dispatch.call(this, "PutAsync_", objWbemSink, new Variant(iFlags), objWbemNamedValueSet); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param iFlags
     *            an input-parameter of type int
     */
    public void putAsync_(Object objWbemSink, int iFlags) {
        Dispatch.call(this, "PutAsync_", objWbemSink, new Variant(iFlags)); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     */
    public void putAsync_(Object objWbemSink) {
        Dispatch.call(this, "PutAsync_", objWbemSink); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param iFlags
     *            an input-parameter of type int
     */
    public void delete_(int iFlags) {
        Dispatch.call(this, "Delete_", new Variant(iFlags)); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     */
    public void delete_() {
        Dispatch.call(this, "Delete_"); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method and receiving the
     * output-parameter(s).
     * 
     * @param iFlags
     *            an input-parameter of type int
     * @param objWbemNamedValueSet
     *            an input-parameter of type Object
     */
    public void delete_(int iFlags, Object objWbemNamedValueSet) {
        Dispatch.call(this, "Delete_", new Variant(iFlags), objWbemNamedValueSet); //$NON-NLS-1$

    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param iFlags
     *            an input-parameter of type int
     * @param objWbemNamedValueSet
     *            an input-parameter of type Object
     * @param objWbemAsyncContext
     *            an input-parameter of type Object
     */
    public void deleteAsync_(Object objWbemSink, int iFlags, Object objWbemNamedValueSet,
            Object objWbemAsyncContext) {
        Dispatch.call(this, "DeleteAsync_", objWbemSink, new Variant(iFlags), objWbemNamedValueSet, //$NON-NLS-1$
                objWbemAsyncContext);
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param iFlags
     *            an input-parameter of type int
     * @param objWbemNamedValueSet
     *            an input-parameter of type Object
     */
    public void deleteAsync_(Object objWbemSink, int iFlags, Object objWbemNamedValueSet) {
        Dispatch.call(this, "DeleteAsync_", objWbemSink, new Variant(iFlags), objWbemNamedValueSet); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param iFlags
     *            an input-parameter of type int
     */
    public void deleteAsync_(Object objWbemSink, int iFlags) {
        Dispatch.call(this, "DeleteAsync_", objWbemSink, new Variant(iFlags)); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     */
    public void deleteAsync_(Object objWbemSink) {
        Dispatch.call(this, "DeleteAsync_", objWbemSink); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param iFlags
     *            an input-parameter of type int
     * @param objWbemNamedValueSet
     *            an input-parameter of type Object
     * @return the result is of type ISWbemObjectSet
     */
    public ISWbemObjectSet instances_(int iFlags, Object objWbemNamedValueSet) {
        return new ISWbemObjectSet(Dispatch.call(this, "Instances_", new Variant(iFlags), //$NON-NLS-1$
                objWbemNamedValueSet).toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param iFlags
     *            an input-parameter of type int
     * @return the result is of type ISWbemObjectSet
     */
    public ISWbemObjectSet instances_(int iFlags) {
        return new ISWbemObjectSet(Dispatch.call(this, "Instances_", new Variant(iFlags)) //$NON-NLS-1$
                .toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type ISWbemObjectSet
     */
    public ISWbemObjectSet instances_() {
        return new ISWbemObjectSet(Dispatch.call(this, "Instances_").toDispatch()); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param iFlags
     *            an input-parameter of type int
     * @param objWbemNamedValueSet
     *            an input-parameter of type Object
     * @param objWbemAsyncContext
     *            an input-parameter of type Object
     */
    public void instancesAsync_(Object objWbemSink, int iFlags, Object objWbemNamedValueSet,
            Object objWbemAsyncContext) {
        Dispatch.call(this, "InstancesAsync_", objWbemSink, new Variant(iFlags), //$NON-NLS-1$
                objWbemNamedValueSet, objWbemAsyncContext);
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param iFlags
     *            an input-parameter of type int
     * @param objWbemNamedValueSet
     *            an input-parameter of type Object
     */
    public void instancesAsync_(Object objWbemSink, int iFlags, Object objWbemNamedValueSet) {
        Dispatch.call(this, "InstancesAsync_", objWbemSink, new Variant(iFlags), //$NON-NLS-1$
                objWbemNamedValueSet);
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param iFlags
     *            an input-parameter of type int
     */
    public void instancesAsync_(Object objWbemSink, int iFlags) {
        Dispatch.call(this, "InstancesAsync_", objWbemSink, new Variant(iFlags)); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     */
    public void instancesAsync_(Object objWbemSink) {
        Dispatch.call(this, "InstancesAsync_", objWbemSink); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param iFlags
     *            an input-parameter of type int
     * @param objWbemNamedValueSet
     *            an input-parameter of type Object
     * @return the result is of type ISWbemObjectSet
     */
    public ISWbemObjectSet subclasses_(int iFlags, Object objWbemNamedValueSet) {
        return new ISWbemObjectSet(Dispatch.call(this, "Subclasses_", new Variant(iFlags), //$NON-NLS-1$
                objWbemNamedValueSet).toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param iFlags
     *            an input-parameter of type int
     * @return the result is of type ISWbemObjectSet
     */
    public ISWbemObjectSet subclasses_(int iFlags) {
        return new ISWbemObjectSet(Dispatch.call(this, "Subclasses_", new Variant(iFlags)) //$NON-NLS-1$
                .toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type ISWbemObjectSet
     */
    public ISWbemObjectSet subclasses_() {
        return new ISWbemObjectSet(Dispatch.call(this, "Subclasses_").toDispatch()); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param iFlags
     *            an input-parameter of type int
     * @param objWbemNamedValueSet
     *            an input-parameter of type Object
     * @param objWbemAsyncContext
     *            an input-parameter of type Object
     */
    public void subclassesAsync_(Object objWbemSink, int iFlags, Object objWbemNamedValueSet,
            Object objWbemAsyncContext) {
        Dispatch.call(this, "SubclassesAsync_", objWbemSink, new Variant(iFlags), //$NON-NLS-1$
                objWbemNamedValueSet, objWbemAsyncContext);
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param iFlags
     *            an input-parameter of type int
     * @param objWbemNamedValueSet
     *            an input-parameter of type Object
     */
    public void subclassesAsync_(Object objWbemSink, int iFlags, Object objWbemNamedValueSet) {
        Dispatch.call(this, "SubclassesAsync_", objWbemSink, new Variant(iFlags), //$NON-NLS-1$
                objWbemNamedValueSet);
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param iFlags
     *            an input-parameter of type int
     */
    public void subclassesAsync_(Object objWbemSink, int iFlags) {
        Dispatch.call(this, "SubclassesAsync_", objWbemSink, new Variant(iFlags)); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     */
    public void subclassesAsync_(Object objWbemSink) {
        Dispatch.call(this, "SubclassesAsync_", objWbemSink); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strAssocClass
     *            an input-parameter of type String
     * @param strResultClass
     *            an input-parameter of type String
     * @param strResultRole
     *            an input-parameter of type String
     * @param strRole
     *            an input-parameter of type String
     * @param bClassesOnly
     *            an input-parameter of type boolean
     * @param bSchemaOnly
     *            an input-parameter of type boolean
     * @param strRequiredAssocQualifier
     *            an input-parameter of type String
     * @param strRequiredQualifier
     *            an input-parameter of type String
     * @param iFlags
     *            an input-parameter of type int
     * @param objWbemNamedValueSet
     *            an input-parameter of type Object
     * @return the result is of type ISWbemObjectSet
     */
    public ISWbemObjectSet associators_(String strAssocClass, String strResultClass,
            String strResultRole, String strRole, boolean bClassesOnly, boolean bSchemaOnly,
            String strRequiredAssocQualifier, String strRequiredQualifier, int iFlags,
            Object objWbemNamedValueSet) {
        return new ISWbemObjectSet(Dispatch.callN(
                this,
                "Associators_", //$NON-NLS-1$
                new Object[] { strAssocClass, strResultClass, strResultRole, strRole,
                        new Variant(bClassesOnly), new Variant(bSchemaOnly),
                        strRequiredAssocQualifier, strRequiredQualifier, new Variant(iFlags),
                        objWbemNamedValueSet }).toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strAssocClass
     *            an input-parameter of type String
     * @param strResultClass
     *            an input-parameter of type String
     * @param strResultRole
     *            an input-parameter of type String
     * @param strRole
     *            an input-parameter of type String
     * @param bClassesOnly
     *            an input-parameter of type boolean
     * @param bSchemaOnly
     *            an input-parameter of type boolean
     * @param strRequiredAssocQualifier
     *            an input-parameter of type String
     * @param strRequiredQualifier
     *            an input-parameter of type String
     * @param iFlags
     *            an input-parameter of type int
     * @return the result is of type ISWbemObjectSet
     */
    public ISWbemObjectSet associators_(String strAssocClass, String strResultClass,
            String strResultRole, String strRole, boolean bClassesOnly, boolean bSchemaOnly,
            String strRequiredAssocQualifier, String strRequiredQualifier, int iFlags) {
        return new ISWbemObjectSet(Dispatch.callN(
                this,
                "Associators_", //$NON-NLS-1$
                new Object[] { strAssocClass, strResultClass, strResultRole, strRole,
                        new Variant(bClassesOnly), new Variant(bSchemaOnly),
                        strRequiredAssocQualifier, strRequiredQualifier, new Variant(iFlags) })
                .toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strAssocClass
     *            an input-parameter of type String
     * @param strResultClass
     *            an input-parameter of type String
     * @param strResultRole
     *            an input-parameter of type String
     * @param strRole
     *            an input-parameter of type String
     * @param bClassesOnly
     *            an input-parameter of type boolean
     * @param bSchemaOnly
     *            an input-parameter of type boolean
     * @param strRequiredAssocQualifier
     *            an input-parameter of type String
     * @param strRequiredQualifier
     *            an input-parameter of type String
     * @return the result is of type ISWbemObjectSet
     */
    public ISWbemObjectSet associators_(String strAssocClass, String strResultClass,
            String strResultRole, String strRole, boolean bClassesOnly, boolean bSchemaOnly,
            String strRequiredAssocQualifier, String strRequiredQualifier) {
        return new ISWbemObjectSet(Dispatch.call(this, "Associators_", strAssocClass, //$NON-NLS-1$
                strResultClass, strResultRole, strRole, new Variant(bClassesOnly),
                new Variant(bSchemaOnly), strRequiredAssocQualifier, strRequiredQualifier)
                .toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strAssocClass
     *            an input-parameter of type String
     * @param strResultClass
     *            an input-parameter of type String
     * @param strResultRole
     *            an input-parameter of type String
     * @param strRole
     *            an input-parameter of type String
     * @param bClassesOnly
     *            an input-parameter of type boolean
     * @param bSchemaOnly
     *            an input-parameter of type boolean
     * @param strRequiredAssocQualifier
     *            an input-parameter of type String
     * @return the result is of type ISWbemObjectSet
     */
    public ISWbemObjectSet associators_(String strAssocClass, String strResultClass,
            String strResultRole, String strRole, boolean bClassesOnly, boolean bSchemaOnly,
            String strRequiredAssocQualifier) {
        return new ISWbemObjectSet(Dispatch.call(this, "Associators_", strAssocClass, //$NON-NLS-1$
                strResultClass, strResultRole, strRole, new Variant(bClassesOnly),
                new Variant(bSchemaOnly), strRequiredAssocQualifier).toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strAssocClass
     *            an input-parameter of type String
     * @param strResultClass
     *            an input-parameter of type String
     * @param strResultRole
     *            an input-parameter of type String
     * @param strRole
     *            an input-parameter of type String
     * @param bClassesOnly
     *            an input-parameter of type boolean
     * @param bSchemaOnly
     *            an input-parameter of type boolean
     * @return the result is of type ISWbemObjectSet
     */
    public ISWbemObjectSet associators_(String strAssocClass, String strResultClass,
            String strResultRole, String strRole, boolean bClassesOnly, boolean bSchemaOnly) {
        return new ISWbemObjectSet(Dispatch.call(this, "Associators_", strAssocClass, //$NON-NLS-1$
                strResultClass, strResultRole, strRole, new Variant(bClassesOnly),
                new Variant(bSchemaOnly)).toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strAssocClass
     *            an input-parameter of type String
     * @param strResultClass
     *            an input-parameter of type String
     * @param strResultRole
     *            an input-parameter of type String
     * @param strRole
     *            an input-parameter of type String
     * @param bClassesOnly
     *            an input-parameter of type boolean
     * @return the result is of type ISWbemObjectSet
     */
    public ISWbemObjectSet associators_(String strAssocClass, String strResultClass,
            String strResultRole, String strRole, boolean bClassesOnly) {
        return new ISWbemObjectSet(Dispatch.call(this, "Associators_", strAssocClass, //$NON-NLS-1$
                strResultClass, strResultRole, strRole, new Variant(bClassesOnly)).toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strAssocClass
     *            an input-parameter of type String
     * @param strResultClass
     *            an input-parameter of type String
     * @param strResultRole
     *            an input-parameter of type String
     * @param strRole
     *            an input-parameter of type String
     * @return the result is of type ISWbemObjectSet
     */
    public ISWbemObjectSet associators_(String strAssocClass, String strResultClass,
            String strResultRole, String strRole) {
        return new ISWbemObjectSet(Dispatch.call(this, "Associators_", strAssocClass, //$NON-NLS-1$
                strResultClass, strResultRole, strRole).toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strAssocClass
     *            an input-parameter of type String
     * @param strResultClass
     *            an input-parameter of type String
     * @param strResultRole
     *            an input-parameter of type String
     * @return the result is of type ISWbemObjectSet
     */
    public ISWbemObjectSet associators_(String strAssocClass, String strResultClass,
            String strResultRole) {
        return new ISWbemObjectSet(Dispatch.call(this, "Associators_", strAssocClass, //$NON-NLS-1$
                strResultClass, strResultRole).toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strAssocClass
     *            an input-parameter of type String
     * @param strResultClass
     *            an input-parameter of type String
     * @return the result is of type ISWbemObjectSet
     */
    public ISWbemObjectSet associators_(String strAssocClass, String strResultClass) {
        return new ISWbemObjectSet(Dispatch.call(this, "Associators_", strAssocClass, //$NON-NLS-1$
                strResultClass).toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strAssocClass
     *            an input-parameter of type String
     * @return the result is of type ISWbemObjectSet
     */
    public ISWbemObjectSet associators_(String strAssocClass) {
        return new ISWbemObjectSet(Dispatch.call(this, "Associators_", strAssocClass).toDispatch()); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type ISWbemObjectSet
     */
    public ISWbemObjectSet associators_() {
        return new ISWbemObjectSet(Dispatch.call(this, "Associators_").toDispatch()); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strAssocClass
     *            an input-parameter of type String
     * @param strResultClass
     *            an input-parameter of type String
     * @param strResultRole
     *            an input-parameter of type String
     * @param strRole
     *            an input-parameter of type String
     * @param bClassesOnly
     *            an input-parameter of type boolean
     * @param bSchemaOnly
     *            an input-parameter of type boolean
     * @param strRequiredAssocQualifier
     *            an input-parameter of type String
     * @param strRequiredQualifier
     *            an input-parameter of type String
     * @param iFlags
     *            an input-parameter of type int
     * @param objWbemNamedValueSet
     *            an input-parameter of type Object
     * @param objWbemAsyncContext
     *            an input-parameter of type Object
     */
    public void associatorsAsync_(Object objWbemSink, String strAssocClass, String strResultClass,
            String strResultRole, String strRole, boolean bClassesOnly, boolean bSchemaOnly,
            String strRequiredAssocQualifier, String strRequiredQualifier, int iFlags,
            Object objWbemNamedValueSet, Object objWbemAsyncContext) {
        Dispatch.callN(this, "AssociatorsAsync_", new Object[] { objWbemSink, strAssocClass, //$NON-NLS-1$
                strResultClass, strResultRole, strRole, new Variant(bClassesOnly),
                new Variant(bSchemaOnly), strRequiredAssocQualifier, strRequiredQualifier,
                new Variant(iFlags), objWbemNamedValueSet, objWbemAsyncContext });
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strAssocClass
     *            an input-parameter of type String
     * @param strResultClass
     *            an input-parameter of type String
     * @param strResultRole
     *            an input-parameter of type String
     * @param strRole
     *            an input-parameter of type String
     * @param bClassesOnly
     *            an input-parameter of type boolean
     * @param bSchemaOnly
     *            an input-parameter of type boolean
     * @param strRequiredAssocQualifier
     *            an input-parameter of type String
     * @param strRequiredQualifier
     *            an input-parameter of type String
     * @param iFlags
     *            an input-parameter of type int
     * @param objWbemNamedValueSet
     *            an input-parameter of type Object
     */
    public void associatorsAsync_(Object objWbemSink, String strAssocClass, String strResultClass,
            String strResultRole, String strRole, boolean bClassesOnly, boolean bSchemaOnly,
            String strRequiredAssocQualifier, String strRequiredQualifier, int iFlags,
            Object objWbemNamedValueSet) {
        Dispatch.callN(this, "AssociatorsAsync_", new Object[] { objWbemSink, strAssocClass, //$NON-NLS-1$
                strResultClass, strResultRole, strRole, new Variant(bClassesOnly),
                new Variant(bSchemaOnly), strRequiredAssocQualifier, strRequiredQualifier,
                new Variant(iFlags), objWbemNamedValueSet });
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strAssocClass
     *            an input-parameter of type String
     * @param strResultClass
     *            an input-parameter of type String
     * @param strResultRole
     *            an input-parameter of type String
     * @param strRole
     *            an input-parameter of type String
     * @param bClassesOnly
     *            an input-parameter of type boolean
     * @param bSchemaOnly
     *            an input-parameter of type boolean
     * @param strRequiredAssocQualifier
     *            an input-parameter of type String
     * @param strRequiredQualifier
     *            an input-parameter of type String
     * @param iFlags
     *            an input-parameter of type int
     */
    public void associatorsAsync_(Object objWbemSink, String strAssocClass, String strResultClass,
            String strResultRole, String strRole, boolean bClassesOnly, boolean bSchemaOnly,
            String strRequiredAssocQualifier, String strRequiredQualifier, int iFlags) {
        Dispatch.callN(this, "AssociatorsAsync_", new Object[] { objWbemSink, strAssocClass, //$NON-NLS-1$
                strResultClass, strResultRole, strRole, new Variant(bClassesOnly),
                new Variant(bSchemaOnly), strRequiredAssocQualifier, strRequiredQualifier,
                new Variant(iFlags) });
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strAssocClass
     *            an input-parameter of type String
     * @param strResultClass
     *            an input-parameter of type String
     * @param strResultRole
     *            an input-parameter of type String
     * @param strRole
     *            an input-parameter of type String
     * @param bClassesOnly
     *            an input-parameter of type boolean
     * @param bSchemaOnly
     *            an input-parameter of type boolean
     * @param strRequiredAssocQualifier
     *            an input-parameter of type String
     * @param strRequiredQualifier
     *            an input-parameter of type String
     */
    public void associatorsAsync_(Object objWbemSink, String strAssocClass, String strResultClass,
            String strResultRole, String strRole, boolean bClassesOnly, boolean bSchemaOnly,
            String strRequiredAssocQualifier, String strRequiredQualifier) {
        Dispatch.callN(this, "AssociatorsAsync_", new Object[] { objWbemSink, strAssocClass, //$NON-NLS-1$
                strResultClass, strResultRole, strRole, new Variant(bClassesOnly),
                new Variant(bSchemaOnly), strRequiredAssocQualifier, strRequiredQualifier });
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strAssocClass
     *            an input-parameter of type String
     * @param strResultClass
     *            an input-parameter of type String
     * @param strResultRole
     *            an input-parameter of type String
     * @param strRole
     *            an input-parameter of type String
     * @param bClassesOnly
     *            an input-parameter of type boolean
     * @param bSchemaOnly
     *            an input-parameter of type boolean
     * @param strRequiredAssocQualifier
     *            an input-parameter of type String
     */
    public void associatorsAsync_(Object objWbemSink, String strAssocClass, String strResultClass,
            String strResultRole, String strRole, boolean bClassesOnly, boolean bSchemaOnly,
            String strRequiredAssocQualifier) {
        Dispatch.call(this, "AssociatorsAsync_", objWbemSink, strAssocClass, strResultClass, //$NON-NLS-1$
                strResultRole, strRole, new Variant(bClassesOnly), new Variant(bSchemaOnly),
                strRequiredAssocQualifier);
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strAssocClass
     *            an input-parameter of type String
     * @param strResultClass
     *            an input-parameter of type String
     * @param strResultRole
     *            an input-parameter of type String
     * @param strRole
     *            an input-parameter of type String
     * @param bClassesOnly
     *            an input-parameter of type boolean
     * @param bSchemaOnly
     *            an input-parameter of type boolean
     */
    public void associatorsAsync_(Object objWbemSink, String strAssocClass, String strResultClass,
            String strResultRole, String strRole, boolean bClassesOnly, boolean bSchemaOnly) {
        Dispatch.call(this, "AssociatorsAsync_", objWbemSink, strAssocClass, strResultClass, //$NON-NLS-1$
                strResultRole, strRole, new Variant(bClassesOnly), new Variant(bSchemaOnly));
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strAssocClass
     *            an input-parameter of type String
     * @param strResultClass
     *            an input-parameter of type String
     * @param strResultRole
     *            an input-parameter of type String
     * @param strRole
     *            an input-parameter of type String
     * @param bClassesOnly
     *            an input-parameter of type boolean
     */
    public void associatorsAsync_(Object objWbemSink, String strAssocClass, String strResultClass,
            String strResultRole, String strRole, boolean bClassesOnly) {
        Dispatch.call(this, "AssociatorsAsync_", objWbemSink, strAssocClass, strResultClass, //$NON-NLS-1$
                strResultRole, strRole, new Variant(bClassesOnly));
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strAssocClass
     *            an input-parameter of type String
     * @param strResultClass
     *            an input-parameter of type String
     * @param strResultRole
     *            an input-parameter of type String
     * @param strRole
     *            an input-parameter of type String
     */
    public void associatorsAsync_(Object objWbemSink, String strAssocClass, String strResultClass,
            String strResultRole, String strRole) {
        Dispatch.call(this, "AssociatorsAsync_", objWbemSink, strAssocClass, strResultClass, //$NON-NLS-1$
                strResultRole, strRole);
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strAssocClass
     *            an input-parameter of type String
     * @param strResultClass
     *            an input-parameter of type String
     * @param strResultRole
     *            an input-parameter of type String
     */
    public void associatorsAsync_(Object objWbemSink, String strAssocClass, String strResultClass,
            String strResultRole) {
        Dispatch.call(this, "AssociatorsAsync_", objWbemSink, strAssocClass, strResultClass, //$NON-NLS-1$
                strResultRole);
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strAssocClass
     *            an input-parameter of type String
     * @param strResultClass
     *            an input-parameter of type String
     */
    public void associatorsAsync_(Object objWbemSink, String strAssocClass, String strResultClass) {
        Dispatch.call(this, "AssociatorsAsync_", objWbemSink, strAssocClass, strResultClass); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strAssocClass
     *            an input-parameter of type String
     */
    public void associatorsAsync_(Object objWbemSink, String strAssocClass) {
        Dispatch.call(this, "AssociatorsAsync_", objWbemSink, strAssocClass); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     */
    public void associatorsAsync_(Object objWbemSink) {
        Dispatch.call(this, "AssociatorsAsync_", objWbemSink); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strResultClass
     *            an input-parameter of type String
     * @param strRole
     *            an input-parameter of type String
     * @param bClassesOnly
     *            an input-parameter of type boolean
     * @param bSchemaOnly
     *            an input-parameter of type boolean
     * @param strRequiredQualifier
     *            an input-parameter of type String
     * @param iFlags
     *            an input-parameter of type int
     * @param objWbemNamedValueSet
     *            an input-parameter of type Object
     * @return the result is of type ISWbemObjectSet
     */
    public ISWbemObjectSet references_(String strResultClass, String strRole, boolean bClassesOnly,
            boolean bSchemaOnly, String strRequiredQualifier, int iFlags,
            Object objWbemNamedValueSet) {
        return new ISWbemObjectSet(Dispatch.call(this, "References_", strResultClass, strRole, //$NON-NLS-1$
                new Variant(bClassesOnly), new Variant(bSchemaOnly), strRequiredQualifier,
                new Variant(iFlags), objWbemNamedValueSet).toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strResultClass
     *            an input-parameter of type String
     * @param strRole
     *            an input-parameter of type String
     * @param bClassesOnly
     *            an input-parameter of type boolean
     * @param bSchemaOnly
     *            an input-parameter of type boolean
     * @param strRequiredQualifier
     *            an input-parameter of type String
     * @param iFlags
     *            an input-parameter of type int
     * @return the result is of type ISWbemObjectSet
     */
    public ISWbemObjectSet references_(String strResultClass, String strRole, boolean bClassesOnly,
            boolean bSchemaOnly, String strRequiredQualifier, int iFlags) {
        return new ISWbemObjectSet(Dispatch.call(this, "References_", strResultClass, strRole, //$NON-NLS-1$
                new Variant(bClassesOnly), new Variant(bSchemaOnly), strRequiredQualifier,
                new Variant(iFlags)).toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strResultClass
     *            an input-parameter of type String
     * @param strRole
     *            an input-parameter of type String
     * @param bClassesOnly
     *            an input-parameter of type boolean
     * @param bSchemaOnly
     *            an input-parameter of type boolean
     * @param strRequiredQualifier
     *            an input-parameter of type String
     * @return the result is of type ISWbemObjectSet
     */
    public ISWbemObjectSet references_(String strResultClass, String strRole, boolean bClassesOnly,
            boolean bSchemaOnly, String strRequiredQualifier) {
        return new ISWbemObjectSet(Dispatch.call(this, "References_", strResultClass, strRole, //$NON-NLS-1$
                new Variant(bClassesOnly), new Variant(bSchemaOnly), strRequiredQualifier)
                .toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strResultClass
     *            an input-parameter of type String
     * @param strRole
     *            an input-parameter of type String
     * @param bClassesOnly
     *            an input-parameter of type boolean
     * @param bSchemaOnly
     *            an input-parameter of type boolean
     * @return the result is of type ISWbemObjectSet
     */
    public ISWbemObjectSet references_(String strResultClass, String strRole, boolean bClassesOnly,
            boolean bSchemaOnly) {
        return new ISWbemObjectSet(Dispatch.call(this, "References_", strResultClass, strRole, //$NON-NLS-1$
                new Variant(bClassesOnly), new Variant(bSchemaOnly)).toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strResultClass
     *            an input-parameter of type String
     * @param strRole
     *            an input-parameter of type String
     * @param bClassesOnly
     *            an input-parameter of type boolean
     * @return the result is of type ISWbemObjectSet
     */
    public ISWbemObjectSet references_(String strResultClass, String strRole, boolean bClassesOnly) {
        return new ISWbemObjectSet(Dispatch.call(this, "References_", strResultClass, strRole, //$NON-NLS-1$
                new Variant(bClassesOnly)).toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strResultClass
     *            an input-parameter of type String
     * @param strRole
     *            an input-parameter of type String
     * @return the result is of type ISWbemObjectSet
     */
    public ISWbemObjectSet references_(String strResultClass, String strRole) {
        return new ISWbemObjectSet(Dispatch.call(this, "References_", strResultClass, strRole) //$NON-NLS-1$
                .toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strResultClass
     *            an input-parameter of type String
     * @return the result is of type ISWbemObjectSet
     */
    public ISWbemObjectSet references_(String strResultClass) {
        return new ISWbemObjectSet(Dispatch.call(this, "References_", strResultClass).toDispatch()); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type ISWbemObjectSet
     */
    public ISWbemObjectSet references_() {
        return new ISWbemObjectSet(Dispatch.call(this, "References_").toDispatch()); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strResultClass
     *            an input-parameter of type String
     * @param strRole
     *            an input-parameter of type String
     * @param bClassesOnly
     *            an input-parameter of type boolean
     * @param bSchemaOnly
     *            an input-parameter of type boolean
     * @param strRequiredQualifier
     *            an input-parameter of type String
     * @param iFlags
     *            an input-parameter of type int
     * @param objWbemNamedValueSet
     *            an input-parameter of type Object
     * @param objWbemAsyncContext
     *            an input-parameter of type Object
     */
    public void referencesAsync_(Object objWbemSink, String strResultClass, String strRole,
            boolean bClassesOnly, boolean bSchemaOnly, String strRequiredQualifier, int iFlags,
            Object objWbemNamedValueSet, Object objWbemAsyncContext) {
        Dispatch.callN(this, "ReferencesAsync_", new Object[] { objWbemSink, strResultClass, //$NON-NLS-1$
                strRole, new Variant(bClassesOnly), new Variant(bSchemaOnly), strRequiredQualifier,
                new Variant(iFlags), objWbemNamedValueSet, objWbemAsyncContext });
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strResultClass
     *            an input-parameter of type String
     * @param strRole
     *            an input-parameter of type String
     * @param bClassesOnly
     *            an input-parameter of type boolean
     * @param bSchemaOnly
     *            an input-parameter of type boolean
     * @param strRequiredQualifier
     *            an input-parameter of type String
     * @param iFlags
     *            an input-parameter of type int
     * @param objWbemNamedValueSet
     *            an input-parameter of type Object
     */
    public void referencesAsync_(Object objWbemSink, String strResultClass, String strRole,
            boolean bClassesOnly, boolean bSchemaOnly, String strRequiredQualifier, int iFlags,
            Object objWbemNamedValueSet) {
        Dispatch.call(this, "ReferencesAsync_", objWbemSink, strResultClass, strRole, new Variant( //$NON-NLS-1$
                bClassesOnly), new Variant(bSchemaOnly), strRequiredQualifier, new Variant(iFlags),
                objWbemNamedValueSet);
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strResultClass
     *            an input-parameter of type String
     * @param strRole
     *            an input-parameter of type String
     * @param bClassesOnly
     *            an input-parameter of type boolean
     * @param bSchemaOnly
     *            an input-parameter of type boolean
     * @param strRequiredQualifier
     *            an input-parameter of type String
     * @param iFlags
     *            an input-parameter of type int
     */
    public void referencesAsync_(Object objWbemSink, String strResultClass, String strRole,
            boolean bClassesOnly, boolean bSchemaOnly, String strRequiredQualifier, int iFlags) {
        Dispatch.call(this, "ReferencesAsync_", objWbemSink, strResultClass, strRole, new Variant( //$NON-NLS-1$
                bClassesOnly), new Variant(bSchemaOnly), strRequiredQualifier, new Variant(iFlags));
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strResultClass
     *            an input-parameter of type String
     * @param strRole
     *            an input-parameter of type String
     * @param bClassesOnly
     *            an input-parameter of type boolean
     * @param bSchemaOnly
     *            an input-parameter of type boolean
     * @param strRequiredQualifier
     *            an input-parameter of type String
     */
    public void referencesAsync_(Object objWbemSink, String strResultClass, String strRole,
            boolean bClassesOnly, boolean bSchemaOnly, String strRequiredQualifier) {
        Dispatch.call(this, "ReferencesAsync_", objWbemSink, strResultClass, strRole, new Variant( //$NON-NLS-1$
                bClassesOnly), new Variant(bSchemaOnly), strRequiredQualifier);
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strResultClass
     *            an input-parameter of type String
     * @param strRole
     *            an input-parameter of type String
     * @param bClassesOnly
     *            an input-parameter of type boolean
     * @param bSchemaOnly
     *            an input-parameter of type boolean
     */
    public void referencesAsync_(Object objWbemSink, String strResultClass, String strRole,
            boolean bClassesOnly, boolean bSchemaOnly) {
        Dispatch.call(this, "ReferencesAsync_", objWbemSink, strResultClass, strRole, new Variant( //$NON-NLS-1$
                bClassesOnly), new Variant(bSchemaOnly));
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strResultClass
     *            an input-parameter of type String
     * @param strRole
     *            an input-parameter of type String
     * @param bClassesOnly
     *            an input-parameter of type boolean
     */
    public void referencesAsync_(Object objWbemSink, String strResultClass, String strRole,
            boolean bClassesOnly) {
        Dispatch.call(this, "ReferencesAsync_", objWbemSink, strResultClass, strRole, new Variant( //$NON-NLS-1$
                bClassesOnly));
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strResultClass
     *            an input-parameter of type String
     * @param strRole
     *            an input-parameter of type String
     */
    public void referencesAsync_(Object objWbemSink, String strResultClass, String strRole) {
        Dispatch.call(this, "ReferencesAsync_", objWbemSink, strResultClass, strRole); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strResultClass
     *            an input-parameter of type String
     */
    public void referencesAsync_(Object objWbemSink, String strResultClass) {
        Dispatch.call(this, "ReferencesAsync_", objWbemSink, strResultClass); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     */
    public void referencesAsync_(Object objWbemSink) {
        Dispatch.call(this, "ReferencesAsync_", objWbemSink); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strMethodName
     *            an input-parameter of type String
     * @param objWbemInParameters
     *            an input-parameter of type Object
     * @param iFlags
     *            an input-parameter of type int
     * @param objWbemNamedValueSet
     *            an input-parameter of type Object
     * @return the result is of type ISWbemObject
     */
    public ISWbemObject execMethod_(String strMethodName, Object objWbemInParameters, int iFlags,
            Object objWbemNamedValueSet) {
        return new ISWbemObject(Dispatch.call(this, "ExecMethod_", strMethodName, //$NON-NLS-1$
                objWbemInParameters, new Variant(iFlags), objWbemNamedValueSet).toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strMethodName
     *            an input-parameter of type String
     * @param objWbemInParameters
     *            an input-parameter of type Object
     * @param iFlags
     *            an input-parameter of type int
     * @return the result is of type ISWbemObject
     */
    public ISWbemObject execMethod_(String strMethodName, Object objWbemInParameters, int iFlags) {
        return new ISWbemObject(Dispatch.call(this, "ExecMethod_", strMethodName, //$NON-NLS-1$
                objWbemInParameters, new Variant(iFlags)).toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strMethodName
     *            an input-parameter of type String
     * @param objWbemInParameters
     *            an input-parameter of type Object
     * @return the result is of type ISWbemObject
     */
    public ISWbemObject execMethod_(String strMethodName, Object objWbemInParameters) {
        return new ISWbemObject(Dispatch.call(this, "ExecMethod_", strMethodName, //$NON-NLS-1$
                objWbemInParameters).toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strMethodName
     *            an input-parameter of type String
     * @return the result is of type ISWbemObject
     */
    public ISWbemObject execMethod_(String strMethodName) {
        return new ISWbemObject(Dispatch.call(this, "ExecMethod_", strMethodName).toDispatch()); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strMethodName
     *            an input-parameter of type String
     * @param objWbemInParameters
     *            an input-parameter of type Object
     * @param iFlags
     *            an input-parameter of type int
     * @param objWbemNamedValueSet
     *            an input-parameter of type Object
     * @param objWbemAsyncContext
     *            an input-parameter of type Object
     */
    public void execMethodAsync_(Object objWbemSink, String strMethodName,
            Object objWbemInParameters, int iFlags, Object objWbemNamedValueSet,
            Object objWbemAsyncContext) {
        Dispatch.call(this, "ExecMethodAsync_", objWbemSink, strMethodName, objWbemInParameters, //$NON-NLS-1$
                new Variant(iFlags), objWbemNamedValueSet, objWbemAsyncContext);
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strMethodName
     *            an input-parameter of type String
     * @param objWbemInParameters
     *            an input-parameter of type Object
     * @param iFlags
     *            an input-parameter of type int
     * @param objWbemNamedValueSet
     *            an input-parameter of type Object
     */
    public void execMethodAsync_(Object objWbemSink, String strMethodName,
            Object objWbemInParameters, int iFlags, Object objWbemNamedValueSet) {
        Dispatch.call(this, "ExecMethodAsync_", objWbemSink, strMethodName, objWbemInParameters, //$NON-NLS-1$
                new Variant(iFlags), objWbemNamedValueSet);
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strMethodName
     *            an input-parameter of type String
     * @param objWbemInParameters
     *            an input-parameter of type Object
     * @param iFlags
     *            an input-parameter of type int
     */
    public void execMethodAsync_(Object objWbemSink, String strMethodName,
            Object objWbemInParameters, int iFlags) {
        Dispatch.call(this, "ExecMethodAsync_", objWbemSink, strMethodName, objWbemInParameters, //$NON-NLS-1$
                new Variant(iFlags));
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strMethodName
     *            an input-parameter of type String
     * @param objWbemInParameters
     *            an input-parameter of type Object
     */
    public void execMethodAsync_(Object objWbemSink, String strMethodName,
            Object objWbemInParameters) {
        Dispatch.call(this, "ExecMethodAsync_", objWbemSink, strMethodName, objWbemInParameters); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strMethodName
     *            an input-parameter of type String
     */
    public void execMethodAsync_(Object objWbemSink, String strMethodName) {
        Dispatch.call(this, "ExecMethodAsync_", objWbemSink, strMethodName); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type ISWbemObject
     */
    public ISWbemObject clone_() {
        return new ISWbemObject(Dispatch.call(this, "Clone_").toDispatch()); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param iFlags
     *            an input-parameter of type int
     * @return the result is of type String
     */
    public String getObjectText_(int iFlags) {
        return Dispatch.call(this, "GetObjectText_", new Variant(iFlags)).toString(); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type String
     */
    public String getObjectText_() {
        return Dispatch.call(this, "GetObjectText_").toString(); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param iFlags
     *            an input-parameter of type int
     * @return the result is of type ISWbemObject
     */
    public ISWbemObject spawnDerivedClass_(int iFlags) {
        return new ISWbemObject(Dispatch.call(this, "SpawnDerivedClass_", new Variant(iFlags)) //$NON-NLS-1$
                .toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type ISWbemObject
     */
    public ISWbemObject spawnDerivedClass_() {
        return new ISWbemObject(Dispatch.call(this, "SpawnDerivedClass_").toDispatch()); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param iFlags
     *            an input-parameter of type int
     * @return the result is of type ISWbemObject
     */
    public ISWbemObject spawnInstance_(int iFlags) {
        return new ISWbemObject(Dispatch.call(this, "SpawnInstance_", new Variant(iFlags)) //$NON-NLS-1$
                .toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type ISWbemObject
     */
    public ISWbemObject spawnInstance_() {
        return new ISWbemObject(Dispatch.call(this, "SpawnInstance_").toDispatch()); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemObject
     *            an input-parameter of type Object
     * @param iFlags
     *            an input-parameter of type int
     * @return the result is of type boolean
     */
    public boolean compareTo_(Object objWbemObject, int iFlags) {
        return Dispatch.call(this, "CompareTo_", objWbemObject, new Variant(iFlags)).changeType( //$NON-NLS-1$
                Variant.VariantBoolean).getBoolean();
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemObject
     *            an input-parameter of type Object
     * @return the result is of type boolean
     */
    public boolean compareTo_(Object objWbemObject) {
        return Dispatch.call(this, "CompareTo_", objWbemObject).changeType(Variant.VariantBoolean) //$NON-NLS-1$
                .getBoolean();
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
     * @return the result is of type ISWbemPropertySet
     */
    public ISWbemPropertySet getProperties_() {
        return new ISWbemPropertySet(Dispatch.get(this, "Properties_").toDispatch()); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type ISWbemMethodSet
     */
    public ISWbemMethodSet getMethods_() {
        return new ISWbemMethodSet(Dispatch.get(this, "Methods_").toDispatch()); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type Variant
     */
    public Variant getDerivation_() {
        return Dispatch.get(this, "Derivation_"); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type ISWbemObjectPath
     */
    public ISWbemObjectPath getPath_() {
        return new ISWbemObjectPath(Dispatch.get(this, "Path_").toDispatch()); //$NON-NLS-1$
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
