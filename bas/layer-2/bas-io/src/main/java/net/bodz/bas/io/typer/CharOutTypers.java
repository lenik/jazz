package net.bodz.bas.io.typer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.io.ICharOut;
import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.io.adapter.WriterPrintOut;
import net.bodz.bas.typer.std.AbstractCommonTypers;
import net.bodz.bas.typer.std.IParser;

public class CharOutTypers
        extends AbstractCommonTypers<ICharOut> {

    public CharOutTypers() {
        super(ICharOut.class);
    }

    @Override
    protected Object queryInt(int typerIndex) {
        if (typerIndex == IParser.typerIndex)
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
