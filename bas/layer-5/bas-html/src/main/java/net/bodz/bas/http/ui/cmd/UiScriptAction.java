package net.bodz.bas.http.ui.cmd;

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
