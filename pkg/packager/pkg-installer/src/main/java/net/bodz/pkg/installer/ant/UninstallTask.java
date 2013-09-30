package net.bodz.pkg.installer.ant;

import net.bodz.pkg.installer.IComponent;

/**
 * Ant task for uninstall Distins-Project
 */
public class UninstallTask
        extends ExecuteProjectTask {

    @Override
    protected int getType() {
        return IComponent.UNINSTALL;
    }

}
