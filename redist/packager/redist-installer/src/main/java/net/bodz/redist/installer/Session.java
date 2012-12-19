package net.bodz.redist.installer;

import java.beans.ExceptionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;

import net.bodz.bas.c.java.util.TextMap;
import net.bodz.bas.c.java.util.TreeTextMap;
import net.bodz.bas.c.java.util.regex.UnixStyleVarProcessor;
import net.bodz.bas.c.m2.MavenProjectOrigin;
import net.bodz.bas.c.system.SystemColos;
import net.bodz.bas.gui.dialog.IUserDialogs;
import net.bodz.bas.i18n.nls.II18nCapable;
import net.bodz.bas.log.Logger;
import net.bodz.bas.sio.IPrintOut;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.IFsDir;
import net.bodz.bas.vfs.impl.jdk.JdkFile;
import net.bodz.bas.xml.XMLs;
import net.bodz.redist.installer.util.Flags;

public class Session
        implements ISession, II18nCapable {

    static final String attachmentsPath = "a/";
    static final String registryPath = "registry.xml";

    private final IProject project;
    protected final IUserDialogs dialogs;
    protected Logger logger;

    private Components components;
    private Scheme scheme;

    private Flags flags;
    private TextMap<Object> variables;

    private List<IFsDir> resFolders;
    private List<ClassLoader> searchLoaders;

    // Execution Variables
    // private Stack<Component> stack;
    private TextMap<StatedAttachment> apool;

    public Session(IProject project, IUserDialogs userDialogs, Logger logger) {
        if (project == null)
            throw new NullPointerException("project");
        if (userDialogs == null)
            throw new NullPointerException("userDialogs");
        if (logger == null)
            throw new NullPointerException("logger");
        this.project = project;
        this.dialogs = userDialogs;
        this.logger = logger;

        logger.info(tr._("Collect components"));
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

        resFolders = new ArrayList<IFsDir>();

        // addResFolder(new JavaioFile(FilePath.canoniOf(".")/*, true*/));
        addResFolder(new JdkFile(SystemColos.workdir.get()/* , true */));

        MavenProjectOrigin installerPo = MavenProjectOrigin.fromClass(Installer.class);
        MavenProjectOrigin userPo = MavenProjectOrigin.fromClass(project.getClass());

        IFile projectResBase = new JdkFile(userPo.getResourceDir(project.getClass()));
        addResFolder(projectResBase);

        IFile installerResBase = new JdkFile(installerPo.getResourceDir(Installer.class));
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

    @Override
    public Scheme getScheme() {
        return scheme;
    }

    @Override
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

    @Override
    public void setScheme(Scheme scheme) {
        this.scheme = scheme;
    }

    @Override
    public IUserDialogs getUserDialogs() {
        return dialogs;
    }

    @Override
    public Logger getLogger() {
        return logger;
    }

    @Override
    public void setLogger(Logger logger) {
        this.logger = logger;
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
        UnixStyleVarProcessor expander = new UnixStyleVarProcessor(variables);
        String expansion = expander.process(s);
        return expansion;
    }

    @Override
    public List<IFsDir> getResFolders() {
        return resFolders;
    }

    @Override
    public boolean addResFolder(IFsDir resFolder) {
        if (resFolders.contains(resFolder))
            return false;
        resFolders.add(resFolder);
        return true;
    }

    @Override
    public void addResFolder(int index, IFsDir resFolder) {
        resFolders.add(index, resFolder);
    }

    @Override
    public IFile newResource(String resPath)
            throws IOException {
        assert !resFolders.isEmpty();
        IFsDir output = resFolders.get(0);
        return (IFile) output.getChild(resPath);
    }

    @Override
    public IFile findResource(String path, boolean autoCreate)
            throws IOException {
        IFile firstUnknownLink = null;
        for (IFsDir resFolder : resFolders) {
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
                throw new NoSuchElementException(tr._("Resource isn\'t existed: ") + path);
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
            logger.warn(e);
        }
    }

    @Override
    public void closeAttachments() {
        for (Entry<String, StatedAttachment> entry : apool.entrySet()) {
            StatedAttachment a = entry.getValue();
            logger.info(tr._("Close attachment "), a);
            try {
                a.close();
            } catch (IOException e) {
                logger.warn(tr._("Can\'t close attachment: "), a);
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
                logger.info(tr._("Loading registry from "), registryLink);
                InputStream in = registryLink.getInputSource().newInputStream();
                Object obj = XMLs.decode(in, new ExWarn());
                in.close();
                if (!(obj instanceof Map<?, ?>))
                    throw new SessionException(tr._("bad decoded registry: ") + obj);
                @SuppressWarnings("unchecked") Map<String, Object> registry = (Map<String, Object>) obj;
                logger.info(tr._("Registry data to load: "), registry);
                components.importRegistry(registry);
                logger.info(tr._("Registry loaded"));
            }
        } catch (IOException e) {
            throw new SessionException(tr._("Failed to load registry"), e);
        }
    }

    @Override
    public void saveRegistry()
            throws SessionException {
        try {
            // save registry after packed
            TextMap<Object> registry = components.exportRegistry();
            if (registry != null) {
                logger.info(tr._("Registry data to save: "), registry);
                IFile registryLink = newResource(registryPath);
                logger.info(tr._("Saving registry to "), registryLink);
                OutputStream out = registryLink.getOutputTarget(false).newOutputStream();
                XMLs.encode(registry, out, new ExWarn());
                out.close();
                logger.info(tr._("Registry saved"));
            }
        } catch (IOException e) {
            throw new SessionException(tr._("Failed to save registry"), e);
        }
    }

    @Override
    public void dump(IPrintOut out) {
        out.println(tr._("Session "), this, ": ");
        out.println(tr._("  Project = "), project);
        out.println(tr._("  Scheme = "), scheme);
        for (IFsDir resFolder : resFolders)
            out.println(tr._("  Resource Folder = "), resFolder);
        for (Map.Entry<String, Object> e : variables.entrySet())
            out.printf(tr._("  Var %s = %s\n"), e.getKey(), e.getValue());
        int i = 0;
        i = 0;
        for (ClassLoader searchLoader : searchLoaders)
            out.printf(tr._("  Search Loader[%d] = %s\n"), i++, searchLoader);
        if (apool != null) {
            Set<Entry<String, StatedAttachment>> entrySet = apool.entrySet();
            for (Entry<String, StatedAttachment> entry : entrySet) {
                String name = entry.getKey();
                StatedAttachment a = entry.getValue();
                out.printf(tr._("  Pool Attachment[%s] = %s\n"), name, a);
            }
        }
        out.println(tr._("  Component Tree: "));
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
