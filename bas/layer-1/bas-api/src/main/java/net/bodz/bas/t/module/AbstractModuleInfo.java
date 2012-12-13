package net.bodz.bas.t.module;

/**
 * To make the default {@link #getPackageName()} and all the related methods work, your direct
 * derivation of this abstract class should be declared as <code>final</code>.
 */
public abstract class AbstractModuleInfo
        implements IModuleInfo {

    private String packageName;

    public AbstractModuleInfo() {
        this.packageName = getClass().getPackage().getName();

        String moduleClassName = getClass().getName();
        String moduleName;
        if (moduleClassName.endsWith("ModuleInfo"))
            moduleName = moduleClassName.substring(0, moduleClassName.length() - "ModuleInfo".length());
        else if (moduleClassName.endsWith("Info"))
            moduleName = moduleClassName.substring(0, moduleClassName.length() - "Info".length());
        else
            moduleName = moduleClassName;

        // ResourceBundleNLS rbNLS = new ResourceBundleNLS(null, moduleName);
        // this.NLS = rbNLS;
    }

    public String getPackageName() {
        return packageName;
    }

}
