package net.bodz.bas.lang.events;

import java.beans.ExceptionListener;

public interface ExceptionSource {

    void addExceptionListener(ExceptionListener listener);

    void removeExceptionListener(ExceptionListener listener);

}
