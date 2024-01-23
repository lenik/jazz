package net.bodz.lily.tool.wsdoc;

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

public abstract class AbstractTypeInfo {

    ModuleInfo module;
    AbstractTypeInfo parent;

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

    Set<Class<? extends Annotation>> includeAnnotations = new HashSet<>();
    Set<Class<? extends Annotation>> excludeAnnotations = new HashSet<>();
    {
        excludeAnnotations.add(Redundant.class);
        excludeAnnotations.add(Derived.class);
    }

    int maxDetailLevel = DetailLevel.EXPERT;

    protected void checkToAddProperty(IProperty property) {
        for (Class<? extends Annotation> a : excludeAnnotations)
            if (property.isAnnotationPresent(a))
                return;

        DetailLevel aDetailLevel = property.getAnnotation(DetailLevel.class);
        if (aDetailLevel != null) {
            int detailLevel = aDetailLevel.value();
            if (detailLevel > maxDetailLevel)
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
            initType(type, indexer);
            clazz = clazz.getSuperclass();
        }
        parsed = true;
    }

    protected abstract void initType(IType declaredType, ModuleIndexer indexer);

    public IJsonResponse getProperties() {
        JsonResult result = new JsonResult();
        JsonWriter out = result.begin("propertyGroups").array();
        PropertyExporter exporter = new PropertyExporter(ModuleIndexer.getInstance());

        exporter.export(out, this);

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
