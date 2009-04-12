package net.bodz.bas.lang.script;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import net.bodz.bas.nls.LangNLS;
import net.bodz.bas.types.TypeMap;
import net.bodz.bas.types.TypeMap.HashTypeMap;
import net.bodz.bas.types.util.Ns;

public class Scripts {

    private static TypeMap<ScriptClass<?>>          explicitScriptClasses;
    private static TypeMap<ReflectedScriptClass<?>> forceConverted;
    private static TypeMap<ReflectedScriptClass<?>> noforceConverted;
    static {
        explicitScriptClasses = new HashTypeMap<ScriptClass<?>>();
        forceConverted = new HashTypeMap<ReflectedScriptClass<?>>();
        noforceConverted = new HashTypeMap<ReflectedScriptClass<?>>();
    }

    /**
     * @param dynamicImpl
     */
    @SuppressWarnings("unchecked")
    public static <T> ScriptClass<T> getScriptClass(Class<?> origClass,
            Object dynamicImpl) throws ScriptException {
        assert origClass != null;
        ScriptClass<?> sclass = explicitScriptClasses.get(origClass);
        if (sclass == null) {
            Class<? extends ScriptClass<?>> scType;
            scType = Ns._getValue(origClass, ScriptType.class);
            if (scType == null)
                throw new ScriptException(LangNLS
                        .getString("Scripts.nosclassFor") //$NON-NLS-1$
                        + origClass);
            sclass = newScriptClass(scType, origClass, dynamicImpl);
            explicitScriptClasses.put(origClass, sclass);
        }
        return (ScriptClass<T>) sclass;
    }

    public static <T> ScriptClass<T> getScriptClass(Class<?> origClass)
            throws ScriptException {
        return getScriptClass(origClass, null);
    }

    public static <T> ScriptClass<T> getScriptClass(Object dynamicImpl)
            throws ScriptException {
        assert dynamicImpl != null;
        return getScriptClass(dynamicImpl.getClass(), dynamicImpl);
    }

    private static final Class<?>[] STATIC_CTOR  = { Class.class };
    private static final Class<?>[] DYNAMIC_CTOR = { Class.class, Object.class };

    @SuppressWarnings("unchecked")
    static ScriptClass<?> newScriptClass(
            Class<? extends ScriptClass<?>> scType, Class<?> origClass,
            Object dynamicImpl) {
        ScriptClass<?> sclass;
        try {
            if (dynamicImpl == null) {
                Constructor<ScriptClass<?>> staticCtor;
                staticCtor = (Constructor<ScriptClass<?>>) scType
                        .getConstructor(STATIC_CTOR);
                sclass = staticCtor.newInstance(origClass);
            } else {
                Constructor<ScriptClass<?>> dynamicCtor;
                dynamicCtor = (Constructor<ScriptClass<?>>) scType
                        .getConstructor(DYNAMIC_CTOR);
                sclass = dynamicCtor.newInstance(origClass, dynamicImpl);
            }
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(LangNLS.getString("Scripts.noctor"), e); //$NON-NLS-1$
        } catch (Exception e) {
            throw new RuntimeException(
                    LangNLS.getString("Scripts.errNewInst"), e); //$NON-NLS-1$
        }
        return sclass;
    }

    /**
     * (Singleton) Convert specified java class to script class,
     * 
     * @param clazz
     *            java class to be converted
     * @param forceAccess
     *            whether all members are converted.
     * @throws NullPointerException
     *             if <code>clazz</code> is null
     */
    @SuppressWarnings("unchecked")
    public static <T> ScriptClass<T> convertClass(Class<?> clazz,
            boolean forceAccess) throws ScriptException {
        assert clazz != null;
        ReflectedScriptClass<?> sclass;
        if (forceAccess) {
            sclass = forceConverted.get(clazz);
            if (sclass == null) {
                sclass = new ReflectedScriptClass<T>((Class<T>) clazz, true);
                forceConverted.put(clazz, sclass);
            }
        } else {
            sclass = noforceConverted.get(clazz);
            if (sclass == null) {
                sclass = new ReflectedScriptClass<T>((Class<T>) clazz, false);
                noforceConverted.put(clazz, sclass);
            }
        }
        return (ScriptClass<T>) sclass;
    }

    /**
     * (Singleton) Convert specified java class to script class, only public
     * members are converted.
     * 
     * @throws NullPointerException
     *             if <code>clazz</code> is null
     */
    public static <T> ScriptClass<T> convertClass(Class<?> clazz)
            throws ScriptException {
        assert clazz != null;
        return convertClass(clazz, false);
    }

    @SuppressWarnings("unchecked")
    public static <T> ScriptField<T> convertField(Class<?> clazz,
            final String memberName, boolean forceAccess)
            throws ScriptException {
        ScriptClass<?> sclass = convertClass(clazz, forceAccess);
        return (ScriptField<T>) sclass.getField(memberName);
    }

    public static <T> ScriptField<T> convertField(Class<?> clazz,
            final String memberName) throws ScriptException {
        return convertField(clazz, memberName, false);
    }

    public static <T> ScriptField<T> convertField(Field field,
            boolean forceAccess) throws ScriptException {
        Class<?> clazz = field.getDeclaringClass();
        return convertField(clazz, field.getName(), forceAccess);
    }

    public static <T> ScriptField<T> convertField(Field field)
            throws ScriptException {
        return convertField(field, false);
    }

    @SuppressWarnings("unchecked")
    public static <T> ScriptMethod<T> convertMethod(Class<?> clazz,
            String memberName, boolean forceAccess) throws ScriptException {
        ScriptClass<?> sclass = convertClass(clazz, forceAccess);
        return (ScriptMethod<T>) sclass.getMethod(memberName);
    }

    public static <T> ScriptMethod<T> convertMethod(Class<?> clazz,
            final String memberName) throws ScriptException {
        return convertMethod(clazz, memberName, false);
    }

    public static <T> ScriptMethod<T> convertMethod(Method method,
            boolean forceAccess) throws ScriptException {
        Class<?> clazz = method.getDeclaringClass();
        return convertMethod(clazz, method.getName(), forceAccess);
    }

    public static <T> ScriptMethod<T> convertMethod(Method method)
            throws ScriptException {
        return convertMethod(method, false);
    }

}
