package user;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.typer.std.AbstractCommonTypers;
import net.bodz.bas.typer.std.IParser;

public class ASLTypers
        extends AbstractCommonTypers<ASL> {

    public ASLTypers() {
        super(ASL.class);
    }

    @Override
    protected Object queryInt(int typerIndex) {
        switch (typerIndex) {
        case IParser.typerIndex:
            return this;
        default:
            return null;
        }
    }

    @Override
    public ASL parse(String text, IOptions options)
            throws ParseException {
        int slash = text.indexOf('/');
        int age = Integer.parseInt(text.substring(0, slash));
        text = text.substring(slash + 1);
        slash = text.indexOf('/');
        String sex = text.substring(0, slash);
        String location = text.substring(slash + 1);
        return new ASL(age, "m".equalsIgnoreCase(sex), location);
    }

}