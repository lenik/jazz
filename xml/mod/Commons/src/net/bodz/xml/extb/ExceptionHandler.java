package net.bodz.xml.extb;

public interface ExceptionHandler {

    int CANCEL = 0;
    int IGNORE = 1;
    int RETRY  = 2;

    int onException(ExceptionType type, Exception e);

}
