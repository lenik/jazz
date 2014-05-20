package net.bodz.pkg.sis;

import java.io.IOException;
import java.util.*;

import net.bodz.bas.c.action.IProgressMonitor;
import net.bodz.bas.c.java.util.HashTextMap;
import net.bodz.bas.c.java.util.TextMap;
import net.bodz.bas.c.java.util.TreeTextMap;
import net.bodz.bas.c.java.util.regex.UnixStyleVarExpander;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.io.ICharOut;
import net.bodz.bas.io.res.IRandomResource;
import net.bodz.bas.log.ILogger;
import net.bodz.bas.meta.build.IVersion;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.std.misc.Licenses;
import net.bodz.bas.t.iterator.RecIterator;
import net.bodz.bas.text.rst.ElementHandlerException;
import net.bodz.bas.text.rst.IElementHandler;
import net.bodz.bas.text.rst.IRstOutput;
import net.bodz.bas.text.rst.IRstSerializable;
import net.bodz.bas.text.rst.RstLoader;
import net.bodz.bas.text.rst.RstOutputImpl;
import net.bodz.bas.vfs.IFile;
import net.bodz.mda.xjdoc.model.artifact.ArtifactDoc;

/**
 * Project Root
 */
public class AbstractSisProject
        extends AbstractSisComponent
        implements ISisProject {

    private static final long serialVersionUID = 1L;

    private TextMap<ISisComponent> componentMap;
    private Map<ISisComponent, Set<ISisComponent>> rdepsMap;
    private Map<ISisComponent, ISisComponent> followingMap;

    private ISisArchive archive;

    private TextMap<SisVariable> variableMap;

    private List<ISisInstallProfile> installProfiles;
    private ISisInstallProfile selectedInstallProfiles;

    private boolean rebootRequired;

    public AbstractSisProject(Class<?> artifactClass) {
        super(null, "project");

        componentMap = new HashTextMap<ISisComponent>();
        rdepsMap = new HashMap<ISisComponent, Set<ISisComponent>>();
        followingMap = new IdentityHashMap<ISisComponent, ISisComponent>();

        // sisPackage = new SisPackage(baseDir);
        variableMap = new TreeTextMap<SisVariable>();
        installProfiles = new ArrayList<ISisInstallProfile>();

// addArchive(new PojfFile(SystemColos.workdir.get()/* , true */));
//
// MavenPomDir sisiPomDir = MavenPomDir.fromClass(SisProjectInstaller.class);
// MavenPomDir projectPomDir = MavenPomDir.fromClass(getClass());
//
// IFile projectResBase = new PojfFile(projectPomDir.getResourceDir(getClass()));
// addArchive(projectResBase);
//
// IFile installerResBase = new PojfFile(sisiPomDir.getResourceDir(SisProjectInstaller.class));
// addArchive(installerResBase);
//
// searchLoaders = new ArrayList<ClassLoader>();
// searchLoaders.add(ClassLoader.getSystemClassLoader());

    }

    @Override
    public IVersion getVersion() {
        return getXjdoc().to(ArtifactDoc.class).getVersion();
    }

    @Override
    public String getLicense() {
        return Licenses.GPLv2;
    }

    /** ⇱ Part: Variables */
    /* _____________________________ */static section.iface __VARIABLES__;

    @Override
    public TextMap<SisVariable> getVariableMap() {
        return variableMap;
    }

    @Override
    public SisVariable getVariable(String varName) {
        if (varName == null)
            throw new NullPointerException("varName");
        return variableMap.get(varName);
    }

    @Override
    public void setVariable(String varName, SisVariable variable) {
        if (variable == null)
            throw new NullPointerException("variable");
        variableMap.put(varName, variable);
    }

    @Override
    public <T> T getValue(String varName) {
        if (varName == null)
            throw new NullPointerException("varName");
        SisVariable variable = getVariable(varName);
        if (variable == null)
            throw new NoSuchElementException(varName);
        return (T) variable.getValue();
    }

    @Override
    public void setValue(String varName, Object value) {
        if (varName == null)
            throw new NullPointerException("varName");
        SisVariable variable = getVariable(varName);
        if (variable == null)
            throw new NoSuchElementException(varName);
        variable.setValue(value);
    }

    @Override
    public String expand(String s) {
        UnixStyleVarExpander expander = new UnixStyleVarExpander(variableMap);
        String expansion = expander.process(s);
        return expansion;
    }

    // ________________________________________________________________________
    // ⇱ Part: Session
    //

    @Override
    public ISisArchive getArchive() {
        return archive;
    }

    @Override
    public void setArchive(ISisArchive archive) {
        if (archive == null)
            throw new NullPointerException("archive");
        this.archive = archive;
    }

    @Override
    public final void setArchive(IFile archiveFile) {
        if (archiveFile == null)
            throw new NullPointerException("archiveFile");
        SisArchive archive = new SisArchive(archiveFile);
        setArchive(archive);
    }

    @Override
    public List<ISisInstallProfile> getInstallProfiles() {
        return installProfiles;
    }

    @Override
    public ISisInstallProfile getInstallProfile() {
        return selectedInstallProfiles;
    }

    @Override
    public void setInstallProfile(ISisInstallProfile installProfile) {
        if (installProfile == null)
            throw new NullPointerException("installProfile");
        selectedInstallProfiles = installProfile;

        for (ISisComponent component : descendants()) {
            installProfile.preset(component);
        }
    }

    @Override
    public boolean isRebootRequired() {
        return rebootRequired;
    }

    @Override
    public void setRebootRequired(boolean rebootRequired) {
        this.rebootRequired = rebootRequired;
    }

    /** ⇱ Implementation Of {@link ISisComponentManager}. */
    /* _____________________________ */static section.iface __COMPONENT_MANAGER__;

    @Override
    public List<ISisComponent> getMissingDependencies(Iterable<? extends ISisComponent> components) {
        Set<ISisComponent> domain = new HashSet<ISisComponent>();
        for (ISisComponent c : components)
            domain.add(c);

        Set<ISisComponent> missingSet = new HashSet<ISisComponent>();
        for (ISisComponent c : components)
            addMissingTree(missingSet, domain, c);

        return new ArrayList<ISisComponent>(missingSet);
    }

    void addMissingTree(Set<ISisComponent> missingSet, Set<ISisComponent> domain, ISisComponent missing) {
        if (!domain.contains(missing))
            if (missingSet.add(missing))
                for (ISisComponent dep : missing.getDependencies())
                    addMissingTree(missingSet, domain, dep);
    }

    @Override
    public ISisComponent getComponent(String id) {
        return componentMap.get(id);
    }

    @Override
    public ISisComponent getFollowing(ISisComponent node) {
        return followingMap.get(node);
    }

    @Override
    public void updateDependencies() {
        rdepsMap = new HashMap<ISisComponent, Set<ISisComponent>>();
        for (ISisComponent c : descendants()) {
            for (ISisComponent dep : c.getDependencies()) {
                Set<ISisComponent> set = rdepsMap.get(dep);
                if (set == null)
                    rdepsMap.put(dep, set = new HashSet<>());
                set.add(c);
            }
        }
    }

    @Override
    public Collection<? extends ISisComponent> getDependencies(ISisComponent obj) {
        return obj.getDependencies();
    }

    @Override
    public Iterable<? extends ISisComponent> dependencyClosure(ISisComponent obj) {
        return obj.dependencyClosure();
    }

    @Override
    public Collection<ISisComponent> getDependents(ISisComponent obj) {
        Set<ISisComponent> rdeps = rdepsMap.get(obj);
        if (rdeps == null)
            return Collections.emptySet();
        else
            return Collections.unmodifiableSet(rdeps);
    }

    @Override
    public Iterable<ISisComponent> dependentClosure(ISisComponent obj) {

        class RdepRecIterator
                extends RecIterator<ISisComponent> {

            public RdepRecIterator(ISisComponent start) {
                super(getDependents(start).iterator());
            }

            @Override
            protected Iterator<? extends ISisComponent> expand(ISisComponent element) {
                Set<ISisComponent> rdeps = rdepsMap.get(element);
                if (rdeps == null)
                    return Collections.emptyIterator();
                else
                    return rdeps.iterator();
            }

        }

        return new Iterable<ISisComponent>() {
            @Override
            public Iterator<ISisComponent> iterator() {
                return new RdepRecIterator(AbstractSisProject.this);
            }
        };
    }

    // ________________________________________________________________________
    // ⇱ Part: Actions
    //

    @Override
    public int getWorks() {
        return 1;
    }

    @Override
    public void pack(IProgressMonitor monitor, IOptions options) {
        super.pack(monitor, options);

        try {
            saveProjectRst();
            monitor.worked(1);
        } catch (Exception e) {
            ILogger logger = options.get(ILogger.class);
            logger.error("Failed to save project.rst.", e);
            return;
        }

        archive.close();
    }

    @Override
    public void install(IProgressMonitor monitor, IOptions options) {
        try {
            loadProjectRst();
            monitor.worked(1);
        } catch (Exception e) {
            ILogger logger = options.get(ILogger.class);
            logger.error("Failed to load project.rst.", e);
            return;
        }

        super.install(monitor, options);

        archive.close();
    }

    @Override
    public void remove(IProgressMonitor monitor, IOptions options) {
        try {
            loadProjectRst();
            monitor.worked(1);
        } catch (Exception e) {
            ILogger logger = options.get(ILogger.class);
            logger.error("Failed to load project.rst.", e);
            return;
        }

        super.remove(monitor, options);

        archive.close();
    }

    /** ⇱ Implementation Of {@link IRstSerializable}. */
    /* _____________________________ */static section.iface __RST__;

    @Override
    public IElementHandler beginChild(String name, String[] args)
            throws ParseException, ElementHandlerException {

        ISisComponent child = getChild(CHILD_KEY_PREFIX + name);
        if (child == null)
            throw new ParseException("Unknown child name: " + name);

        return child.getElementHandler();
    }

    void loadProjectRst()
            throws IOException, ElementHandlerException, ParseException {
        IRandomResource projectRst = getArchive().getDataResource("project.rst", false);
        RstLoader loader = new RstLoader();
        loader.load(projectRst, this);
    }

    void saveProjectRst()
            throws IOException {
        IRandomResource projectRst = getArchive().getDataResource("project.rst", true);
        ICharOut out = projectRst.newCharOut();
        try {
            IRstOutput rstOutput = RstOutputImpl.from(out);
            writeObject(rstOutput);
        } finally {
            out.close();
        }
    }

}
