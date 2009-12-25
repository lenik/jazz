package net.bodz.bas.commons.modules;

/**
 * bas-c1 module
 * 
 * @test {@link ModuleInfoTest}
 */
public final class ModuleInfo
        extends AbstractModuleInfo {

    private static ModuleInfo instance = new ModuleInfo();

    public static IModuleInfo getInstance() {
        return instance;
    }

}
