package net.bodz.mda.rtl;

import net.bodz.bas.cli.util.JavaLauncher;

/**
 * <pre>
 *     set _=-v
 *     set _=%_% -Jclass=RewriteParserImpl
 *     set _=%_% -Jpackage=net.sf.freejava.script.rewrite
 *     set _=%_% -Jextends=RewriteParser
 *     rem set _=%_% -Jimplements
 *     set _=%_% -Jsemantic=Object
 *     set _=%_% -Jthrows=IOException,ParseException
 *     set _=%_% -Jnorun
 *     set _=%_% -Jnoconstruct
 * call yaccj %_% rewrite.y
 */
public class ByaccjCompiler extends JavaLauncher {

    @Override
    protected String getMainClassName() {
        return null;
    }

}
