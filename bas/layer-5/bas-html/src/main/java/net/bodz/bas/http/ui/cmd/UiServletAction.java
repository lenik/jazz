package net.bodz.bas.http.ui.cmd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.bodz.bas.c.string.StringArray;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.ui.dom1.UiObject;
import net.bodz.bas.ui.model.action.IActionContext;
import net.bodz.bas.ui.model.action.Location;
import net.bodz.bas.ui.model.action.RollbackException;
import net.bodz.mda.xjdoc.XjdocLoaderException;
import net.bodz.mda.xjdoc.model.IElementDoc;

public abstract class UiServletAction
        extends UiObject
        implements IServletAction {

    private List<Class<?>> locations;
    private Map<String, String> scripts;
    private Map<String, String> attributes;

    public UiServletAction() {
        locations = new ArrayList<Class<?>>();
        scripts = new HashMap<>();
        attributes = new HashMap<>();

        Location aLocation = getClass().getAnnotation(Location.class);
        if (aLocation != null)
            for (Class<?> c : aLocation.value())
                locations.add(c);

        for (Entry<String, ?> entry : getXjdoc().getTagMap().entrySet()) {
            String key = entry.getKey();
            if (key.startsWith("cmd.")) {
                key = key.substring(4);
                List<?> array = (List<?>) entry.getValue();
                scripts.put(key, StringArray.join("; ", array));
            }
            if (key.startsWith("attr.")) {
                key = key.substring(5);
                Object array = entry.getValue();
                attributes.put(key, StringArray.join(" ", array));
            }
        }
    }

    @Override
    protected IElementDoc loadXjdoc()
            throws XjdocLoaderException, ParseException, IOException {
        return super.loadXjdoc();
    }

    @Override
    public List<? extends Class<?>> getLocations() {
        return locations;
    }

    @Override
    public Class<?> getTargetClass() {
        return null;
    }

    @Override
    public boolean canRun(Object obj) {
        return true;
    }

    @Override
    public final Object run(Object obj, IActionContext context)
            throws Exception {
        IServletActionContext servletActionContext = (IServletActionContext) context;
        return run(obj, servletActionContext);
    }

    @Override
    public boolean canRollback(Object obj) {
        return false;
    }

    @Override
    public final void rollback(Object obj, IActionContext context)
            throws RollbackException {
        IServletActionContext servletActionContext = (IServletActionContext) context;
        rollback(obj, servletActionContext);
    }

    @Override
    public void rollback(Object obj, IServletActionContext context)
            throws RollbackException {
    }

    @Override
    public boolean isAsync() {
        // getClass().isAnnotationPresent(Async)
        return getXjdoc().getTag("cmd.async") != null;
    }

    @Override
    public boolean isScriptOnly() {
        return false;
    }

    @Override
    public Set<String> getScriptIds() {
        return scripts.keySet();
    }

    @Override
    public String getScript(String scriptId, Object obj) {
        return scripts.get(scriptId);
    }

    static final String notImplementedScript = "alert('not implmented')";

    protected void addScript(String scriptId) {
        scripts.put(scriptId, notImplementedScript);
    }

    protected void addScript(String scriptId, String script) {
        scripts.put(scriptId, script);
    }

    @Override
    public Map<String, String> getAttributeMap() {
        return attributes;
    }

}
