package net.bodz.mda.parsers.util;

import java.io.File;

import net.bodz.bas.cli.util.JavaLauncher;

/**
 * <pre>
 * call jflex --nobak rewrite.l
 */
public class JFlexCompiler extends JavaLauncher {

    File lex;
    File outdir;

    @Override
    protected String getMainClassName() {
        return "JFlex.Main";
    }

    public void compile() {
        // launch(args);
    }

}
