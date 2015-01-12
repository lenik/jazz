package net.bodz.bas.ctx.scope;

import net.bodz.bas.jvm.stack.Caller;
import net.bodz.bas.t.preorder.PackageNamePreorder;

public class PackageScopeToken
        extends PreorderScopeToken<String> {

    public PackageScopeToken(String packageName) {
        super(PackageNamePreorder.getInstance(), packageName);
    }

    public static PackageScopeToken forName(String packageName) {
        return new PackageScopeToken(packageName);
    }

    public static PackageScopeToken forClass(Class<?> clazz) {
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

    public static PackageScopeToken forCaller() {
        Class<?> callerClass = Caller.getCallerClass(1);
        return new PackageScopeToken(getPackageName(callerClass));
    }

    public static PackageScopeToken forCallerParent() {
        Class<?> callerClass = Caller.getCallerClass(1);
        return new PackageScopeToken(getParentPackageName(callerClass));
    }

}
