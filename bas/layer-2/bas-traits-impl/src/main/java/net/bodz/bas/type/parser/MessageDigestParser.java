package net.bodz.bas.type.parser;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import net.bodz.bas.traits.AbstractParser;
import net.bodz.bas.util.exception.ParseException;

public class MessageDigestParser
        extends AbstractParser<MessageDigest> {

    @Override
    public MessageDigest parse(String name)
            throws ParseException {
        String provider = null;
        int d = name.indexOf("::"); 
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
