package net.bodz.art.installer;

import static net.bodz.art.installer.nls.PackNLS.PackNLS;

import java.beans.ExceptionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;

import net.bodz.art.installer.util.Flags;
import net.bodz.bas.c.java.util.TextMap;
import net.bodz.bas.c.java.util.TreeTextMap;
import net.bodz.bas.log.Logger;
import net.bodz.bas.sio.IPrintOut;
import net.bodz.bas.ui.UserInterface;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.IFsTree;
import net.bodz.bas.vfs.SystemColos;
import net.bodz.bas.vfs.impl.javaio.JavaioFile;
import net.bodz.bas.xml.XMLs;

public class Session
        implements ISession {

    static final String attachmentsPath = "a/";
    static final String registryPath = "registry.xml";

    private final IProject project;
    protected final UserInterface UI;
    protected Logger L;

    private Components components;
    private Scheme scheme;

    private Flags flags;
    private TextMap<Object> variables;

    private List<IFsTree> resFolders;
    private List<ClassLoader> searchLoaders;

    // Execution Variables
    // private Stack<Component> stack;
    private TextMap<StatedAttachment> apool;

    public Session(IProject project, UserInterface userInterface, Logger logger) {
        if (project == null)
            throw new NullPointerException("project");
        if (userInterface == null)
            throw new NullPointerException("userInterface");
        if (logger == null)
            throw new NullPointerException("logger");
        this.project = project;
        this.UI = userInterface;
        this.L = logger;

        logger.info(PackNLS.getString("Session.collectComponents"));
        components = Components.collect(project);
        scheme = new Schemes.Default();
        flags = new Flags();

        Map<String, Variable> vardef = project.getVariables();
        assert vardef != null;
        variables = new TreeTextMap<Object>();
        for (Map.Entry<String, Variable> e : vardef.entrySet()) {
            String name = e.getKey();
            Variable var = e.getValue();
            assert var != null;
            Object defaultValue = var.getDefaultValue();
            variables.put(name, defaultValue);
        }

        resFolders = new ArrayList<IFsTree>();
        addResFolder(new JavaioFile(Files.canoniOf("."), true));
        addResFolder(new JavaioFile(SystemColos.cwd.get()/* , true */));
        Class<?> projectClass = project.getClass();
        IFsTree projectResBase = SJProject.getResBase(projectClass);
        addResFolder(projectResBase);
        IFsTree installerResBase = SJProject.getResBase(Installer.class);
        addResFolder(installerResBase);

        searchLoaders = new ArrayList<ClassLoader>();
        searchLoaders.add(ClassLoader.getSystemClassLoader());

        apool = new TreeTextMap<StatedAttachment>();
    }

    @Override
    public IProject getProject() {
        return project;
    }

    @Override
    public Components getComponents() {
        return components;
    }

    @Override
    public IComponent getComponent(String id) {
        return components.get(id);
    }

    public Scheme getScheme() {
        return scheme;
    }

    public void setScheme(String schemeName) {
        if (schemeName == null)
            throw new NullPointerException("schemeName");
        Scheme[] schemes = project.getSchemes();
        assert schemes != null;
        for (Scheme scheme : schemes) {
            String name = scheme.getName();
            if (schemeName.equalsIgnoreCase(name)) {
                setScheme(scheme);
                return;
            }
        }
        throw new NoSuchElementException(schemeName);
    }

    public void setScheme(Scheme scheme) {
        this.scheme = scheme;
    }

    @Override
    public UserInterface getUserInterface() {
        return UI;
    }

    @Override
    public Logger getLogger() {
        return L;
    }

    @Override
    public void setLogger(Logger logger) {
        this.L = logger;
    }

    @Override
    public Flags getFlags() {
        return flags;
    }

    @Override
    public Object get(String name) {
        if (variables == null)
            return variables.get(name);
        return variables.get(name);
    }

    @Override
    public File getFile(String name) {
        Object value = get(name);
        if (value == null)
            return null;
        if (value instanceof File)
            return (File) value;
        return new File(value.toString());
    }

    @Override
    public void set(String name, Object value) {
        if (variables == null)
            throw new IllegalStateException("Set to the session env using setEnv.");
        variables.put(name, value);
    }

    @Override
    public String expand(String s) {
        VariableExpand expander = new VariableExpand(variables);
        String expansion = expander.process(s);
        return expansion;
    }

    @Override
    public List<IFsTree> getResFolders() {
        return resFolders;
    }

    @Override
    public boolean addResFolder(IFsTree resFolder) {
        if (resFolders.contains(resFolder))
            return false;
        resFolders.add(resFolder);
        return true;
    }

    @Override
    public void addResFolder(int index, IFsTree resFolder) {
        resFolders.add(index, resFolder);
    }

    @Override
    public IFile newResource(String resPath)
            throws IOException {
        assert !resFolders.isEmpty();
        IFsTree output = resFolders.get(0);
        return (IFile) output.getChild(resPath);
    }

    @Override
    public IFile findResource(String path, boolean autoCreate)
            throws IOException {
        IFile firstUnknownLink = null;
        for (IFsTree resFolder : resFolders) {
            // if path.isAsbolute...
            IFile link = (IFile) resFolder.getChild(path);
            Boolean exists = link.exists();
            if (exists == Boolean.TRUE)
                return link;
            if (exists == null && firstUnknownLink == null)
                firstUnknownLink = link;
        }
        if (autoCreate)
            return newResource(path);
        else {
            if (firstUnknownLink == null)
                throw new NoSuchElementException(PackNLS.getString("Session.resIsntExisted") + path);
            return firstUnknownLink;
        }
    }

    protected String getAttachmentPath(String name) {
        return attachmentsPath + name;
    }

    @Override
    public Attachment getAttachment(String name, boolean autoCreate)
            throws IOException {
        String path = getAttachmentPath(name);
        StatedAttachment a = apool.get(path);
        if (a == null) {
            IFile res = findResource(path, autoCreate);
            a = new StatedAttachment(res, "utf-8");
            apool.put(path, a);
        }
        return a;
    }

    class ExWarn
            implements ExceptionListener {
        @Override
        public void exceptionThrown(Exception e) {
            L.warn(e);
        }
    }

    @Override
    public void closeAttachments() {
        for (Entry<String, StatedAttachment> entry : apool.entrySet()) {
            StatedAttachment a = entry.getValue();
            L.info(PackNLS.getString("Session.closeAttachment"), a);
            try {
                a.close();
            } catch (IOException e) {
                L.warn(PackNLS.getString("Session.cantCloseAttachment"), a);
            }
        }
        apool.clear();
    }

    @Override
    public void loadRegistry()
            throws SessionException {
        try {
            // load registry before install/uinstall
            IFile registryLink = findResource(registryPath, false);
            if (registryLink.exists() == Boolean.TRUE) {
                L.info(PackNLS.getString("Session.loadRegistry"), registryLink);
                InputStream in = registryLink.getInputSource().newInputStream();
                Object obj = XMLs.decode(in, new ExWarn());
                in.close();
                if (!(obj instanceof Map<?, ?>))
                    throw new SessionException(PackNLS.getString("Session.registryIsntMap") + obj);
                @SuppressWarnings("unchecked")
                Map<String, Object> registry = (Map<String, Object>) obj;
                L.info(PackNLS.getString("Session.registryToLoad"), registry);
                components.importRegistry(registry);
                L.info(PackNLS.getString("Session.registryLoaded"));
            }
        } catch (IOException e) {
            throw new SessionException(PackNLS.getString("Session.failedToLoadRegistry"), e);
        }
    }

    @Override
    public void saveRegistry()
            throws SessionException {
        try {
            // save registry after packed
            TextMap<Object> registry = components.exportRegistry();
            if (registry != null) {
                L.info(PackNLS.getString("Session.registryToSave"), registry);
                IFile registryLink = newResource(registryPath);
                L.info(PackNLS.getString("Session.saveRegistryTo"), registryLink);
                OutputStream out = registryLink.getOutputTarget(false).newOutputStream();
                XMLs.encode(registry, out, new ExWarn());
                out.close();
                L.info(PackNLS.getString("Session.registrySaved"));
            }
        } catch (IOException e) {
            throw new SessionException(PackNLS.getString("Session.failedToSaveRegistry"), e);
        }
    }

    @Override
    public void dump(IPrintOut out) {
        out.println(PackNLS.getString("Session.session_"), this, ": ");
        out.println(PackNLS.getString("Session._project"), project);
        out.println(PackNLS.getString("Session._scheme"), scheme);
        for (IFsTree resFolder : resFolders)
            out.println(PackNLS.getString("Session._resFolder"), resFolder);
        for (Map.Entry<String, Object> e : variables.entrySet())
            out.printf(PackNLS.getString("Session._var_ss"), e.getKey(), e.getValue());
        int i = 0;
        i = 0;
        for (ClassLoader searchLoader : searchLoaders)
            out.printf(PackNLS.getString("Session._searchLoader_ds"), i++, searchLoader);
        if (apool != null) {
            Set<Entry<String, StatedAttachment>> entrySet = apool.entrySet();
            for (Entry<String, StatedAttachment> entry : entrySet) {
                String name = entry.getKey();
                StatedAttachment a = entry.getValue();
                out.printf(PackNLS.getString("Session._poolAttachment_ss"), name, a);
            }
        }
        out.println(PackNLS.getString("Session._componentTree"));
        dump(out, project, "    ");
    }

    void dump(IPrintOut out, IComponent c, String prefix) {
        boolean included = getScheme().isIncluded(c);
        String flags = "";
        if (included)
            flags += "x";
        out.printf(prefix + "(%s) %s: %s\n", //
                flags, c.getId(), c.getClass().getSimpleName());
        List<? extends IComponent> children = c.getChildren();
        if (children != null) {
            for (IComponent child : children)
                dump(out, child, prefix + "  ");
        }
    }

}
