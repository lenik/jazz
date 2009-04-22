package net.bodz.dist.ins;

public class InstallTask extends ExecuteProjectTask {

    @Override
    protected int getType() {
        return Component.INSTALL;
    }

}
