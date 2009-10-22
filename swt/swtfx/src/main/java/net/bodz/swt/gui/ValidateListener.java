package net.bodz.swt.gui;

import java.util.EventListener;


public interface ValidateListener extends EventListener {

    void validate(ValidateEvent e) throws ValidateException;

}
