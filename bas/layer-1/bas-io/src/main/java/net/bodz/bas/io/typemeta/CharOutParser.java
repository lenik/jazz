package net.bodz.bas.io.typemeta;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import net.bodz.bas.exceptions.ParseException;
import net.bodz.bas.sio.ILineCharOut;
import net.bodz.bas.sio.WriterCharOut;
import net.bodz.bas.type.traits.AbstractParser;

public class CharOutParser
        extends AbstractParser<ILineCharOut> {

    @Override
    public ILineCharOut parse(String path)
            throws ParseException {
        FileOutputStream out;
        try {
            out = new FileOutputStream(path);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        OutputStreamWriter writer = new OutputStreamWriter(out);
        return new WriterCharOut(writer);
    }

}
