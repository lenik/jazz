package net.bodz.bas.ctx.scope.id;

import net.bodz.bas.jvm.stack.Caller;
import net.bodz.bas.t.preorder.PackageNamePreorder;

public class PackageScopeDescriptor
        extends PreorderScopeDescriptor<String> {

    public PackageScopeDescriptor(String packageName) {
        super(PackageNamePreorder.getInstance(), packageName);
    }

    public static PackageScopeDescriptor forName(String packageName) {
        return new PackageScopeDescriptor(packageName);
    }

    public static PackageScopeDescriptor forClass(Class<?> clazz) {
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

    public static PackageScopeDescriptor forCaller() {
        Class<?> callerClass = Caller.getCallerClass(1);
        return new PackageScopeDescriptor(getPackageName(callerClass));
    }

    public static PackageScopeDescriptor forCallerParent() {
        Class<?> callerClass = Caller.getCallerClass(1);
        return new PackageScopeDescriptor(getParentPackageName(callerClass));
    }

}
