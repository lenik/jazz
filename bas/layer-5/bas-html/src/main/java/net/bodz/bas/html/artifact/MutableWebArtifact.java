package net.bodz.bas.html.artifact;

import net.bodz.bas.http.ctx.IBasePath;
import net.bodz.bas.std.rfc.mime.ContentType;
import net.bodz.bas.std.rfc.mime.ContentTypes;

public class MutableWebArtifact
        extends MutableArtifact
        implements IArtifact {

    private static final long serialVersionUID = 1L;

    private IBasePath basePath;
    private String path;

    public MutableWebArtifact() {
    }

    public MutableWebArtifact(String name, String versionStr, ContentType contentType, IBasePath basePath, String path) {
        super(name, versionStr, contentType);
        this.basePath = basePath;
        this.path = path;
    }

    public static MutableWebArtifact javascript(String name, String versionStr, IBasePath basePath, String path) {
        return new MutableWebArtifact(name, versionStr, ContentTypes.text_javascript, basePath, path);
    }

    public static MutableWebArtifact css(String name, String versionStr, IBasePath basePath, String path) {
        return new MutableWebArtifact(name, versionStr, ContentTypes.text_css, basePath, path);
    }

    @Override
    public String getHref(String requestURI) {
        return basePath.from(requestURI) + path;
    }

}
