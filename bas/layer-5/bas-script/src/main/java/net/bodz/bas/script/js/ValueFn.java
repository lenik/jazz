package net.bodz.bas.script.js;

import java.io.IOException;

import org.graalvm.polyglot.Value;

import net.bodz.bas.c.org.json.JsonBuffer;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.fmt.json.IJsonOut;

public class ValueFn {

    public static String dumpBoxed(Value value)
            throws FormatException {
        JsonBuffer out = new JsonBuffer();
        try {
            dumpBoxed(out, value);
        } catch (IOException e) {
            throw new UnexpectedException(e);
        }
        return out.toString();
    }

    public static void dumpBoxed(IJsonOut out, Value value)
            throws IOException, FormatException {
        ValueDumper dumper = new ValueDumper(out);
        dumper.dumpBoxed(value);
    }

    public static Object convert(Value value) {
        if (value == null)
            return null;
        if (value.isHostObject())
            return value.asHostObject();
        return value;
    }

}
