package net.bodz.bas.c.autowire;

import java.lang.annotation.Annotation;
import java.util.*;

import net.bodz.bas.c.type.IndexedTypes;
import net.bodz.bas.err.DuplicatedKeyException;
import net.bodz.bas.err.IllegalConfigException;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.autowire.ProjectDependencies;
import net.bodz.bas.meta.autowire.ProjectName;

public class ProjectList {

    static final Logger logger = LoggerFactory.getLogger(ProjectList.class);

    Map<String, ProjectMetadata> nameMap = new HashMap<>();
    Map<Class<?>, String[]> dependenciesMap = new HashMap<>();

    public Set<String> getNames() {
        autoLoad();
        return nameMap.keySet();
    }

    public ProjectMetadata getProject(String name) {
        autoLoad();
        return nameMap.get(name);
    }

    public Set<String> getTopLevelNames() {
        autoLoad();
        Set<String> depUnion = new HashSet<>();
        for (String[] deps : dependenciesMap.values())
            for (String dep : deps)
                depUnion.add(dep);

        Set<String> topLevels = new HashSet<>();
        for (String name : nameMap.keySet())
            if (!depUnion.contains(name))
                topLevels.add(name);

        return topLevels;
    }

    public List<ProjectMetadata> getTopLevelProjects() {
        Set<String> names = getTopLevelNames();
        List<ProjectMetadata> projects = new ArrayList<>();
        for (String name : names) {
            ProjectMetadata project = getProject(name);
            projects.add(project);
        }
        Collections.sort(projects, ProjectMetadata.ORDER);
        return projects;
    }

    public ProjectMetadata topLevelProject() {
        List<ProjectMetadata> projects = getTopLevelProjects();
        if (projects.isEmpty())
            throw new IllegalConfigException("No project.");
        int n = projects.size();
        return projects.get(n - 1);
    }

    public String topLevelName() {
        return topLevelProject().getName();
    }

    public ClassLoader topLevelClassLoader() {
        return topLevelProject().getProjectClass().getClassLoader();
    }

    public ProjectNode toTree(String name) {
        ProjectMetadata project = nameMap.get(name);
        if (project == null)
            return null;

        ProjectNode node = new ProjectNode();
        node.project = project;

        for (String dep : dependenciesMap.get(project.getProjectClass())) {
            ProjectNode depNode = toTree(dep);
            if (depNode != null)
                node.dependencies.add(depNode);
        }
        return node;
    }

    boolean loaded;

    synchronized void reload() {
        loaded = false;
        load();
        loaded = true;
    }

    synchronized void autoLoad() {
        if (!loaded) {
            load();
            loaded = true;
        }
    }

    void load() {
        Iterable<Class<? extends ProjectName>> classes = IndexedTypes.list(ProjectName.class, true);
        for (Class<?> clazz : classes) {
            ProjectName aProjectName = getAnnotation(clazz, ProjectName.class);
            if (aProjectName != null) {
                String name = aProjectName.value();
                ProjectMetadata existing = nameMap.get(name);
                if (existing != null)
                    throw new DuplicatedKeyException(name + " from " + existing.getProjectClass());
                ProjectMetadata project = new ProjectMetadata(clazz);
                nameMap.put(name, project);
            }
        }

        for (Class<?> clazz : classes) {
            Class<?>[] depClasses;
            ProjectDependencies aDependencies = getAnnotation(clazz, ProjectDependencies.class);
            if (aDependencies == null)
                depClasses = new Class<?>[0];
            else
                depClasses = aDependencies.value();

            String[] depNames = new String[depClasses.length];
            for (int i = 0; i < depClasses.length; i++) {
                Class<?> depClass = depClasses[i];
                ProjectName aDepName = getAnnotation(depClass, ProjectName.class);
                if (aDepName == null) {
                    String f = "Project class %s has a dependency w/o @ProgramName: %s";
                    throw new IllegalArgumentException(String.format(f, //
                            clazz.getName(), depClass.getName()));
                }
                String depName = aDepName.value();
                if (!nameMap.containsKey(depName)) {
                    String f = "Project class %s: unknown dependency: %s";
                    throw new IllegalArgumentException(String.format(f, //
                            clazz.getName(), depClass.getName()));
                }
                depNames[i] = depName;
            }
            dependenciesMap.put(clazz, depNames);
        }
    }

    static <A extends Annotation> A getAnnotation(Class<?> clazz, Class<A> annotationClass) {
        try {
            return clazz.getAnnotation(annotationClass);
        } catch (Throwable e) {
            logger.errorf(e, "error get anntation %s from %s.", annotationClass, clazz);
            logger.error("Hint: check the classpath.");
            // System.exit(1);
            throw new RuntimeException(e);
        }
    }

    public void dump() {
        for (String name : nameMap.keySet()) {
            System.err.println("project " + name + " := " + nameMap.get(name));
        }

        for (ProjectMetadata project : getTopLevelProjects()) {
            String mesg = String.format("project %s (priority %d): %s", //
                    project.getName(), project.getPriority(), project.getProjectClass());
            System.err.println(mesg);
        }

        for (Class<?> clazz : dependenciesMap.keySet()) {
            String[] deps = dependenciesMap.get(clazz);
            System.err.println(clazz + " => " + Arrays.asList(deps));
        }
    }

    public static ProjectList INSTANCE = new ProjectList();

}
