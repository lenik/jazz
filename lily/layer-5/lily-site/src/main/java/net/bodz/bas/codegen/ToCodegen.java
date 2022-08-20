package net.bodz.bas.codegen;

import net.bodz.bas.c.string.StringEscape;
import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.io.Stdio;
import net.bodz.bas.io.impl.TreeOutImpl;
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
     * Count of space chars for a tab char. Default 8.
     *
     * @option -t =NCHAR
     */
    int tabSize = 4;

    /**
     * Width of a single indent level. Default 4.
     *
     * @option -n =NCHAR
     */
    int indentSize = 4;

    /**
     * Use ITreeOut instead of StringBuilder.
     *
     * @option -T
     */
    boolean indented;

    /**
     * Use printf instead of println.
     *
     * @option -f
     */
    boolean format = false;

    /**
     * Insert braces ({ ,}) to the enter/leave codes, to keep code indentation after IDE code
     * format.
     *
     * @option -b
     */
    boolean braces;

    @Override
    public void processFile(FileHandler handler)
            throws Exception {

        String ENTER = braces ? "out.enter(); {" : "out.enter();";
        String LEAVE = braces ? "out.leave(); }" : "out.leave();";

        // ITreeOut out = handler.openTreeOut();
        ITreeOut out = TreeOutImpl.from(Stdio.cout);
        int lastLevel = 0;
        for (String line : handler.read().lines(true)) {
            Indentation indentation = Indentation.compute(line, indentSize, tabSize);
            int level = indentation.level;

            if (indented) {
                line = line.substring(indentation.textOffset);
                if (!line.isEmpty()) {
                    for (int i = lastLevel; i < level; i++)
                        out.enterln(ENTER);
                    for (int i = lastLevel; i > level; i--)
                        out.leaveln(LEAVE);
                    lastLevel = level;
                }
            }

            String literal = escape(line);

            if (indented) {
                if (format) {
                    literal = literal.replace("%", "%%");
                    out.println("out.printf(\"" + literal + "\\n\");");
                } else {
                    if (literal.isEmpty())
                        out.println("out.println();");
                    else
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

    public static void main(String[] args)
            throws Exception {
        new ToCodegen().execute(args);
    }

}
