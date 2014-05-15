package net.bodz.bas.site;

import net.bodz.bas.html.artifact.MutableArtifactManager;
import net.bodz.bas.html.artifact.MutableWebArtifact;

public class BasicSiteArtifacts
        extends MutableArtifactManager
        implements IBasicSiteAnchors {

    public static final String FONT_AWESOME = "font-awesome";
    public static final String JQUERY_MIN = "jquery-min";

    MutableWebArtifact fontAwesome = MutableWebArtifact.css(FONT_AWESOME, "3.2.1", //
            _webjars_.join("font-awesome/3.2.1/css/font-awesome.css"));
    MutableWebArtifact jQueryMin = MutableWebArtifact.javascript(JQUERY_MIN, null, //
            _js_.join("jquery/jquery.min.js"));

    public BasicSiteArtifacts() {
        addArtifact(fontAwesome);
        addArtifact(jQueryMin);
    }

}
