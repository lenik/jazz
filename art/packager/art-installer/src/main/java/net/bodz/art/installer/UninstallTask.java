package net.bodz.art.installer;

/**
 * Ant task for uninstall Distins-Project
 */
public class UninstallTask extends ExecuteProjectTask {

    @Override
    protected int getType() {
        return IComponent.UNINSTALL;
    }

}
