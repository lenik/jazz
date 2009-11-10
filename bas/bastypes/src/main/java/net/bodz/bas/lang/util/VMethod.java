package net.bodz.bas.lang.util;

import java.lang.reflect.InvocationTargetException;

public interface VMethod {

    Object invokel(Object obj, Class<?>[] paramTypes, Object... params) throws NoSuchMethodException,
            IllegalArgumentException, IllegalAccessException, InvocationTargetException;

    Object invoke(Object obj, Object... params) throws IllegalArgumentException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException;

    Object _invokel(Object obj, Class<?>[] paramTypes, Object... params);

    Object _invoke(Object obj, Object... params);

}
