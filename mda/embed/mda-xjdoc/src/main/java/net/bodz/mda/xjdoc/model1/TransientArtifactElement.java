package net.bodz.mda.xjdoc.model1;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import net.bodz.bas.i18n.dom.DomainString;
import net.bodz.bas.i18n.dom1.IEditableElement;

/**
 * Though {@link TransientArtifactElement} is-a {@link Serializable}, the serialization on this
 * object (of any subclass) won't work because of the underlying transient fields.
 */
public abstract class TransientArtifactElement
        extends AbstractArtifactElement
        implements IEditableElement {

    private static final long serialVersionUID = 1L;

    transient ArtifactDoc artifactDoc;
    transient boolean artifactDocLoaded;

    transient int userLevel;
    transient int modifiers;
    transient Set<String> tagNames = new HashSet<String>();

    public TransientArtifactElement() {
    }

    public TransientArtifactElement(ArtifactDoc artifactDoc) {
        this.artifactDoc = artifactDoc;
    }

    @Override
    public synchronized ArtifactDoc getArtifactDoc() {
        if (artifactDoc == null) {
            if (!artifactDocLoaded) {
                artifactDoc = loadArtifactDoc();
                artifactDocLoaded = true;
            }
        }
        return artifactDoc;
    }

    public void setArtifactDoc(ArtifactDoc artifactDoc) {
        this.artifactDoc = artifactDoc;
        artifactDocLoaded = true;
    }

    protected abstract ArtifactDoc loadArtifactDoc();

    @Override
    public void setDisplayName(DomainString displayName) {
        this.displayName = displayName;
    }

    @Override
    public void setDescription(DomainString description) {
        this.description = description;
    }

    @Override
    public void setHelpDoc(DomainString helpDoc) {
        this.helpDoc = helpDoc;
    }

    @Override
    public int getUserLevel() {
        return userLevel;
    }

    @Override
    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
    }

    @Override
    public int getModifiers() {
        return modifiers;
    }

    @Override
    public void setModifiers(int modifiers) {
        this.modifiers = modifiers;
    }

    @Override
    public Set<String> getTagNames() {
        return tagNames;
    }

    @Override
    public void addTagName(String tagName) {
        if (tagName == null)
            throw new NullPointerException("tagName");
        tagNames.add(tagName);
    }

    @Override
    public void removeTagName(String tagName) {
        if (tagName == null)
            throw new NullPointerException("tagName");
        tagNames.remove(tagName);
    }

}
