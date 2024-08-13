package net.bodz.bas.io;

import java.io.IOException;

public interface IErrorHandler {

    boolean readError(IOException e);

    boolean writeError(IOException e);

}
