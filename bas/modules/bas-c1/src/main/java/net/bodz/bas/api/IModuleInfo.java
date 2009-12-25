package net.bodz.bas.api;

import java.util.Locale;

import net.bodz.bas.api.nls.NLS;

public interface IModuleInfo {

    String getPackageName();

    NLS getNLS();

    NLS getNLS(Locale locale);

}
