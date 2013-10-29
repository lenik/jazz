package net.bodz.bas.context;

import net.bodz.bas.jvm.stack.Caller;
import net.bodz.bas.t.preorder.PackageNamePreorder;

public class PackageContextId
        extends PreorderContextId<String> {

    public PackageContextId(IContextId fallbackContext, String packageName) {
        super(fallbackContext, PackageNamePreorder.getInstance(), packageName);
    }

    public static PackageContextId forName(String packageName) {
        return new PackageContextId(StaticContextId.getInstance(), packageName);
    }

    public static PackageContextId forClass(Class<?> clazz) {
        return forName(clazz.getPackage().getName());
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

    public static PackageContextId forCaller(IContextId fallbackId) {
        Class<?> callerClass = Caller.getCallerClass(1);
        return new PackageContextId(fallbackId, getPackageName(callerClass));
    }

    public static PackageContextId forCaller() {
        Class<?> callerClass = Caller.getCallerClass(1);
        return new PackageContextId(StaticContextId.getInstance(), getPackageName(callerClass));
    }

    public static PackageContextId forCallerParent(IContextId fallbackContext) {
        Class<?> callerClass = Caller.getCallerClass(1);
        return new PackageContextId(fallbackContext, getParentPackageName(callerClass));
    }

    public static PackageContextId forCallerParent() {
        Class<?> callerClass = Caller.getCallerClass(1);
        return new PackageContextId(StaticContextId.getInstance(), getParentPackageName(callerClass));
    }

}
