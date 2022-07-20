package net.bodz.lily.gen.cli;

import net.bodz.bas.c.string.StringEscape;
import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.meta.build.ProgramName;
import net.bodz.bas.program.skel.BatchCLI;
import net.bodz.bas.program.skel.FileHandler;

/**
 * Generate java string builder generation code.
 */
@ProgramName("codegen")
public class ToCodegen
        extends BatchCLI {

    /**
     * Count of space chars for a tab char. Default 4.
     *
     * @option -t =NCHAR
     */
    int tabSize = 4;

    /**
     * Use ITreeOut instead of StringBuilder.
     *
     * @option -T
     */
    boolean indented = true;

    /**
     * Use printf instead of println.
     *
     * @option -f
     */
    boolean format = false;

    @Override
    public void processFile(FileHandler handler)
            throws Exception {
        ITreeOut out = handler.openTreeOut();
        int lastLevel = 0;
        for (String line : handler.read().lines(true)) {
            Indentation indentation = getIndentation(line);
            int level = indentation.level;

            if (level != lastLevel && indented) {
                for (int i = lastLevel; i < level; i++)
                    out.println("out.enter();");
                for (int i = lastLevel; i >= level; i--)
                    out.println("out.leave();");
                line = line.substring(indentation.textOffset);
            }

            String literal = escape(line);

            if (indented) {
                if (format) {
                    literal = literal.replace("%", "%%");
                    out.println("out.printf(\"" + literal + "\\n\");");
                } else {
                    out.println("out.println(\"" + literal + "\");");
                }
            } else {
                if (format) {
                    literal = literal.replace("%", "%%");
                    out.println("sb.append(String.format(\"" + literal + "\\n\"));");
                } else {
                    out.println("sb.append(\"" + literal + "\\n\");");
                }
            }
        }
    }

    String escape(String s) {
        return StringEscape.escapeJava(s);
    }

    Indentation getIndentation(String s) {
        int pos = 0, len = s.length();
        int n = 0;
        L: while (pos < len) {
            switch (s.charAt(pos)) {
            case ' ':
                n++;
                break;

            case '\t':
                n += tabSize - (n % tabSize);
                break;

            default:
                break L;
            }
            pos++;
        }
        int level = n / tabSize;
        return new Indentation(level, pos);
    }

    public static void main(String[] args)
            throws Exception {
        new ToCodegen().execute(args);
    }

}
