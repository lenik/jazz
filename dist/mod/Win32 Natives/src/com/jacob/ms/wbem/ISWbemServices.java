/**
 * JacobGen generated file --- do not edit
 *
 * (http://www.sourceforge.net/projects/jacob-project */
package com.jacob.ms.wbem;

import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class ISWbemServices extends Dispatch {

    public static final String componentName = "WbemScripting.ISWbemServices"; //$NON-NLS-1$

    public ISWbemServices() {
        super(componentName);
    }

    /**
     * This constructor is used instead of a case operation to turn a Dispatch
     * object into a wider object - it must exist in every wrapper class whose
     * instances may be returned from method calls wrapped in VT_DISPATCH
     * Variants.
     */
    public ISWbemServices(Dispatch d) {
        // take over the IDispatch pointer
        m_pDispatch = d.m_pDispatch;
        // null out the input's pointer
        d.m_pDispatch = 0;
    }

    public ISWbemServices(String compName) {
        super(compName);
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strObjectPath
     *            an input-parameter of type String
     * @param iFlags
     *            an input-parameter of type int
     * @return the result is of type ISWbemObject
     */
    public ISWbemObject get(String strObjectPath, int iFlags) {
        return new ISWbemObject(Dispatch.call(this, "Get", strObjectPath, new Variant(iFlags)) //$NON-NLS-1$
                .toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strObjectPath
     *            an input-parameter of type String
     * @return the result is of type ISWbemObject
     */
    public ISWbemObject get(String strObjectPath) {
        return new ISWbemObject(Dispatch.call(this, "Get", strObjectPath).toDispatch()); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type ISWbemObject
     */
    public ISWbemObject get() {
        return new ISWbemObject(Dispatch.call(this, "Get").toDispatch()); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strObjectPath
     *            an input-parameter of type String
     * @param iFlags
     *            an input-parameter of type int
     * @param objWbemNamedValueSet
     *            an input-parameter of type Object
     */
    public void getAsync(Object objWbemSink, String strObjectPath, int iFlags,
            Object objWbemNamedValueSet) {
        Dispatch.call(this, "GetAsync", objWbemSink, strObjectPath, new Variant(iFlags), //$NON-NLS-1$
                objWbemNamedValueSet);
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strObjectPath
     *            an input-parameter of type String
     * @param iFlags
     *            an input-parameter of type int
     */
    public void getAsync(Object objWbemSink, String strObjectPath, int iFlags) {
        Dispatch.call(this, "GetAsync", objWbemSink, strObjectPath, new Variant(iFlags)); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strObjectPath
     *            an input-parameter of type String
     */
    public void getAsync(Object objWbemSink, String strObjectPath) {
        Dispatch.call(this, "GetAsync", objWbemSink, strObjectPath); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     */
    public void getAsync(Object objWbemSink) {
        Dispatch.call(this, "GetAsync", objWbemSink); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method and receiving the
     * output-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strObjectPath
     *            an input-parameter of type String
     * @param iFlags
     *            an input-parameter of type int
     * @param objWbemNamedValueSet
     *            an input-parameter of type Object
     * @param objWbemAsyncContext
     *            an input-parameter of type Object
     */
    public void getAsync(Object objWbemSink, String strObjectPath, int iFlags,
            Object objWbemNamedValueSet, Object objWbemAsyncContext) {
        Dispatch.call(this, "GetAsync", objWbemSink, strObjectPath, new Variant(iFlags), //$NON-NLS-1$
                objWbemNamedValueSet, objWbemAsyncContext);

    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strObjectPath
     *            an input-parameter of type String
     * @param iFlags
     *            an input-parameter of type int
     */
    public void delete(String strObjectPath, int iFlags) {
        Dispatch.call(this, "Delete", strObjectPath, new Variant(iFlags)); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strObjectPath
     *            an input-parameter of type String
     */
    public void delete(String strObjectPath) {
        Dispatch.call(this, "Delete", strObjectPath); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method and receiving the
     * output-parameter(s).
     * 
     * @param strObjectPath
     *            an input-parameter of type String
     * @param iFlags
     *            an input-parameter of type int
     * @param objWbemNamedValueSet
     *            an input-parameter of type Object
     */
    public void delete(String strObjectPath, int iFlags, Object objWbemNamedValueSet) {
        Dispatch.call(this, "Delete", strObjectPath, new Variant(iFlags), objWbemNamedValueSet); //$NON-NLS-1$

    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strObjectPath
     *            an input-parameter of type String
     * @param iFlags
     *            an input-parameter of type int
     * @param objWbemNamedValueSet
     *            an input-parameter of type Object
     */
    public void deleteAsync(Object objWbemSink, String strObjectPath, int iFlags,
            Object objWbemNamedValueSet) {
        Dispatch.call(this, "DeleteAsync", objWbemSink, strObjectPath, new Variant(iFlags), //$NON-NLS-1$
                objWbemNamedValueSet);
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strObjectPath
     *            an input-parameter of type String
     * @param iFlags
     *            an input-parameter of type int
     */
    public void deleteAsync(Object objWbemSink, String strObjectPath, int iFlags) {
        Dispatch.call(this, "DeleteAsync", objWbemSink, strObjectPath, new Variant(iFlags)); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strObjectPath
     *            an input-parameter of type String
     */
    public void deleteAsync(Object objWbemSink, String strObjectPath) {
        Dispatch.call(this, "DeleteAsync", objWbemSink, strObjectPath); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method and receiving the
     * output-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strObjectPath
     *            an input-parameter of type String
     * @param iFlags
     *            an input-parameter of type int
     * @param objWbemNamedValueSet
     *            an input-parameter of type Object
     * @param objWbemAsyncContext
     *            an input-parameter of type Object
     */
    public void deleteAsync(Object objWbemSink, String strObjectPath, int iFlags,
            Object objWbemNamedValueSet, Object objWbemAsyncContext) {
        Dispatch.call(this, "DeleteAsync", objWbemSink, strObjectPath, new Variant(iFlags), //$NON-NLS-1$
                objWbemNamedValueSet, objWbemAsyncContext);

    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strClass
     *            an input-parameter of type String
     * @param iFlags
     *            an input-parameter of type int
     * @return the result is of type ISWbemObjectSet
     */
    public ISWbemObjectSet instancesOf(String strClass, int iFlags) {
        return new ISWbemObjectSet(Dispatch
                .call(this, "InstancesOf", strClass, new Variant(iFlags)).toDispatch()); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strClass
     *            an input-parameter of type String
     * @return the result is of type ISWbemObjectSet
     */
    public ISWbemObjectSet instancesOf(String strClass) {
        return new ISWbemObjectSet(Dispatch.call(this, "InstancesOf", strClass).toDispatch()); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method and receiving the
     * output-parameter(s).
     * 
     * @param strClass
     *            an input-parameter of type String
     * @param iFlags
     *            an input-parameter of type int
     * @param objWbemNamedValueSet
     *            an input-parameter of type Object
     * @return the result is of type ISWbemObjectSet
     */
    public ISWbemObjectSet instancesOf(String strClass, int iFlags, Object objWbemNamedValueSet) {
        ISWbemObjectSet result_of_InstancesOf = new ISWbemObjectSet(Dispatch.call(this,
                "InstancesOf", strClass, new Variant(iFlags), objWbemNamedValueSet).toDispatch()); //$NON-NLS-1$

        return result_of_InstancesOf;
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strClass
     *            an input-parameter of type String
     * @param iFlags
     *            an input-parameter of type int
     * @param objWbemNamedValueSet
     *            an input-parameter of type Object
     */
    public void instancesOfAsync(Object objWbemSink, String strClass, int iFlags,
            Object objWbemNamedValueSet) {
        Dispatch.call(this, "InstancesOfAsync", objWbemSink, strClass, new Variant(iFlags), //$NON-NLS-1$
                objWbemNamedValueSet);
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strClass
     *            an input-parameter of type String
     * @param iFlags
     *            an input-parameter of type int
     */
    public void instancesOfAsync(Object objWbemSink, String strClass, int iFlags) {
        Dispatch.call(this, "InstancesOfAsync", objWbemSink, strClass, new Variant(iFlags)); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strClass
     *            an input-parameter of type String
     */
    public void instancesOfAsync(Object objWbemSink, String strClass) {
        Dispatch.call(this, "InstancesOfAsync", objWbemSink, strClass); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method and receiving the
     * output-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strClass
     *            an input-parameter of type String
     * @param iFlags
     *            an input-parameter of type int
     * @param objWbemNamedValueSet
     *            an input-parameter of type Object
     * @param objWbemAsyncContext
     *            an input-parameter of type Object
     */
    public void instancesOfAsync(Object objWbemSink, String strClass, int iFlags,
            Object objWbemNamedValueSet, Object objWbemAsyncContext) {
        Dispatch.call(this, "InstancesOfAsync", objWbemSink, strClass, new Variant(iFlags), //$NON-NLS-1$
                objWbemNamedValueSet, objWbemAsyncContext);

    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strSuperclass
     *            an input-parameter of type String
     * @param iFlags
     *            an input-parameter of type int
     * @return the result is of type ISWbemObjectSet
     */
    public ISWbemObjectSet subclassesOf(String strSuperclass, int iFlags) {
        return new ISWbemObjectSet(Dispatch.call(this, "SubclassesOf", strSuperclass, //$NON-NLS-1$
                new Variant(iFlags)).toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strSuperclass
     *            an input-parameter of type String
     * @return the result is of type ISWbemObjectSet
     */
    public ISWbemObjectSet subclassesOf(String strSuperclass) {
        return new ISWbemObjectSet(Dispatch.call(this, "SubclassesOf", strSuperclass).toDispatch()); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @return the result is of type ISWbemObjectSet
     */
    public ISWbemObjectSet subclassesOf() {
        return new ISWbemObjectSet(Dispatch.call(this, "SubclassesOf").toDispatch()); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method and receiving the
     * output-parameter(s).
     * 
     * @param strSuperclass
     *            an input-parameter of type String
     * @param iFlags
     *            an input-parameter of type int
     * @param objWbemNamedValueSet
     *            an input-parameter of type Object
     * @return the result is of type ISWbemObjectSet
     */
    public ISWbemObjectSet subclassesOf(String strSuperclass, int iFlags,
            Object objWbemNamedValueSet) {
        ISWbemObjectSet result_of_SubclassesOf = new ISWbemObjectSet(Dispatch.call(this,
                "SubclassesOf", strSuperclass, new Variant(iFlags), objWbemNamedValueSet) //$NON-NLS-1$
                .toDispatch());

        return result_of_SubclassesOf;
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strSuperclass
     *            an input-parameter of type String
     * @param iFlags
     *            an input-parameter of type int
     * @param objWbemNamedValueSet
     *            an input-parameter of type Object
     */
    public void subclassesOfAsync(Object objWbemSink, String strSuperclass, int iFlags,
            Object objWbemNamedValueSet) {
        Dispatch.call(this, "SubclassesOfAsync", objWbemSink, strSuperclass, new Variant(iFlags), //$NON-NLS-1$
                objWbemNamedValueSet);
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strSuperclass
     *            an input-parameter of type String
     * @param iFlags
     *            an input-parameter of type int
     */
    public void subclassesOfAsync(Object objWbemSink, String strSuperclass, int iFlags) {
        Dispatch.call(this, "SubclassesOfAsync", objWbemSink, strSuperclass, new Variant(iFlags)); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strSuperclass
     *            an input-parameter of type String
     */
    public void subclassesOfAsync(Object objWbemSink, String strSuperclass) {
        Dispatch.call(this, "SubclassesOfAsync", objWbemSink, strSuperclass); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     */
    public void subclassesOfAsync(Object objWbemSink) {
        Dispatch.call(this, "SubclassesOfAsync", objWbemSink); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method and receiving the
     * output-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strSuperclass
     *            an input-parameter of type String
     * @param iFlags
     *            an input-parameter of type int
     * @param objWbemNamedValueSet
     *            an input-parameter of type Object
     * @param objWbemAsyncContext
     *            an input-parameter of type Object
     */
    public void subclassesOfAsync(Object objWbemSink, String strSuperclass, int iFlags,
            Object objWbemNamedValueSet, Object objWbemAsyncContext) {
        Dispatch.call(this, "SubclassesOfAsync", objWbemSink, strSuperclass, new Variant(iFlags), //$NON-NLS-1$
                objWbemNamedValueSet, objWbemAsyncContext);

    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strQuery
     *            an input-parameter of type String
     * @param strQueryLanguage
     *            an input-parameter of type String
     * @param iFlags
     *            an input-parameter of type int
     * @return the result is of type ISWbemObjectSet
     */
    public ISWbemObjectSet execQuery(String strQuery, String strQueryLanguage, int iFlags) {
        return new ISWbemObjectSet(Dispatch.call(this, "ExecQuery", strQuery, strQueryLanguage, //$NON-NLS-1$
                new Variant(iFlags)).toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strQuery
     *            an input-parameter of type String
     * @param strQueryLanguage
     *            an input-parameter of type String
     * @return the result is of type ISWbemObjectSet
     */
    public ISWbemObjectSet execQuery(String strQuery, String strQueryLanguage) {
        return new ISWbemObjectSet(Dispatch.call(this, "ExecQuery", strQuery, strQueryLanguage) //$NON-NLS-1$
                .toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strQuery
     *            an input-parameter of type String
     * @return the result is of type ISWbemObjectSet
     */
    public ISWbemObjectSet execQuery(String strQuery) {
        return new ISWbemObjectSet(Dispatch.call(this, "ExecQuery", strQuery).toDispatch()); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method and receiving the
     * output-parameter(s).
     * 
     * @param strQuery
     *            an input-parameter of type String
     * @param strQueryLanguage
     *            an input-parameter of type String
     * @param iFlags
     *            an input-parameter of type int
     * @param objWbemNamedValueSet
     *            an input-parameter of type Object
     * @return the result is of type ISWbemObjectSet
     */
    public ISWbemObjectSet execQuery(String strQuery, String strQueryLanguage, int iFlags,
            Object objWbemNamedValueSet) {
        ISWbemObjectSet result_of_ExecQuery = new ISWbemObjectSet(Dispatch.call(this, "ExecQuery", //$NON-NLS-1$
                strQuery, strQueryLanguage, new Variant(iFlags), objWbemNamedValueSet).toDispatch());

        return result_of_ExecQuery;
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strQuery
     *            an input-parameter of type String
     * @param strQueryLanguage
     *            an input-parameter of type String
     * @param lFlags
     *            an input-parameter of type int
     * @param objWbemNamedValueSet
     *            an input-parameter of type Object
     */
    public void execQueryAsync(Object objWbemSink, String strQuery, String strQueryLanguage,
            int lFlags, Object objWbemNamedValueSet) {
        Dispatch.call(this, "ExecQueryAsync", objWbemSink, strQuery, strQueryLanguage, new Variant( //$NON-NLS-1$
                lFlags), objWbemNamedValueSet);
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strQuery
     *            an input-parameter of type String
     * @param strQueryLanguage
     *            an input-parameter of type String
     * @param lFlags
     *            an input-parameter of type int
     */
    public void execQueryAsync(Object objWbemSink, String strQuery, String strQueryLanguage,
            int lFlags) {
        Dispatch.call(this, "ExecQueryAsync", objWbemSink, strQuery, strQueryLanguage, new Variant( //$NON-NLS-1$
                lFlags));
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strQuery
     *            an input-parameter of type String
     * @param strQueryLanguage
     *            an input-parameter of type String
     */
    public void execQueryAsync(Object objWbemSink, String strQuery, String strQueryLanguage) {
        Dispatch.call(this, "ExecQueryAsync", objWbemSink, strQuery, strQueryLanguage); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strQuery
     *            an input-parameter of type String
     */
    public void execQueryAsync(Object objWbemSink, String strQuery) {
        Dispatch.call(this, "ExecQueryAsync", objWbemSink, strQuery); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method and receiving the
     * output-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strQuery
     *            an input-parameter of type String
     * @param strQueryLanguage
     *            an input-parameter of type String
     * @param lFlags
     *            an input-parameter of type int
     * @param objWbemNamedValueSet
     *            an input-parameter of type Object
     * @param objWbemAsyncContext
     *            an input-parameter of type Object
     */
    public void execQueryAsync(Object objWbemSink, String strQuery, String strQueryLanguage,
            int lFlags, Object objWbemNamedValueSet, Object objWbemAsyncContext) {
        Dispatch.call(this, "ExecQueryAsync", objWbemSink, strQuery, strQueryLanguage, new Variant( //$NON-NLS-1$
                lFlags), objWbemNamedValueSet, objWbemAsyncContext);

    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strObjectPath
     *            an input-parameter of type String
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
    public ISWbemObjectSet associatorsOf(String strObjectPath, String strAssocClass,
            String strResultClass, String strResultRole, String strRole, boolean bClassesOnly,
            boolean bSchemaOnly, String strRequiredAssocQualifier, String strRequiredQualifier,
            int iFlags) {
        return new ISWbemObjectSet(Dispatch.callN(
                this,
                "AssociatorsOf", //$NON-NLS-1$
                new Object[] { strObjectPath, strAssocClass, strResultClass, strResultRole,
                        strRole, new Variant(bClassesOnly), new Variant(bSchemaOnly),
                        strRequiredAssocQualifier, strRequiredQualifier, new Variant(iFlags) })
                .toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strObjectPath
     *            an input-parameter of type String
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
    public ISWbemObjectSet associatorsOf(String strObjectPath, String strAssocClass,
            String strResultClass, String strResultRole, String strRole, boolean bClassesOnly,
            boolean bSchemaOnly, String strRequiredAssocQualifier, String strRequiredQualifier) {
        return new ISWbemObjectSet(Dispatch.callN(
                this,
                "AssociatorsOf", //$NON-NLS-1$
                new Object[] { strObjectPath, strAssocClass, strResultClass, strResultRole,
                        strRole, new Variant(bClassesOnly), new Variant(bSchemaOnly),
                        strRequiredAssocQualifier, strRequiredQualifier }).toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strObjectPath
     *            an input-parameter of type String
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
    public ISWbemObjectSet associatorsOf(String strObjectPath, String strAssocClass,
            String strResultClass, String strResultRole, String strRole, boolean bClassesOnly,
            boolean bSchemaOnly, String strRequiredAssocQualifier) {
        return new ISWbemObjectSet(Dispatch.call(this, "AssociatorsOf", strObjectPath, //$NON-NLS-1$
                strAssocClass, strResultClass, strResultRole, strRole, new Variant(bClassesOnly),
                new Variant(bSchemaOnly), strRequiredAssocQualifier).toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strObjectPath
     *            an input-parameter of type String
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
    public ISWbemObjectSet associatorsOf(String strObjectPath, String strAssocClass,
            String strResultClass, String strResultRole, String strRole, boolean bClassesOnly,
            boolean bSchemaOnly) {
        return new ISWbemObjectSet(Dispatch.call(this, "AssociatorsOf", strObjectPath, //$NON-NLS-1$
                strAssocClass, strResultClass, strResultRole, strRole, new Variant(bClassesOnly),
                new Variant(bSchemaOnly)).toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strObjectPath
     *            an input-parameter of type String
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
    public ISWbemObjectSet associatorsOf(String strObjectPath, String strAssocClass,
            String strResultClass, String strResultRole, String strRole, boolean bClassesOnly) {
        return new ISWbemObjectSet(Dispatch.call(this, "AssociatorsOf", strObjectPath, //$NON-NLS-1$
                strAssocClass, strResultClass, strResultRole, strRole, new Variant(bClassesOnly))
                .toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strObjectPath
     *            an input-parameter of type String
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
    public ISWbemObjectSet associatorsOf(String strObjectPath, String strAssocClass,
            String strResultClass, String strResultRole, String strRole) {
        return new ISWbemObjectSet(Dispatch.call(this, "AssociatorsOf", strObjectPath, //$NON-NLS-1$
                strAssocClass, strResultClass, strResultRole, strRole).toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strObjectPath
     *            an input-parameter of type String
     * @param strAssocClass
     *            an input-parameter of type String
     * @param strResultClass
     *            an input-parameter of type String
     * @param strResultRole
     *            an input-parameter of type String
     * @return the result is of type ISWbemObjectSet
     */
    public ISWbemObjectSet associatorsOf(String strObjectPath, String strAssocClass,
            String strResultClass, String strResultRole) {
        return new ISWbemObjectSet(Dispatch.call(this, "AssociatorsOf", strObjectPath, //$NON-NLS-1$
                strAssocClass, strResultClass, strResultRole).toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strObjectPath
     *            an input-parameter of type String
     * @param strAssocClass
     *            an input-parameter of type String
     * @param strResultClass
     *            an input-parameter of type String
     * @return the result is of type ISWbemObjectSet
     */
    public ISWbemObjectSet associatorsOf(String strObjectPath, String strAssocClass,
            String strResultClass) {
        return new ISWbemObjectSet(Dispatch.call(this, "AssociatorsOf", strObjectPath, //$NON-NLS-1$
                strAssocClass, strResultClass).toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strObjectPath
     *            an input-parameter of type String
     * @param strAssocClass
     *            an input-parameter of type String
     * @return the result is of type ISWbemObjectSet
     */
    public ISWbemObjectSet associatorsOf(String strObjectPath, String strAssocClass) {
        return new ISWbemObjectSet(Dispatch.call(this, "AssociatorsOf", strObjectPath, //$NON-NLS-1$
                strAssocClass).toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strObjectPath
     *            an input-parameter of type String
     * @return the result is of type ISWbemObjectSet
     */
    public ISWbemObjectSet associatorsOf(String strObjectPath) {
        return new ISWbemObjectSet(Dispatch.call(this, "AssociatorsOf", strObjectPath).toDispatch()); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method and receiving the
     * output-parameter(s).
     * 
     * @param strObjectPath
     *            an input-parameter of type String
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
    public ISWbemObjectSet associatorsOf(String strObjectPath, String strAssocClass,
            String strResultClass, String strResultRole, String strRole, boolean bClassesOnly,
            boolean bSchemaOnly, String strRequiredAssocQualifier, String strRequiredQualifier,
            int iFlags, Object objWbemNamedValueSet) {
        ISWbemObjectSet result_of_AssociatorsOf = new ISWbemObjectSet(Dispatch.callN(
                this,
                "AssociatorsOf", //$NON-NLS-1$
                new Object[] { strObjectPath, strAssocClass, strResultClass, strResultRole,
                        strRole, new Variant(bClassesOnly), new Variant(bSchemaOnly),
                        strRequiredAssocQualifier, strRequiredQualifier, new Variant(iFlags),
                        objWbemNamedValueSet }).toDispatch());

        return result_of_AssociatorsOf;
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strObjectPath
     *            an input-parameter of type String
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
    public void associatorsOfAsync(Object objWbemSink, String strObjectPath, String strAssocClass,
            String strResultClass, String strResultRole, String strRole, boolean bClassesOnly,
            boolean bSchemaOnly, String strRequiredAssocQualifier, String strRequiredQualifier,
            int iFlags, Object objWbemNamedValueSet) {
        Dispatch.callN(this, "AssociatorsOfAsync", new Object[] { objWbemSink, strObjectPath, //$NON-NLS-1$
                strAssocClass, strResultClass, strResultRole, strRole, new Variant(bClassesOnly),
                new Variant(bSchemaOnly), strRequiredAssocQualifier, strRequiredQualifier,
                new Variant(iFlags), objWbemNamedValueSet });
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strObjectPath
     *            an input-parameter of type String
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
    public void associatorsOfAsync(Object objWbemSink, String strObjectPath, String strAssocClass,
            String strResultClass, String strResultRole, String strRole, boolean bClassesOnly,
            boolean bSchemaOnly, String strRequiredAssocQualifier, String strRequiredQualifier,
            int iFlags) {
        Dispatch.callN(this, "AssociatorsOfAsync", new Object[] { objWbemSink, strObjectPath, //$NON-NLS-1$
                strAssocClass, strResultClass, strResultRole, strRole, new Variant(bClassesOnly),
                new Variant(bSchemaOnly), strRequiredAssocQualifier, strRequiredQualifier,
                new Variant(iFlags) });
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strObjectPath
     *            an input-parameter of type String
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
    public void associatorsOfAsync(Object objWbemSink, String strObjectPath, String strAssocClass,
            String strResultClass, String strResultRole, String strRole, boolean bClassesOnly,
            boolean bSchemaOnly, String strRequiredAssocQualifier, String strRequiredQualifier) {
        Dispatch.callN(this, "AssociatorsOfAsync", new Object[] { objWbemSink, strObjectPath, //$NON-NLS-1$
                strAssocClass, strResultClass, strResultRole, strRole, new Variant(bClassesOnly),
                new Variant(bSchemaOnly), strRequiredAssocQualifier, strRequiredQualifier });
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strObjectPath
     *            an input-parameter of type String
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
    public void associatorsOfAsync(Object objWbemSink, String strObjectPath, String strAssocClass,
            String strResultClass, String strResultRole, String strRole, boolean bClassesOnly,
            boolean bSchemaOnly, String strRequiredAssocQualifier) {
        Dispatch.callN(this, "AssociatorsOfAsync", new Object[] { objWbemSink, strObjectPath, //$NON-NLS-1$
                strAssocClass, strResultClass, strResultRole, strRole, new Variant(bClassesOnly),
                new Variant(bSchemaOnly), strRequiredAssocQualifier });
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strObjectPath
     *            an input-parameter of type String
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
    public void associatorsOfAsync(Object objWbemSink, String strObjectPath, String strAssocClass,
            String strResultClass, String strResultRole, String strRole, boolean bClassesOnly,
            boolean bSchemaOnly) {
        Dispatch.call(this, "AssociatorsOfAsync", objWbemSink, strObjectPath, strAssocClass, //$NON-NLS-1$
                strResultClass, strResultRole, strRole, new Variant(bClassesOnly), new Variant(
                        bSchemaOnly));
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strObjectPath
     *            an input-parameter of type String
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
    public void associatorsOfAsync(Object objWbemSink, String strObjectPath, String strAssocClass,
            String strResultClass, String strResultRole, String strRole, boolean bClassesOnly) {
        Dispatch.call(this, "AssociatorsOfAsync", objWbemSink, strObjectPath, strAssocClass, //$NON-NLS-1$
                strResultClass, strResultRole, strRole, new Variant(bClassesOnly));
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strObjectPath
     *            an input-parameter of type String
     * @param strAssocClass
     *            an input-parameter of type String
     * @param strResultClass
     *            an input-parameter of type String
     * @param strResultRole
     *            an input-parameter of type String
     * @param strRole
     *            an input-parameter of type String
     */
    public void associatorsOfAsync(Object objWbemSink, String strObjectPath, String strAssocClass,
            String strResultClass, String strResultRole, String strRole) {
        Dispatch.call(this, "AssociatorsOfAsync", objWbemSink, strObjectPath, strAssocClass, //$NON-NLS-1$
                strResultClass, strResultRole, strRole);
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strObjectPath
     *            an input-parameter of type String
     * @param strAssocClass
     *            an input-parameter of type String
     * @param strResultClass
     *            an input-parameter of type String
     * @param strResultRole
     *            an input-parameter of type String
     */
    public void associatorsOfAsync(Object objWbemSink, String strObjectPath, String strAssocClass,
            String strResultClass, String strResultRole) {
        Dispatch.call(this, "AssociatorsOfAsync", objWbemSink, strObjectPath, strAssocClass, //$NON-NLS-1$
                strResultClass, strResultRole);
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strObjectPath
     *            an input-parameter of type String
     * @param strAssocClass
     *            an input-parameter of type String
     * @param strResultClass
     *            an input-parameter of type String
     */
    public void associatorsOfAsync(Object objWbemSink, String strObjectPath, String strAssocClass,
            String strResultClass) {
        Dispatch.call(this, "AssociatorsOfAsync", objWbemSink, strObjectPath, strAssocClass, //$NON-NLS-1$
                strResultClass);
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strObjectPath
     *            an input-parameter of type String
     * @param strAssocClass
     *            an input-parameter of type String
     */
    public void associatorsOfAsync(Object objWbemSink, String strObjectPath, String strAssocClass) {
        Dispatch.call(this, "AssociatorsOfAsync", objWbemSink, strObjectPath, strAssocClass); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strObjectPath
     *            an input-parameter of type String
     */
    public void associatorsOfAsync(Object objWbemSink, String strObjectPath) {
        Dispatch.call(this, "AssociatorsOfAsync", objWbemSink, strObjectPath); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method and receiving the
     * output-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strObjectPath
     *            an input-parameter of type String
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
    public void associatorsOfAsync(Object objWbemSink, String strObjectPath, String strAssocClass,
            String strResultClass, String strResultRole, String strRole, boolean bClassesOnly,
            boolean bSchemaOnly, String strRequiredAssocQualifier, String strRequiredQualifier,
            int iFlags, Object objWbemNamedValueSet, Object objWbemAsyncContext) {
        Dispatch.callN(this, "AssociatorsOfAsync", new Object[] { objWbemSink, strObjectPath, //$NON-NLS-1$
                strAssocClass, strResultClass, strResultRole, strRole, new Variant(bClassesOnly),
                new Variant(bSchemaOnly), strRequiredAssocQualifier, strRequiredQualifier,
                new Variant(iFlags), objWbemNamedValueSet, objWbemAsyncContext });

    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strObjectPath
     *            an input-parameter of type String
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
    public ISWbemObjectSet referencesTo(String strObjectPath, String strResultClass,
            String strRole, boolean bClassesOnly, boolean bSchemaOnly, String strRequiredQualifier,
            int iFlags) {
        return new ISWbemObjectSet(Dispatch.call(this, "ReferencesTo", strObjectPath, //$NON-NLS-1$
                strResultClass, strRole, new Variant(bClassesOnly), new Variant(bSchemaOnly),
                strRequiredQualifier, new Variant(iFlags)).toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strObjectPath
     *            an input-parameter of type String
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
    public ISWbemObjectSet referencesTo(String strObjectPath, String strResultClass,
            String strRole, boolean bClassesOnly, boolean bSchemaOnly, String strRequiredQualifier) {
        return new ISWbemObjectSet(Dispatch.call(this, "ReferencesTo", strObjectPath, //$NON-NLS-1$
                strResultClass, strRole, new Variant(bClassesOnly), new Variant(bSchemaOnly),
                strRequiredQualifier).toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strObjectPath
     *            an input-parameter of type String
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
    public ISWbemObjectSet referencesTo(String strObjectPath, String strResultClass,
            String strRole, boolean bClassesOnly, boolean bSchemaOnly) {
        return new ISWbemObjectSet(Dispatch.call(this, "ReferencesTo", strObjectPath, //$NON-NLS-1$
                strResultClass, strRole, new Variant(bClassesOnly), new Variant(bSchemaOnly))
                .toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strObjectPath
     *            an input-parameter of type String
     * @param strResultClass
     *            an input-parameter of type String
     * @param strRole
     *            an input-parameter of type String
     * @param bClassesOnly
     *            an input-parameter of type boolean
     * @return the result is of type ISWbemObjectSet
     */
    public ISWbemObjectSet referencesTo(String strObjectPath, String strResultClass,
            String strRole, boolean bClassesOnly) {
        return new ISWbemObjectSet(Dispatch.call(this, "ReferencesTo", strObjectPath, //$NON-NLS-1$
                strResultClass, strRole, new Variant(bClassesOnly)).toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strObjectPath
     *            an input-parameter of type String
     * @param strResultClass
     *            an input-parameter of type String
     * @param strRole
     *            an input-parameter of type String
     * @return the result is of type ISWbemObjectSet
     */
    public ISWbemObjectSet referencesTo(String strObjectPath, String strResultClass, String strRole) {
        return new ISWbemObjectSet(Dispatch.call(this, "ReferencesTo", strObjectPath, //$NON-NLS-1$
                strResultClass, strRole).toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strObjectPath
     *            an input-parameter of type String
     * @param strResultClass
     *            an input-parameter of type String
     * @return the result is of type ISWbemObjectSet
     */
    public ISWbemObjectSet referencesTo(String strObjectPath, String strResultClass) {
        return new ISWbemObjectSet(Dispatch.call(this, "ReferencesTo", strObjectPath, //$NON-NLS-1$
                strResultClass).toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strObjectPath
     *            an input-parameter of type String
     * @return the result is of type ISWbemObjectSet
     */
    public ISWbemObjectSet referencesTo(String strObjectPath) {
        return new ISWbemObjectSet(Dispatch.call(this, "ReferencesTo", strObjectPath).toDispatch()); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method and receiving the
     * output-parameter(s).
     * 
     * @param strObjectPath
     *            an input-parameter of type String
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
    public ISWbemObjectSet referencesTo(String strObjectPath, String strResultClass,
            String strRole, boolean bClassesOnly, boolean bSchemaOnly, String strRequiredQualifier,
            int iFlags, Object objWbemNamedValueSet) {
        ISWbemObjectSet result_of_ReferencesTo = new ISWbemObjectSet(Dispatch.call(this,
                "ReferencesTo", strObjectPath, strResultClass, strRole, new Variant(bClassesOnly), //$NON-NLS-1$
                new Variant(bSchemaOnly), strRequiredQualifier, new Variant(iFlags),
                objWbemNamedValueSet).toDispatch());

        return result_of_ReferencesTo;
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strObjectPath
     *            an input-parameter of type String
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
    public void referencesToAsync(Object objWbemSink, String strObjectPath, String strResultClass,
            String strRole, boolean bClassesOnly, boolean bSchemaOnly, String strRequiredQualifier,
            int iFlags, Object objWbemNamedValueSet) {
        Dispatch.callN(this, "ReferencesToAsync", new Object[] { objWbemSink, strObjectPath, //$NON-NLS-1$
                strResultClass, strRole, new Variant(bClassesOnly), new Variant(bSchemaOnly),
                strRequiredQualifier, new Variant(iFlags), objWbemNamedValueSet });
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strObjectPath
     *            an input-parameter of type String
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
    public void referencesToAsync(Object objWbemSink, String strObjectPath, String strResultClass,
            String strRole, boolean bClassesOnly, boolean bSchemaOnly, String strRequiredQualifier,
            int iFlags) {
        Dispatch.call(this, "ReferencesToAsync", objWbemSink, strObjectPath, strResultClass, //$NON-NLS-1$
                strRole, new Variant(bClassesOnly), new Variant(bSchemaOnly), strRequiredQualifier,
                new Variant(iFlags));
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strObjectPath
     *            an input-parameter of type String
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
    public void referencesToAsync(Object objWbemSink, String strObjectPath, String strResultClass,
            String strRole, boolean bClassesOnly, boolean bSchemaOnly, String strRequiredQualifier) {
        Dispatch.call(this, "ReferencesToAsync", objWbemSink, strObjectPath, strResultClass, //$NON-NLS-1$
                strRole, new Variant(bClassesOnly), new Variant(bSchemaOnly), strRequiredQualifier);
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strObjectPath
     *            an input-parameter of type String
     * @param strResultClass
     *            an input-parameter of type String
     * @param strRole
     *            an input-parameter of type String
     * @param bClassesOnly
     *            an input-parameter of type boolean
     * @param bSchemaOnly
     *            an input-parameter of type boolean
     */
    public void referencesToAsync(Object objWbemSink, String strObjectPath, String strResultClass,
            String strRole, boolean bClassesOnly, boolean bSchemaOnly) {
        Dispatch.call(this, "ReferencesToAsync", objWbemSink, strObjectPath, strResultClass, //$NON-NLS-1$
                strRole, new Variant(bClassesOnly), new Variant(bSchemaOnly));
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strObjectPath
     *            an input-parameter of type String
     * @param strResultClass
     *            an input-parameter of type String
     * @param strRole
     *            an input-parameter of type String
     * @param bClassesOnly
     *            an input-parameter of type boolean
     */
    public void referencesToAsync(Object objWbemSink, String strObjectPath, String strResultClass,
            String strRole, boolean bClassesOnly) {
        Dispatch.call(this, "ReferencesToAsync", objWbemSink, strObjectPath, strResultClass, //$NON-NLS-1$
                strRole, new Variant(bClassesOnly));
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strObjectPath
     *            an input-parameter of type String
     * @param strResultClass
     *            an input-parameter of type String
     * @param strRole
     *            an input-parameter of type String
     */
    public void referencesToAsync(Object objWbemSink, String strObjectPath, String strResultClass,
            String strRole) {
        Dispatch.call(this, "ReferencesToAsync", objWbemSink, strObjectPath, strResultClass, //$NON-NLS-1$
                strRole);
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strObjectPath
     *            an input-parameter of type String
     * @param strResultClass
     *            an input-parameter of type String
     */
    public void referencesToAsync(Object objWbemSink, String strObjectPath, String strResultClass) {
        Dispatch.call(this, "ReferencesToAsync", objWbemSink, strObjectPath, strResultClass); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strObjectPath
     *            an input-parameter of type String
     */
    public void referencesToAsync(Object objWbemSink, String strObjectPath) {
        Dispatch.call(this, "ReferencesToAsync", objWbemSink, strObjectPath); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method and receiving the
     * output-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strObjectPath
     *            an input-parameter of type String
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
    public void referencesToAsync(Object objWbemSink, String strObjectPath, String strResultClass,
            String strRole, boolean bClassesOnly, boolean bSchemaOnly, String strRequiredQualifier,
            int iFlags, Object objWbemNamedValueSet, Object objWbemAsyncContext) {
        Dispatch.callN(this, "ReferencesToAsync", new Object[] { objWbemSink, strObjectPath, //$NON-NLS-1$
                strResultClass, strRole, new Variant(bClassesOnly), new Variant(bSchemaOnly),
                strRequiredQualifier, new Variant(iFlags), objWbemNamedValueSet,
                objWbemAsyncContext });

    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strQuery
     *            an input-parameter of type String
     * @param strQueryLanguage
     *            an input-parameter of type String
     * @param iFlags
     *            an input-parameter of type int
     * @param objWbemNamedValueSet
     *            an input-parameter of type Object
     * @return the result is of type ISWbemEventSource
     */
    public ISWbemEventSource execNotificationQuery(String strQuery, String strQueryLanguage,
            int iFlags, Object objWbemNamedValueSet) {
        return new ISWbemEventSource(Dispatch.call(this, "ExecNotificationQuery", strQuery, //$NON-NLS-1$
                strQueryLanguage, new Variant(iFlags), objWbemNamedValueSet).toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strQuery
     *            an input-parameter of type String
     * @param strQueryLanguage
     *            an input-parameter of type String
     * @param iFlags
     *            an input-parameter of type int
     * @return the result is of type ISWbemEventSource
     */
    public ISWbemEventSource execNotificationQuery(String strQuery, String strQueryLanguage,
            int iFlags) {
        return new ISWbemEventSource(Dispatch.call(this, "ExecNotificationQuery", strQuery, //$NON-NLS-1$
                strQueryLanguage, new Variant(iFlags)).toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strQuery
     *            an input-parameter of type String
     * @param strQueryLanguage
     *            an input-parameter of type String
     * @return the result is of type ISWbemEventSource
     */
    public ISWbemEventSource execNotificationQuery(String strQuery, String strQueryLanguage) {
        return new ISWbemEventSource(Dispatch.call(this, "ExecNotificationQuery", strQuery, //$NON-NLS-1$
                strQueryLanguage).toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strQuery
     *            an input-parameter of type String
     * @return the result is of type ISWbemEventSource
     */
    public ISWbemEventSource execNotificationQuery(String strQuery) {
        return new ISWbemEventSource(Dispatch.call(this, "ExecNotificationQuery", strQuery) //$NON-NLS-1$
                .toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strQuery
     *            an input-parameter of type String
     * @param strQueryLanguage
     *            an input-parameter of type String
     * @param iFlags
     *            an input-parameter of type int
     * @param objWbemNamedValueSet
     *            an input-parameter of type Object
     * @param objWbemAsyncContext
     *            an input-parameter of type Object
     */
    public void execNotificationQueryAsync(Object objWbemSink, String strQuery,
            String strQueryLanguage, int iFlags, Object objWbemNamedValueSet,
            Object objWbemAsyncContext) {
        Dispatch.call(this, "ExecNotificationQueryAsync", objWbemSink, strQuery, strQueryLanguage, //$NON-NLS-1$
                new Variant(iFlags), objWbemNamedValueSet, objWbemAsyncContext);
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strQuery
     *            an input-parameter of type String
     * @param strQueryLanguage
     *            an input-parameter of type String
     * @param iFlags
     *            an input-parameter of type int
     * @param objWbemNamedValueSet
     *            an input-parameter of type Object
     */
    public void execNotificationQueryAsync(Object objWbemSink, String strQuery,
            String strQueryLanguage, int iFlags, Object objWbemNamedValueSet) {
        Dispatch.call(this, "ExecNotificationQueryAsync", objWbemSink, strQuery, strQueryLanguage, //$NON-NLS-1$
                new Variant(iFlags), objWbemNamedValueSet);
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strQuery
     *            an input-parameter of type String
     * @param strQueryLanguage
     *            an input-parameter of type String
     * @param iFlags
     *            an input-parameter of type int
     */
    public void execNotificationQueryAsync(Object objWbemSink, String strQuery,
            String strQueryLanguage, int iFlags) {
        Dispatch.call(this, "ExecNotificationQueryAsync", objWbemSink, strQuery, strQueryLanguage, //$NON-NLS-1$
                new Variant(iFlags));
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strQuery
     *            an input-parameter of type String
     * @param strQueryLanguage
     *            an input-parameter of type String
     */
    public void execNotificationQueryAsync(Object objWbemSink, String strQuery,
            String strQueryLanguage) {
        Dispatch.call(this, "ExecNotificationQueryAsync", objWbemSink, strQuery, strQueryLanguage); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strQuery
     *            an input-parameter of type String
     */
    public void execNotificationQueryAsync(Object objWbemSink, String strQuery) {
        Dispatch.call(this, "ExecNotificationQueryAsync", objWbemSink, strQuery); //$NON-NLS-1$
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strObjectPath
     *            an input-parameter of type String
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
    public ISWbemObject execMethod(String strObjectPath, String strMethodName,
            Object objWbemInParameters, int iFlags, Object objWbemNamedValueSet) {
        return new ISWbemObject(Dispatch.call(this, "ExecMethod", strObjectPath, strMethodName, //$NON-NLS-1$
                objWbemInParameters, new Variant(iFlags), objWbemNamedValueSet).toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strObjectPath
     *            an input-parameter of type String
     * @param strMethodName
     *            an input-parameter of type String
     * @param objWbemInParameters
     *            an input-parameter of type Object
     * @param iFlags
     *            an input-parameter of type int
     * @return the result is of type ISWbemObject
     */
    public ISWbemObject execMethod(String strObjectPath, String strMethodName,
            Object objWbemInParameters, int iFlags) {
        return new ISWbemObject(Dispatch.call(this, "ExecMethod", strObjectPath, strMethodName, //$NON-NLS-1$
                objWbemInParameters, new Variant(iFlags)).toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strObjectPath
     *            an input-parameter of type String
     * @param strMethodName
     *            an input-parameter of type String
     * @param objWbemInParameters
     *            an input-parameter of type Object
     * @return the result is of type ISWbemObject
     */
    public ISWbemObject execMethod(String strObjectPath, String strMethodName,
            Object objWbemInParameters) {
        return new ISWbemObject(Dispatch.call(this, "ExecMethod", strObjectPath, strMethodName, //$NON-NLS-1$
                objWbemInParameters).toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param strObjectPath
     *            an input-parameter of type String
     * @param strMethodName
     *            an input-parameter of type String
     * @return the result is of type ISWbemObject
     */
    public ISWbemObject execMethod(String strObjectPath, String strMethodName) {
        return new ISWbemObject(Dispatch.call(this, "ExecMethod", strObjectPath, strMethodName) //$NON-NLS-1$
                .toDispatch());
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strObjectPath
     *            an input-parameter of type String
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
    public void execMethodAsync(Object objWbemSink, String strObjectPath, String strMethodName,
            Object objWbemInParameters, int iFlags, Object objWbemNamedValueSet,
            Object objWbemAsyncContext) {
        Dispatch
                .call(this, "ExecMethodAsync", objWbemSink, strObjectPath, strMethodName, //$NON-NLS-1$
                        objWbemInParameters, new Variant(iFlags), objWbemNamedValueSet,
                        objWbemAsyncContext);
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strObjectPath
     *            an input-parameter of type String
     * @param strMethodName
     *            an input-parameter of type String
     * @param objWbemInParameters
     *            an input-parameter of type Object
     * @param iFlags
     *            an input-parameter of type int
     * @param objWbemNamedValueSet
     *            an input-parameter of type Object
     */
    public void execMethodAsync(Object objWbemSink, String strObjectPath, String strMethodName,
            Object objWbemInParameters, int iFlags, Object objWbemNamedValueSet) {
        Dispatch.call(this, "ExecMethodAsync", objWbemSink, strObjectPath, strMethodName, //$NON-NLS-1$
                objWbemInParameters, new Variant(iFlags), objWbemNamedValueSet);
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strObjectPath
     *            an input-parameter of type String
     * @param strMethodName
     *            an input-parameter of type String
     * @param objWbemInParameters
     *            an input-parameter of type Object
     * @param iFlags
     *            an input-parameter of type int
     */
    public void execMethodAsync(Object objWbemSink, String strObjectPath, String strMethodName,
            Object objWbemInParameters, int iFlags) {
        Dispatch.call(this, "ExecMethodAsync", objWbemSink, strObjectPath, strMethodName, //$NON-NLS-1$
                objWbemInParameters, new Variant(iFlags));
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strObjectPath
     *            an input-parameter of type String
     * @param strMethodName
     *            an input-parameter of type String
     * @param objWbemInParameters
     *            an input-parameter of type Object
     */
    public void execMethodAsync(Object objWbemSink, String strObjectPath, String strMethodName,
            Object objWbemInParameters) {
        Dispatch.call(this, "ExecMethodAsync", objWbemSink, strObjectPath, strMethodName, //$NON-NLS-1$
                objWbemInParameters);
    }

    /**
     * Wrapper for calling the ActiveX-Method with input-parameter(s).
     * 
     * @param objWbemSink
     *            an input-parameter of type Object
     * @param strObjectPath
     *            an input-parameter of type String
     * @param strMethodName
     *            an input-parameter of type String
     */
    public void execMethodAsync(Object objWbemSink, String strObjectPath, String strMethodName) {
        Dispatch.call(this, "ExecMethodAsync", objWbemSink, strObjectPath, strMethodName); //$NON-NLS-1$
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
