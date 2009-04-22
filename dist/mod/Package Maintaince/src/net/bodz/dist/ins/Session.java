package net.bodz.dist.ins;

import java.beans.ExceptionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Map.Entry;

import net.bodz.bas.io.CharOut;
import net.bodz.bas.io.FileResFolder;
import net.bodz.bas.io.FileResLink;
import net.bodz.bas.io.Files;
import net.bodz.bas.io.ResFolder;
import net.bodz.bas.io.ResLink;
import net.bodz.bas.io.URLResLink;
import net.bodz.bas.types.HashTextMap;
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
    protected final LogTerm           L;

    private Components                components;
    private Scheme                    scheme;

    private Flags                     flags;
    private TextMap<Object>           attributes;
    private TextMap<File>             baseDirs;

    private ResFolder                 resFolder;
    private List<File>                searchPaths;
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

        Map<String, Object> _env = project.getEnv();
        if (_env == null)
            attributes = new TreeTextMap<Object>();
        else
            attributes = new TreeTextMap<Object>(_env);
        attributes = null;
        baseDirs = new HashTextMap<File>();
        for (BaseDir baseDir : project.getBaseDirs()) {
            String name = baseDir.getName();
            File preferred = baseDir.getPreferred();
            logger.fdebug("Preferred base dir %s: %s\n", name, preferred);
            baseDirs.put(name, preferred);
        }

        setResFolder(Files.canoniOf("."));
        searchPaths = new ArrayList<File>();
        searchPaths.add(new File(".")); //$NON-NLS-1$
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
    public Flags getFlags() {
        return flags;
    }

    @Override
    public Object get(String attr) {
        if (attributes == null)
            return attributes.get(attr);
        return attributes.get(attr);
    }

    @Override
    public void set(String attr, Object value) {
        if (attributes == null)
            throw new IllegalStateException(
                    "Set to the session env using setEnv.");
        attributes.put(attr, value);
    }

    @Override
    public File getBaseDir(String name) {
        return baseDirs.get(name);
    }

    @Override
    public void setBaseDir(String name, File dir) {
        if (dir == null)
            throw new NullPointerException("null dir for base=" + name); //$NON-NLS-1$
        baseDirs.put(name, dir);
    }

    @Override
    public ResFolder getResFolder() {
        return resFolder;
    }

    @Override
    public void setResFolder(ResFolder resFolder) {
        this.resFolder = resFolder;
    }

    @Override
    public void setResFolder(File dir) {
        if (dir == null)
            throw new NullPointerException("dir");
        FileResFolder folder = new FileResFolder(dir);
        folder.setAutoMkdirs(true);
        setResFolder(folder);
    }

    @Override
    public ResLink newResource(String resPath) throws IOException {
        return resFolder.get(resPath);
    }

    @Override
    public ResLink findResource(String path) throws IOException {
        // if path.isAsbolute...
        ResLink link = resFolder.get(path);
        if (link.exists() != Boolean.TRUE) {
            for (File searchPath : searchPaths) {
                File foundFile = new File(searchPath, path);
                if (foundFile.isFile())
                    return new FileResLink(foundFile);
            }
            for (ClassLoader loader : searchLoaders) {
                URL resURL = loader.getResource(path);
                if (resURL != null)
                    return new URLResLink(resURL);
            }
        }
        return link;
    }

    protected String getAttachmentPath(String name) {
        return attachmentsPath + "/" + name; //$NON-NLS-1$
    }

    @Override
    public Attachment getAttachment(String name) throws IOException {
        String path = getAttachmentPath(name);
        StatedAttachment a = apool.get(path);
        if (a == null) {
            ResLink res = findResource(path);
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
            ResLink registryLink = findResource(registryPath);
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
        out.println("  ResFolder = ", resFolder);
        for (Map.Entry<String, File> e : baseDirs.entrySet())
            out.printf("  BaseDir[%s] = %s\n", e.getKey(), e.getValue());
        int i = 0;
        for (File searchPath : searchPaths) {
            searchPath = Files.canoniOf(searchPath);
            out.printf("  Search Path[%d] = %s\n", i++, searchPath);
        }
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
        out.printf(prefix + "%s: %s\n", //
                c.getId(), c.getClass().getSimpleName());
        List<? extends Component> children = c.getChildren();
        if (children != null) {
            for (Component child : children)
                dump(out, child, prefix + "  ");
        }
    }
}
