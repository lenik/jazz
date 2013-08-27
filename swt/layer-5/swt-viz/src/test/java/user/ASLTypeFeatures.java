package user;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.tf.std.AbstractCommonTypeFeatures;
import net.bodz.bas.tf.std.IParser;

public class ASLTypeFeatures
        extends AbstractCommonTypeFeatures<ASL> {

    public ASLTypeFeatures() {
        super(ASL.class);
    }

    @Override
    protected Object _query(int typeFeatureIndex) {
        switch (typeFeatureIndex) {
        case IParser.typeFeatureIndex:
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