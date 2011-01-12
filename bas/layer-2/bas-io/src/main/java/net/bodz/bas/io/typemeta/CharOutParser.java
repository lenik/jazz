package net.bodz.bas.io.typemeta;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import net.bodz.bas.sio.IPrintOut;
import net.bodz.bas.sio.WriterPrintOut;
import net.bodz.bas.type.traits.AbstractParser;
import net.bodz.bas.util.exception.ParseException;

public class CharOutParser
        extends AbstractParser<IPrintOut> {

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
