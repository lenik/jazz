package net.bodz.bas.io.data;

import java.io.IOException;

import org.junit.Test;

import net.bodz.bas.io.IByteOut;
import net.bodz.bas.io.IDataOut;

public class DataOutImplBETest
        extends AbstractDataOutTest {

    @Override
    protected IDataOut create(IByteOut byteOut) {
        return new DataOutImplBE(byteOut);
    }

    @Test
    public void testWriteFloat()
            throws IOException {
        DataBuffer db = getDataOut();
        db.writeFloat(12.345f);
        db.writeFloat(Float.NaN);
        db.writeFloat(Float.POSITIVE_INFINITY);
        byte[] byteArray = db.toByteArray();
        assertArrayEquals(floatBytesBE, byteArray);
    }

}
