package net.bodz.bas.context;

import net.bodz.bas.jvm.stack.Caller;
import net.bodz.bas.t.preorder.PackageNamePreorder;

public class PackageContext
        extends PreorderContext<String> {

    public PackageContext(String packageName) {
        super(PackageNamePreorder.getInstance(), packageName);
    }

    public static PackageContext forName(String packageName) {
        return new PackageContext(packageName);
    }

    public static PackageContext forClass(Class<?> clazz) {
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

    public static PackageContext forCaller() {
        Class<?> callerClass = Caller.getCallerClass(1);
        return new PackageContext(getPackageName(callerClass));
    }

    public static PackageContext forCallerParent() {
        Class<?> callerClass = Caller.getCallerClass(1);
        return new PackageContext(getParentPackageName(callerClass));
    }

}
