package net.bodz.bas.t.project;

/**
 * To make the default {@link #getPackageName()} and all the related methods work, your direct
 * derivation of this abstract class should be declared as <code>final</code>.
 */
public abstract class AbstractJazzModule
        implements IJazzModule {

    private String packageName;
    private String moduleName;

    public AbstractJazzModule() {
        this.packageName = getClass().getPackage().getName();

        String moduleClassName = getClass().getSimpleName();
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

    public String getModuleName() {
        return moduleName;
    }

}
