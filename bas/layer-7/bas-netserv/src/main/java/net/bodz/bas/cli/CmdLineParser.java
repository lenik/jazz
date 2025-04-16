package net.bodz.bas.cli;

import java.util.ArrayList;
import java.util.List;

public class CmdLineParser {

    public List<String> parse(String input) {
        List<String> args = new ArrayList<>();
        StringBuilder arg = new StringBuilder();
        char inQuotes = 0;
        boolean escapeNextChar = false;
        boolean inWhitespace = true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (escapeNextChar) {
                switch (c) {
                    case 'n':
                        c = '\n';
                        break;
                    case 't':
                        c = '\t';
                        break;
                    case '0':
                        c = '\0';
                        break;
                }
                arg.append(c);
                escapeNextChar = false;
            } else {
                switch (c) {
                    case '\\':
                        escapeNextChar = true;
                        inWhitespace = false;
                        continue;

                    case '\'':
                    case '"':
                        if (inQuotes == 0)
                            inQuotes = c;
                        else if (inQuotes == c)
                            inQuotes = 0;
                        inWhitespace = false;
                        continue;
                }
            }

            if (inQuotes == 0 && Character.isWhitespace(c)) {
                if (inWhitespace)
                    continue;
                args.add(arg.toString());
                arg.setLength(0);
                inWhitespace = true;
                continue;
            } else {
                arg.append(c);
                inWhitespace = false;
            }
        } // for input

        if (!inWhitespace)
            args.add(arg.toString());

        return args;
    }

    public static void main(String[] args) {
        CmdLineParser parser = new CmdLineParser();
        List<String> v = parser.parse("   foo  bar 1 ' 2 3 '.");
        System.out.println(v);
    }

}
