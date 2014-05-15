package net.bodz.bas.html.artifact;

import java.io.Serializable;
import java.util.Collection;

import net.bodz.bas.html.ConflictedVersionException;
import net.bodz.bas.meta.build.IVersion;
import net.bodz.bas.std.rfc.mime.ContentType;

public class MutableArtifact
        extends AbstractArtifact
        implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private IVersion version;
    private ContentType contentType;
    private MutableArtifactDependent dependent = new MutableArtifactDependent();

    public MutableArtifact() {
    }

    public MutableArtifact(String name, String versionStr, ContentType contentType) {
        this.name = name;
        if (versionStr != null)
            this.version = IVersion.fn.parse(versionStr);
        this.contentType = contentType;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public IVersion getVersion() {
        return version;
    }

    public void setVersion(IVersion version) {
        this.version = version;
    }

    public final void SetVersion(String versionStr) {
        IVersion version = IVersion.fn.parse(versionStr);
        setVersion(version);
    }

    @Override
    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    @Override
    public String getHref(String requestURI) {
        return null;
    }

    /** ⇱ Implementation Of {@link IArtifactDependent}. */
    /* _____________________________ */static section.iface __DEPENDENT__;

    @Override
    public Collection<? extends IArtifactDependency> getDependencies() {
        return dependent.getDependencies();
    }

    @Override
    public Collection<? extends IArtifactDependency> getDependencies(String type) {
        return dependent.getDependencies(type);
    }

    public final MutableArtifactDependency addDependency(String name, String type) {
        return dependent.addDependency(name, type);
    }

    public final MutableArtifactDependency addDependency(String name, String type, String minVersionStr,
            String maxVersionStr)
            throws ConflictedVersionException {
        return dependent.addDependency(name, type, minVersionStr, maxVersionStr);
    }

    public void addDependency(IArtifactDependency dependency)
            throws ConflictedVersionException {
        dependent.addDependency(dependency);
    }

    public void removeDependency(String name) {
        dependent.removeDependency(name);
    }

}
