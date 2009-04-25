package net.bodz.dist.ins;

/**
 * Ant task for uninstall Distins-Project
 */
public class UninstallTask extends ExecuteProjectTask {

    @Override
    protected int getType() {
        return Component.UNINSTALL;
    }

}
