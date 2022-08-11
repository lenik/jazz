package net.bodz.bas.c.autowire;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.c.type.IndexedTypes;
import net.bodz.bas.err.DuplicatedKeyException;
import net.bodz.bas.err.IllegalConfigException;
import net.bodz.bas.meta.autowire.ProjectDependencies;
import net.bodz.bas.meta.autowire.ProjectName;

public class ProjectList {

    Map<String, Class<?>> nameMap = new HashMap<>();
    Map<Class<?>, String[]> dependenciesMap = new HashMap<>();

    public Set<String> getNames() {
        autoLoad();
        return nameMap.keySet();
    }

    public Class<?> getProjectClass(String name) {
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

    public String topLevelName() {
        Set<String> names = getTopLevelNames();
        if (names.isEmpty())
            throw new IllegalConfigException("No project.");
        if (names.size() > 1)
            throw new IllegalConfigException("More than one top level project.");
        return names.iterator().next();
    }

    public ProjectNode toTree(String name) {
        Class<?> clazz = nameMap.get(name);
        if (clazz == null)
            return null;

        ProjectNode node = new ProjectNode();
        node.name = name;

        for (String dep : dependenciesMap.get(clazz)) {
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
            ProjectName aProjectName = clazz.getAnnotation(ProjectName.class);
            if (aProjectName != null) {
                String name = aProjectName.value();
                Class<?> existing = nameMap.get(name);
                if (existing != null)
                    throw new DuplicatedKeyException(name);
                nameMap.put(name, clazz);
            }
        }

        for (Class<?> clazz : classes) {
            Class<?>[] depClasses;
            ProjectDependencies aDependencies = clazz.getAnnotation(ProjectDependencies.class);
            if (aDependencies == null)
                depClasses = new Class<?>[0];
            else
                depClasses = aDependencies.value();

            String[] depNames = new String[depClasses.length];
            for (int i = 0; i < depClasses.length; i++) {
                Class<?> depClass = depClasses[i];
                ProjectName aDepName = depClass.getAnnotation(ProjectName.class);
                if (aDepName == null) {
                    String f = "Project class %s has a dependency w/o @ProgramName: %s";
                    throw new IllegalArgumentException(String.format(f, //
                            clazz.getName(), depClass.getName()));
                }
                String depName = aDepName.value();
                if (!nameMap.containsKey(depName)) {
                    String f = "Project class %s: invalid dependency: %s";
                    throw new IllegalArgumentException(String.format(f, //
                            clazz.getName(), depClass.getName()));
                }
                depNames[i] = depName;
            }
            dependenciesMap.put(clazz, depNames);
        }
    }

    public static ProjectList INSTANCE = new ProjectList();

}
