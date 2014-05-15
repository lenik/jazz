package net.bodz.bas.site;

import net.bodz.bas.http.ctx.IAnchor;
import net.bodz.bas.http.ctx.WebAppAnchor;

public interface IBasicSiteAnchors {

    IAnchor _webApp_ = new WebAppAnchor("/");
    IAnchor _fonts_ = _webApp_.join("fonts/");
    IAnchor _js_ = _webApp_.join("js/");
    IAnchor _webjars_ = _webApp_.join("webjars/");

}
