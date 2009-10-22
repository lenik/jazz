package net.bodz.mda.tmpl.xtx;

import java.io.IOException;
import java.io.InputStream;

import net.bodz.bas.lang.err.CompileException;
import net.bodz.bas.sys.Processes;
import net.bodz.bas.types.util.Arrays2;

public class Helper {

    static class InterpretedCompiler implements TemplateScript {

        private XtxLang  lang;
        private String[] shellCommand;

        public InterpretedCompiler(XtxLang lang, String[] shellCommand) {
            if (lang == null)
                throw new NullPointerException("lang");
            if (shellCommand == null)
                throw new NullPointerException("shellCommand");
            this.lang = lang;
            this.shellCommand = shellCommand;
        }

        public XtxLang getLanguage() {
            return lang;
        }

        @Override
        public InputStream execute(String... args) throws CompileException, TemplateException,
                IOException {
            if (args == null)
                args = new String[0];
            String[] cmdarray;
            if (args == null)
                cmdarray = args;
            else
                cmdarray = Arrays2.concat(shellCommand, args);
            Process process = Processes.shellExec(cmdarray);
            InputStream in = process.getInputStream();
            return in;
        }
    }

    public static InterpretedCompiler interpretedCompiler(XtxLang lang, String... shellCommand) {
        return new InterpretedCompiler(lang, shellCommand);
    }

}
