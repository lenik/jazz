package net.bodz.bas.javadoc;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import net.bodz.bas.i18n.ContextCharset;

public class JavadocFactory {

    static ContextCharset contextCharset = ContextCharset.getInstance();

    public static Javadoc getPackageJavadoc(Package package_) {
        return null;
    }

    public static Javadoc getClassJavadoc(Class<?> declaringType) {
        return null;
    }

    public static Javadoc getFieldJavadoc(Field field) {
        return null;
    }

    public static Javadoc getMethodJavadoc(Method method) {
        return null;
    }

    public static String getMethodSignature(Method method) {
        return null;
    }

}
