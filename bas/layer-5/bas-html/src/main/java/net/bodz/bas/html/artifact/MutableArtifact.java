package net.bodz.bas.html.artifact;

import java.io.Serializable;
import java.util.Collection;

import net.bodz.bas.html.ConflictedVersionException;
import net.bodz.bas.http.ctx.IAnchor;
import net.bodz.bas.meta.build.IVersion;
import net.bodz.bas.std.rfc.mime.ContentType;

public class MutableArtifact
        extends AbstractArtifact
        implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private IVersion version;
    private ArtifactType type;
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

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public IVersion getVersion() {
        return version;
    }

    @Override
    public void setVersion(IVersion version) {
        this.version = version;
    }

    public final void SetVersion(String versionStr) {
        IVersion version = IVersion.fn.parse(versionStr);
        setVersion(version);
    }

    @Override
    public ArtifactType getType() {
        return type;
    }

    @Override
    public void setType(ArtifactType type) {
        this.type = type;
    }

    @Override
    public ContentType getContentType() {
        return contentType;
    }

    @Override
    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    @Override
    public IAnchor getAnchor() {
        return null;
    }

    /** ⇱ Implementation Of {@link IArtifactDependent}. */
    /* _____________________________ */static section.iface __DEPENDENT__;

    @Override
    public Collection<? extends IArtifactDependency> getDependencies() {
        return dependent.getDependencies();
    }

    @Override
    public Collection<? extends IArtifactDependency> getDependencies(ArtifactType type) {
        return dependent.getDependencies(type);
    }

    @Override
    public final MutableArtifactDependency addDependency(String name, ArtifactType type) {
        return dependent.addDependency(name, type);
    }

    @Override
    public final MutableArtifactDependency addDependency(String name, ArtifactType type, String minVersionStr,
            String maxVersionStr)
            throws ConflictedVersionException {
        return dependent.addDependency(name, type, minVersionStr, maxVersionStr);
    }

    @Override
    public final MutableArtifactDependency addDependency(IArtifact artifact) {
        return dependent.addDependency(artifact);
    }

    @Override
    public final void addDependency(IArtifactDependency dependency)
            throws ConflictedVersionException {
        dependent.addDependency(dependency);
    }

    @Override
    public final void removeDependency(String name) {
        dependent.removeDependency(name);
    }

}
