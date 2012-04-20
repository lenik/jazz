package net.bodz.bas.srt.impl;

import java.io.IOException;
import java.io.Reader;

import net.bodz.bas.srt.SerializeException;

/** ic?; (; -> \;) */
public class CharacterSerializer
        extends StringSerializer {

    @Override
    public Character unserialize(Reader s)
            throws IOException, SerializeException {
        String str = (String) super.unserialize(s);
        return str.charAt(0);
    }

}
