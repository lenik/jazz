package net.bodz.mda.xjdoc.model.javadoc;

import net.bodz.bas.err.ParseException;
import net.bodz.mda.xjdoc.tagtype.AbstractScalarTagType;

public class AuthorTagType
        extends AbstractScalarTagType<Author> {

    @Override
    public Class<?> getValueType() {
        return Author.class;
    }

    @Override
    protected Author parse(String s)
            throws ParseException {
        return Author.parse(s);
    }

    @Override
    protected String format(Author value) {
        return value.toString();
    }

    static AuthorTagType instance = new AuthorTagType();

    public static AuthorTagType getInstance() {
        return instance;
    }

}
