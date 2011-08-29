package net.bodz.bas.util.exception;

import java.beans.ExceptionListener;

public interface ExceptionSource {

    void addExceptionListener(ExceptionListener listener);

    void removeExceptionListener(ExceptionListener listener);

}
