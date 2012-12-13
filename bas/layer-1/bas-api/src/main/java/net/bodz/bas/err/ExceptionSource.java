package net.bodz.bas.err;

import java.beans.ExceptionListener;

public interface ExceptionSource {

    void addExceptionListener(ExceptionListener listener);

    void removeExceptionListener(ExceptionListener listener);

}
