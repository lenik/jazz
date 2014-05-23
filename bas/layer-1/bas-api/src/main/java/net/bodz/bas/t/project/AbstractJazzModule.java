package net.bodz.bas.t.project;

/**
 * To make the default {@link #getPackageName()} and all the related methods work, your direct
 * derivation of this abstract class should be declared as <code>final</code>.
 */
public abstract class AbstractJazzModule
        implements IJazzModule {

    private String name;
    private String packageName;

    public AbstractJazzModule() {
        String baseName = getClass().getSimpleName();
        if (baseName.endsWith("Module"))
            baseName = baseName.substring(0, baseName.length() - 6);

        this.name = baseName;
        this.packageName = getClass().getPackage().getName();
    }

    @Override
    public String getName() {
        return name;
    }

    public String getPackageName() {
        return packageName;
    }

}
