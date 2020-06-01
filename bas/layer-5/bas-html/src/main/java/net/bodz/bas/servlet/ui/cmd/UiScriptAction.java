package net.bodz.bas.servlet.ui.cmd;

public abstract class UiScriptAction
        extends UiServletAction {

    @Override
    public boolean isScriptOnly() {
        return true;
    }

    @Override
    public Object run(Object obj, IServletActionContext context) {
        return null;
    }

}
