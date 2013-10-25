package net.bodz.pkg.sisi.page;

import java.util.ArrayList;
import java.util.List;

import net.bodz.pkg.sis.ISisComponent;
import net.bodz.pkg.sis.ISisProject;
import net.bodz.swt.c.pageflow.PageMethod;

public abstract class ConfigPage
        extends SisiPage {

    static final String CPrefix = "C:";

    protected final ISisComponent owner;

    public ConfigPage(ISisComponent owner) {
        if (owner == null)
            throw new NullPointerException("owner");
        this.owner = owner;
    }

    public ISisProject getProject() {
        return owner.getProject();
    }

    @Override
    public List<PageMethod> getMethods() {
        List<PageMethod> methods = new ArrayList<PageMethod>();
        PageMethod next = getNextMethod();
        if (next != null)
            methods.add(next);

        List<PageMethod> userMethods = super.getMethods();
        if (userMethods != null)
            methods.addAll(userMethods);

        return methods;
    }

    protected PageMethod getNextMethod() {
        String ownerId = owner.getId();
        ISisComponent owner = getProject().getComponent(ownerId);
        ISisComponent following = getProject().getFollowing(owner);
        if (following == null)
            return new PageMethod(SummaryPage.class);
        return new PageMethod(CPrefix + following.getId(), "next");
    }

}
