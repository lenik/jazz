package net.bodz.bios;

import java.io.Flushable;
import java.io.IOException;

public interface Sender extends Flushable {

    void send(Object data) throws IOException;

    @Override
    void flush() throws IOException;

}
