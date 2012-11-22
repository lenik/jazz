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
public class TransientArtifactElement
        extends AbstractArtifactElement
        implements IEditableElement {

    private static final long serialVersionUID = 1L;

    transient ArtifactDoc artifactDoc;
    transient int userLevel;
    transient int modifiers;
    transient Set<String> tags = new HashSet<String>();

    public TransientArtifactElement() {
    }

    public TransientArtifactElement(ArtifactDoc artifactDoc) {
        this.artifactDoc = artifactDoc;
    }

    @Override
    public ArtifactDoc getArtifactDoc() {
        return artifactDoc;
    }

    public void setArtifactDoc(ArtifactDoc artifactDoc) {
        this.artifactDoc = artifactDoc;
    }

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
    public Set<String> getTags() {
        return tags;
    }

    @Override
    public void addTag(String tag) {
        if (tag == null)
            throw new NullPointerException("tag");
        tags.add(tag);
    }

    @Override
    public void removeTag(String tag) {
        if (tag == null)
            throw new NullPointerException("tag");
        tags.remove(tag);
    }

}
