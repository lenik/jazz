package net.bodz.bas.io.typemeta;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import net.bodz.bas.sio.ICharOut;
import net.bodz.bas.sio.IPrintOut;
import net.bodz.bas.sio.WriterPrintOut;
import net.bodz.bas.traits.AbstractCommonTraits;
import net.bodz.bas.traits.IParser;
import net.bodz.bas.util.exception.ParseException;

public class CharOutTraits
        extends AbstractCommonTraits<ICharOut> {

    public CharOutTraits() {
        super(ICharOut.class);
    }

    @Override
    public IParser<ICharOut> getParser() {
        return this;
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
