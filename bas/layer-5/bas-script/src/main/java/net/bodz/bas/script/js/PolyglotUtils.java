package net.bodz.bas.script.js;

import org.graalvm.polyglot.PolyglotException;
import org.graalvm.polyglot.PolyglotException.StackFrame;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.SourceSection;

import net.bodz.bas.c.string.StringQuote;
import net.bodz.bas.c.string.Strings;

public class PolyglotUtils {

    public static String getGuestStackDump(PolyglotException e) {
        StringBuilder sb = new StringBuilder(1000);

        for (StackFrame frame : e.getPolyglotStackTrace()) {
            if (frame.isGuestFrame()) {
                SourceSection location = frame.getSourceLocation();
                if (location == null)
                    continue;

                Source source = location.getSource();
                if (source.hasCharacters()) {
                    String code = source.getCharacters().toString();
                    int start = location.getCharIndex();
                    int end = location.getCharEndIndex();
                    String selection = code.substring(start, end);
                    String min = Strings.ellipsis(selection, 80);
                    String quoted = StringQuote.qqJavaString(min);

                    // file.js:LINE:COL "code"
                    sb.append("    ");
                    sb.append(source.getName());
                    sb.append(":");
                    sb.append(location.getStartLine());
                    sb.append(":");
                    sb.append(location.getStartColumn());
                    sb.append(" ");
                    sb.append(quoted);
                    sb.append("\n");
                }
            }
        }

        if (sb.length() == 0)
            return "(no location info)";
        else
            return sb.toString();
    }

}
