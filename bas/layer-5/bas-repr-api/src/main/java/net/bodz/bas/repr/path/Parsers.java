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

}
