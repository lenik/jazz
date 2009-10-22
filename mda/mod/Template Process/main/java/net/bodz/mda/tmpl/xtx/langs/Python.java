package net.bodz.mda.tmpl.xtx.langs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.io.CharOut;
import net.bodz.mda.tmpl.xtx.CodeEmitter;
import net.bodz.mda.tmpl.xtx.Helper;
import net.bodz.mda.tmpl.xtx.TemplateScript;
import net.bodz.mda.tmpl.xtx.XtxLang;
import net.bodz.mda.tmpl.xtx._CodeEmitter;

public class Python implements XtxLang {

    String program = "python";

    @Override
    public String getName() {
        return "python";
    }

    @Override
    public boolean matches(String fileName) {
        if (fileName == null)
            throw new NullPointerException("fileName");
        return fileName.endsWith(".py") || fileName.endsWith(".pyc");
    }

    @Override
    public CodeEmitter newEmitter(CharOut out, String... options) {
        return new Emitter(out);
    }

    @Override
    public TemplateScript compile(String path) {
        if (path == null)
            throw new NullPointerException("path");
        List<String> cmd = new ArrayList<String>();
        cmd.add(program);
        cmd.add(path);
        return Helper.interpretedCompiler(this, cmd.toArray(new String[0]));
    }

    class Emitter extends _CodeEmitter {

        private String indent = "";

        public Emitter(CharOut out) {
            super(out);
            // not supported, currently.
            this.multiline = true;
        }

        @Override
        public void start() throws IOException {
            super.start();
            emitCode("from sys import stdout\n");
        }

        @Override
        protected String emitCommentLine(String s) {
            return "# " + s + "\n";
        }

        @Override
        public void emitCode(String code) throws IOException {
            int nl = code.lastIndexOf('\n');
            int end = code.length();
            // only matching lines with \n-lookback.
            // pattern: (\n.*?)$
            while (nl != -1) {
                String x = getIndent(code, nl + 1, end);
                if (x != null) {
                    indent = x;
                    break;
                }
                end = nl;
                nl = code.lastIndexOf('\n', nl - 1);
            }
            // for current python-xtx, because all statements must be on separate line,
            // so check also when no \n-lookback found.
            String x = getIndent(code, 0, end);
            if (x != null)
                indent = x;
            super.emitCode(code);
        }

        StringBuffer buf = new StringBuffer();

        public String getIndent(String s, int start, int end) {
            buf.setLength(0);
            for (int i = start; i < end; i++) {
                char c = s.charAt(i);
                if (Character.isWhitespace(c))
                    buf.append(c);
                else
                    return buf.toString();
            }
            return null;
        }

        @Override
        public void emitPrintExpr(String exprCode, boolean newLine) throws IOException {
            String code;
            if (newLine)
                code = indent + "print " + exprCode + "\n";
            else
                code = indent + "stdout.write(" + exprCode + ".__str__())\n";
            emitCode(code);
        }

        @Override
        public void emitHereDoc(String doc) throws IOException {
            emitCode("print '''\n");
            emitCode(doc);
            emitCode("'''\n");
        }

        @Override
        protected String quoteHeredoc(String s) {
            s = s.replace("'", "\\\'");
            return s;
        }

    }

}
