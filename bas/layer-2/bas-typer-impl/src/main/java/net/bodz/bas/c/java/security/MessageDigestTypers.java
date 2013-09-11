package net.bodz.bas.c.java.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.typer.std.AbstractCommonTypers;
import net.bodz.bas.typer.std.IParser;
import net.bodz.bas.typer.std.ISampleGenerator;

public class MessageDigestTypers
        extends AbstractCommonTypers<MessageDigest> {

    public MessageDigestTypers() {
        super(MessageDigest.class);
    }

    @Override
    protected Object queryInt(int typerIndex) {
        switch (typerIndex) {
        case IParser.typerIndex:
        case ISampleGenerator.typerIndex:
            return this;
        }
        return null;
    }

    @Override
    public MessageDigest parse(String name, IOptions options)
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

    List<String> messageDigestNames;

    @Override
    public synchronized MessageDigest newSample(IOptions options)
            throws CreateException {
        if (messageDigestNames == null) {
            messageDigestNames = new ArrayList<String>();
            for (Provider provider : Security.getProviders()) {
                for (Object _entry : provider.keySet()) {
                    String entry = (String) _entry;
                    if (entry.startsWith("MessageDigest."))
                        messageDigestNames.add(entry.substring("MessageDigest.".length()));
                }
            }
        }

        Random prng = options.get(Random.class, random);

        int randomIndex = prng.nextInt(messageDigestNames.size());
        String randomMesageDigestName = messageDigestNames.get(randomIndex);
        try {
            MessageDigest randomMessageDigest = MessageDigest.getInstance(randomMesageDigestName);
            return randomMessageDigest;
        } catch (NoSuchAlgorithmException e) {
            // The specific message digest is lost since last scan of providers.
            // Refresh and do it again.
            messageDigestNames = null;
            return newSample();
        }
    }

}
