package net.bodz.pkg.installer.ant;

import net.bodz.pkg.installer.IComponent;

/**
 * Ant task for install Distins-Project
 */
public class InstallTask
        extends ExecuteProjectTask {

    @Override
    protected int getType() {
        return IComponent.INSTALL;
    }

}
