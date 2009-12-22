package net.bodz.bas.commons.scripting.util;

import java.lang.reflect.InvocationTargetException;

import net.bodz.bas.commons.util.Types;

public abstract class _VMethod implements VMethod {

    public Object invoke(Object obj, Object... params) throws IllegalArgumentException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException {
        Class<?>[] paramTypes = Types.getTypes(params);
        return invokel(obj, paramTypes, params);
    }

    @Override
    public Object _invoke(Object obj, Object... params) {
        try {
            return invoke(obj, params);
        } catch (IllegalArgumentException e) {
            throw new ReflectException(e);
        } catch (NoSuchMethodException e) {
            throw new ReflectException(e);
        } catch (IllegalAccessException e) {
            throw new ReflectException(e);
        } catch (InvocationTargetException e) {
            throw new ReflectException(e);
        }
    }

    @Override
    public Object _invokel(Object obj, Class<?>[] paramTypes, Object... params) {
        try {
            return invokel(obj, paramTypes, params);
        } catch (IllegalArgumentException e) {
            throw new ReflectException(e);
        } catch (NoSuchMethodException e) {
            throw new ReflectException(e);
        } catch (IllegalAccessException e) {
            throw new ReflectException(e);
        } catch (InvocationTargetException e) {
            throw new ReflectException(e);
        }
    }

}
