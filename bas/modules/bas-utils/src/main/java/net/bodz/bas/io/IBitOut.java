package net.bodz.bas.io;

import java.io.IOException;

import net.bodz.bas.math.IBits;

public interface IBitOut {

    void write(IBits bits) throws IOException;

}
