package net.bodz.dist.ins.builtins;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.bodz.dist.ins.IComponent;
import net.bodz.dist.ins.ISession;
import net.bodz.dist.ins.InstallException;
import net.bodz.dist.ins._Component;

import org.eclipse.swt.graphics.ImageData;

public class Section extends _Component {

    protected String         name;
    protected String         text;
    protected String         doc;
    protected ImageData      image;

    private List<IComponent> children;

    public Section(boolean defaultSelected, String name, String text, String doc) {
        super(true, defaultSelected);
        this.name = name;
        this.text = text;
        this.doc = doc;
        children = new ArrayList<IComponent>();
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public ImageData getImage() {
        return image;
    }

    public void setImage(ImageData image) {
        this.image = image;
    }

    @Override
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    @Override
    public Collection<IComponent> getChildren() {
        return children;
    }

    public void add(IComponent child) {
        children.add(child);
    }

    public void remove(IComponent child) {
        children.remove(child);
    }

    @Override
    public void pack(ISession session) throws InstallException {
        for (IComponent c : children)
            c.pack(session);
    }

    @Override
    public boolean install(ISession session) throws InstallException {
        boolean allSucceeded = true;
        for (IComponent c : children)
            allSucceeded &= c.install(session); // managed...?
        return allSucceeded;
    }

    @Override
    public void uninstall(ISession session) throws InstallException {
        for (IComponent c : children)
            c.uninstall(session);
    }

}
