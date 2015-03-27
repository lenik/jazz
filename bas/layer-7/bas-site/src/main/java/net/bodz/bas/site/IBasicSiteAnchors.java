package net.bodz.bas.site;

import net.bodz.bas.http.ctx.IAnchor;
import net.bodz.bas.http.ctx.WebAppAnchor;
import net.bodz.bas.site.config.LocalResourceMappings;
import net.bodz.bas.site.config.MyResourceMappings;

public interface IBasicSiteAnchors {

    IAnchor _webApp_ = new WebAppAnchor("/");

    /** @see LocalResourceMappings#backgroundsDir */
    IAnchor _backgrounds_ = _webApp_.join("backgrounds/");

    /** @see LocalResourceMappings#fontsDir */
    IAnchor _fonts_ = _webApp_.join("fonts/");

    /** @see LocalResourceMappings#iconsDir */
    IAnchor _icons_ = _webApp_.join("icons/");

    /** @see LocalResourceMappings#javascriptDir */
    IAnchor _js_ = _webApp_.join("js/");
    IAnchor _jQueryUI_ = _webApp_.join("js/jquery-ui/");
    IAnchor _jQueryUIThemes_ = _webApp_.join("js/jquery-ui-themes/");

    /** @see LocalResourceMappings#webjarsDir */
    IAnchor _webjars_ = _webApp_.join("webjars/");

    /** @see MyResourceMappings#chunkDir */
    IAnchor _chunk_ = _webApp_.join("chunk/");

}
