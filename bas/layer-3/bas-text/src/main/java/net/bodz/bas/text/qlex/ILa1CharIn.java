package net.bodz.bas.text.qlex;

import java.io.IOException;

import net.bodz.bas.io.ICharIn;

public interface ILa1CharIn
        extends
            ICharIn {

    int look()
            throws IOException;

}
