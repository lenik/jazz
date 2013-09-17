package net.bodz.pkg.installer;

/**
 * Ant task for install Distins-Project
 * 
 * @test {@link InstallTaskTest}
 */
public class InstallTask
        extends ExecuteProjectTask {

    @Override
    protected int getType() {
        return IComponent.INSTALL;
    }

}
