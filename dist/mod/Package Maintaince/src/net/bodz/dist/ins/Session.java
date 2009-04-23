package net.bodz.dist.ins;

import java.beans.ExceptionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Map.Entry;

import net.bodz.bas.io.CWD;
import net.bodz.bas.io.CharOut;
import net.bodz.bas.io.FileResFolder;
import net.bodz.bas.io.ResFolder;
import net.bodz.bas.io.ResLink;
import net.bodz.bas.text.interp.VariableExpand;
import net.bodz.bas.types.TextMap;
import net.bodz.bas.types.TreeTextMap;
import net.bodz.bas.ui.UserInterface;
import net.bodz.bas.util.LogTerm;
import net.bodz.bas.xml.XMLs;
import net.bodz.dist.ins.util.Flags;

public class Session implements ISession {

    static final String               attachmentsPath = ".attachments";
    static final String               registryPath    = "registry.xml";

    private final Project             project;
    protected final UserInterface     UI;
    protected LogTerm                 L;

    private Components                components;
    private Scheme                    scheme;

    private Flags                     flags;
    private TextMap<Object>           variables;

    private List<ResFolder>           resFolders;
    private List<ClassLoader>         searchLoaders;

    // Execution Variables
    // private Stack<Component> stack;
    private TextMap<StatedAttachment> apool;

    public Session(Project project, UserInterface userInterface, LogTerm logger) {
        if (project == null)
            throw new NullPointerException("project"); //$NON-NLS-1$
        if (userInterface == null)
            throw new NullPointerException("userInterface");
        if (logger == null)
            throw new NullPointerException("logger"); //$NON-NLS-1$
        this.project = project;
        this.UI = userInterface;
        this.L = logger;

        logger.info("Collect components");
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

        resFolders = new ArrayList<ResFolder>();
        addResFolder(new FileResFolder(CWD.getcwd(), true));

        searchLoaders = new ArrayList<ClassLoader>();
        searchLoaders.add(ClassLoader.getSystemClassLoader());

        apool = new TreeTextMap<StatedAttachment>();
    }

    @Override
    public Project getProject() {
        return project;
    }

    @Override
    public Component getComponent(String id) {
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
    public LogTerm getLogger() {
        return L;
    }

    @Override
    public void setLogger(LogTerm logger) {
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
    public List<ResFolder> getResFolders() {
        return resFolders;
    }

    @Override
    public void addResFolder(ResFolder resFolder) {
        resFolders.add(resFolder);
    }

    @Override
    public void addResFolder(int index, ResFolder resFolder) {
        resFolders.add(index, resFolder);
    }

    @Override
    public ResLink newResource(String resPath) throws IOException {
        assert !resFolders.isEmpty();
        ResFolder output = resFolders.get(0);
        return output.get(resPath);
    }

    @Override
    public ResLink findResource(String path, boolean autoCreate) throws IOException {
        ResLink firstUnknownLink = null;
        for (ResFolder resFolder : resFolders) {
            // if path.isAsbolute...
            ResLink link = resFolder.get(path);
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
                throw new NoSuchElementException("Resource isn't existed: " + path);
            return firstUnknownLink;
        }
    }

    protected String getAttachmentPath(String name) {
        return attachmentsPath + "/" + name; //$NON-NLS-1$
    }

    @Override
    public Attachment getAttachment(String name, boolean autoCreate) throws IOException {
        String path = getAttachmentPath(name);
        StatedAttachment a = apool.get(path);
        if (a == null) {
            ResLink res = findResource(path, autoCreate);
            a = new StatedAttachment(res, "utf-8"); //$NON-NLS-1$
            apool.put(path, a);
        }
        return a;
    }

    class ExWarn implements ExceptionListener {
        @Override
        public void exceptionThrown(Exception e) {
            L.warn(e);
        }
    }

    @Override
    public void closeAttachments() {
        for (Entry<String, StatedAttachment> entry : apool.entrySet()) {
            StatedAttachment a = entry.getValue();
            L.debug("Close attachment ", a);
            try {
                a.close();
            } catch (IOException e) {
                L.warn("Can't close attachment: ", a);
            }
        }
        apool.clear();
    }

    @Override
    public void loadRegistry() throws SessionException {
        try {
            // load registry before install/uinstall
            ResLink registryLink = findResource(registryPath, false);
            if (registryLink.exists() == Boolean.TRUE) {
                L.info("Loading registry from ", registryLink);
                InputStream in = registryLink.openInputStream();
                Object obj = XMLs.decode(in, new ExWarn());
                in.close();
                if (!(obj instanceof Map<?, ?>))
                    throw new SessionException("bad decoded registry: " + obj);
                @SuppressWarnings("unchecked")
                Map<String, Object> registry = (Map<String, Object>) obj;
                L.info("Registry data to load: ", registry);
                components.importRegistry(registry);
                L.info("Registry loaded");
            }
        } catch (IOException e) {
            throw new SessionException("Failed to load registry", e);
        }
    }

    @Override
    public void saveRegistry() throws SessionException {
        try {
            // save registry after packed
            TextMap<Object> registry = components.exportRegistry();
            if (registry != null) {
                L.info("Registry data to save: ", registry);
                ResLink registryLink = newResource(registryPath);
                L.info("Saving registry to ", registryLink);
                OutputStream out = registryLink.openOutputStream(false);
                XMLs.encode(registry, out, new ExWarn());
                out.close();
                L.info("Registry saved");
            }
        } catch (IOException e) {
            throw new SessionException("Failed to save registry", e);
        }
    }

    @Override
    public void dump(CharOut out) {
        out.println("Session ", this, ": ");
        out.println("  Project = ", project);
        out.println("  Scheme = ", scheme);
        for (ResFolder resFolder : resFolders)
            out.println("  Resource Folder = ", resFolder);
        for (Map.Entry<String, Object> e : variables.entrySet())
            out.printf("  Var %s = %s\n", e.getKey(), e.getValue());
        int i = 0;
        i = 0;
        for (ClassLoader searchLoader : searchLoaders)
            out.printf("  Search Loader[%d] = %s\n", i++, searchLoader);
        if (apool != null) {
            Set<Entry<String, StatedAttachment>> entrySet = apool.entrySet();
            for (Entry<String, StatedAttachment> entry : entrySet) {
                String name = entry.getKey();
                StatedAttachment a = entry.getValue();
                out.printf("  Pool Attachment[%s] = %s\n", name, a);
            }
        }
        out.println("  Component Tree: ");
        dump(out, project, "    ");
    }

    void dump(CharOut out, Component c, String prefix) {
        boolean included = getScheme().isIncluded(c);
        String flags = "";
        if (included)
            flags += "x";
        out.printf(prefix + "(%s) %s: %s\n", //
                flags, c.getId(), c.getClass().getSimpleName());
        List<? extends Component> children = c.getChildren();
        if (children != null) {
            for (Component child : children)
                dump(out, child, prefix + "  ");
        }
    }
}
