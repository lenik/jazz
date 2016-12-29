package net.bodz.mda.xjdoc.tagtype;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.build.IVersion;

public class VersionTagType
        extends AbstractScalarTagType<IVersion> {

    @Override
    public Class<?> getValueType() {
        return IVersion.class;
    }

    @Override
    protected IVersion parse(String s)
            throws ParseException {
        return IVersion.fn.parse(s);
    }

    @Override
    protected String format(IVersion value) {
        if (value == null)
            return null;
        else
            return value.toString();
    }

    public static final VersionTagType INSTANCE = new VersionTagType();

}
