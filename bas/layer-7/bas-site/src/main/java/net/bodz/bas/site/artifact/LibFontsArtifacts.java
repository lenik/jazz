package net.bodz.bas.site.artifact;

import net.bodz.bas.html.artifact.IArtifact;
import net.bodz.bas.html.artifact.MutableArtifactManager;
import net.bodz.bas.site.IBasicSiteAnchors;

public class LibFontsArtifacts
        extends MutableArtifactManager
        implements IBasicSiteAnchors {

    IArtifact fontAwesome = css("font-awesome", "4.2.0", //
            _fonts_.join("font-awesome/css/font-awesome.min.css"));

}
