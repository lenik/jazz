package net.bodz.mda.tmpl.xtx.langs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.io.CharOut;
import net.bodz.bas.lang.TRunnable;
import net.bodz.mda.tmpl.xtx.CodeEmitter;
import net.bodz.mda.tmpl.xtx.Helper;
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
    public TRunnable<String[], Exception> compile(String path) {
        if (path == null)
            throw new NullPointerException("path");
        List<String> cmd = new ArrayList<String>();
        cmd.add(program);
        cmd.add(path);
        return Helper.interpretedCompiler(true, cmd.toArray(new String[0]));
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
            String code = "print " + exprCode;
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
