package net.bodz.bas.data.struct;

import java.io.IOException;

import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.io.BByteIn;
import net.bodz.bas.io.BByteOut;
import net.bodz.bas.io.IDataIn;
import net.bodz.bas.io.data.DataInImplBE;
import net.bodz.bas.io.data.DataInImplLE;
import net.bodz.bas.io.data.DataOutImplBE;
import net.bodz.bas.io.data.DataOutImplLE;

public abstract class DataStruct
        implements IDataStruct {

    private static final long serialVersionUID = 1L;

    @SuppressWarnings("resource")
    @Override
    public IDataIn transfer(int format) {
        int size = size();
        BByteOut bo = new BByteOut(size);

        try {
            switch (format) {
            case FORMAT_LE:
                writeObject(DataOutImplLE.from(bo));
                break;
            case FORMAT_BE:
                writeObject(DataOutImplBE.from(bo));
                break;
            default:
                throw new IllegalArgumentException("Bad format: " + format);
            }
        } catch (IOException e) {
            throw new UnexpectedException(e);
        }

        byte[] buf = bo.toByteArray();
        BByteIn bi = new BByteIn(buf);

        switch (format) {
        case FORMAT_LE:
            return DataInImplLE.from(bi);
        case FORMAT_BE:
            return DataInImplBE.from(bi);
        default:
            throw new UnexpectedException();
        }
    }

}
