package net.bodz.bas.data.struct;

import java.io.IOException;
import java.io.Serializable;

import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.io.BByteIn;
import net.bodz.bas.io.BByteOut;
import net.bodz.bas.io.IByteIn;
import net.bodz.bas.io.IDataIn;
import net.bodz.bas.io.data.DataInImplBE;
import net.bodz.bas.io.data.DataInImplLE;
import net.bodz.bas.io.data.DataOutImplBE;
import net.bodz.bas.io.data.DataOutImplLE;

public interface IDataStruct
        extends IOctetStreamForm,
                Serializable {

    int dataSize();

    int FORMAT_LE = 0;
    int FORMAT_BE = 1;

    default IDataIn transfer(int format) {
        return fn.transfer(this, format);
    }

    class fn {

        public static IDataIn transfer(IDataStruct struct, int format) {
            int size = struct.dataSize();
            BByteOut bo = new BByteOut(size);

            try {
                switch (format) {
                    case FORMAT_LE:
                        struct.writeObject(DataOutImplLE.from(bo));
                        break;
                    case FORMAT_BE:
                        struct.writeObject(DataOutImplBE.from(bo));
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
                    return DataInImplLE.from((IByteIn) bi);
                case FORMAT_BE:
                    return DataInImplBE.from((IByteIn) bi);
                default:
                    throw new UnexpectedException();
            }
        }

    }

}
