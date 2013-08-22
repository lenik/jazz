package net.bodz.bas.io.typemeta;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.mf.std.AbstractCommonMdaFeatures;
import net.bodz.bas.mf.std.IParser;
import net.bodz.bas.sio.IByteOut;
import net.bodz.bas.sio.OutputStreamByteOut;

public class ByteOutMdaFeatures
        extends AbstractCommonMdaFeatures<IByteOut> {

    public ByteOutMdaFeatures() {

        super(IByteOut.class);
    }

    @Override
    protected Object query(int mdaFeaturesIndex) {
        if (mdaFeaturesIndex == IParser.mdaFeatureIndex)
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
