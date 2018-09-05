package net.bodz.lily.codegen.doc;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.t.project.IJazzModule;
import net.bodz.mda.xjdoc.Xjdocs;
import net.bodz.mda.xjdoc.model.ClassDoc;

public class ModuleInfo
// implements IDependencyAware<ModuleInfo>
{

    IJazzModule module;
    ClassDoc doc;
    TreeSet<EntityInfo> entities;

    Set<ModuleInfo> dependencies = new LinkedHashSet<>();

    public ModuleInfo(IJazzModule module) {
        this.module = module;
        this.doc = Xjdocs.getDefaultProvider().getClassDoc(module.getClass());
        this.entities = new TreeSet<>(EntityNameOrder.INSTANCE);
    }

    public IJazzModule getModule() {
        return module;
    }

    public ClassDoc getDoc() {
        return doc;
    }

    public TreeSet<EntityInfo> getEntities() {
        return entities;
    }

    public void add(EntityInfo entityInfo) {
        entities.add(entityInfo);
    }

    public void addDependency(ModuleInfo dep) {
        if (dep.isDependency(this))
            throw new IllegalUsageException("Dead loop detected: " + dep);
        dependencies.add(dep);
    }

    public boolean isDependency(ModuleInfo module) {
        if (this == module)
            return true;
        for (ModuleInfo dep : dependencies)
            if (dep.isDependency(module))
                return true;
        return false;
    }

    public boolean isDependedBy(ModuleInfo module) {
        return module.isDependency(this);
    }

    @Override
    public String toString() {
        return module.getName();
    }

}
