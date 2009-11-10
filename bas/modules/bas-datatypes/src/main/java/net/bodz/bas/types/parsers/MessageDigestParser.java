package net.bodz.bas.types.parsers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.types.TypeParser;

public class MessageDigestParser implements TypeParser {

    @Override
    public MessageDigest parse(String name) throws ParseException {
        String provider = null;
        int d = name.indexOf("::"); //$NON-NLS-1$
        if (d != -1) {
            provider = name.substring(0, d);
            name = name.substring(d + 2);
        }
        try {
            if (provider == null)
                return MessageDigest.getInstance(name);
            else
                return MessageDigest.getInstance(name, provider);
        } catch (NoSuchAlgorithmException e) {
            throw new ParseException(e.getMessage(), e);
        } catch (NoSuchProviderException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

}
