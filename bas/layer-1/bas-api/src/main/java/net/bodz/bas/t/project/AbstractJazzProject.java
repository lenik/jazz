package net.bodz.bas.t.project;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public abstract class AbstractJazzProject
        implements IJazzProject {

    @Override
    public List<IJazzModule> getModules() {
        List<IJazzModule> modules = new ArrayList<IJazzModule>();
        for (IJazzModule module : ServiceLoader.load(IJazzModule.class)) {
            if (module.getProject() == this)
                modules.add(module);
        }
        return modules;
    }

}
