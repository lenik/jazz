package net.bodz.dist.ins;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.bodz.bas.io.FileResFolder;
import net.bodz.bas.io.FileResLink;
import net.bodz.bas.io.Files;
import net.bodz.bas.io.ResFolder;
import net.bodz.bas.io.ResLink;
import net.bodz.bas.io.URLResLink;
import net.bodz.bas.lang.err.UnexpectedException;
import net.bodz.bas.log.LogTerm;
import net.bodz.bas.log.LogTerms;
import net.bodz.bas.types.LinkedStack;
import net.bodz.bas.types.Stack;
import net.bodz.bas.types.TextMap;
import net.bodz.bas.types.TextMap.DerHashTextMap;
import net.bodz.bas.types.TextMap.HashTextMap;
import net.bodz.bas.types.TextMap.TreeTextMap;
import net.bodz.dist.ins.util.Flags;

public abstract class _Session implements ISession, ProgressChangeListener {

    private IProject               project;
    private Components             components;

    private Flags                  flags;
    private List<CancelListener>   cancelListeners;

    private Map<String, Object>    env;
    private DerHashTextMap<Object> vars;
    private TextMap<File>          baseDirs;

    private ResFolder              resFolder;
    private List<File>             searchPaths;
    private List<ClassLoader>      searchLoaders;

    protected final LogTerm        logger;

    // Execution Variables

    private boolean                canceling;
    private Stack<IComponent>      stack;
    private TextMap<Attachment>    apool;

    private TreeProgress           progress;

    public _Session(IProject project) {
        if (project == null)
            throw new NullPointerException("project"); //$NON-NLS-1$
        this.project = project;
        components = Components.collect(project);
        flags = new Flags();

        Map<String, Object> _env = project.getEnv();
        if (_env == null)
            env = new TreeTextMap<Object>();
        else
            env = new TreeTextMap<Object>(_env);
        vars = null;
        baseDirs = new HashTextMap<File>();
        for (BaseDir baseDir : project.getBaseDirs())
            baseDirs.put(baseDir.getName(), baseDir.getPreferred());

        setResFolder(Files.canoniOf("."));
        searchPaths = new ArrayList<File>();
        searchPaths.add(new File(".")); //$NON-NLS-1$
        searchLoaders = new ArrayList<ClassLoader>();
        searchLoaders.add(ClassLoader.getSystemClassLoader());

        logger = LogTerms.get(getClass());
        if (logger == null)
            throw new NullPointerException("logger");

        apool = new TreeTextMap<Attachment>();

        progress = new TreeProgress(1);
        progress.addProgressChangeListener(this);
    }

    @Override
    public IProject getProject() {
        return project;
    }

    @Override
    public IComponent getComponent(String id) {
        return components.get(id);
    }

    @Override
    public boolean isCanceling() {
        return canceling;
    }

    public void setCanceling(boolean canceling) {
        this.canceling = canceling;
    }

    public void cancel() {
        setCanceling(true);
    }

    public void setCanceled() {
        canceling = false;
        if (cancelListeners != null) {
            CancelEvent e = new CancelEvent(null, this);
            for (CancelListener l : cancelListeners)
                l.cancel(e);
        }
    }

    @Override
    public void addCancelListener(CancelListener l) {
        if (cancelListeners == null)
            cancelListeners = new ArrayList<CancelListener>(1);
        cancelListeners.add(l);
    }

    @Override
    public void removeCancelListener(CancelListener l) {
        if (cancelListeners != null)
            cancelListeners.remove(l);
    }

    @Override
    public Flags getFlags() {
        return flags;
    }

    @Override
    public Progress getProgress() {
        return progress;
    }

    @Override
    public LogTerm getLogger() {
        return logger;
    }

    protected Stack<IComponent> getStack() {
        return stack;
    }

    public Map<String, Object> getEnv() {
        return env;
    }

    public void setEnv(Map<String, Object> env) {
        this.env = env;
    }

    @Override
    public Object get(String attr) {
        if (vars == null)
            return env.get(attr);
        return vars.get(attr);
    }

    @Override
    public void set(String attr, Object value) {
        if (vars == null)
            throw new IllegalStateException(
                    "Set to the session env using setEnv.");
        vars.put(attr, value);
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
        return ".attachments/" + name; //$NON-NLS-1$
    }

    @Override
    public IAttachment getAttachment(String name) throws IOException {
        String path = getAttachmentPath(name);
        Attachment a = apool.get(path);
        if (a == null) {
            ResLink res = findResource(path);
            a = new Attachment(res, "utf-8"); //$NON-NLS-1$
            apool.put(path, a);
        }
        return a;
    }

    static final int PACK      = 1;
    static final int INSTALL   = 2;
    static final int UNINSTALL = 3;

    synchronized void execute(int action) throws InstallException {
        stack = new LinkedStack<IComponent>();
        canceling = false;
        execute(action, project);
        for (Entry<String, Attachment> entry : apool.entrySet()) {
            Attachment a = entry.getValue();
            logger.debug("Close attachment ", a);
            try {
                a.close();
            } catch (IOException e) {
                logger.warn("Can't close attachment: ", a);
            }
        }
        assert stack.isEmpty() : "stack corrupted";
        stack = null;
        apool.clear();
    }

    void execute(int action, IComponent node) throws InstallException {
        // enter
        stack.push(node);
        vars = new DerHashTextMap<Object>(vars == null ? env : vars);
        progress = new TreeProgress(progress, 1, 0);

        switch (action) {
        case PACK:
            logger.debug("Pack ", node);
            node.pack(this);
            break;
        case INSTALL:
            logger.debug("Install ", node);
            boolean succeeded = node.install(this);
            if (succeeded) {
                // included for next time uninstall.
            } else {
                logger.warn("Failed to install ", node);
            }
            break;
        case UNINSTALL:
            logger.debug("Uninstall ", node);
            node.uninstall(this);
            break;
        default:
            throw new UnexpectedException("Bad action: " + action);
        }
        Collection<IComponent> children = node.getChildren();
        if (children == null)
            throw new NullPointerException("children");
        for (IComponent child : children) {
            execute(action, child);
        }

        // leave
        stack.pop();
        Map<String, Object> orig = vars.getOrig();
        if (stack.isEmpty()) {
            assert orig == env;
            vars = null;
        } else {
            assert orig instanceof DerHashTextMap<?>;
            vars = (DerHashTextMap<Object>) orig;
        }

        int parentUnits = progress.getParentUnits();
        progress = progress.getParent();
        progress.incr(parentUnits);
    }

    @Override
    public void pack() throws InstallException {
        execute(PACK);
    }

    @Override
    public void install() throws InstallException {
        execute(INSTALL);
    }

    @Override
    public void uninstall() throws InstallException {
        execute(UNINSTALL);
    }

}
