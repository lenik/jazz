package net.bodz.bas.api;

import java.util.Locale;

import net.bodz.bas.i18n.NLS;

public interface IModuleInfo {

    String getPackageName();

    NLS getNLS();

    NLS getNLS( Locale locale );

}
