package net.bodz.lily.entity.manager;

public class SaveContentCommand
        extends SaveCommand {

    @Override
    public boolean isContentCommand() {
        return true;
    }

}
