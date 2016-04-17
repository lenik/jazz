package net.bodz.bas.ui.model.action;

import java.util.EventObject;

public class ActionEvent
        extends EventObject {

    private static final long serialVersionUID = 1L;

    public ActionEvent(IAction source) {
        super(source);
    }

    @Override
    public IAction getSource() {
        return (IAction) super.getSource();
    }

}
