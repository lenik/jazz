package net.bodz.bas.html.artifact;

import net.bodz.bas.http.ctx.IAnchor;
import net.bodz.bas.std.rfc.mime.ContentType;
import net.bodz.bas.std.rfc.mime.ContentTypes;

public class MutableWebArtifact
        extends MutableArtifact
        implements IArtifact {

    private static final long serialVersionUID = 1L;

    private IAnchor anchor;

    public MutableWebArtifact() {
    }

    public MutableWebArtifact(String name, String versionStr, ContentType contentType, IAnchor anchor) {
        super(name, versionStr, contentType);
        this.anchor = anchor;
    }

    @Override
    public IAnchor getAnchor() {
        return anchor;
    }

    public static MutableWebArtifact javascript(String name, String versionStr, IAnchor anchor) {
        MutableWebArtifact artifact = new MutableWebArtifact(name, versionStr, ContentTypes.text_javascript, anchor);
        artifact.setType(ArtifactType.SCRIPT);
        return artifact;
    }

    public static MutableWebArtifact css(String name, String versionStr, IAnchor anchor) {
        MutableWebArtifact artifact = new MutableWebArtifact(name, versionStr, ContentTypes.text_css, anchor);
        artifact.setType(ArtifactType.STYLESHEET);
        return artifact;
    }

}
