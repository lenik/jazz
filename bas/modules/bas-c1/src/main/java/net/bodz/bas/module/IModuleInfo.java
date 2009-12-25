package net.bodz.bas.module;

import java.util.Locale;

import net.bodz.bas.nls_2.NLS;

public interface IModuleInfo {

    String getPackageName();

    NLS getNLS();

    NLS getNLS(Locale locale);

}
