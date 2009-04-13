package net.bodz.dist.ins;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.bodz.bas.io.FileResFolder;
import net.bodz.bas.io.FileResLink;
import net.bodz.bas.io.Files;
import net.bodz.bas.io.ResFolder;
import net.bodz.bas.io.ResLink;
import net.bodz.bas.io.URLResLink;
import net.bodz.bas.lang.err.UnexpectedException;
import net.bodz.bas.log.ALog;
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
    private Stack<IComponent>      stack;

    private Flags                  flags;
    private boolean                canceling;
    private List<CancelListener>   cancelListeners;

    private DerHashTextMap<Object> vars;
    private TextMap<File>          baseDirs;

    private ResFolder              resFolder;
    private List<File>             searchPaths;
    private List<ClassLoader>      searchLoaders;
    private File                   attachmentDir;

    public static final int        FATAL    = ALog.ERROR - 1;
    public static final int        ERROR    = ALog.ERROR;
    public static final int        WARN     = ALog.WARN;
    public static final int        INFO     = ALog.INFO;
    public static final int        DETAIL   = ALog.DETAIL;
    public static final int        DEBUG    = ALog.DEBUG;

    protected int                  logLevel = INFO;
    private TreeProgress           progress;

    public _Session(IProject project, TextMap<Object> env, int logLevel) {
        if (project == null)
            throw new NullPointerException("project"); //$NON-NLS-1$
        if (env == null) {
            env = new TreeTextMap<Object>();
        }
        this.project = project;
        this.logLevel = logLevel;
        components = Components.collect(project);
        stack = new LinkedStack<IComponent>();
        flags = new Flags();

        vars = new DerHashTextMap<Object>(env);
        baseDirs = new HashTextMap<File>();
        for (BaseDir baseDir : project.getBaseDirs())
            baseDirs.put(baseDir.getName(), baseDir.getPreferred());

        setResFolder(Files.canoniOf("."));
        searchPaths = new ArrayList<File>();
        searchPaths.add(new File(".")); //$NON-NLS-1$
        searchLoaders = new ArrayList<ClassLoader>();
        searchLoaders.add(ClassLoader.getSystemClassLoader());
        attachmentDir = new File("."); //$NON-NLS-1$

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

    protected void log(int level, Object... args) {
        if (level <= logLevel)
            _log(level, args);
        if (level <= INFO)
            progress.incr();
    }

    protected abstract void _log(int level, Object... args);

    @Override
    public final void logFatal(Object... args) {
        log(FATAL, args);
    }

    @Override
    public final void logError(Object... args) {
        log(ERROR, args);
    }

    @Override
    public final void logWarn(Object... args) {
        log(WARN, args);
    }

    @Override
    public final void logInfo(Object... args) {
        log(INFO, args);
    }

    @Override
    public final void logDetail(Object... args) {
        log(DETAIL, args);
    }

    @Override
    public final void logDebug(Object... args) {
        log(DEBUG, args);
    }

    @Override
    public Object get(String attr) {
        return vars.get(attr);
    }

    @Override
    public void set(String attr, Object value) {
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
        if (link.exists() == Boolean.TRUE)
            return link;
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
        File newFile = new File(attachmentDir, path);
        return new FileResLink(newFile);
    }

    protected String getAttachmentPath(String name) {
        return ".attachments/" + name; //$NON-NLS-1$
    }

    @Override
    public IAttachment getAttachment(String name) throws IOException {
        String path = getAttachmentPath(name);
        ResLink res = findResource(path);
        Attachment a = new Attachment(res, "utf-8"); //$NON-NLS-1$
        return a;
    }

    static final int PACK      = 1;
    static final int INSTALL   = 2;
    static final int UNINSTALL = 3;

    synchronized void execute(int action) throws InstallException {
        execute(action, project);
    }

    void execute(int action, IComponent node) throws InstallException {
        // enter
        stack.push(node);
        vars = new DerHashTextMap<Object>(vars);
        progress = new TreeProgress(progress, 1, 0);

        node.pack(this);
        switch (action) {
        case PACK:
            node.pack(this);
            break;
        case INSTALL:
            boolean succeeded = node.install(this);
            if (succeeded) {
                // included for next time uninstall.
            }
            break;
        case UNINSTALL:
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
        vars = (DerHashTextMap<Object>) vars.getOrig();
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
