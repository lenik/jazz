package net.bodz.bas.site;

import net.bodz.bas.servlet.ctx.IAnchor;
import net.bodz.bas.servlet.ctx.WebAppAnchor;
import net.bodz.bas.site.config.EmbeddedResourceMappings;
import net.bodz.bas.site.config.MyResourceMappings;
import net.bodz.bas.site.config.UsrShareResourceMappings;

public interface IBasicSiteAnchors {

    IAnchor _webApp_ = new WebAppAnchor("/");

    /** @see UsrShareResourceMappings#backgroundsDir */
    IAnchor _backgrounds_ = _webApp_.join("backgrounds/");

    /** @see UsrShareResourceMappings#fontsDir */
    IAnchor _fonts_ = _webApp_.join("libfont/");

    /** @see UsrShareResourceMappings#iconsDir */
    IAnchor _icons_ = _webApp_.join("icons/");

    /** @see UsrShareResourceMappings#javascriptDir */
    IAnchor _js_ = _webApp_.join("libjs-alt/");
    IAnchor _jQueryUI_ = _webApp_.join("libjs-alt/jquery-ui/");
    IAnchor _jQueryUIThemes_ = _webApp_.join("libjs-alt/jquery-ui-themes/");

    IAnchor _npm_ = _webApp_.join("node_modules/");

    /** @see EmbeddedResourceMappings#webjarsDir */
    IAnchor _webjars_ = _webApp_.join("webjars/");

    /** @see MyResourceMappings#chunkDir */
    IAnchor _chunk_ = _webApp_.join("chunk/");

    /** @see MyResourceMappings#publicDir */
    IAnchor _public_ = _webApp_.join("public/");

}
