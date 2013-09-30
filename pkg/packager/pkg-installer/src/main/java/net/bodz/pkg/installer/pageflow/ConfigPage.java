package net.bodz.pkg.installer.pageflow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.bodz.pkg.installer.IComponent;
import net.bodz.pkg.installer.ISession;
import net.bodz.pkg.installer.IScheme;
import net.bodz.swt.c.pageflow.AbstractPage;
import net.bodz.swt.c.pageflow.PageMethod;

public abstract class ConfigPage
        extends AbstractPage {

    static final String CPrefix = "C:";

    protected final IComponent owner;
    protected final ISession session;

    public ConfigPage(IComponent owner, ISession session) {
        if (owner == null)
            throw new NullPointerException("owner");
        if (session == null)
            throw new NullPointerException("session");
        this.owner = owner;
        this.session = session;
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
        String nextId = getNextConfigId(session, ownerId);
        if (nextId == null)
            return new PageMethod(SummaryPage.class);
        return new PageMethod(CPrefix + nextId, "next");
    }

    public static String getNextConfigId(ISession session, String startId) {
        Map<String, String> nextMap = session.getComponents().getNextMap();
        IScheme scheme = session.getScheme();
        String nextId = startId;
        while (true) {
            nextId = nextMap.get(nextId);
            if (nextId == null)
                break;
            IComponent c = session.getComponent(nextId);
            if (c.hasConfig())
                if (scheme == null || scheme.showConfig(c)) {
                    return nextId;
                }
        }
        return null;
    }

}
