package net.bodz.bas.c.java.util.regex;

import java.io.IOException;
import java.util.regex.Matcher;

public interface IPartProcessor {

    void process(CharSequence in, int start, int end, Appendable out, Matcher matcher)
            throws IOException;

}
