package net.bodz.dist.ins;

public class UninstallTask extends ExecuteProjectTask {

    @Override
    protected int getType() {
        return Component.UNINSTALL;
    }

}
