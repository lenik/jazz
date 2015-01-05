package net.bodz.bas.site;

import net.bodz.bas.http.ctx.IAnchor;
import net.bodz.bas.http.ctx.WebAppAnchor;

public interface IBasicSiteAnchors {

    IAnchor _webApp_ = new WebAppAnchor("/");

    /** @see BasicSiteServerConfig#backgroundsDir */
    IAnchor _backgrounds_ = _webApp_.join("backgrounds/");

    /** @see BasicSiteServerConfig#fontsDir */
    IAnchor _fonts_ = _webApp_.join("fonts/");

    /** @see BasicSiteServerConfig#iconsDir */
    IAnchor _icons_ = _webApp_.join("icons/");

    /** @see BasicSiteServerConfig#javascriptDir */
    IAnchor _js_ = _webApp_.join("js/");
    IAnchor _jQueryUI_ = _webApp_.join("js/jquery-ui/");
    IAnchor _jQueryUIThemes_ = _webApp_.join("js/jquery-ui-themes/");

    /** @see BasicSiteServerConfig#webjarsDir */
    IAnchor _webjars_ = _webApp_.join("webjars/");

}
