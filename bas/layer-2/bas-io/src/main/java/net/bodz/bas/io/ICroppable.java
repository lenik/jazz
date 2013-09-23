package net.bodz.bas.io;

import java.io.IOException;

import net.bodz.bas.io.res.IStreamResource;

public interface ICroppable {

    IStreamResource crop(long start, long end)
            throws IOException;

}
