package net.bodz.lily.codegen.doc;

import net.bodz.bas.potato.ITypeProvider;
import net.bodz.bas.potato.element.IType;
import net.bodz.bas.potato.provider.bean.BeanTypeProvider;
import net.bodz.mda.xjdoc.Xjdocs;
import net.bodz.mda.xjdoc.model.ClassDoc;

public abstract class AbstractTypeInfo<self_t extends AbstractTypeInfo<?>> {

    ModuleInfo module;
    self_t parent;

    Class<?> declaredClass;
    ClassDoc doc;
    String displayName;

    boolean parsed;

    public AbstractTypeInfo(Class<?> declaredClass) {
        if (declaredClass == null)
            throw new NullPointerException("declaredClass");
        this.declaredClass = declaredClass;
        this.doc = Xjdocs.getDefaultProvider().getOrCreateClassDoc(declaredClass);
        this.displayName = DocUtil.getLabel(doc);
    }

    public Class<?> getDeclaredClass() {
        return declaredClass;
    }

    public ClassDoc getDoc() {
        return doc;
    }

    public String getDisplayName() {
        return displayName;
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

    static ITypeProvider declares;
    static {
        declares = new BeanTypeProvider(//
                ITypeProvider.PROPERTIES | ITypeProvider.METHODS | ITypeProvider.DOCS);
    }

}
