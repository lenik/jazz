package net.bodz.bas.t.project;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

import net.bodz.bas.err.DuplicatedKeyException;
import net.bodz.bas.meta.autowire.ProjectName;

public class JazzProjects {

    Map<String, IJazzProject> nameMap = new HashMap<>();
    Map<Class<?>, IJazzProject> instanceMap = new HashMap<>();

    public IJazzProject getProject(String name) {
        autoLoad();
        return nameMap.get(name);
    }

    public IJazzProject getProject(Class<?> type) {
        autoLoad();
        return instanceMap.get(type);
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
        for (IJazzProject project : ServiceLoader.load(IJazzProject.class)) {
            Class<?> clazz = project.getClass();
            IJazzProject existing = instanceMap.get(clazz);
            if (existing != null)
                throw new DuplicatedKeyException(clazz.getName());
            instanceMap.put(clazz, project);

            ProjectName aProjectName = clazz.getAnnotation(ProjectName.class);
            if (aProjectName != null) {
                String name = aProjectName.value();
                existing = nameMap.get(name);
                if (existing != null)
                    throw new DuplicatedKeyException(name);
                nameMap.put(name, project);
            }
        }
    }

    public static final JazzProjects INSTANCE = new JazzProjects();

}
