package net.bodz.bas.c.loader;

import java.util.LinkedHashSet;
import java.util.Set;

import net.bodz.bas.t.tree.AbstractListTreeNode;

public class ClassLoaderNode
        extends AbstractListTreeNode<ClassLoaderNode> {

    private static final long serialVersionUID = 1L;

    ClassLoader loader;
    Set<String> tags = new LinkedHashSet<String>();

    public ClassLoaderNode(ClassLoader loader) {
        this.loader = loader;
    }

    @Override
    public ClassLoaderNode addNewChild(String key) {
        return new ClassLoaderNode(null).attach(this, key);
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
