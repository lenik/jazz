package net.bodz.mda.xjdoc.tagtype;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.flatf.IFlatfOutput;

public class URLMapTag
        extends AbstractMapDocTag<URL> {

    @Override
    protected URL parseJavadoc(String s)
            throws ParseException {
        try {
            return new URI(s).toURL();
        } catch (URISyntaxException | MalformedURLException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

    @Override
    protected String formatJavadoc(URL value) {
        return value.toString();
    }

    @Override
    protected URL parseFlatf(String s)
            throws ParseException {
        try {
            return new URI(s).toURL();
        } catch (URISyntaxException | MalformedURLException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

    @Override
    protected String formatFlatf(IFlatfOutput out, URL value) {
        return value.toString();
    }

}
