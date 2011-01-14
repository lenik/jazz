package net.bodz.bas.context;

import net.bodz.bas.collection.preorder.PackageNamePreorder;
import net.bodz.bas.jvm.stack.Caller;

public class PackageContext
        extends PreorderContext<String> {

    public PackageContext(IContext fallbackContext, String packageName) {
        super(fallbackContext, PackageNamePreorder.getInstance(), packageName);
    }

    public PackageContext(String packageName) {
        this(StaticContext.getInstance(), packageName);
    }

    public static PackageContext getInstance(IContext fallbackContext, String packageName) {
        return new PackageContext(fallbackContext, packageName);
    }

    public static PackageContext getInstance(String packageName) {
        return new PackageContext(StaticContext.getInstance(), packageName);
    }

    static String getPackageName(Class<?> clazz) {
        String packageName = clazz.getName();
        packageName = packageName.replace('$', '.');
        return packageName;
    }

    static String getParentPackageName(Class<?> clazz) {
        String packageName = getPackageName(clazz);
        int lastDot = packageName.lastIndexOf('.');
        if (lastDot == -1)
            return null;
        String parentPackageName = packageName.substring(0, lastDot);
        return parentPackageName;
    }

    public static PackageContext getCallerPackageContext(IContext fallbackContext) {
        Class<?> callerClass = Caller.getCallerClass(2);
        return getInstance(fallbackContext, getPackageName(callerClass));
    }

    public static PackageContext getCallerPackageContext() {
        Class<?> callerClass = Caller.getCallerClass(2);
        return getInstance(StaticContext.getInstance(), getPackageName(callerClass));
    }

    public static PackageContext getCallerParentPackageContext(IContext fallbackContext) {
        Class<?> callerClass = Caller.getCallerClass(2);
        return getInstance(fallbackContext, getParentPackageName(callerClass));
    }

    public static PackageContext getCallerParentPackageContext() {
        Class<?> callerClass = Caller.getCallerClass(2);
        return getInstance(StaticContext.getInstance(), getParentPackageName(callerClass));
    }

}
