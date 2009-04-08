package net.bodz.dist.ins.builtins;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.bodz.dist.ins.IComponent;
import net.bodz.dist.ins.ISession;
import net.bodz.dist.ins.InstallException;
import net.bodz.dist.ins._Component;

public class Section extends _Component {

    String           name;
    String           caption;
    String           description;

    List<IComponent> children;

    public Section(boolean defaultSelected, String name, String caption,
            String description) {
        super(true, defaultSelected);
        this.name = name;
        this.caption = caption;
        this.description = description;
        children = new ArrayList<IComponent>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getCaption() {
        return caption;
    }

    @Override
    public String getDescription() {
        return description;
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
    public int getProgressSize(int job) {
        return children.size();
    }

    @Override
    public void dump(ISession session) throws InstallException {
        for (IComponent c : children)
            c.dump(session);
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
