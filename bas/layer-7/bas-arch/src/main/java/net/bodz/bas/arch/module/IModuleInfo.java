package net.bodz.bas.arch.module;

import java.util.Locale;

public interface IModuleInfo {

    String getPackageName();

    NLS getNLS();

    NLS getNLS(Locale locale);

}
