package net.bodz.bas.ctx.scope.impl;

import net.bodz.bas.jvm.stack.Caller;
import net.bodz.bas.t.preorder.PackageNamePreorder;

public class PackageScopeInstance
        extends PreorderScopeInstance<String> {

    public PackageScopeInstance(String packageName) {
        super(PackageNamePreorder.getInstance(), packageName);
    }

    public static PackageScopeInstance forName(String packageName) {
        return new PackageScopeInstance(packageName);
    }

    public static PackageScopeInstance forClass(Class<?> clazz) {
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

    public static PackageScopeInstance forCaller() {
        Class<?> callerClass = Caller.getCallerClass(1);
        return new PackageScopeInstance(getPackageName(callerClass));
    }

    public static PackageScopeInstance forCallerParent() {
        Class<?> callerClass = Caller.getCallerClass(1);
        return new PackageScopeInstance(getParentPackageName(callerClass));
    }

}
