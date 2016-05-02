package net.bodz.bas.site.artifact;

import net.bodz.bas.html.artifact.ArtifactBuilder;
import net.bodz.bas.html.artifact.IArtifact;
import net.bodz.bas.html.artifact.StaticArtifactProvider;
import net.bodz.bas.site.IBasicSiteAnchors;

public class LibFontsArtifacts
        extends StaticArtifactProvider
        implements IBasicSiteAnchors {

    static final ArtifactBuilder fn = new ArtifactBuilder();

    public LibFontsArtifacts() {
        super(fn.getContainer());
    }

    public static IArtifact fontAwesome = fn.css("font-awesome", "4.2.0", //
            _fonts_.join("font-awesome/css/font-awesome.min.css"));

}
