package net.bodz.bas.io.typemeta;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.mf.std.AbstractCommonMdaFeatures;
import net.bodz.bas.mf.std.IParser;
import net.bodz.bas.sio.ICharOut;
import net.bodz.bas.sio.IPrintOut;
import net.bodz.bas.sio.WriterPrintOut;

public class CharOutMdaFeatures
        extends AbstractCommonMdaFeatures<ICharOut> {

    public CharOutMdaFeatures() {
        super(ICharOut.class);
    }

    @Override
    protected Object query(int mdaFeaturesIndex) {
        if (mdaFeaturesIndex == IParser.mdaFeatureIndex)
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
