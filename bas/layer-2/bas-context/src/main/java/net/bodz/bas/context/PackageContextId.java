package net.bodz.bas.context;

import net.bodz.bas.jvm.stack.Caller;
import net.bodz.bas.t.preorder.PackageNamePreorder;

public class PackageContextId
        extends PreorderContextId<String> {

    public PackageContextId(IContextId fallbackContext, String packageName) {
        super(fallbackContext, PackageNamePreorder.getInstance(), packageName);
    }

    public PackageContextId(String packageName) {
        this(StaticContextId.getInstance(), packageName);
    }

    public static PackageContextId getInstance(IContextId fallbackContext, String packageName) {
        return new PackageContextId(fallbackContext, packageName);
    }

    public static PackageContextId getInstance(String packageName) {
        return new PackageContextId(StaticContextId.getInstance(), packageName);
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

    public static PackageContextId getCallerPackageContext(IContextId fallbackContext) {
        Class<?> callerClass = Caller.getCallerClass(2);
        return getInstance(fallbackContext, getPackageName(callerClass));
    }

    public static PackageContextId getCallerPackageContext() {
        Class<?> callerClass = Caller.getCallerClass(2);
        return getInstance(StaticContextId.getInstance(), getPackageName(callerClass));
    }

    public static PackageContextId getCallerParentPackageContext(IContextId fallbackContext) {
        Class<?> callerClass = Caller.getCallerClass(2);
        return getInstance(fallbackContext, getParentPackageName(callerClass));
    }

    public static PackageContextId getCallerParentPackageContext() {
        Class<?> callerClass = Caller.getCallerClass(2);
        return getInstance(StaticContextId.getInstance(), getParentPackageName(callerClass));
    }

}
