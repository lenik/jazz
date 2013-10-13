package net.bodz.bas.c.loader;

import java.util.LinkedHashSet;
import java.util.Set;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.t.tree.AbstractListTreeNode;

public class ClassLoaderNode
        extends AbstractListTreeNode<ClassLoaderNode> {

    private static final long serialVersionUID = 1L;

    ClassLoader loader;
    Set<String> tags = new LinkedHashSet<>();

    public ClassLoaderNode(ClassLoaderNode parent, ClassLoader loader) {
        super(parent);
        this.loader = loader;
    }

    @Override
    protected ClassLoaderNode newChild()
            throws CreateException {
        return new ClassLoaderNode(this, null);
    }

    public ClassLoader getClassLoader() {
        return loader;
    }

    public void setLoader(ClassLoader loader) {
        this.loader = loader;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void addTag(String tag) {
        if (tag == null)
            throw new NullPointerException("tag");
        tags.add(tag);
    }

    public void removeTag(String tag) {
        if (tag == null)
            throw new NullPointerException("tag");
        tags.remove(tag);
    }

}
