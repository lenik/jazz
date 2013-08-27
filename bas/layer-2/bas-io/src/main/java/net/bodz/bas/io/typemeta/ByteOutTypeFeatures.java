package net.bodz.bas.io.typemeta;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.tf.std.AbstractCommonTypeFeatures;
import net.bodz.bas.tf.std.IParser;
import net.bodz.bas.sio.IByteOut;
import net.bodz.bas.sio.OutputStreamByteOut;

public class ByteOutTypeFeatures
        extends AbstractCommonTypeFeatures<IByteOut> {

    public ByteOutTypeFeatures() {

        super(IByteOut.class);
    }

    @Override
    protected Object _query(int typeFeatureIndex) {
        if (typeFeatureIndex == IParser.typeFeatureIndex)
            return this;
        return null;
    }

    @Override
    public IByteOut parse(String path)
            throws ParseException {
        FileOutputStream out;
        try {
            out = new FileOutputStream(path);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return new OutputStreamByteOut(out);
    }

}
