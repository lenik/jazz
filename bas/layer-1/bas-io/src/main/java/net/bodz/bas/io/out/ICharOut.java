package net.bodz.bas.io.out;

import java.io.IOException;

public interface ICharOut {

    void write(char[] chars, int off, int len) throws IOException;

}
