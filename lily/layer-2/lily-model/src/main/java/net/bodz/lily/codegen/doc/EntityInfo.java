package net.bodz.lily.codegen.doc;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.c.type.TypeParam;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.potato.element.IPropertyMap;
import net.bodz.bas.potato.element.IType;
import net.bodz.lily.model.base.CoObject;

/**
 * @see ModuleInfo
 */
public class EntityInfo
        extends AbstractTypeInfo<EntityInfo> {

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

    public Set<EntityInfo> getDependencies() {
        return dependencies;
    }

    @Override
    public String toString() {
        return declaredClass.getName();
    }

}
