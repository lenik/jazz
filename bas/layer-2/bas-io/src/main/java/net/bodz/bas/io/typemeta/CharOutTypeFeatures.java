package net.bodz.bas.io.typemeta;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.tf.std.AbstractCommonTypeFeatures;
import net.bodz.bas.tf.std.IParser;
import net.bodz.bas.sio.ICharOut;
import net.bodz.bas.sio.IPrintOut;
import net.bodz.bas.sio.WriterPrintOut;

public class CharOutTypeFeatures
        extends AbstractCommonTypeFeatures<ICharOut> {

    public CharOutTypeFeatures() {
        super(ICharOut.class);
    }

    @Override
    protected Object _query(int typeFeatureIndex) {
        if (typeFeatureIndex == IParser.typeFeatureIndex)
            return this;
        return null;
    }

    @Override
    public IPrintOut parse(String path)
            throws ParseException {
        FileOutputStream out;
        try {
            out = new FileOutputStream(path);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        OutputStreamWriter writer = new OutputStreamWriter(out);
        return new WriterPrintOut(writer);
    }

}
