package net.bodz.dist.ins;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.io.Files;
import net.bodz.bas.types.LinkedStack;
import net.bodz.bas.types.TextMap;
import net.bodz.bas.types.TextMap.DerHashTextMap;
import net.bodz.bas.types.TextMap.HashTextMap;

public abstract class _Session implements ISession {

    private IProject                    project;
    private LinkedStack<IComponent>     cstack;

    private boolean                     canceled;
    private List<InstallCancelListener> cancelListeners;

    private Flags                       flags;

    private static final int            FATAL    = 0;
    private static final int            ERROR    = 1;
    private static final int            WARN     = 2;
    private static final int            INFO     = 4;
    private static final int            DETAIL   = 5;
    private static final int            DEBUG    = 6;

    private int                         logLevel = INFO;
    private TreeProgress                progress;

    private DerHashTextMap<Object>      vars;
    private TextMap<File>               baseDirs;

    private List<File>                  searchPaths;
    private List<ClassLoader>           searchLoaders;
    private File                        attachmentDir;

    public _Session(IProject project, TextMap<Object> env, int logLevel) {
        if (project == null)
            throw new NullPointerException("project");
        if (env == null)
            throw new NullPointerException("env");
        this.project = project;
        this.logLevel = logLevel;
        cstack = new LinkedStack<IComponent>();
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
    public IComponent getStack(int pos) {
        IComponent component = cstack.get(pos);
        return component;
    }

    @Override
    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
        if (cancelListeners != null) {
            InstallCancelEvent e = new InstallCancelEvent(null, this);
            for (InstallCancelListener l : cancelListeners)
                l.installCancel(e);
        }
    }

    public void addCancelListener(InstallCancelListener l) {
        if (cancelListeners == null)
            cancelListeners = new ArrayList<InstallCancelListener>(1);
        cancelListeners.add(l);
    }

    public void removeCancelListener(InstallCancelListener l) {
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
        cstack.push(component);
        component.enter(this);
        vars = new DerHashTextMap<Object>(vars);
        progress = new TreeProgress(progress, progressSize);
    }

    protected void leave() {
        IComponent component = cstack.pop();
        component.leave(this);
        vars = (DerHashTextMap<Object>) vars.getOrig();
        progress = progress.getParent();
        updateProgress();
    }

    protected String amap(String name) {
        return "ext/" + name;
    }

    @Override
    public IAttachment getAttachment(String name) {
        String path = amap(name);
        // if path.isAsbolute...

        for (File searchPath : searchPaths) {
            File file = new File(searchPath, path);
            if (file.isFile()) {
                URL url = Files.getURL(file);
                Attachment a = new Attachment(url, file);
                return a;
            }
        }
        for (ClassLoader loader : searchLoaders) {
            URL url = loader.getResource(path);
            if (url != null) {
                File file;
                try {
                    file = Files.getFile(url);
                } catch (MalformedURLException e) {
                    file = null;
                }
                Attachment a = new Attachment(url, file);
                return a;
            }
        }
        File file = new File(attachmentDir, path);
        URL url = Files.getURL(file);
        Attachment a = new Attachment(url, file);
        return a;
    }

    @Override
    public void dump(IComponent component, int pnum) throws InstallException {
        int csize = component.getProgressSize(IComponent.DUMP);
        enter(component, csize);
        component.install(this);
        leave();
    }

    @Override
    public void install(IComponent component, int pnum) throws InstallException {
        int csize = component.getProgressSize(IComponent.INSTALL);
        enter(component, csize);
        component.install(this);
        leave();
    }

    @Override
    public void uninstall(IComponent component, int pnum)
            throws InstallException {
        int csize = component.getProgressSize(IComponent.UNINSTALL);
        enter(component, csize);
        component.uninstall(this);
        leave();
    }

}
