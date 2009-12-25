package net.bodz.bas.module;

import net.bodz.bas.module.ModuleInfoTest;

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
