package user;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.mf.std.AbstractCommonMdaFeatures;
import net.bodz.bas.mf.std.IParser;

public class ASLMdaFeatures
        extends AbstractCommonMdaFeatures<ASL> {

    public ASLMdaFeatures() {
        super(ASL.class);
    }

    @Override
    protected Object _query(int mdaFeatureIndex) {
        switch (mdaFeatureIndex) {
        case IParser.mdaFeatureIndex:
            return this;
        default:
            return null;
        }
    }

    @Override
    public ASL parse(String text)
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