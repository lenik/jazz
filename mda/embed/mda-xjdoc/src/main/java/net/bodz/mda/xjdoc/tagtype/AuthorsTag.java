package net.bodz.mda.xjdoc.tagtype;

import net.bodz.bas.err.ParseException;
import net.bodz.mda.xjdoc.model.javadoc.Author;

public class AuthorsTag
        extends RepeatDocTag<Author> {

    @Override
    protected Author parse(String s)
            throws ParseException {
        return Author.parse(s);
    }

    @Override
    protected String format(Author value) {
        return value.toString();
    }

}
