package net.bodz.violet.store;

import java.util.Random;
import java.util.StringTokenizer;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.typer.std.AbstractCommonTypers;
import net.bodz.bas.typer.std.IFormatter;
import net.bodz.bas.typer.std.IParser;
import net.bodz.bas.typer.std.ISampleGenerator;

public class Dim3dTypers
        extends AbstractCommonTypers<Dim3d> {

    public Dim3dTypers() {
        super(Dim3d.class);
    }

    @Override
    protected Object queryInt(int typerIndex) {
        switch (typerIndex) {
        case IFormatter.typerIndex:
        case IParser.typerIndex:
        case ISampleGenerator.typerIndex:
            return this;
        }
        return null;
    }

    @Override
    public String format(Dim3d object, IOptions options) {
        return object.toString();
    }

    @Override
    public Dim3d parse(String text, IOptions options)
            throws ParseException {
        Dim3d result = new Dim3d();
        StringTokenizer tokens = new StringTokenizer(text, "x");
        int index = 0;
        while (tokens.hasMoreTokens()) {
            String token = tokens.nextToken();
            double val;
            try {
                val = Double.parseDouble(token);
            } catch (NumberFormatException e) {
                throw new ParseException(String.format(//
                        "Invalid dimension component[%d]: %s", index, e.getMessage()));
            }
            switch (index++) {
            case 0:
                result.dx = val;
                break;
            case 1:
                result.dy = val;
                break;
            case 2:
                result.dz = val;
                break;
            default:
                throw new ParseException(String.format(//
                        "Unexpected dimension component[%d]: %s", index, token));
            }
        }
        return result;
    }

    @Override
    public Dim3d newSample(IOptions options)
            throws CreateException {
        Random prng = options.get(Random.class, random);
        Dim3d a = new Dim3d();
        a.dx = prng.nextDouble();
        a.dy = prng.nextDouble();
        a.dz = prng.nextDouble();
        return a;
    }

}
