package net.bodz.lily.codegen.doc;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.c.org.json.JsonWriter;
import net.bodz.bas.c.type.TypeParam;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.potato.element.IPropertyMap;
import net.bodz.bas.potato.element.IType;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.IPathDispatchable;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.site.ajax.AjaxResult;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.model.base.CoObject;

/**
 * @see ModuleInfo
 */
public class EntityInfo
        extends AbstractTypeInfo<EntityInfo>
        implements IPathDispatchable {

    MaskInfo maskInfo;
    IndexInfo indexInfo;

    ReferenceMap refmap = new ReferenceMap();
    IPropertyMap propertyMap;

    private Set<EntityInfo> dependencies = new LinkedHashSet<>();

    public EntityInfo(Class<?> clazz, Class<?> maskClass) {
        super(clazz);
        if (maskClass != null)
            this.maskInfo = new MaskInfo(maskClass);
    }

    public void setMapperClass(Class<?> mapperClass) {
        // skip
    }

    public void setIndexClass(Class<?> indexClass) {
        this.indexInfo = new IndexInfo(indexClass);
    }

    public ReferenceMap getReferenceMap() {
        return refmap;
    }

    public AjaxResult getProperties() {
        AjaxResult result = new AjaxResult();
        JsonWriter out = result.begin("groups").array();
        EntityInfo node = this;
        while (node != null) {
            out.object();
            // out.entry("declaredClass", node.getDeclaredClass().getName());
            out.entry("name", node.declaredClass.getName());
            out.entry("doc", node.doc.toString());
            out.key("properties");
            out.array();
            for (IProperty property : node.propertyMap.getProperties()) {
                out.object();
                out.entry("name", property.getName());
                out.entry("type", property.getPropertyType().getName());
                out.entry("label", property.getLabel());
                out.entry("description", property.getDescription());
                out.endObject();
            }
            out.endArray();
            node = node.parent;
            out.endObject();
        }
        out.endArray();
        return result.succeed();
    }

    public AjaxResult getMaskProperties() {
        AjaxResult result = new AjaxResult();
        JsonWriter out = result.begin("groups").array();
        MaskInfo node = maskInfo;
        while (node != null) {
            out.object();
            // out.entry("declaredClass", node.getDeclaredClass().getName());
            out.entry("name", node.declaredClass.getName());
            out.entry("doc", node.doc.toString());
            out.key("properties");
            out.array();
            for (IProperty property : node.properties.values()) {
                out.object();
                out.entry("name", property.getName());
                out.entry("type", property.getPropertyType().getName());
                out.entry("label", property.getLabel());
                out.entry("description", property.getDescription());
                out.endObject();
            }
            out.endArray();
            node = node.parent;
            out.endObject();
        }
        out.endArray();
        return result.succeed();
    }

    public Set<EntityInfo> getDependencies() {
        return dependencies;
    }

    @Override
    public String toString() {
        return declaredClass.getName();
    }

    @Override
    protected void parse(IType declaredType, ModuleIndexer indexer) {
        propertyMap = declaredType.getPropertyMap();
        for (IProperty property : propertyMap.getProperties()) {
            parseIfAnyRef(property, indexer);
        }
    }

    void parseIfAnyRef(IProperty property, ModuleIndexer indexer) {
        Class<?> type = property.getPropertyType();
        if (CoObject.class.isAssignableFrom(type)) { // is a relation reference.
            refmap.put(type, ReferenceType.ManyToOne);
            EntityInfo target = indexer.getEntity(type);
            if (target != this)
                dependencies.add(target);
            return;
        }
        if (Collection.class.isAssignableFrom(type)) {
            Class<?> param = TypeParam.infer1(type, Collection.class, 0);
            if (CoObject.class.isAssignableFrom(param)) {
                refmap.put(type, ReferenceType.OneToMany);
                return;
            }
        }
        if (Map.class.isAssignableFrom(type)) {
            Class<?> kp = TypeParam.infer1(type, Map.class, 0);
            Class<?> vp = TypeParam.infer1(type, Map.class, 1);
            if (CoObject.class.isAssignableFrom(kp)) {
                refmap.put(type, ReferenceType.OneToMany);
                return;
            }
            if (CoObject.class.isAssignableFrom(vp)) {
                refmap.put(type, ReferenceType.OneToMany);
                return;
            }
        }
    }

    @Override
    public IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        String token = tokens.peek();
        if (token == null)
            return null;

        Object target = null;
        switch (token) {
        case "test":
            target = 1;
            break;
        }

        if (target == null)
            return null;
        return PathArrival.shift(previous, target, tokens);
    }

}
