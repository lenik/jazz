package net.bodz.bas.types.parsers;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import net.bodz.bas.io.CharOuts;
import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.log.ALog;
import net.bodz.bas.log.ALogs;
import net.bodz.bas.log.LogOuts;
import net.bodz.bas.types.TypeParser;

public class ALogParser implements TypeParser {

    @Override
    public ALog parse(String path) throws ParseException {
        FileOutputStream out;
        try {
            out = new FileOutputStream(path);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return ALogs.get(LogOuts.get(CharOuts.get(out), path));
    }

}
