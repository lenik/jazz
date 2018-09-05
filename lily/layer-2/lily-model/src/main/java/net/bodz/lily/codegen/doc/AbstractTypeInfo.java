package net.bodz.lily.codegen.doc;

import net.bodz.bas.potato.element.IType;
import net.bodz.bas.potato.provider.reflect.ReflectTypeProvider_declared;
import net.bodz.bas.potato.provider.reflect.ReflectType_declared;
import net.bodz.mda.xjdoc.Xjdocs;
import net.bodz.mda.xjdoc.model.ClassDoc;

public abstract class AbstractTypeInfo<self_t extends AbstractTypeInfo<?>> {

    ModuleInfo module;
    self_t parent;

    Class<?> declaredClass;
    ClassDoc doc;

    boolean parsed;

    public AbstractTypeInfo(Class<?> declaredClass) {
        if (declaredClass == null)
            throw new NullPointerException("declaredClass");
        this.declaredClass = declaredClass;
        this.doc = Xjdocs.getDefaultProvider().getOrCreateClassDoc(declaredClass);
    }

    public synchronized void parseUptoParent(ModuleIndexer indexer) {
        if (parsed)
            return;
        Class<?> clazz = declaredClass;
        Class<?> stopClass = null;
        if (parent != null)
            stopClass = parent.declaredClass;
        while (clazz != stopClass) {
            IType type = declares.loadType(clazz);
            parse(type, indexer);
            clazz = clazz.getSuperclass();
        }
        parsed = true;
    }

    protected abstract void parse(IType declaredType, ModuleIndexer indexer);

    static ReflectTypeProvider_declared declares;
    static {
        declares = new ReflectTypeProvider_declared(ReflectType_declared.PUBLIC);

    }

}
