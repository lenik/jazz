package net.bodz.dist.ins;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.log.ALog;
import net.bodz.bas.types.LinkedStack;
import net.bodz.bas.types.Stack;
import net.bodz.bas.types.TextMap;
import net.bodz.bas.types.TextMap.DerHashTextMap;
import net.bodz.bas.types.TextMap.HashTextMap;
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
        if (env == null)
            throw new NullPointerException("env"); //$NON-NLS-1$
        this.project = project;
        this.logLevel = logLevel;
        components = Components.collect(project);
        stack = new LinkedStack<IComponent>();
        flags = new Flags();

        vars = new DerHashTextMap<Object>(env);
        baseDirs = new HashTextMap<File>();
        for (BaseDir baseDir : project.getBaseDirs())
            baseDirs.put(baseDir.getName(), baseDir.getPreferred());

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
    public ResourceUnion findResource(String path) {
        // if path.isAsbolute...
        for (File searchPath : searchPaths) {
            File foundFile = new File(searchPath, path);
            if (foundFile.isFile())
                return new ResourceUnion(foundFile);
        }
        for (ClassLoader loader : searchLoaders) {
            URL resURL = loader.getResource(path);
            if (resURL != null)
                return new ResourceUnion(resURL);
        }
        File newFile = new File(attachmentDir, path);
        return new ResourceUnion(newFile);
    }

    protected String getAttachmentPath(String name) {
        return ".attachments/" + name; //$NON-NLS-1$
    }

    @Override
    public IAttachment getAttachment(String name) {
        String path = getAttachmentPath(name);
        ResourceUnion res = findResource(path);
        Attachment a = new Attachment(res, "utf-8"); //$NON-NLS-1$
        return a;
    }

    protected void enter(IComponent component, int parentUnits) {
        stack.push(component);
        vars = new DerHashTextMap<Object>(vars);
        progress = new TreeProgress(progress, parentUnits, 0);
    }

    protected void leave() {
        stack.pop();
        vars = (DerHashTextMap<Object>) vars.getOrig();
        int parentUnits = progress.getParentUnits();
        progress = progress.getParent();
        progress.incr(parentUnits);
    }

    @Override
    public void pack(IComponent component, int parentUnits)
            throws InstallException {
        enter(component, parentUnits);
        component.install(this);
        leave();
    }

    @Override
    public void install(IComponent component, int parentUnits)
            throws InstallException {
        enter(component, parentUnits);
        component.install(this);
        leave();
    }

    @Override
    public void uninstall(IComponent component, int parentUnits)
            throws InstallException {
        enter(component, parentUnits);
        component.uninstall(this);
        leave();
    }

}
