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

public class Perl implements XtxLang {

    String  perlProgram = "perl";
    boolean warning;

    @Override
    public String getName() {
        return "perl";
    }

    @Override
    public boolean matches(String fileName) {
        if (fileName == null)
            throw new NullPointerException("fileName");
        return fileName.endsWith(".p") || fileName.endsWith(".pl");
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
        cmd.add(perlProgram);
        if (warning)
            cmd.add("-w");
        cmd.add(path);
        return Helper.interpretedCompiler(this, cmd.toArray(new String[0]));
    }

    class Emitter extends _CodeEmitter {

        public Emitter(CharOut out) {
            super(out);
        }

        @Override
        protected String emitCommentLine(String s) {
            return "# " + s + "\n";
        }

        @Override
        public void emitPrintExpr(String exprCode, boolean newLine) throws IOException {
            String code = "print " + exprCode + ";\n";
            emitCode(code);
            if (newLine)
                emitCode("print \"\n\"; \n");
        }

        @Override
        public void emitHereDoc(String doc) throws IOException {
            emitCode("print <<'EOT'; \n");
            emitCode(doc);
            emitCode("EOT\n");
        }

        @Override
        protected String quoteHeredoc(String s) {
            if (s.contains("EOT"))
                return null;
            return s;
        }

    }

}
