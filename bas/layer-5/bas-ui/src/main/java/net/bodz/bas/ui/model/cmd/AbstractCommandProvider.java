package net.bodz.bas.ui.model.cmd;

public abstract class AbstractCommandProvider
        implements ICommandProvider {

    @Override
    public Class<?> getTargetClass() {
        return void.class;
    }

}
