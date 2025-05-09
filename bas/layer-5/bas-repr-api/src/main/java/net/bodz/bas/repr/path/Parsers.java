package net.bodz.bas.repr.path;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.meta.decl.NotNull;

public class Parsers {

    @NotNull
    public static List<String> parsePath(String path) {
        List<String> tokens = new ArrayList<>(20);
        int pos = 0;
        int slash;
        while ((slash = path.indexOf('/', pos)) != -1) {
            if (slash > pos) { // skip empty tokens like '//', and trailing token.
                String token = path.substring(pos, slash);
                tokens.add(token);
            }
            pos = slash + 1;
        }

        // pos = 0 or (lastSlash + 1)
        if (pos < path.length()) {
            String token = path.substring(pos);
            tokens.add(token);
        }
        return tokens;
    }

    public static String datePart(String str) {
        int sp = str.indexOf(' ');
        if (sp != -1)
            if (str.indexOf(':', sp + 1) != -1)
                str = str.substring(0, sp);

        int pos = str.indexOf('T');
        if (pos != -1)
            str = str.substring(0, pos);

        return str;
    }

    public static String timePart(String str) {
        int sp = str.indexOf(' ');
        if (sp != -1) {
            if (str.indexOf(':', sp) != -1)
                str = str.substring(sp + 1);
        }

        int pos = str.indexOf('T');
        if (pos != -1) {
            String suffix = str.substring(pos);
            boolean weekday = suffix.startsWith("Tue") || suffix.startsWith("Thu") || suffix.startsWith("TUE") || suffix.startsWith("THU");
            if (!weekday)
                str = str.substring(0, pos + 1);
        }
        return str;
    }

}
