package net.bodz.bas.arch.module;

import net.bodz.bas.i18n.nls.NLS;
import net.bodz.bas.i18n.nls.ResourceBundleNLS;

/**
 * To make the default {@link #getPackageName()} and all the related methods work, your direct
 * derivation of this abstract class should be declared as <code>final</code>.
 */
public abstract class AbstractModuleInfo
        implements IModuleInfo {

    private String packageName;
    private NLS NLS;

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

        ResourceBundleNLS rbNLS = new ResourceBundleNLS(null, moduleName);
        this.NLS = rbNLS;
    }

    public String getPackageName() {
        return packageName;
    }

    @Override
    public NLS getNLS() {
        return NLS;
    }

}
