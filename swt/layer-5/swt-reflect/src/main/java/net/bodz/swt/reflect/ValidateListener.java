package net.bodz.swt.reflect;

import java.util.EventListener;


public interface ValidateListener extends EventListener {

    void validate(ValidateEvent e) throws ValidateException;

}
