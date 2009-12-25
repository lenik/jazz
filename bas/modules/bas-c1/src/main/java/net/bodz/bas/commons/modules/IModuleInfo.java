package net.bodz.bas.commons.modules;

import java.util.Locale;

import net.bodz.bas.commons.nls_2.NLS;

public interface IModuleInfo {

    String getPackageName();

    NLS getNLS();

    NLS getNLS(Locale locale);

}
