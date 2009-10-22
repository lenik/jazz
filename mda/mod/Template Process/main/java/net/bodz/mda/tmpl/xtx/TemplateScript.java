package net.bodz.mda.tmpl.xtx;

import java.io.IOException;
import java.io.InputStream;

import net.bodz.bas.lang.err.CompileException;

public interface TemplateScript {

    XtxLang getLanguage();

    InputStream execute(String... args) throws CompileException, TemplateException, IOException;

}
