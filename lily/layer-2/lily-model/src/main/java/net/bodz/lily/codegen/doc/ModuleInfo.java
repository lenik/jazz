package net.bodz.lily.codegen.doc;

import java.util.TreeSet;

import net.bodz.bas.c.type.ClassComparator;
import net.bodz.bas.t.order.AbstractNonNullComparator;
import net.bodz.bas.t.project.IJazzModule;

public class ModuleInfo {

    IJazzModule module;
    TreeSet<EntityInfo> entities;

    public ModuleInfo(IJazzModule module) {
        this.module = module;
        this.entities = new TreeSet<>(EntityInfoNameCmp.getInstance());
    }

    public void add(EntityInfo entityInfo) {
        entities.add(entityInfo);
    }

}

class EntityInfoNameCmp
        extends AbstractNonNullComparator<EntityInfo> {

    ClassComparator classComparator = ClassComparator.getInstance();

    @Override
    public int compareNonNull(EntityInfo o1, EntityInfo o2) {
        return classComparator.compareNonNull(o1.declaredClass, o2.declaredClass);
    }

    static final EntityInfoNameCmp instance = new EntityInfoNameCmp();

    public static EntityInfoNameCmp getInstance() {
        return instance;
    }

}