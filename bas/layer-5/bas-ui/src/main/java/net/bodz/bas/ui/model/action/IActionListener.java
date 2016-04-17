package net.bodz.bas.ui.model.action;

import java.util.EventListener;

public interface IActionListener
        extends EventListener {

    void actionPlayed(ActionEvent e);

    void actionRollbacked(ActionEvent e);

}
