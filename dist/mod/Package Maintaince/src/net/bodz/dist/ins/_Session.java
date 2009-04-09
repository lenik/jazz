package net.bodz.dist.ins;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.types.LinkedStack;
import net.bodz.bas.types.Stack;
import net.bodz.bas.types.TextMap;
import net.bodz.bas.types.TextMap.DerHashTextMap;
import net.bodz.bas.types.TextMap.HashTextMap;
import net.bodz.bas.types.TextMap.TreeTextMap;

public abstract class _Session implements ISession {

    class StackEntry {

        final String     id;
        final IComponent component;
        TextMap<Object>  componentRegistry;

        public StackEntry(StackEntry parent, IComponent component) {
            this.component = component;
            String prefix = parent != null ? parent.id + "/" : "";
            String name = component.getName();
            if (name == null)
                name = "1";
            id = prefix + name;
            componentRegistry = registry.get(id);
        }

    }

    private IProject                 project;
    private TextMap<TextMap<Object>> registry;

    private Stack<StackEntry>        stack;

    private boolean                  canceled;
    private List<CancelListener>     cancelListeners;

    private Flags                    flags;

    private static final int         FATAL    = 0;
    private static final int         ERROR    = 1;
    private static final int         WARN     = 2;
    private static final int         INFO     = 4;
    private static final int         DETAIL   = 5;
    private static final int         DEBUG    = 6;

    private int                      logLevel = INFO;
    private TreeProgress             progress;

    private DerHashTextMap<Object>   vars;
    private TextMap<File>            baseDirs;

    private List<File>               searchPaths;
    private List<ClassLoader>        searchLoaders;
    private File                     attachmentDir;

    public _Session(IProject project, TextMap<Object> env, int logLevel) {
        if (project == null)
            throw new NullPointerException("project");
        if (env == null)
            throw new NullPointerException("env");
        this.project = project;
        this.logLevel = logLevel;
        stack = new LinkedStack<StackEntry>();
        flags = new Flags();

        vars = new DerHashTextMap<Object>(env);
        baseDirs = new HashTextMap<File>();
        for (BaseDir baseDir : project.getBaseDirs())
            baseDirs.put(baseDir.getName(), baseDir.getPreferred());

        searchPaths = new ArrayList<File>();
        searchPaths.add(new File("."));
        searchLoaders = new ArrayList<ClassLoader>();
        searchLoaders.add(ClassLoader.getSystemClassLoader());
        attachmentDir = new File(".");
    }

    @Override
    public IProject getProject() {
        return project;
    }

    /**
     * @throws IndexOutOfBoundsException
     *             if <code>pos</code> is invalid.
     */
    @Override
    public IComponent getParentComponent(int stackPosition) {
        StackEntry stackEntry = stack.get(stackPosition);
        return stackEntry.component;
    }

    @Override
    public String getComponentId() {
        StackEntry stackEntry = stack.top();
        return stackEntry.id;
    }

    @Override
    public TextMap<Object> getComponentRegistry() {
        return getComponentRegistry(0);
    }

    public TextMap<Object> getComponentRegistry(int stackPosition) {
        StackEntry stackEntry = stack.get(stackPosition);
        if (stackEntry.componentRegistry == null)
            stackEntry.componentRegistry = new TreeTextMap<Object>();
        return stackEntry.componentRegistry;
    }

    @Override
    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
        if (cancelListeners != null) {
            CancelEvent e = new CancelEvent(null, this);
            for (CancelListener l : cancelListeners)
                l.cancel(e);
        }
    }

    public void addCancelListener(CancelListener l) {
        if (cancelListeners == null)
            cancelListeners = new ArrayList<CancelListener>(1);
        cancelListeners.add(l);
    }

    public void removeCancelListener(CancelListener l) {
        if (cancelListeners != null)
            cancelListeners.remove(l);
    }

    @Override
    public Flags getFlags() {
        return flags;
    }

    @Override
    public void setComponentProgress(int progressIndex) {
        progress.setIndex(progressIndex);
    }

    protected float getProgress() {
        return progress.get();
    }

    protected abstract void updateProgress();

    void log(int level, Object... args) {
        if (logLevel >= level)
            _log(args);
        progress.incr();
        updateProgress();
    }

    protected void _log(Object... args) {
        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            System.out.print(arg);
        }
        System.out.println();
    }

    @Override
    public void logFatal(Object... args) {
        log(FATAL, args);
    }

    @Override
    public void logError(Object... args) {
        log(ERROR, args);
    }

    @Override
    public void logWarn(Object... args) {
        log(WARN, args);
    }

    @Override
    public void logInfo(Object... args) {
        log(INFO, args);
    }

    @Override
    public void logDetail(Object... args) {
        log(DETAIL, args);
    }

    @Override
    public void logDebug(Object... args) {
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
            throw new NullPointerException("null dir for base=" + name);
        baseDirs.put(name, dir);
    }

    protected void enter(IComponent component, int progressSize) {
        StackEntry parentEntry = stack.isEmpty() ? null : stack.top();
        StackEntry entry = new StackEntry(parentEntry, component);
        stack.push(entry);
        vars = new DerHashTextMap<Object>(vars);
        progress = new TreeProgress(progress, progressSize);
    }

    protected void leave() {
        StackEntry stackEntry = stack.pop();
        TextMap<Object> localmap = stackEntry.componentRegistry;
        if (localmap != null && !localmap.isEmpty())
            registry.put(stackEntry.id, localmap);
        vars = (DerHashTextMap<Object>) vars.getOrig();
        progress = progress.getParent();
        updateProgress();
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
        return ".attachments/" + name;
    }

    @Override
    public IAttachment getAttachment(String name) {
        String path = getAttachmentPath(name);
        ResourceUnion res = findResource(path);
        Attachment a = new Attachment(res, "utf-8");
        return a;
    }

    @Override
    public void dump(IComponent component, int pnum) throws InstallException {
        int csize = 1; // component.getProgressSize(IComponent.DUMP);
        enter(component, csize);
        component.install(this);
        leave();
    }

    @Override
    public void install(IComponent component, int pnum) throws InstallException {
        int csize = 1; // component.getProgressSize(IComponent.INSTALL);
        enter(component, csize);
        component.install(this);
        leave();
    }

    @Override
    public void uninstall(IComponent component, int pnum)
            throws InstallException {
        int csize = 1; // component.getProgressSize(IComponent.UNINSTALL);
        enter(component, csize);
        component.uninstall(this);
        leave();
    }

}
