package net.bodz.lily.codegen.doc;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import net.bodz.bas.c.org.json.JsonWriter;
import net.bodz.bas.meta.bean.DetailLevel;
import net.bodz.bas.meta.cache.Derived;
import net.bodz.bas.meta.decl.Redundant;
import net.bodz.bas.potato.ITypeProvider;
import net.bodz.bas.potato.element.IMethod;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.potato.element.IType;
import net.bodz.bas.potato.provider.bean.BeanTypeProvider;
import net.bodz.bas.site.json.IJsonResponse;
import net.bodz.bas.site.json.JsonResult;
import net.bodz.mda.xjdoc.Xjdocs;
import net.bodz.mda.xjdoc.model.ClassDoc;

public abstract class AbstractTypeInfo<self_t extends AbstractTypeInfo<self_t>> {

    ModuleInfo module;
    self_t parent;

    Class<?> declaredClass;
    ClassDoc doc;
    String displayName;

    boolean parsed;
    Map<String, IProperty> properties = new TreeMap<>();

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

    public Map<String, IProperty> getPropertyMap() {
        return properties;
    }

    Set<Class<? extends Annotation>> includes = new HashSet<>();
    Set<Class<? extends Annotation>> excludes = new HashSet<>();
    {
        excludes.add(Redundant.class);
        excludes.add(Derived.class);
    }

    protected void addProperty(IProperty property) {
        boolean included = false;
        if (!included)
            for (Class<? extends Annotation> a : excludes)
                if (property.isAnnotationPresent(a))
                    return;

        DetailLevel aDetailLevel = property.getAnnotation(DetailLevel.class);
        if (aDetailLevel != null) {
            int detailLevel = aDetailLevel.value();
            if (detailLevel > DetailLevel.EXPERT)
                return;
        }
        properties.put(property.getName(), property);
    }

    public abstract Map<String, IMethod> getMethodMap();

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

    public IJsonResponse getProperties() {
        JsonResult result = new JsonResult();
        JsonWriter out = result.begin("propertyGroups").array();
        PropertyExporter exporter = new PropertyExporter(ModuleIndexer.getInstance());

        @SuppressWarnings("unchecked")
        self_t start = (self_t) this;
        exporter.export(out, start);

        out.endArray();
        return result.succeed();
    }

    static ITypeProvider declares;
    static {
        declares = new BeanTypeProvider(//
                ITypeProvider.I_Properties | ITypeProvider.I_Methods | ITypeProvider.I_Docs //
                        | BeanTypeProvider.I_DeclaredOnly | BeanTypeProvider.I_ReadOnly);
    }

}
